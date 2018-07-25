package com.denluoyia.douyuekotlin.model.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by denluoyia
 * Date 2018/07/25
 * DouYue
 */


/**
    author	单读视频
    avatar	https://img.owspace.com/Public/static/avatar/2.png
    bookmark	0
    category	TO WATCH
    comment	108
    content	<p>“我觉得我们都挺容易去概念化地理解一个人。”许知远在采访后回忆。他以对娱乐明星“空洞”的代入感开始，到同姚晨共话黑泽明笔下的“蛤蟆油”；以姚晨穿着的那双小白鞋为初印象，到头脑中反复回旋着曾经小镇姑娘式的生活，可以说，他在头脑中已经重新透视了姚晨所代表的文化符号。这一过程，也许你认为是许知远的偏见，其实，是我们每个人的偏见。<br/></p><h2>主持人：</h2><p>许知远，作家，单向空间联合创始人。2000 年毕业于北京大学计算机系。曾任《经济观察报》主笔、《商业周刊/中文版》执行主编。已出版作品《那些忧伤的年轻人》《一个游荡者的世界》《祖国的陌生人》等。</p><p><img src="http://img.owspace.com/Public/uploads/Editor/2016-07-13/1468386530268929.jpg" width="720" height="1280" /></p><h2>第二期嘉宾：</h2><p>姚晨，中国内地女演员，联合国难民署中国亲善大使。第 2 届华鼎奖电视剧盛典最佳女主角，第 18 届北京大学生电影节最受欢迎女演员，代表作有《武林外传》《潜伏》《离婚律师》等。2013 年、2014 年入围美国《时代》周刊“全球 100 位最具影响力人物”，2014 年当选；2015 年入围该杂志评选的“全球 30 位最具网络影响力人物”；倍耐力年历首位华人封面。2016 年世界经济论坛水晶奖获得者，也是首位获得该奖项的华人女性; 2016 年世界经济论坛“全球青年领袖”中国唯一入选女星。</p><p><img src="http://img.owspace.com/Public/uploads/Editor/2016-07-13/1468386530405683.jpg" width="720" height="1280" /></p><p>“过去十年，还有一个重要的问题是平民的兴起，平民从来没有获得过这样的一次表达机会，而姚晨身上具有某种平民精神，同时她又理解自己是怎么回事。“许知远在第二期《十三邀》中这样总结。的确，在被科技引领的今天，每一个人都有可能在下一秒被千万人聆听，这是属于普通人的特别时代。</p><p>许知远说，他是一个不太靠谱的作家，试图捕捉时代的精神，却又常常厌恶时代的流行情绪；他也是一个勉强的创业者，努力获得商业上的成功，却又不完全相信商业的逻辑。这个时代在他眼中，有着过分娱乐化和浅薄的一面，所以，他试图寻找一种方式，打破大家思维中的惯性，同时，他也希望了解这个时代中，新的动力、情绪和人们的内心世界。</p><p>于是，腾讯新闻联合单向空间，寻找了 13 位在一定意义上来说具有模板作用的个人，向他们发出邀请，希望通过访谈的形式，观察他们的行为，分享他们的经验和心得。从正在发生的样本出发，探求中国发展的切片。</p><p><img src="http://img.owspace.com/Public/uploads/Editor/2016-07-13/1468386530190980.jpg" width="720" height="1280" /></p>
    create_time	1468388678
    excerpt	耀眼的光环总让我们以为明星都是空洞的，而姚晨却以某种平民精神，试图理解世界、观察自身。本期“十三邀”，许知远专访姚晨。
    fm
    fm_play
    good	184
    hot_comments	Array
    html5	http://static.owspace.com/wap/292296.html
    id	292296
    lead	腾讯新闻和单向空间联合出品的谈话节目《十三邀》，是许知远对这个时代所做的新思考。这是第二期。在这一期，姚晨来到位于北京花家地的单向空间，用她自己的话说，这是许知远的“地盘”。长达四个小时的对话，他们都聊了什么？
    link_id	0
    link_url
    lunar_type	1
    model	2
    name
    parseXML	1
    position	0
    share	http://static.owspace.com/wap/292296.html
    template
    thumbnail	https://img.owspace.com/Public/uploads/Picture/2016-07-13/5785d02bd885f.jpg
    title	一位演员也是
    一个世界
    tpl	2
    uid	2132
    update_time	2016/07/13
    video	http://img.owspace.com/V_poa248777_1468383459.0986272.mp4
    view	9.1w
 */
data class DetailDataBean(@SerializedName("author")
                          val author : String = "",
                          @SerializedName("avatar")
                          val avatar : String = "",
                          @SerializedName("bookmark")
                          val bookmark : String = "",
                          @SerializedName("category")
                          val category : String = "",
                          @SerializedName("comment")
                          val comment : String = "",
                          @SerializedName("content")
                          val content : String = "",
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
                          @SerializedName("link_id")
                          val linkId : String = "",
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
                          @SerializedName("share")
                          val share : String = "",
                          @SerializedName("template")
                          val template : String = "",
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
                          val view : String = ""

)