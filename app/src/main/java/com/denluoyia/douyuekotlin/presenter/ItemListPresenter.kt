package com.denluoyia.douyuekotlin.presenter

import com.denluoyia.douyuekotlin.constant.URLConstant
import com.denluoyia.douyuekotlin.manager.net.IRequestCallback
import com.denluoyia.douyuekotlin.manager.net.OkHttpUtil
import com.denluoyia.douyuekotlin.model.bean.HomeItemBean
import com.denluoyia.douyuekotlin.util.TimeUtil
import java.util.*

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
class ItemListPresenter(view : ItemListContract.ItemListView, type : Int) : ItemListContract.Presenter {

    private var mView : ItemListContract.ItemListView? = view
    private var mType : Int = type
    private var mCurrPage : Int = type

    override fun loadInitData() {
        mCurrPage = 1
        requestData()

    }

    override fun loadMoreData() {
        mCurrPage++
        requestData()
    }

    private fun requestData(){

        var params = LinkedHashMap<String, String>()
        params["p"] = mCurrPage.toString()
        params["model"] = mType.toString()
        params["time"] = TimeUtil.getTimeStamp()
        params["device_id"] = "866963027059338"
        var callback : IRequestCallback<HomeItemBean> = object : IRequestCallback<HomeItemBean>(){
            override fun onFailure(e: Exception) {
                if (e.message.isNullOrEmpty()){
                    mView!!.loadDataFailed(e.message)
                }else{
                    mView!!.loadDataFailed(null)
                }
            }

            override fun onSuccess(result: HomeItemBean) {
                if (mCurrPage == 1){
                    mView!!.loadInitDataSuccess(result)
                }else{
                    mView!!.loadMoreDataSuccess(result)
                }
            }
        }
        OkHttpUtil.getInstance().get(URLConstant.URL_CATEGORIES_LIST, params, callback)
    }

    override fun detachView() {
        mView = null
    }

}

