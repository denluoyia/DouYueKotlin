package com.denluoyia.douyue.util

import android.util.Log
import com.denluoyia.douyue.constant.Configs

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
class LogUtil {

    companion object {
        /** 日志级别 V D I W E 依次增强 */

        val DEBUG = Configs.DEBUG

        fun v(tag: String, content: String?) {
            if (!DEBUG) return
            Log.v(tag, content ?: "null")
        }

        fun v(tag: String, vararg objects: Any) {
            if (!DEBUG) return
            Log.v(tag, getInfo(*objects))
        }

        fun d(tag: String, content: String?) {
            if (!DEBUG) return
            Log.d(tag, content ?: "null")
        }

        fun d(tag: String, vararg objects: Any) {
            if (!DEBUG) return
            Log.d(tag, getInfo(*objects))
        }


        fun i(tag: String, content: String?) {
            if (!DEBUG) return
            Log.i(tag, content ?: "null")
        }

        fun i(tag: String, vararg objects: Any) {
            if (!DEBUG) return
            Log.i(tag, getInfo(*objects))
        }

        fun w(tag: String, content: String?) {
            if (!DEBUG) return
            Log.w(tag, content ?: "null")
        }

        fun w(tag: String, vararg objects: Any) {
            if (!DEBUG) return
            Log.w(tag, getInfo(*objects))
        }


        fun e(tag: String, content: String?) {
            if (!DEBUG) return
            Log.e(tag, content ?: "null")
        }

        private fun getInfo(vararg objects: Any): String {
            val sb = StringBuilder()
            if (objects == null) {
                return "no message"
            } else {
                for (obj in objects) {
                    sb.append("--")
                    sb.append(obj)
                }
            }

            return sb.toString()
        }
    }
}