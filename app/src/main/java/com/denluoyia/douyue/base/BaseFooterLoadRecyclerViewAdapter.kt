package com.denluoyia.douyue.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.denluoyia.douyue.R

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
open abstract class BaseFooterLoadRecyclerViewAdapter<T, VHH : BaseItemViewHolder> : RecyclerView.Adapter<BaseItemViewHolder> {

    open val TAG : String = javaClass.simpleName
    open val FOOTER_TYPE : Int = 0x01
    open val CONTENT_TYPE : Int = 0X02

    open var mActivity : BaseActivity? = null
    open var mDataList : ArrayList<T> = ArrayList<T>()

    open var hasMore : Boolean = true
    open var isError : Boolean = false

    fun setIsHasMore(hasMore: Boolean) {
        this.hasMore = hasMore
    }

    fun setIsError(isError: Boolean) {
        this.isError = isError
    }

    constructor(activity: BaseActivity){
        this.mActivity = activity
    }

    open abstract fun iCreateViewHolder(parent : ViewGroup, viewType : Int) : VHH
    open abstract fun iBindViewHolder(viewHolder : VHH, position : Int)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder {
        return if (viewType == CONTENT_TYPE){
            iCreateViewHolder(parent, viewType)
        }else{
            FooterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_footer_item_layout, parent, false))
        }
    }

    override fun onBindViewHolder(holder: BaseItemViewHolder, position: Int) {
        if (holder is FooterViewHolder){
            if (mDataList.size == 0) return
            var footerViewHolder : FooterViewHolder = holder
            if (isError){
                isError = false
                footerViewHolder.pbLoading.visibility = View.GONE
                footerViewHolder.tvNoMoreTip.visibility = View.GONE
                footerViewHolder.tvErrorTip.visibility = View.VISIBLE
                footerViewHolder.tvErrorTip.setOnClickListener({
                    //重新加载 ~ 回调接口
                })
            }

            if (hasMore){
                footerViewHolder.pbLoading.visibility = View.VISIBLE
                footerViewHolder.tvNoMoreTip.visibility = View.GONE
                footerViewHolder.tvErrorTip.visibility = View.GONE
            }else{
                footerViewHolder.pbLoading.visibility = View.GONE
                footerViewHolder.tvNoMoreTip.visibility = View.VISIBLE
                footerViewHolder.tvErrorTip.visibility = View.GONE
            }
        }else{
            var vhh : VHH = holder as VHH
            iBindViewHolder(vhh, position)
        }
    }


    /**
     * 初始加载
     */
    fun refreshDataList(dataList : ArrayList<T>){
        if (dataList == null) return
        mDataList.clear()
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun loadMoreDataList(dataList : ArrayList<T>){
        if (dataList == null || dataList.size == 0) return
        hasMore = true
        var beforeSize : Int = mDataList.size
        mDataList.addAll(dataList)
        notifyItemRangeInserted(beforeSize, dataList.size)
    }

    override fun getItemViewType(position: Int): Int {
        if (position + 1 == itemCount) return FOOTER_TYPE
        return CONTENT_TYPE
    }

    fun getItem(position: Int) : T? {
        return if (position < mDataList.size) mDataList.get(position) else null
    }

    override fun getItemCount(): Int {
        return mDataList.size + 1
    }

    class FooterViewHolder : BaseItemViewHolder {
        var pbLoading: ProgressBar
        var tvNoMoreTip : TextView
        var tvErrorTip : TextView
        constructor(itemView : View) : super(itemView) {
            pbLoading = itemView.findViewById(R.id.pb_loading)
            tvNoMoreTip = itemView.findViewById(R.id.tv_no_more_tip)
            tvErrorTip = itemView.findViewById(R.id.tv_error_tip)
        }
    }
}