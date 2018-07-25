package com.denluoyia.douyuekotlin.view.activity

import android.net.Uri
import android.view.View
import android.widget.MediaController
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.denluoyia.douyuekotlin.R
import com.denluoyia.douyuekotlin.base.BaseActivity
import com.denluoyia.douyuekotlin.model.bean.DetailBean
import com.denluoyia.douyuekotlin.presenter.DetailContract
import com.denluoyia.douyuekotlin.presenter.DetailPresenter
import com.denluoyia.douyuekotlin.util.HtmlParseUtil
import kotlinx.android.synthetic.main.activity_video.*
import kotlinx.android.synthetic.main.layout_detail_bottom_content.*

class VideoActivity : BaseActivity() , DetailContract.View{
    private lateinit var postId : String
    private lateinit var coverImgUrl : String
    private lateinit var videoUrl : String
    private lateinit var mPresenter : DetailPresenter
    private lateinit var mHtmlParseUtil: HtmlParseUtil


    override fun setContentViewId(): Int {
        overridePendingTransition(R.anim.translate_bottom_in, R.anim.translate_bottom_out)
        return R.layout.activity_video
    }

    override fun doBusiness() {
        postId = intent.getStringExtra("id")
        coverImgUrl = intent.getStringExtra("img_url")
        if (postId.isNullOrEmpty()) return
        initToolbar(toolbar)
        Glide.with(this).load(coverImgUrl!!).into(iv_top_cover)
        mHtmlParseUtil = HtmlParseUtil(this)
        mPresenter = DetailPresenter(this)
        mPresenter.loadData(postId)
    }

    @OnClick(R.id.btn_init_play, R.id.iv_collect)
    fun onClick(view : View){
        when(view.id){
            R.id.btn_init_play -> {
                startPlay()
            }
            R.id.iv_collect -> {

            }
        }
    }

    private fun startPlay(){
        if(!videoUrl.isNullOrEmpty()){
            var uri : Uri = Uri.parse(videoUrl)
            video_view.setVideoURI(uri)
            video_view.setMediaController(MediaController(this))
            video_view.setOnPreparedListener({video_view.start()})
            fl_layout.visibility = View.GONE
        }
    }



    override fun loadDataSuccess(bean: DetailBean) {
        if (bean.datas.parseXML == 1) {
            videoUrl = bean.datas.video
            detail_type.text = "音频"
            update_time.text = bean.datas.updateTime
            tv_title.text = bean.datas.title
            tv_author.text = bean.datas.author
            detail_lead_text.text = bean.datas.lead
            mHtmlParseUtil.loadHtml(bean.datas.content, HtmlParseUtil.HmtlType.STRING, detail_content)
        }
    }

    override fun loadDataFailed(msg: String?) {
    }

    override fun start() {
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.translate_bottom_out)
    }
}
