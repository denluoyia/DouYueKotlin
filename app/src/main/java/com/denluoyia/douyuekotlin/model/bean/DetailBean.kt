package com.denluoyia.douyuekotlin.model.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by denluoyia
 * Date 2018/07/25
 * DouYue
 */


data class DetailBean(@SerializedName("code")
                      val code : Int,
                      @SerializedName("msg")
                      val msg : String,
                      @SerializedName("status")
                      val status : String,
                      @SerializedName("datas")
                      val datas : DetailDataBean


)