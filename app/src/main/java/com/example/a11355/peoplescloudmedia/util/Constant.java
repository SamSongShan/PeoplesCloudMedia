package com.example.a11355.peoplescloudmedia.util;


import com.example.a11355.peoplescloudmedia.R;

/**
 * 常量
 */
public interface Constant {

    interface URL {

        //本地服务器地址
        String BaseUrl = "http://service.lml9.com/Index.asmx/";
        String BaseImg = "http://service.lml9.com";
        String BaseH5 = "http://net.lml9.com";

        String DefaultHeadImg = "res:///" + R.drawable.head;

        String WeChatToken = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + ID.WeChat_APP_ID +
                "&secret=" + ID.WeChat_APP_Secret + "&code=%s&grant_type=authorization_code";
        String WeChatInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";

        String GoogleMap = "http://ditu.google.cn/maps?f=d&source=s_d&saddr=%s,%s&daddr=%s,%s&hl=zh";
        String BaiduMapApp = "intent://map/direction?origin=latlng:%s|name:%s&destination=latlng:%s|name:%s" +
                "&mode=driving&src=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";
        String BaiduMapUrl = "http://api.map.baidu.com/direction?origin=latlng:%s|name:%s&destination=latlng:%s|name:%s"
                + "&mode=driving&region=%s&output=html&src=%s";
        String MapTrans = "http://api.map.baidu.com/ag/coord/convert?from=%d&to=%d&x=%s&y=%s";


        //用户注册
        String Registered = BaseUrl + "Registered";
        //获取短信
        String GetMobileCode = BaseUrl + "GetMobileCode";
        // 用户登录
        String LoginRole = BaseUrl + "LoginRole";

        //版本检查
        String GetAppVersion = BaseUrl + "GetAppVersion";
        //获取个人信息
        String GetEntityUser = BaseUrl + "GetEntityUser";
        //图片上传
        String UploadImg = BaseUrl + "UploadImg";

        //用户基本信息修改(输入参数string Json)
        /*
        * UserId:用户编号
 Token:登录的判断标识
 Action:修改信息( 昵称-NickName、头像-HeadIcon、性别-Gender，个性签名-Signature,手机号-Mobile)
 ActionValue:值
        * */

        String UpdateUserEntity = BaseUrl + "UpdateUserEntity";
        //清除缓存接口
        String GetAreaLastDate = BaseUrl + "GetAreaLastDate";
        //视频接口
        String GetVideoList = BaseUrl + "GetVideoList";

        //我的推广列表(我推荐注册的用户)
        String GetRecommendUserList = BaseUrl + "GetRecommendUserList";

        //消息列表
        String GetMessageList = BaseUrl + "GetMessageList";

        //获取文章分类列表-发现模块
        String GetNewsTypeList = BaseUrl + "GetNewsTypeList";

        //【31】获取文章列表-发现模块(输入参数string Json)
        String GetNewsList = BaseUrl + "GetNewsList";

        //【38】获取文章收藏列表-发现模块(输入参数string Json)
        String GetFindNewsCollectList = BaseUrl + "GetFindNewsCollectList";


        //【40】获取用户视频收藏列表(输入参数string Json)
        String GetUserCollecVideoList = BaseUrl + "GetUserCollecVideoList";


        //36】添加文章收藏-发现模块(输入参数string Json)
        String AddFindCollect = BaseUrl + "AddFindCollect";

        //【37】取消文章收藏-发现模块(输入参数string Json)
        String CancelFindCollect = BaseUrl + "CancelFindCollect";

        //我的关注列表(输入参数string Json)
        String GetAttentionToList = BaseUrl + "GetAttentionToList";

        //我的粉丝列表(输入参数string Json)
        String GetFansList = BaseUrl + "GetFansList";

        //获取文章收藏列表- 文章是否收藏
        String GetFindOutDetail = BaseUrl + "GetFindOutDetail";

