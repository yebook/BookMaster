package com.kermitye.bookmaster.model

import com.kermitye.bookmaster.contract.BookshelfContract
import com.kermitye.bookmaster.contract.SearchContract

/**
 * Created by kermitye on 2018/9/28 11:09
 */
class SearchModel : SearchContract.ISearchModel {

    companion object {
        fun newInstance(): SearchModel = SearchModel()
    }

}