package com.kermitye.bookmaster.manager

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.kermitye.bookmaster.MyApp
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.R.id.mIvHead
import com.kermitye.bookmaster.ext.option
import jp.wasabeef.glide.transformations.BlurTransformation

/**
 * Created by kermitye on 2018/10/8 16:13
 */
object ImageManager {

    //加载drawable图片
    fun displayImage(resId: Int, imageView: ImageView) {
        Glide.with(MyApp.instance).load(resId).option(R.mipmap.imag_loading, R.mipmap.image_error)
                .into(imageView)
    }

    //加载drawable图片
    fun displayImage(resId: Int, imageView: ImageView, defaultImage: Int) {
        Glide.with(MyApp.instance).load(resId).option(R.mipmap.imag_loading, R.mipmap.image_error)
                .into(imageView)
    }

    fun displayImage(url: String, imageView: ImageView) {
        Glide.with(MyApp.instance).load(url).option(R.mipmap.imag_loading, R.mipmap.image_error)
                .into(imageView)
    }

    fun displayImageToBlur(url: String, imageView: ImageView) {
        Glide.with(MyApp.instance).load(url)
                .apply(bitmapTransform(BlurTransformation(25, 20)))  //radius设置模糊度(0.0-25.0之间)， sampling设置图片的缩放比例
                .into(imageView)
    }

    fun displayImageToCir(url: String, imageView: ImageView) {
        Glide.with(MyApp.instance).load(url).apply(RequestOptions.bitmapTransform(CircleCrop())).into(imageView)
    }

    fun displayImage(url: String, imageView: ImageView, defResId: Int) {
        Glide.with(MyApp.instance.getApplicationContext()).load(url)
                .option(R.mipmap.imag_loading, R.mipmap.image_error)
                .into(imageView)
    }

    fun displayImage(uri: Uri, imageView: ImageView) {
        Glide.with(MyApp.instance).load(uri).option(R.mipmap.imag_loading, R.mipmap.image_error).into(imageView)
    }


    /**
     * 清除缓存
     *
     * @param context
     */
    fun clearCache(context: Context) {
        clearMemoryCache(context)
        Thread(Runnable { clearDiskCache(context) }).start()
    }

    /**
     * 清除内存缓存
     *
     * @param context
     */
    fun clearMemoryCache(context: Context) {
        Glide.get(context).clearMemory()
    }

    /**
     * 清除磁盘缓存
     *
     * @param context
     */
    fun clearDiskCache(context: Context) {
        Glide.get(context).clearDiskCache()
    }

}