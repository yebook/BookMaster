package com.kermitye.bookmaster.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kermitye.baselib.ext.BaseExt
import com.kermitye.bookmaster.MyApp

/**
 * Created by kermitye on 2018/10/17 16:03
 */
object SpUtils {
    private var searchHistory by BaseExt.preference(MyApp.instance, "searchHistory", "")

    fun getSearchHistory(): ArrayList<String> {
        if (searchHistory.isNullOrEmpty())
            return arrayListOf()
        return Gson().fromJson(searchHistory, object : TypeToken<List<String>>() {}.type)
    }

    fun setSearchHistory(value: List<String>) {
        searchHistory = Gson().toJson(value)
    }

}