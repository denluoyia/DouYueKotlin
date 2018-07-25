package com.denluoyia.douyuekotlin.view.activity

import com.bumptech.glide.Glide
import com.denluoyia.douyuekotlin.R
import com.denluoyia.douyuekotlin.base.BaseActivity
import com.denluoyia.douyuekotlin.model.bean.DetailBean
import com.denluoyia.douyuekotlin.presenter.DetailContract
import com.denluoyia.douyuekotlin.presenter.DetailPresenter
import com.denluoyia.douyuekotlin.util.HtmlParseUtil
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.layout_detail_bottom_content.*

/**
 * 进入详情页传递两个参数：
 * id 和 封面图片url: thumbnail
 */

class ArticleActivity : BaseActivity(), DetailContract.View {

    private lateinit var postId : String
    private lateinit var coverImgUrl : String
    private lateinit var mPresenter : DetailPresenter
    private lateinit var mHtmlParseUtil: HtmlParseUtil

    override fun setContentViewId(): Int {
        overridePendingTransition(R.anim.translate_bottom_in, R.anim.translate_bottom_out)
        return R.layout.activity_article
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

    override fun loadDataSuccess(bean: DetailBean) {
        if (bean.datas.parseXML == 1){
            detail_type.text = "文字"
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

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }
}
