package com.denluoyia.douyue.constant

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
class URLConstant {
    companion object {

        /**
         * <p>分类列表</p>
         * <p>http://static.owspace.com/?c=api&a=getList&p=1&model=1&page_id=0&create_time=0&client=android&version=1.3.0&time=1467867330&device_id=866963027059338&show_sdv=1</p>
         *
         * @param c
         * @param a
         * @param page
         * @param model(0:首页，1：文字，2：声音，3：影像，4：单向历)
         * @param pageId
         * @param time
         * @param deviceId
         * @param show_sdv
         * @return
         */
        val URL_CATEGORIES_LIST : String = "http://static.owspace.com/?c=api&a=getList&create_time=0&client=android&version=1.3.0&show_sdv=1&"
    }
}