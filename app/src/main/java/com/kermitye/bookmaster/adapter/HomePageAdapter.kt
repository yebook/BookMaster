package com.kermitye.bookmaster.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by kermitye
 * Date: 2018/9/26 17:35
 * Desc:
 */
class HomePageAdapter<T: Fragment> (var titles: List<String>, var datas: List<T>, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): T {
        return datas.get(position)
    }

    override fun getCount(): Int {
        return datas.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles.get(position)
    }
}