        //34】增加文章浏览量-发现模块(输入参数string Json)
        String AddFindPV = BaseUrl + "AddFindPV";


        //35】增加文章分享量-发现模块(输入参数string Json)
        String AddFindSharingPV = BaseUrl + "AddFindSharingPV";


        //【32】获取文章评论列表-发现模块(输入参数string Json)
        String GetNewsCommentList = BaseUrl + "GetNewsCommentList";

        //【33】添加文章评论-发现模块(输入参数string Json)
        String AddFindComment = BaseUrl + "AddFindComment ";


        //【54】获取人众推文章列表(输入参数string Json)
        String GetRZTArticleList = BaseUrl + "GetRZTArticleList ";

        //【获取人众推广告列表(输入参数string Json)
        String GetAdvertisingList = BaseUrl + "GetAdvertisingList ";

        //【53】新增人众推文章（文章抓取）(输入参数string Json)
        String AddRZTArticle = BaseUrl + "AddRZTArticle ";


        //【【12】用户编号获取用户实体信息(输入参数string Json)
        String GetVideoDetail = BaseUrl + "GetVideoDetail";

        //【16】添加视频浏览量(输入参数string Json)
        String AddVideoPV = BaseUrl + "AddVideoPV";

        //【15】添加视频收藏(输入参数string Json)
        String AddUserCollect = BaseUrl + "AddUserCollect";

        //【17】取消用户收藏(输入参数string Json)
        String CancelUserCollect = BaseUrl + "CancelUserCollect";

        //【18】添加视频分享量(输入参数string Json)
        String AddVideoSharingPV = BaseUrl + "AddVideoSharingPV";

        //【14】获取视频评论列表(输入参数string Json)

        String GetUserEvaluationeList = BaseUrl + "GetUserEvaluationeList";

//【13】添加视频评论(输入参数string Json)

        String AddUserEvaluation = BaseUrl + "AddUserEvaluation";

//【50】新增/修改人众推广告(输入参数string Json)

        String UpdateAdvertising = BaseUrl + "UpdateAdvertising";


//【46】获取首页轮播图列表(输入参数string Json)

        String GetHomeRotateList = BaseUrl + "GetHomeRotateList";

        //【26】获取/查询音频列表(输入参数string Json)

        String GetMusicList = BaseUrl + "GetMusicList";

        //【45】视频上传

        String UploadVideo = BaseUrl + "UploadVideo";


        //【70】新增/修改图文编辑文章（名片和产品）(输入参数string Json)
        String UpdateGraphicEditor = BaseUrl + "UpdateGraphicEditor";


      //【21】用户名片基本信息修改(输入参数string Json)
      String UpdateBusinessCardEntity = BaseUrl + "UpdateBusinessCardEntity";

        //【20】用户编号获取用户名片信息(输入参数string Json)
        String GetBusinessCardInfo = BaseUrl + "GetBusinessCardInfo";




        //【22】用户名片公司信息修改(输入参数string Json)
        String UpdateCardCompanyInfo = BaseUrl + "UpdateCardCompanyInfo";

        //【41】行政区域
        String GetAreaList=BaseUrl + "GetAreaList";

        //【25】用户名片其他信息修改(标题，音乐)(输入参数string Json)
        String UpdateCardOther=BaseUrl + "UpdateCardOther";

        //【27】用户产品信息修改(输入参数string Json)
        String UpdateUserProductEntity=BaseUrl + "UpdateUserProductEntity";

        //【29】用户编号获取产品完整信息(输入参数string Json)

        String GetUserProductInfo=BaseUrl + "GetUserProductInfo";


        //【71】我的文章列表-图文编辑添加的文章(输入参数string Json)

        String GetGraphicEditorList=BaseUrl + "GetGraphicEditorList";
           //【72】获取图文编辑文章完整信息(输入参数string Json)
        String GetGraphicEditorEntity=BaseUrl + "GetGraphicEditorEntity";

















