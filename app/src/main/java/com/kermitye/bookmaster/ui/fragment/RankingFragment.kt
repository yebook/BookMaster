package com.kermitye.bookmaster.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.ui.base.BaseFragment
import org.jetbrains.anko.bundleOf

/**
 * Created by kermitye on 2018/9/27 17:33
 */
class RankingFragment : BaseFragment() {
    companion object {
        fun newInstance(): RankingFragment {
            val fragment = RankingFragment()
//            fragment.arguments = bundleOf("data" to data)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        var title = arguments?.getString("data")
//        mTvText.text = title
    }
}