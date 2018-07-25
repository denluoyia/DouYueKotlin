package com.denluoyia.douyuekotlin.manager.audioplayer

/**
 * Created by denluoyia
 * Date 2018/07/25
 * DouYue
 */
interface IAudioPlayer {

    fun isPlaying() : Boolean

    fun initStart(url : String)

    fun playOrPause()

    fun getDuration() : Int

    fun getCurrentProgress() : Int

    fun seekTo(progress : Int)

    fun releasePlayer()
}