package com.denluoyia.douyue.model.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */

data class HomeListBean(@SerializedName("author")
                        val author : String = "",
                        @SerializedName("avatar")
                        val avatar : String = "",
                        @SerializedName("bookmark")
                        val bookmark : String = "",
                        @SerializedName("category")
                        val category : String = "",
                        @SerializedName("create_time")
                        val createTime : String = "",
                        @SerializedName("excerpt")
                        val excerpt : String = "",
                        @SerializedName("fm")
                        val fm : String = "",
                        @SerializedName("fm_play")
                        val fmPlay : String = "",
                        @SerializedName("good")
                        val good : String = "",
                        @SerializedName("hot_comments")
                        val hotComments : List<Any> ,
                        @SerializedName("html5")
                        val html5 : String = "",
                        @SerializedName("id")
                        val id : String = "",
                        @SerializedName("lead")
                        val lead : String = "",
                        @SerializedName("link_url")
                        val linkUrl : String = "",
                        @SerializedName("lunar_type")
                        val lunarType : String = "",
                        @SerializedName("model")
                        val model : String = "",
                        @SerializedName("name")
                        val name : String = "",
                        @SerializedName("parseXML")
                        val parseXML : Int = 0,
                        @SerializedName("position")
                        val position : String = "",
                        @SerializedName("publish_time")
                        val publishTime : String = "",
                        @SerializedName("share")
                        val share : String = "",
                        @SerializedName("show_uid")
                        val showUid : String = "",
                        @SerializedName("status")
                        val status : String = "",
                        @SerializedName("thumbnail")
                        val thumbnail : String = "",
                        @SerializedName("title")
                        val title : String = "",
                        @SerializedName("tpl")
                        val tpl : Int,
                        @SerializedName("uid")
                        val uid : String = "",
                        @SerializedName("update_time")
                        val updateTime : String = "",
                        @SerializedName("video")
                        val video : String = "",
                        @SerializedName("view")
                        val view : String = "",
                        @SerializedName("tags")
                        val tags : List<TagsBean>? = null
)