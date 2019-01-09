package com.kermitye.bookmaster.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.kermitye.baselib.ext.setVisible
import com.kermitye.baselib.mvp.MvpActivity
import com.kermitye.baselib.mvp.MvpFragment
import com.kermitye.baselib.util.LogUtil
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.adapter.KeyWordsAdapter
import com.kermitye.bookmaster.adapter.SearchBooksAdapter
import com.kermitye.bookmaster.adapter.SearchHistoryAdapter
import com.kermitye.bookmaster.contract.SearchContract
import com.kermitye.bookmaster.model.bean.SearchBook
import com.kermitye.bookmaster.presenter.SearchPresenterImpl
import com.kermitye.bookmaster.util.SpUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_search_books.view.*
import kotlinx.android.synthetic.main.layout_auto_complete.*
import kotlinx.android.synthetic.main.layout_hot_search.*
import kotlinx.android.synthetic.main.layout_search_list.*
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

/**
 * Created by kermitye on 2018/9/28 16:11
 */
class SearchActivity : MvpActivity<SearchPresenterImpl>(), SearchContract.ISearchView {
    companion object {
        const val TYPE_NONE = 0
        const val TYPE_KEY_WORDS = 1
        const val TYPE_BOOKS = 2
    }

    var mKeyWords = arrayListOf<String>()
    val mKeyWordsAdapter by lazy { KeyWordsAdapter(mKeyWords) }

    var mSearchBooks = arrayListOf<SearchBook>()
    val mSearchBooksAdapter by lazy { SearchBooksAdapter(mSearchBooks) }
    var mJumpChange = false
    val mSearchHistory by lazy { SpUtils.getSearchHistory() }
    val mKeyHistoryAdapter by lazy { SearchHistoryAdapter(mSearchHistory) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.activity_search, R.id.mTopView)
        initView()
    }

    override fun initPresenter(): SearchPresenterImpl = SearchPresenterImpl.newInstance()

    override fun attachMV() {
        mPresenter.attachMV(this)
    }

    fun initView() {
        mIvBack.setOnClickListener { onBackPressedSupport() }
        mIvClean.setOnClickListener { mEtKeyWord.setText("") }
        mTvCleanHistory.setOnClickListener {
            mTvCleanHistory.setVisible(false)
            mSearchHistory.clear()
            SpUtils.setSearchHistory(mSearchHistory)
            mKeyHistoryAdapter.notifyDataSetChanged()
        }
        showData(TYPE_NONE)
        mTgHot.setOnTagClickListener {
            mJumpChange = true
            mEtKeyWord.setText(it)
            search()
        }

        mRvKeyWords.layoutManager = LinearLayoutManager(this)
        mRvKeyWords.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mRvKeyWords.adapter = mKeyWordsAdapter
        mKeyWordsAdapter.setOnItemClickListener { adapter, view, position ->
            mJumpChange = true
            mEtKeyWord.setText(mKeyWords.get(position))
            search()
        }

        mRvSearchHistory.layoutManager = LinearLayoutManager(this)
        mRvSearchHistory.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mRvSearchHistory.adapter = mKeyHistoryAdapter
        mTvCleanHistory.setVisible(mSearchHistory.size > 0)

        mKeyHistoryAdapter.setOnItemClickListener { adapter, view, position ->
            mJumpChange = true
            mEtKeyWord.setText(mSearchHistory.get(position))
            search()
        }

        mRvSearchBooks.layoutManager = LinearLayoutManager(this)
        mRvSearchBooks.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mRvSearchBooks.adapter = mSearchBooksAdapter
        mSearchBooksAdapter.setOnItemClickListener { adapter, view, position ->

            var intent = Intent(this@SearchActivity, BookDetailActivity::class.java)
            intent.putExtra("id", mSearchBooks.get(position)._id)
            var options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(this@SearchActivity, view.mIvCover, getString(R.string.transition_book_img))
            startActivity(intent, options.toBundle())
//            startActivity<BookDetailActivity>("id" to mSearchBooks.get(position)._id)
        }

        RxTextView.textChanges(mEtKeyWord)
                .debounce(300, TimeUnit.MILLISECONDS).skip(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mIvClean.setVisible(it.length != 0)
                    if (mJumpChange) {
                        mJumpChange = false
                        return@subscribe
                    }
                    mPresenter.getKeyWords(it.toString())
                }

        mIvSearch.setOnClickListener {
            search()
        }
    }


    override fun updateHotWord(data: List<String>) {
        showData(TYPE_NONE)
        if (data.size > 6) {
            mTgHot.setTags(data.subList(0, 6))
        } else {
            mTgHot.setTags(data)
        }
    }

    override fun updateKeyWords(data: List<String>) {
        if (data.size == 0) {
            showData(TYPE_NONE)
            return
        }
        showData(TYPE_KEY_WORDS)
        mKeyWords.clear()
        mKeyWords.addAll(data)
        mKeyWordsAdapter.notifyDataSetChanged()
    }

    override fun updateSearchBooks(data: List<SearchBook>) {
        if (data.size == 0) {
            showData(TYPE_NONE)
            toast("未找到相关书籍")
            return
        }
        showData(TYPE_BOOKS)
        mSearchBooks.clear()
        mSearchBooks.addAll(data)
        mSearchBooksAdapter.notifyDataSetChanged()
    }

    fun search() {
        if (mEtKeyWord.text.isNullOrEmpty()) {
            toast("请输入搜索内容")
            return
        }
        val keyWord = mEtKeyWord.text.toString().trim()
        if (mSearchHistory.contains(keyWord)) {
           mSearchHistory.remove(keyWord)
        }
        mSearchHistory.add(0, keyWord)
        mTvCleanHistory.setVisible(mSearchHistory.size > 0)
        SpUtils.setSearchHistory(mSearchHistory)
        mKeyHistoryAdapter.notifyDataSetChanged()
        hindKeyBoard(mEtKeyWord)
        mPresenter.getSearchBooks(keyWord)
    }

    fun showData(type: Int) {
        mLlHotWord.setVisible(if (type == TYPE_NONE) true else false)
        mRvKeyWords.setVisible(if (type == TYPE_KEY_WORDS) true else false)
        mRvSearchBooks.setVisible(if (type == TYPE_BOOKS) true else false)
    }
}