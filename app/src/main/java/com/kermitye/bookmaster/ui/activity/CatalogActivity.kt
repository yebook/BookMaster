package com.kermitye.bookmaster.ui.activity

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.kermitye.baselib.mvp.MvpActivity
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.adapter.CatalogAdapter
import com.kermitye.bookmaster.contract.CatalogContract
import com.kermitye.bookmaster.model.bean.Chapter
import com.kermitye.bookmaster.presenter.CatalogPresenterImpl
import com.kermitye.bookmaster.ui.widget.StateLayout
import kotlinx.android.synthetic.main.activity_catalog.*

/**
 * Created by kermitye on 2018/10/17 15:43
 */
class CatalogActivity : MvpActivity<CatalogPresenterImpl>(), CatalogContract.ICatalogView {

    val mAdapter by lazy { CatalogAdapter(mChapter) }
    var mChapter = arrayListOf<Chapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.activity_catalog, R.id.mTopView)
        initView()
    }

    override fun initPresenter(): CatalogPresenterImpl = CatalogPresenterImpl.newInstance()

    override fun attachMV() = mPresenter.attachMV(this)


    fun initView() {
        mIvBack.setOnClickListener { onBackPressedSupport() }
        mSl.setConfig(StateLayout.StateLayoutConfig.newInstance()
                .setEmptyLayout(R.layout.layout_empty)
                .setErrorLayout(R.layout.layout_error)
                .setLoadingLayout(R.layout.layout_loading))
        mSl.showView(StateLayout.TYPE_LOADING)

        mRvCatalog.layoutManager = LinearLayoutManager(this)
        mRvCatalog.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mRvCatalog.adapter = mAdapter

        var bookId = intent.getStringExtra("id")
        if (bookId.isNullOrEmpty()) {
            toast("获取书籍目录错误")
            return
        }
        mPresenter.getData(bookId)
    }

    override fun updateData(data: List<Chapter>) {
        if (data != null && data.size > 0)
            mSl.showView(StateLayout.TYPE_CONTENT)
        else
            mSl.showView(StateLayout.TYPE_EMPTY)
        mChapter.clear()
        mChapter.addAll(data)
        mAdapter.notifyDataSetChanged()
    }

}