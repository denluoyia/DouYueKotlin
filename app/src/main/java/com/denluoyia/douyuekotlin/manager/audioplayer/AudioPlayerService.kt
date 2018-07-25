package com.denluoyia.douyuekotlin.manager.audioplayer

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

/**
 * Created by denluoyia
 * Date 2018/07/25
 * DouYue
 */
class AudioPlayerService : Service(), IAudioPlayer {

    private val TAG : String = this::class.java.simpleName

    private lateinit var mPlayer : AudioPlayer
    private var mBinder = LocalBinder()

    override fun onCreate() {
        super.onCreate()
        mPlayer = AudioPlayer.getInstance()
    }

    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }

    override fun isPlaying(): Boolean {
        return mPlayer.isPlaying()
    }

    override fun initStart(url: String) {
        mPlayer.initStart(url)
    }

    override fun playOrPause() {
        mPlayer.playOrPause()
    }

    override fun getDuration(): Int {
        return mPlayer.getDuration()
    }

    override fun getCurrentProgress(): Int {
        return mPlayer.getCurrentProgress()
    }

    override fun seekTo(progress: Int) {
        mPlayer.seekTo(progress)
    }

    override fun releasePlayer() {
        mPlayer.releasePlayer()
    }

    inner class LocalBinder : Binder() {
        fun getAudioPlayerService() : AudioPlayerService{
            return this@AudioPlayerService
        }
    }

}