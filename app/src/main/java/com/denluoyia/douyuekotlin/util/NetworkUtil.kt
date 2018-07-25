package com.denluoyia.douyuekotlin.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
class NetworkUtil {
    private val TAG : String = "NetworkUtil"

    companion object {
        fun isNetworkConnected(context: Context?): Boolean {
            if (context != null) {
                val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                var mNetworkInfo: NetworkInfo? = null
                if (mConnectivityManager != null) {
                    mNetworkInfo = mConnectivityManager.activeNetworkInfo
                }
                if (mNetworkInfo != null) {
                    return mNetworkInfo.isAvailable
                }
            }
            return false
        }

        private fun getNetworkType(context: Context?) : Int{
            if (context != null){
                val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                var mNetworkInfo : NetworkInfo? = null
                if (mConnectivityManager != null){
                    mNetworkInfo = mConnectivityManager.activeNetworkInfo
                }
                if (mNetworkInfo != null){
                    return mNetworkInfo.type
                }
            }
            return -1
        }


        fun isWifi(context: Context?): Boolean {
            return getNetworkType(context) == ConnectivityManager.TYPE_WIFI
        }
    }
}