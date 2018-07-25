package com.denluoyia.douyuekotlin.util

import java.text.SimpleDateFormat

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

        fun formatPlayTime(milliseconds: Int): String {
            val timeFormat = SimpleDateFormat("mm:ss")
            return timeFormat.format(milliseconds)
        }
    }
}