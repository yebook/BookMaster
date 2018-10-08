package com.kermitye.bookmaster.ext

import android.graphics.Color
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions

/**
 * Created by kermitye on 2018/10/8 16:17
 */

inline fun RequestBuilder<*>.option(placeResourceId: Int = -1, errorResourceId: Int = -1) = apply(RequestOptions().let {
    if (placeResourceId != -1)
        it.placeholder(placeResourceId)
    if (errorResourceId != -1)
        it.error(errorResourceId)
    it
})