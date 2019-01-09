package com.kermitye.bookmaster.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kermitye.baselib.mvp.MvpFragment
import com.kermitye.baselib.util.LogUtil
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.adapter.RankingAdapter
import com.kermitye.bookmaster.contract.RankingContract
import com.kermitye.bookmaster.model.bean.Male
import com.kermitye.bookmaster.presenter.RankingPresenterImpl
import com.kermitye.bookmaster.ui.base.BaseFragment
import com.kermitye.bookmaster.ui.widget.StateLayout
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_ranking.*
import org.jetbrains.anko.bundleOf
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by kermitye on 2018/9/27 17:33
 */
class RankingFragment : MvpFragment<RankingPresenterImpl>(), RankingContract.IRankingView {
    companion object {
        fun newInstance(): RankingFragment {
            return RankingFragment()
        }
    }

    var mRankings = arrayListOf<Male>()
    val mRankingAdapter by lazy { RankingAdapter(mRankings) }

    override fun initPresenter(): RankingPresenterImpl = RankingPresenterImpl.newInstance()

    override fun attachMV() {
        mPresenter.attachMV(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        initView()
        initListener()
        mSl.showView(StateLayout.TYPE_LOADING)
        mPresenter.getRanking()
    }


    fun initView() {
        activity?.let {
            mSl.setConfig(StateLayout.StateLayoutConfig.newInstance()
                    .setEmptyLayout(R.layout.layout_empty)
                    .setErrorLayout(R.layout.layout_error)
                    .setLoadingLayout(R.layout.layout_loading))
            mRvRanking.layoutManager = LinearLayoutManager(it)
            mRvRanking.addItemDecoration(DividerItemDecoration(it, DividerItemDecoration.VERTICAL))
            mRvRanking.adapter = mRankingAdapter
        }
    }

    fun initListener() {
        mRankingAdapter.setOnItemClickListener { adapter, view, position ->
            var bean = mRankings.get(position)
            toast("点击: ${bean.shortTitle}")
        }
    }

    override fun updateData(data: List<Male>) {
        mRankings.clear()
        mRankings.addAll(data)
        //        mRankingAdapter.setNewData(data)
        mRankingAdapter.notifyDataSetChanged()

        if (mRankings.size == 0) {
            mSl.showView(StateLayout.TYPE_EMPTY)
        } else {
            mSl.showView(StateLayout.TYPE_CONTENT)
        }
    }
}