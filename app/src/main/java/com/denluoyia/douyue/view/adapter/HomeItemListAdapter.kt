package com.denluoyia.douyue.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.denluoyia.douyue.R
import com.denluoyia.douyue.base.BaseActivity
import com.denluoyia.douyue.base.BaseFooterLoadRecyclerViewAdapter
import com.denluoyia.douyue.base.BaseItemViewHolder
import com.denluoyia.douyue.model.bean.HomeListBean

/**
 * Created by denluoyia
 * Date 2018/07/24
 * DouYue
 */
class HomeItemListAdapter(activity : BaseActivity) : BaseFooterLoadRecyclerViewAdapter<HomeListBean, HomeItemListAdapter.ItemViewHolder>(activity){

    override fun iCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_layout, parent, false))
    }

    override fun iBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        val item : HomeListBean = getItem(position) ?: return
        viewHolder.title.text = item.title
        viewHolder.author.text = item.author
        Glide.with(mActivity!!).load(item.thumbnail).into(viewHolder.iv)
        viewHolder.itemView.setOnClickListener({
            startAction(mActivity!!, viewHolder.iv, item)
        })
    }

    fun startAction(context: Context, view : View, item : HomeListBean){
        //var intent : Intent
    }


    class ItemViewHolder : BaseItemViewHolder{
        var iv : ImageView
        var title : TextView
        var author : TextView
        constructor(itemView : View) : super(itemView){
            iv = itemView.findViewById(R.id.item_iv)
            title = itemView.findViewById(R.id.item_title)
            author = itemView.findViewById(R.id.item_author)
        }

    }
}