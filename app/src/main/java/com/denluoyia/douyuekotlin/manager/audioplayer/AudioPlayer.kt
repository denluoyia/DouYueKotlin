package com.denluoyia.douyuekotlin.manager.audioplayer

import android.media.MediaPlayer
import java.io.IOException

/**
 * Created by denluoyia
 * Date 2018/07/25
 * DouYue
 */
class AudioPlayer : IAudioPlayer{

    companion object {
        @Volatile
        private var sInstance: AudioPlayer? = null

        private lateinit var mPlayer : MediaPlayer

        fun getInstance() : AudioPlayer {
            if (null == sInstance){
                synchronized(AudioPlayer::class.java){
                    if (null == sInstance){
                        sInstance = AudioPlayer()
                    }
                }
            }
            return sInstance!!
        }
    }

    private constructor(){
        mPlayer = MediaPlayer()
    }

    override fun isPlaying() : Boolean {
        return mPlayer.isPlaying
    }

    override fun initStart(url: String) {
        if (url.isNullOrEmpty()) return
        mPlayer.reset()
        try{
            mPlayer.setDataSource(url)
            mPlayer.prepare()
        }catch (e : IOException){
            e.printStackTrace()
        }
    }

    override fun playOrPause() {
        if (mPlayer.isPlaying){
            mPlayer.pause()
        }else{
            mPlayer.start()
        }
    }

    override fun getDuration(): Int {
        return mPlayer.duration
    }

    override fun getCurrentProgress(): Int {
        return mPlayer.currentPosition
    }

    override fun seekTo(progress: Int) {
        mPlayer.seekTo(progress)
        if (!mPlayer.isPlaying){
            mPlayer.start()
        }
    }

    override fun releasePlayer() {
        mPlayer.reset()
        mPlayer.release()
        sInstance = null
    }
}