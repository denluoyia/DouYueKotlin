package com.denluoyia.douyue.manager.net

import com.denluoyia.douyue.util.LogUtil
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
class OkHttpUtil {

    private lateinit var mOkHttpClient : OkHttpClient

    private val DEFAULT_CONNECT_TIMEOUT : Long = 15
    private val DEFAULT_READ_TIMEOUT : Long = 15
    private val DEFAULT_WRITE_TIMEOUT : Long = 20

    companion object {
        @Volatile
        @JvmStatic
        private var INSTANCE : OkHttpUtil? = null

        fun getInstance() : OkHttpUtil {
            if (null == INSTANCE){
                synchronized(OkHttpUtil::class){
                    if (INSTANCE == null){
                        INSTANCE = OkHttpUtil()
                    }
                }
            }

            return INSTANCE!!
        }
    }

    private constructor(){
        var mBuilder = OkHttpClient.Builder()
        mBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        mBuilder.readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
        mBuilder.writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
        mBuilder.hostnameVerifier(MyHostnameVerifier())
        mBuilder.sslSocketFactory(createSSLSocketFactory())
        mOkHttpClient = mBuilder.build()
    }

    /** ============================== 提供外部调用方法 ============================= */

    fun <T> get(url : String, paramsMap: Map<String, String>, requestCallback : IRequestCallback<T>){
        var newUrl : String = url
        if (paramsMap != null && paramsMap.isNotEmpty()){
            val urlBuilder = StringBuilder(url)
            for ((k, v) in paramsMap){
                urlBuilder.append(k).append("=").append(v).append("&")
            }
            newUrl = urlBuilder.toString()
            newUrl.substring(0, newUrl.length - 1)
        }

        val builder = Request.Builder()
        val request = builder.get().url(newUrl).build()
        requestCallback.onStart()
        LogUtil.e("Net-Get:", newUrl)
        mOkHttpClient.newCall(request).enqueue(requestCallback)
    }


    /** ====================================================================== */


    /**
     * 处理请求参数
     * @paramsMap 请求参数
     * @return RequestBody
     */
    fun setRequestBody(paramsMap : Map<String, String>) : RequestBody {
        val builder = FormBody.Builder()
        if (paramsMap != null){
            for ((k, v) in paramsMap){
                builder.add(k, v)
            }
        }
        return builder.build()
    }



    private inner class MyHostnameVerifier : HostnameVerifier {
        override fun verify(hostname: String, session: SSLSession): Boolean {
            return true
        }
    }

    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     * @return SSLSocketFactory
     */
    private fun createSSLSocketFactory(): SSLSocketFactory? {
        var ssfFactory: SSLSocketFactory? = null
        try {
            val sc = SSLContext.getInstance("TLS")
            sc.init(null, arrayOf<TrustManager>(TrustAllCerts()), SecureRandom())
            ssfFactory = sc.socketFactory
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ssfFactory
    }


    /** 用于信任所有证书 */
    class TrustAllCerts : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    }
}