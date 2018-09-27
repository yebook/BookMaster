package com.kermitye.bookmaster.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kermitye.bookmaster.MyApp
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.adapter.HomePageAdapter
import com.kermitye.bookmaster.ui.base.BaseActivity
import com.kermitye.bookmaster.ui.fragment.BookshelfFragment
import com.kermitye.bookmaster.ui.fragment.RankingFragment
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_layout.view.*
import me.yokeyword.fragmentation.ISupportFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.activity_main)
        initView()
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.let { it.statusBarView(mTopView).init() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when(item?.itemId) {
//            R.id.action_search ->
//        }
        return super.onOptionsItemSelected(item)
    }


    fun initView() {
        mNvMenu.setItemIconTintList(null)   //侧滑菜单中的图标显示原色
        setSupportActionBar(mToolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false) //隐藏系统默认的Title
        mToolBar.title = getString(R.string.app_name)
//        mToolBar.setNavigationIcon(R.drawable.ic_menu)

        var toggle = ActionBarDrawerToggle(this, mDrawerLayout, mToolBar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        var titles = arrayListOf("书架", "排行榜")
        var datas = arrayListOf<Fragment>()
        datas.add(BookshelfFragment.newInstance())
        datas.add(RankingFragment.newInstance())
        var adapter = HomePageAdapter(titles, datas, supportFragmentManager)
        mVp.adapter = adapter
        titles.forEach { mTab.addTab(mTab.newTab().setText(it)) }
        mTab.setupWithViewPager(mVp)

        Glide.with(this)
                .load(R.mipmap.head_img)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(14, 3)))
                .into(mNvMenu.getHeaderView(0).mIvHeadBg)

        var mRequestOptions = RequestOptions.circleCropTransform()//.diskCacheStrategy(DiskCacheStrategy.NONE)
        Glide.with(MyApp.instance).load(R.mipmap.head).apply(mRequestOptions).into(mNvMenu.getHeaderView(0).mIvHead)

    }
}
