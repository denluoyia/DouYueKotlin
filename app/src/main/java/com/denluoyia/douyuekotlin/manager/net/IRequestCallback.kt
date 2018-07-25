package com.denluoyia.douyuekotlin.manager.net

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.badoo.mobile.util.WeakHandler
import com.denluoyia.douyuekotlin.DouYueApp
import com.denluoyia.douyuekotlin.util.LogUtil
import com.denluoyia.douyuekotlin.util.NetworkUtil
import com.denluoyia.douyuekotlin.util.StringUtil
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
abstract class IRequestCallback<T> : Callback {

    private val SUCCESS_CODE = 0X01
    private val FAILURE_CODE = 0x02
    private var mHandler : WeakHandler = WeakHandler(Looper.getMainLooper(), UICallback<T>(this))

    fun onStart() {}
    abstract fun onSuccess(result : T)
    abstract fun onFailure(e : Exception)
    fun onUpdateProgress(writeSize : Int, totalSize : Int, isComplete : Boolean){}

    private lateinit var mClassType: Type

    constructor() {
        mClassType = getSuperclassTypeParameter(javaClass) //获取泛型参数类型 T
    }

    private fun getSuperclassTypeParameter(subclass: Class<*>): Type {
        val superclass = subclass.genericSuperclass
        if (superclass is Class<*>) {
            throw RuntimeException("Missing type parameter.")
        }
        return (superclass as ParameterizedType).actualTypeArguments[0]
    }


    override fun onFailure(call: Call?, e: IOException?) {
        var msg : Message = Message.obtain()
        msg.what = FAILURE_CODE
        msg.obj = e
        mHandler.sendMessage(msg)
    }

    override fun onResponse(call: Call?, response: Response?) {
        if (response!!.isSuccessful){
            var responseResult : String? = null
            try {
                responseResult = response.body()!!.string()
                LogUtil.e("OKHttpUtil data:", StringUtil.unicodeToUTF_8(responseResult)!!)
            }catch (e : IOException){
                e.printStackTrace()
            }
            var result : T = Gson().fromJson(responseResult, mClassType)
            var msg : Message = Message.obtain()
            msg.what = SUCCESS_CODE
            msg.obj = result
            mHandler.sendMessage(msg)
        }else{
            val msg = Message.obtain()
            msg.what = FAILURE_CODE
            mHandler.sendMessage(msg)
        }
    }


    inner class UICallback<T> : Handler.Callback {
        private var mCallback : IRequestCallback<T>

        constructor(callback: IRequestCallback<T>){
            mCallback = callback
        }

        override fun handleMessage(msg: Message?): Boolean {
            when(msg!!.what){
                SUCCESS_CODE -> {
                    if (msg.obj != null){
                        mCallback.onSuccess(msg.obj as T)
                    }
                }
                FAILURE_CODE -> {
                    var e = if (NetworkUtil.isNetworkConnected(DouYueApp.getApplication())){
                        IOException("网路似乎有问题")
                    }else if (msg.obj != null && msg.obj is IOException){
                        msg.obj as IOException
                    }else{
                        throw Exception("response is wrong!!!")
                    }

                    if (mCallback != null){
                        mCallback.onFailure(e)
                    }
                }
            }

            return true
        }
    }
}