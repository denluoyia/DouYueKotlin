package com.denluoyia.douyue.view.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.VERTICAL
import com.denluoyia.douyue.R
import com.denluoyia.douyue.base.BaseActivity
import com.denluoyia.douyue.base.BaseFragment
import com.denluoyia.douyue.model.bean.HomeItemBean
import com.denluoyia.douyue.model.bean.HomeListBean
import com.denluoyia.douyue.presenter.ItemListContract
import com.denluoyia.douyue.presenter.ItemListPresenter
import com.denluoyia.douyue.util.LogUtil
import com.denluoyia.douyue.view.adapter.HomeItemListAdapter
import kotlinx.android.synthetic.main.fragment_item_list.*

class ItemListFragment : BaseFragment() , ItemListContract.ItemListView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mPresenter : ItemListPresenter
    private lateinit var mAdapter : HomeItemListAdapter
    private var itemListType : Int = 0
    private var hasMore : Boolean = true
    private var mLastVisibleItemPosition : Int = -1

    companion object {
        fun newInstance(type : Int) : ItemListFragment {
            var fragment  = ItemListFragment()
            var bundle = Bundle()
            bundle.putInt("type", type)
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun setLayoutContentViewId(): Int {
        return R.layout.fragment_item_list
    }

    override fun doBusiness() {
        itemListType = arguments!!.getInt("type")
        LogUtil.e("type", itemListType.toString())
        swipe_refresh_layout.setOnRefreshListener(this)
        swipe_refresh_layout.setColorSchemeResources(R.color.colorAccent)
        recycler_view.layoutManager = LinearLayoutManager(activity, VERTICAL, false)
        var mOnScrollListener : RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE && !swipe_refresh_layout.isRefreshing && hasMore && (mLastVisibleItemPosition + 1 == mAdapter.itemCount)){
                    mPresenter.loadMoreData() //根据滑动到最后一项自动加载更多
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                mLastVisibleItemPosition = (recyclerView!!.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            }
        }
        recycler_view.addOnScrollListener(mOnScrollListener)
        mAdapter = HomeItemListAdapter(activity as BaseActivity)
        recycler_view.adapter = mAdapter
        mPresenter = ItemListPresenter(this, itemListType)
        mPresenter!!.loadInitData()
    }

    override fun loadInitDataSuccess(bean: HomeItemBean) {
        swipe_refresh_layout.isRefreshing = false
        mAdapter.refreshDataList(bean!!.datas as ArrayList<HomeListBean>)
    }

    override fun loadMoreDataSuccess(bean: HomeItemBean) {
        swipe_refresh_layout.isRefreshing = false
        if (bean!!.datas.isNotEmpty()){
            mAdapter.loadMoreDataList(bean!!.datas as ArrayList<HomeListBean>)
        }else{
            hasMore = false
            mAdapter.setIsHasMore(hasMore)
            mAdapter.notifyItemChanged(mAdapter.itemCount - 1)
        }

    }

    override fun loadDataFailed(msg: String?) {
        swipe_refresh_layout.isRefreshing = false
        if (msg != null && !msg.isEmpty()){

        }
    }

    override fun start() {

    }

    override fun onRefresh() {
        swipe_refresh_layout.isRefreshing = true
        mPresenter.loadInitData()
    }


}