        //注册分享链接   RecommendMobile  传推荐人手机号
        String ShearLink = BaseH5 + "/Webuser/Registered?RecommendMobile=";
        // 消息 详情  http://localhost:1548/News/Detail?MessageId=1e7c8e24-7308-441a-a710-4db44a3dd57f
        String MessageDetailsLink = BaseH5 + "/News/Detail?MessageId=";

       /* 发现模块文章详情页
        http://localhost:1548/News/FindOutDetail?Uid=1218&UserId=0
        Uid 传文章ID
        UserId 传当前登录用户ID，未登录传defaul*/

        String NewsDetailsLink = BaseH5 + "/News/FindOutDetail?Uid=%s&UserId=%s";

        /*
        *
        * 分享他人主页
http://localhost:1548/webuser/OthersHome?userId=212bd12a-37f2-4eb4-ae84-6dd9e552434b
userId 传用户ID
        * */

        String OthersHome = BaseH5 + "/webuser/OthersHome?userId=";

        /*
        * 人众推
文章预览页面
http://net.lml9.com/News/RztArticle?ArticleId=a659c161-79a8-4451-baf5-6c9cd1056b3b
ArticleId 传文章ID

        * */
        String RZTPreviewArticle = BaseH5 + "/News/RztArticle?ArticleId=";

        /*
        * 2、小视频
分享页
http://net.lml9.com/News/VideoSharing?VideoId=a3fd141f-f318-42c6-b1f8-e04bada2ad89
VideoId 传视频ID
        *
        * */
        String ShearVideo = BaseH5 + "/News/VideoSharing?VideoId=";

        /*
        * 文章分享页
http://net.lml9.com/News/FindOutSharing?Uid=395
Uid 传文章ID
        * */
        String ShearArticle = BaseH5 + "/News/FindOutSharing?Uid=";

        /*
        *
我的名片查看页面
http://net.lml9.com/WebUser/MyBusinessCardInfo?BusinessCardInfo=9817eb05-1cae-45f4-a900-31137d502a27
BusinessCardInfo 传名片ID
        * */
        String MyBusinessCard = BaseH5 + "/WebUser/MyBusinessCardInfo?BusinessCardInfo=";
        /*
        * 7、自媒体
名片预览页面（注：和前面我的名片查看页面不一样）
http://net.lml9.com/News/UserBusinessCardInfo?BusinessCardInfo=9817eb05-1cae-45f4-a900-31137d502a27
BusinessCardInfo 传名片ID
        *
        * */
        String BusinessCardPreview = BaseH5 + "/News/UserBusinessCardInfo?BusinessCardInfo=";


       /* 产品预览页面
        http://net.lml9.com/News/UserProductInfo?UserProductInfoId=a3f8ec26-6b7c-4505-a9dd-6e0bcf7a35e6
        UserProductInfoId 传产品ID*/
       String ProdectPreview = BaseH5 + "/News/UserProductInfo?UserProductInfoId=";
        //微信新闻官网
        String Wechat_GW = "http://weixin.sogou.com";
        //今日头条官网
        String JRTT_GW = "https://m.toutiao.com/search/?keyword=%E7%83%AD%E7%82%B9&from=search_tab";
        //今日头条官网
        String QQ_GW = "https://xw.qq.com";


        /*6、图文编辑
                文章预览页面
        http://net.lml9.com/News/GraphicEditorDetail?GraphicEditorId=31e58c0a-7840-416e-ab26-702c721974c2
        GraphicEditorId 传文章ID*/
        String GraphicEditorDetail = BaseH5 +"/News/GraphicEditorDetail?GraphicEditorId=";

/*
* 文章编辑页面
http://net.lml9.com/News/RztEditArticle?ArticleId=a659c161-79a8-4451-baf5-6c9cd1056b3b
ArticleId 传文章ID
*
* */
String RztEditArticle = BaseH5 +"/News/RztEditArticle?ArticleId=";

/*
* 人众推文章分享页面，需要修改下，改成这个
http://net.lml9.com/News/RztShareArticle?ArticleId=a659c161-79a8-4451-baf5-6c9cd1056b3b

ArticleId 传文章ID

* 
* */
String RztEditArticleShare = BaseH5 +"/News/RztShareArticle?ArticleId=";



    }

