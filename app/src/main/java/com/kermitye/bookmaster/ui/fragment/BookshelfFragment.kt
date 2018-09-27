package com.kermitye.bookmaster.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_bookshelf.*
import org.jetbrains.anko.bundleOf
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.adapter.BookshelfAdapter
import com.kermitye.bookmaster.ui.base.BaseFragment


/**
 * Created by kermitye on 2018/9/27 12:01
 */
class BookshelfFragment : BaseFragment() {
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


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    fun initData() {
        if (activity != null) {
            mRvBooks.layoutManager = LinearLayoutManager(activity)
//            mRvBooks.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL))
            var data = arrayListOf<String>()
            for (i in 1..8) {
                data.add("书籍名称$i")
            }
            var adpater = BookshelfAdapter(data)
            mRvBooks.adapter = adpater
        }

    }


}