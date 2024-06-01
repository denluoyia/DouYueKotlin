package com.denluoyia.douyuekotlin.view.activity

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.view.View
import android.widget.SeekBar
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.denluoyia.douyuekotlin.R
import com.denluoyia.douyuekotlin.base.BaseActivity
import com.denluoyia.douyuekotlin.manager.audioplayer.AudioPlayerService
import com.denluoyia.douyuekotlin.manager.audioplayer.AudioPlayerService.LocalBinder
import com.denluoyia.douyuekotlin.model.bean.DetailBean
import com.denluoyia.douyuekotlin.presenter.DetailContract
import com.denluoyia.douyuekotlin.presenter.DetailPresenter
import com.denluoyia.douyuekotlin.util.HtmlParseUtil
import com.denluoyia.douyuekotlin.util.TimeUtil
import kotlinx.android.synthetic.main.activity_audio.*
import kotlinx.android.synthetic.main.layout_detail_bottom_content.*

class AudioActivity : BaseActivity(), DetailContract.View{

    private lateinit var postId : String
    private lateinit var coverImgUrl : String
    private lateinit var audioUrl : String
    private lateinit var mPresenter : DetailPresenter
    private lateinit var mHtmlParseUtil: HtmlParseUtil
    private var mAudioPlayerService : AudioPlayerService? = null
    private var mHandler = Handler(Looper.getMainLooper())
    private var r = MyRunnable()

    private var connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mAudioPlayerService = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mAudioPlayerService = (service as LocalBinder).getAudioPlayerService()
        }
    }


    override fun setContentViewId(): Int {
        overridePendingTransition(R.anim.translate_bottom_in, R.anim.translate_bottom_out)
        return R.layout.activity_audio
    }

    override fun doBusiness() {
        postId = intent.getStringExtra("id").toString()
        coverImgUrl = intent.getStringExtra("img_url").toString()
        if (postId.isNullOrEmpty()) return
        initToolbar(toolbar)
        Glide.with(this).load(coverImgUrl!!).into(iv_top_cover)
        bindAudioPlayerService()
        mHtmlParseUtil = HtmlParseUtil(this)
        mPresenter = DetailPresenter(this)
        mPresenter.loadData(postId)
    }

    private fun bindAudioPlayerService(){
        var intent  = Intent(this, AudioPlayerService::class.java)
        intent.action = "com.denluoyia.douyuekotlin.Audio"
        this.bindService(intent, connection, android.content.Context.BIND_AUTO_CREATE)
    }

    override fun loadDataSuccess(bean: DetailBean) {
        if (bean.datas.parseXML == 1){
            audioUrl = bean.datas.fm
            detail_type.text = "声音"
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

    @OnClick(R.id.btn_init_play, R.id.btn_play_or_pause, R.id.btn_play_prev, R.id.btn_play_next)
    fun onClick(view : View){
        when(view.id){
            R.id.btn_init_play -> {
                startPlay()
            }

            R.id.btn_play_or_pause -> {
                mAudioPlayerService!!.playOrPause()
                btn_play_or_pause.setImageResource(if (mAudioPlayerService!!.isPlaying()) R.mipmap.play_btn_pause else R.mipmap.play_btn_play)
            }

            R.id.btn_play_prev -> {
                var newCurrTime : Int = mAudioPlayerService!!.getCurrentProgress() - ((0.05 * mAudioPlayerService!!.getDuration()).toInt())
                mAudioPlayerService!!.seekTo(if (newCurrTime < 0) 0 else newCurrTime)
            }

            R.id.btn_play_next -> {
               var newCurrTime : Int = mAudioPlayerService!!.getCurrentProgress() + ((0.05 * mAudioPlayerService!!.getDuration()).toInt())
               mAudioPlayerService!!.seekTo(if (newCurrTime > mAudioPlayerService!!.getDuration()) mAudioPlayerService!!.getDuration() else newCurrTime)
            }
        }
    }

    private fun startPlay() {
        btn_init_play.visibility = View.GONE
        hint_progress_bar.visibility = View.VISIBLE
        mAudioPlayerService!!.initStart(audioUrl)
        seek_bar.max = mAudioPlayerService!!.getDuration()
        mHandler.post(r)
        hint_progress_bar.visibility = View.GONE
        mAudioPlayerService!!.playOrPause()
        seek_bar_bottom_tip.visibility = View.VISIBLE

    }


    inner class MyRunnable : Runnable {
        override fun run() {
            time_play_curr.text = TimeUtil.formatPlayTime(mAudioPlayerService!!.getCurrentProgress())
            time_play_total.text = TimeUtil.formatPlayTime(mAudioPlayerService!!.getDuration())
            seek_bar.progress = mAudioPlayerService!!.getCurrentProgress()
            seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser){
                        mAudioPlayerService!!.seekTo(seekBar!!.progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    mHandler.removeCallbacks(r)
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    mHandler.post(r)
                }
            })
            mHandler.postDelayed(r, 1000)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.translate_bottom_out)
    }

}
