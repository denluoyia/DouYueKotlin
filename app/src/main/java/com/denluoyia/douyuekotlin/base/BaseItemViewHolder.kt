package com.denluoyia.douyuekotlin.base

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
open class BaseItemViewHolder : RecyclerView.ViewHolder {
    constructor(itemView : View) : super(itemView){
        ButterKnife.bind(itemView)
    }
}