package com.kermitye.bookmaster.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kermitye.baselib.mvp.MvpFragment
import kotlinx.android.synthetic.main.fragment_bookshelf.*
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.R.id.*
import com.kermitye.bookmaster.adapter.BookshelfAdapter
import com.kermitye.bookmaster.contract.BookshelfContract
import com.kermitye.bookmaster.presenter.BookshelfPresenterImpl
import com.kermitye.bookmaster.ui.activity.SearchActivity
import com.kermitye.bookmaster.ui.base.BaseFragment
import com.kermitye.bookmaster.ui.widget.ListDialog
import org.jetbrains.anko.toast


/**
 * Created by kermitye on 2018/9/27 12:01
 */
class BookshelfFragment : BaseFragment<BookshelfContract.BookshelfPresenter>(), BookshelfContract.IBookshelfView {

    val mListDialog by lazy { ListDialog.newInstance(fragmentManager!!) }

    companion object {
        fun newInstance(): BookshelfFragment {
            val fragment = BookshelfFragment()
//            fragment.arguments = bundleOf("data" to data)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookshelf, container, false)
    }

    override fun initPresenter(): BookshelfContract.BookshelfPresenter = BookshelfPresenterImpl.newInstance()

    override fun attachMV() {
        mPresenter.attachMV(this)
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        initData()
        initListener()
    }

    fun initData() {

        if (activity != null) {
            mRvBooks.layoutManager = LinearLayoutManager(activity)
//            mRvBooks.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL))
            var data = arrayListOf<String>()
            for (i in 1..8) {
                data.add("书籍名称$i")
            }
            var adapter = BookshelfAdapter(data)
            adapter.setOnItemClickListener { adapter, view, position ->
                activity!!.toast("点击第 $position 条")
            }
            adapter.setOnItemLongClickListener { adapter, view, position ->
                mListDialog.setTitle(data.get(position)).setList(arrayListOf("置顶", "书籍详情", "删除", "批量管理")).setOnItemClickListener { position, item ->
                    toast("选择第 $position 条：$item")
                }.show()
                true
            }
            mRvBooks.adapter = adapter
        }
    }

    fun initListener() {
        mMsgDialog.setConfimListener { it.dismiss() }
        mFabSearch.setOnClickListener {
            mFam.toggle()
            startActivity(Intent(context, SearchActivity::class.java))
        }
        mFabNightMode.setOnClickListener {
            mFam.toggle()
            mMsgDialog.show("功能暂未完善")
        }
    }

}