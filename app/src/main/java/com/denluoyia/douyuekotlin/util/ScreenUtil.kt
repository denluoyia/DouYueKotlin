package com.denluoyia.douyuekotlin.util

import android.content.Context
import android.hardware.display.DisplayManager
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Created by denluoyia
 * Date 2018/07/25
 * DouYue
 */
class ScreenUtil {

    companion object {
        fun getScreenWidth(context: Context) : Int{
            val wm : WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()
            wm.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }

        fun getScreenHeight(context: Context) : Int{
            val wm : WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()
            wm.defaultDisplay.getMetrics(dm)
            return dm.heightPixels
        }
    }
}