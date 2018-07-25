package com.denluoyia.douyuekotlin.util

import android.app.Activity
import android.os.Handler
import android.os.Message
import android.text.SpannableStringBuilder
import android.widget.LinearLayout
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

/**
 * Function: HTML解析工具
 * Created by denluoyia
 * Date 2018/07/25
 * DouYue
 */
class HtmlParseUtil {
    enum class HmtlType{
        URL,
        STRING
    }

    private var mActivity : Activity
    private lateinit var mDoc : Document
    private lateinit var mParentLinearLayout : LinearLayout
    private lateinit var imgWidth : String
    private lateinit var imgHeight : String
    private lateinit var imgUrl : String
    private lateinit var mPaintUtil : PaintHtmlViewUtil
    private lateinit var strThread : Thread
    private lateinit var urlThread : Thread

    private var mHandler = InnerHandler()

    constructor(activity: Activity){
        this.mActivity = activity
    }

    fun loadHtml(content : String, type : HmtlType, paramLinearLayout : LinearLayout){
        this.mParentLinearLayout = paramLinearLayout
        val str = content.replace("<br/>".toRegex(), "br;")
        if (type == HmtlType.STRING){
            loadHtmlString(str)
        }else{
            loadHtmlUrl(str)
        }
    }

    private fun loadHtmlString(htmlString : String) {
        strThread = Thread(Runnable {
            mDoc = Jsoup.parseBodyFragment(htmlString)
            mHandler.sendEmptyMessage(0)
        })
        strThread.start()
    }

    private fun loadHtmlUrl(url : String){
        urlThread = Thread(Runnable {
            mDoc = Jsoup.connect(url).get()
            mHandler.sendEmptyMessage(0)
        })
        urlThread.start()
    }

    fun parseDocument(paramDocument: Document){
        mPaintUtil = PaintHtmlViewUtil(mActivity)
        var its  = paramDocument.allElements.iterator()
        while (its.hasNext()){
            var e : Element = its.next()
            if (e.nodeName().matches("p".toRegex()) || e.nodeName().matches("h[1-9]?[0-9]?".toRegex()) || e.nodeName().matches("block".toRegex())) {
                val strTemp = e.text().replace("br;".toRegex(), "\n")
                val ssb = SpannableStringBuilder("\n" + strTemp)
                if (e.nodeName() == "h5") {
                    mPaintUtil.addH5TextView(mActivity, mParentLinearLayout, ssb)
                } else {
                    mPaintUtil.addPTextView(mActivity, mParentLinearLayout, ssb)
                }
            }

            if (e.nodeName().matches("img".toRegex())){
                this.imgUrl = e.attr("src")
                this.imgWidth = e.attr("width")
                this.imgHeight = e.attr("height")
                mPaintUtil.addImageView(mActivity, mParentLinearLayout, this.imgWidth, this.imgHeight, imgUrl)
            }
        }
    }

    private inner class InnerHandler : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            parseDocument(mDoc)
        }
    }
}