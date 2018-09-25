package com.kermitye.bookmaster.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.ui.base.BaseFragment
import org.jetbrains.anko.bundleOf

/**
 * Created by kermitye
 * Date: 2018/9/21 17:51
 * Desc:
 */
class BookCaseFragment : BaseFragment() {

    companion object {
        fun newInstance(): BookCaseFragment = BookCaseFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookcast, container, false)
    }

}