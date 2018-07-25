package com.denluoyia.douyuekotlin.view.widget

import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.denluoyia.douyuekotlin.R

/**
 * Created by denluoyia
 * Date 2018/07/24
 * DouYue
 */

@SuppressWarnings("all")
class MainTitleTabView : LinearLayout , ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener{
    private lateinit var mViewPager : ViewPager
    private var mRadioGroup : RadioGroup
    private var mRbText : RadioButton
    private var mRbAudio : RadioButton
    private var mRbVideo : RadioButton

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr : Int) : super(context, attributeSet, defStyleAttr){
        LayoutInflater.from(context).inflate(R.layout.view_main_title_tab, this, true)
        mRadioGroup = findViewById(R.id.tabTitle)
        mRbText = findViewById(R.id.rb_text)
        mRbAudio = findViewById(R.id.rb_audio)
        mRbVideo = findViewById(R.id.rb_video)
        mRadioGroup.setOnCheckedChangeListener(this)
    }

    fun bindViewPager(viewPager : ViewPager){
       this.mViewPager = viewPager
       mViewPager.addOnPageChangeListener(this)
       mViewPager.currentItem = 0
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        var checkI : Int = if (position == 0) R.id.rb_text else if (position == 1) R.id.rb_audio else R.id.rb_video
        mRadioGroup.check(checkI)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when(checkedId){
            R.id.rb_text -> {
                selectItem(0)
                mViewPager.currentItem = 0
            }
            R.id.rb_audio -> {
                selectItem(1)
                mViewPager.currentItem = 1
            }
            R.id.rb_video -> {
                selectItem(2)
                mViewPager.currentItem = 2
            }
        }
    }

    private fun selectItem(position : Int){
        when(position){
            0 -> {
                mRbText.setTextColor(Color.WHITE)
                mRbAudio.setTextColor(getColor(R.color.text_second_color_primary))
                mRbVideo.setTextColor(getColor(R.color.text_second_color_primary))
            }
            1 -> {
                mRbText.setTextColor(getColor(R.color.text_second_color_primary))
                mRbAudio.setTextColor(Color.WHITE)
                mRbVideo.setTextColor(getColor(R.color.text_second_color_primary))
            }
            2 -> {
                mRbText.setTextColor(getColor(R.color.text_second_color_primary))
                mRbAudio.setTextColor(getColor(R.color.text_second_color_primary))
                mRbVideo.setTextColor(Color.WHITE)
            }
        }
    }

    private fun getColor(resId : Int) : Int {
        return context.resources.getColor(resId)
    }

}