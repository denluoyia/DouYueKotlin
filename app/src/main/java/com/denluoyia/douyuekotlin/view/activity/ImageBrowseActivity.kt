package com.denluoyia.douyuekotlin.view.activity

import android.annotation.SuppressLint
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bm.library.PhotoView
import com.bumptech.glide.Glide
import com.denluoyia.douyuekotlin.R
import com.denluoyia.douyuekotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_image_browse.*

/**
 * 图片浏览
 */
class ImageBrowseActivity : BaseActivity() {

    private lateinit var imageUrls : ArrayList<String>
    private lateinit var currImage : String
    private var currPosition : Int = 0


    override fun setContentViewId(): Int {
        overridePendingTransition(R.anim.anim_sacle_enter, R.anim.anim_sacle_exit)
        return R.layout.activity_image_browse
    }

    @SuppressLint("SetTextI18n")
    override fun doBusiness() {
        imageUrls = intent.getStringArrayListExtra("imageUrls")!!
        currImage = intent.getStringExtra("currImage").toString()
        if (imageUrls == null || imageUrls.isEmpty()) finish()
        currPosition = getClickEnterPositionAndPreDeal(currImage)
        tv_page_num_switch.text = ((currPosition + 1).toString() + "/" + imageUrls.size)
        view_pager.pageMargin = (resources.displayMetrics.density * 16).toInt()
        view_pager.adapter = MyPagerAdapter()
        view_pager.addOnPageChangeListener(mOnPageChangeListener)
        view_pager.currentItem = currPosition

    }


    private fun getClickEnterPositionAndPreDeal(url : String) : Int{
        if (imageUrls.size > 2 && imageUrls[0] == imageUrls[1]){
            imageUrls.removeAt(1)
        }
        for (index in imageUrls.indices){
            if (imageUrls[index] == url) return index
        }
        return 0
    }

    private var mOnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            currPosition = position
            tv_page_num_switch.text = ((currPosition+1).toString() + "/" + imageUrls.size)
            view_pager.tag = position
        }

    }

    private inner class MyPagerAdapter : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return imageUrls.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            if (!imageUrls[position].isNullOrEmpty()){
                var photoView : PhotoView = PhotoView(this@ImageBrowseActivity)
                photoView.enable()
                photoView.scaleType = ImageView.ScaleType.FIT_CENTER
                Glide.with(this@ImageBrowseActivity).load(imageUrls[position]).into(photoView)
                container.addView(photoView)
                return photoView
            }
            return super.instantiateItem(container, position)
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.anim_sacle_exit)
    }

    override fun onDestroy() {
        Glide.get(this).clearMemory()
        super.onDestroy()
    }
}
