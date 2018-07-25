package com.denluoyia.douyuekotlin.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.denluoyia.douyuekotlin.R
import com.denluoyia.douyuekotlin.base.BaseActivity
import com.denluoyia.douyuekotlin.base.BaseFooterLoadRecyclerViewAdapter
import com.denluoyia.douyuekotlin.base.BaseItemViewHolder
import com.denluoyia.douyuekotlin.constant.Constant
import com.denluoyia.douyuekotlin.model.bean.HomeListBean
import com.denluoyia.douyuekotlin.view.activity.ArticleActivity
import com.denluoyia.douyuekotlin.view.activity.AudioActivity
import com.denluoyia.douyuekotlin.view.activity.VideoActivity

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
            startAction(mActivity!!, item)
        })
    }

    private fun startAction(context: Context, item : HomeListBean){
        val intent = if (!item.fm.isNullOrEmpty()){
            Intent(context, AudioActivity::class.java)
        }else if (!item.video.isNullOrEmpty()){
            Intent(context, VideoActivity::class.java)
        }else{
            Intent(context, ArticleActivity::class.java)
        }
        intent.putExtra("id", item.id) //详情页的id
        intent.putExtra("img_url", item.thumbnail) //用于转场显示图片
        context.startActivity(intent)
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