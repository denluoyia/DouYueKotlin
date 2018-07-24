package com.denluoyia.douyue.util

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
class StringUtil {

    companion object {
        fun unicodeToUTF_8(src : String?) : String? {
            if (src == null || src!!.isEmpty()) return src
            var out : StringBuilder = StringBuilder()

            var i = 0
            while (i < src.length) {
                val c = src[i]
                if (i + 6 < src.length && c == '\\' && src[i + 1] == 'u') {
                    val hex = src.substring(i + 2, i + 6)
                    try {
                        out.append(Integer.parseInt(hex, 16).toChar())
                    } catch (nfe: NumberFormatException) {
                        nfe.fillInStackTrace()
                    }

                    i = i + 6
                } else {
                    out.append(src[i])
                    ++i
                }
            }

            return out.toString()
        }

    }
}