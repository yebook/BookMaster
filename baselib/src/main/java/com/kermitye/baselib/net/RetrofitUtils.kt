package com.huiming.bestnamerobot.rx

import com.kermitye.baselib.BuildConfig
import com.kermitye.baselib.util.LogUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by kermitye
 * Date: 2018/7/18 10:50
 * Desc:
 */

object RetrofitUtil {

    val CONNECT_TIME_OUT = 30
    val READ_TIME_OUT = 30
    val WRITE_TIME_OUT = 30

    private fun getRetrofit(baseUrl: String): Retrofit {
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build()
        }
        return retrofit
    }

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        } else {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
        }
        return builder.build()
    }

    fun <T> createApi(clazz: Class<T>, url: String) : T {
        return getRetrofit(url).create(clazz)
    }
}