    interface Strings {
        //手机号正则
        String RegexMobile = "^1(3[0-9]|4[5,7]|5[0-9]|7[0-9]|8[0-9])\\d{8}$";
        //邮箱正则
        String RegexEmail = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        //身份证正则
        String RegexIdNum = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}(([0-9]|X)|x)$";
        //微信号正则
        String RegexWecaht = "^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}$";
        //密码正则
        String RegexPassword = "^[a-zA-Z0-9]{1}[a-zA-Z0-9_-]{5,19}$";

        //权限提醒
        String PermissionFileTips = "本应用保存数据时被系统拒绝，请手动授权。\n" +
                "授权方式：点击设置按钮进入应用设置页面，选择权限(或权限管理)->存储空间\n请选择允许";
        //权限提醒
        String PermissionCameraTips = "本应用未获取到拍照权限，请手动授权。\n" +
                "授权方式：点击设置按钮进入应用设置页面，选择权限(或权限管理)->相机\n请选择允许";

        // " 号错误提示
        String ErrorTips1 = "不可包含 \" 号";
        // \ 号错误提示
        String ErrorTips2 = "不可包含 \\ 号";
        // | 号错误提示
        String ErrorTips3 = "不可包含 | 号";
        // _ 号错误提示
        String ErrorTips4 = "不可包含 _ 号";
        //我的页面列表数据
        String[] mines = {"我的文章", "我的推广", "我的名片", "关于我们"};
        //发送短信参数
        String[] GetMobileCodeType = {"用户注册", "密码找回", "微信快捷登录绑定手机"};


    }


    interface Integers {
        //操作成功
        int SUC = 200;
        //token过期
        int TOKEN_OUT_OF = 300;
        //数据为空
        int NULL = 400;
        //数据异常
        int ABNORMAL = 500;

        //验证码失效时间
        int CodeRetryTime = 120;
    }

    interface ID {
        String QQ_APP_ID = "1105927296";
        String WeChat_APP_ID = "wxc70978c2fcbe8ed7";
        String WeChat_APP_Secret = "25f0bb6ce9bb7a4c11106c72742bd87e";
        String WeChat_State = "wechat_sdk_crowd";
    }

    interface Code {
        //打开相册请求码
        int AlbumCode = 0x0001;
        //拍照请求码
        int CameraCode = 0x0002;
        int PermissionCode = 0x1001;

        //更改昵称
        int Nick = 0x2001;
        //更改个性签名
        int Signature = 0x2002;
        //商家注册
        int RegisterForBusinessCode = 0x2003;
        //未登录
        int IntoCertifyCode = 0x2004;
        //登录
        int LoginCode = 0x2005;

        //收藏
        int CollectCode = 0x2006;

        int PickUp=0x2007;
        //自媒体制作个人
        int ZMTZZ_Personal = 0x2008;
        //自媒体制作公司
        int ZMTZZ_Company = 0x2009;
        int ZMTZZ_Music = 0x2010;
        int ZMTZZ_prodect = 0x2011;
        int Link= 0x2012;
    }

    interface IdString {
        String WeChat_APP_ID = "wx09064d186975bb4b";
        String WeChat_APP_Secret = "aadd39ff3f0cd70cbbf96de4d1032e40";
        String WeChat_State = "wechat_sdk_qd";

        String AliPayCompany = "浙江先丰号生物科技有限公司";
        String AliPay_APP_ID = "2017082208318924";
        String AliPayPARTNER = "2088721902958131";//支付宝 商户PID
        String AliPaySELLER = "xf1hsc@163.com";//支付宝 商户收款账号

    }
}