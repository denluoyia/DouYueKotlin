package com.denluoyia.douyue.util

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
class TimeUtil {
    companion object {
        fun getTimeStamp() : String{
            return (System.currentTimeMillis() / 1000).toString()
        }
    }
}