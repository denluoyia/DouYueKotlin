package com.denluoyia.douyuekotlin.util

import android.content.Context
import android.content.Intent
import android.text.SpannableStringBuilder
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.denluoyia.douyuekotlin.R
import com.denluoyia.douyuekotlin.view.activity.ImageBrowseActivity

/**
 * Created by denluoyia
 * Date 2018/07/25
 * DouYue
 */
class PaintHtmlViewUtil {

    private lateinit var pTv : TextView
    private var imgHeight : Int = 0
    private var imgWidth : Int = 0
    private lateinit var llParam : LinearLayout.LayoutParams
    private lateinit var lineView : View
    private var imgUrls = ArrayList<String>()
    private lateinit var mContext : Context

    constructor(context: Context){
        this.mContext = context
    }

    private fun getLinearLayoutParams() : LinearLayout.LayoutParams {
        this.llParam = LinearLayout.LayoutParams(-1, -6)
        return llParam
    }

    fun addLineView(paramContext : Context, paramViewGroup : ViewGroup, paramString : String?){
        this.lineView = View(paramContext)
        this.llParam = getLinearLayoutParams()
        paramViewGroup.addView(this.lineView)
    }

    /** 图片的说明性文字样式 */
    fun addH5TextView(paramContext : Context, paramViewGroup : ViewGroup, paramSpannableStringBuilder : SpannableStringBuilder){
        addLineView(paramContext, paramViewGroup, null)
        this.pTv = TextView(paramContext)
        this.pTv.setSingleLine(false)
        this.pTv.setTextColor(getColor(R.color.colorAccent))
        this.pTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f)
        this.pTv.setText(paramSpannableStringBuilder, TextView.BufferType.SPANNABLE)
        this.pTv.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        paramViewGroup.addView(this.pTv)
    }

    fun addPTextView(paramContext: Context, paramViewGroup: ViewGroup, paramSpannableStringBuilder: SpannableStringBuilder){
        pTv = TextView(paramContext)
        pTv.setSingleLine(false)
        pTv.setLineSpacing(1.2f, 1.5f)
        pTv.setTextIsSelectable(true)
        pTv.setTextColor(getColor(R.color.text_detail))
        pTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        pTv.setText(paramSpannableStringBuilder, TextView.BufferType.SPANNABLE)
        pTv.gravity = Gravity.LEFT
        pTv.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        paramViewGroup.addView(pTv)
    }

    fun addImageView(paramContext: Context, paramViewGroup: ViewGroup, imgWidth : String, imgHeight : String, imgUrl : String){
        var localImageView : ImageView = LayoutInflater.from(paramContext).inflate(R.layout.view_image_view, null) as ImageView
        this.llParam = getLinearLayoutParams()
        if (!imgWidth.isNullOrEmpty() && !imgHeight.isNullOrEmpty()){
            var wStr = imgWidth.replace("px", "")
            var hStr = imgHeight.replace("px", "")
            this.imgWidth = wStr.toInt()
            this.imgHeight = hStr.toInt()
            this.llParam.height = ScreenUtil.getScreenWidth(paramContext) * this.imgHeight / this.imgWidth
        }

        this.llParam.width = LinearLayout.LayoutParams.MATCH_PARENT
        localImageView.layoutParams = this.llParam
        localImageView.setTag(localImageView.id, imgUrl)
        this.imgUrls.add(imgUrl)
        localImageView.setOnClickListener(mOnClickListener)
        paramViewGroup.addView(localImageView)
        Glide.with(paramContext).load(imgUrl).into(localImageView)
    }

    private var mOnClickListener = View.OnClickListener({
        val intent = Intent()
        intent.putStringArrayListExtra("imageUrls", this.imgUrls)
        intent.putExtra("currImage", it.getTag(it.id) as String)
        intent.setClass(mContext, ImageBrowseActivity::class.java!!)
        mContext.startActivity(intent)
    })

    private fun getColor(resId : Int) : Int{
        return mContext.resources.getColor(resId)
    }
}