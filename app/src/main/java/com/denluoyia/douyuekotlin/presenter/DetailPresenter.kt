package com.denluoyia.douyuekotlin.presenter

import com.denluoyia.douyuekotlin.constant.URLConstant
import com.denluoyia.douyuekotlin.manager.net.IRequestCallback
import com.denluoyia.douyuekotlin.manager.net.OkHttpUtil
import com.denluoyia.douyuekotlin.model.bean.DetailBean

/**
 * Created by denluoyia
 * Date 2018/07/25
 * DouYue
 */
class DetailPresenter(view : DetailContract.View) : DetailContract.Presenter {

    private var mView : DetailContract.View? = view

    override fun loadData(postId: String) {
        var params = LinkedHashMap<String, String>()
        params["post_id"] = postId
        var callback : IRequestCallback<DetailBean> = object : IRequestCallback<DetailBean>(){
            override fun onSuccess(result: DetailBean) {
                mView!!.loadDataSuccess(result)
            }

            override fun onFailure(e: Exception) {
                if (e.message.isNullOrEmpty()){
                    mView!!.loadDataFailed(null)
                }else{
                    mView!!.loadDataFailed(e.message)
                }
            }
        }

        OkHttpUtil.getInstance().get(URLConstant.URL_DETAIL, params, callback)
    }

    override fun detachView() {
        mView = null
    }
}