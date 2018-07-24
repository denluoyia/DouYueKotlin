package com.denluoyia.douyue.model.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
data class HomeItemBean(@SerializedName("code")
                    val code : Int,
                        @SerializedName("msg")
                    val msg : String = "",
                        @SerializedName("status")
                    val status : String = "",
                        @SerializedName("datas")
                    val datas : List<HomeListBean>
)