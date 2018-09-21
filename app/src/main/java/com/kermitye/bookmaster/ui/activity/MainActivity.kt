 package com.kermitye.bookmaster.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.ui.base.BaseActivity
import com.kermitye.bookmaster.ui.fragment.BookCaseFragment
import me.yokeyword.fragmentation.ISupportFragment

 class MainActivity : BaseActivity() {

     var mFragments: Array<ISupportFragment> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.activity_main)
        initView()
    }

     override fun initImmersionBar() {
         super.initImmersionBar()
         mImmersionBar?.let { it.statusBarDarkFont(true).init() }
     }

     fun initView() {
         var bookCaseFragment: BookCaseFragment? = findFragment(BookCaseFragment::class.java)
         if (bookCaseFragment == null) {
            mFragments[0] = BookCaseFragment.newInstance()
            mFragments[1] = BookCaseFragment.newInstance()
            mFragments[2] = BookCaseFragment.newInstance()
            mFragments[3] = BookCaseFragment.newInstance()

//             loadMultipleRootFragment()
         } else {

         }
     }


}
