package com.kermitye.bookmaster.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kermitye.baselib.mvp.MvpFragment
import kotlinx.android.synthetic.main.fragment_bookshelf.*
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.adapter.BookshelfAdapter
import com.kermitye.bookmaster.contract.BookshelfContract
import com.kermitye.bookmaster.presenter.BookshelfPresenterImpl
import com.kermitye.bookmaster.ui.activity.SearchActivity
import org.jetbrains.anko.toast


/**
 * Created by kermitye on 2018/9/27 12:01
 */
class BookshelfFragment : MvpFragment<BookshelfContract.BookshelfPresenter>(), BookshelfContract.IBookshelfView {

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
            mRvBooks.adapter = adapter
        }
    }

    fun initListener() {
        mFabSearch.setOnClickListener {
            mFam.toggle()
            startActivity(Intent(context, SearchActivity::class.java))
        }
    }

}