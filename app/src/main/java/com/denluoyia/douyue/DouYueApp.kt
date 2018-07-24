package com.denluoyia.douyue

import android.app.Application

/**
 * Created by denluoyia
 * Date 2018/07/20
 * DouYue Kotlin
 */
class DouYueApp : Application(){

    override fun onCreate() {
        super.onCreate()
        mApp = this
    }

    companion object {
        private var mApp: DouYueApp? = null

        fun getApplication() : DouYueApp? {
            return mApp
        }
    }

}