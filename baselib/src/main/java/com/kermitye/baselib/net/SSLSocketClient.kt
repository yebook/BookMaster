package com.huiming.bestnamerobot.rx

import java.security.SecureRandom
import javax.net.ssl.*
import javax.net.ssl.X509TrustManager
import javax.security.cert.X509Certificate

/**
 * Created by kermitye
 * Date: 2018/8/14 11:28
 * Desc:
 */
object SSLSocketClient {
    fun getSSLSocketFactory(): SSLSocketFactory {
        try {
            var sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, getTrustManager(), SecureRandom())
            return sslContext.socketFactory
        }catch (e: Exception) {
            throw RuntimeException(e);
        }
    }

    fun getTrustManager() = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

        override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

        override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
            return arrayOf()
        }
    })

    fun getHostnameVerifier() = HostnameVerifier { s, sslSession -> true };

}
