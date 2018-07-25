package com.denluoyia.douyuekotlin.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
data class TagsBean(@SerializedName("name")
                    val name : String = ""
) : Serializable