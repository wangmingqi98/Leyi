package com.leyijf.http.retrofit_rxjava_ok;


import com.leyijf.bean.AdvanceAlsoMoneyBean;
import com.leyijf.bean.AlsoMoneyBean;
import com.leyijf.bean.AssetsDetailBean;
import com.leyijf.bean.B_RepaymentDetailBean;
import com.leyijf.bean.BannerBean;
import com.leyijf.bean.BindBankCardBean;
import com.leyijf.bean.ComitGoodsBean;
import com.leyijf.bean.ComitNewPwdBean;
import com.leyijf.bean.CurrentMoneyBean;
import com.leyijf.bean.DealsLoanRepayBean;
import com.leyijf.bean.FinancialRepaymentDetailBean;
import com.leyijf.bean.FyPayBean;
import com.leyijf.bean.GetCodeModel;
import com.leyijf.bean.GetPrpfitBean;
import com.leyijf.bean.GetTrurhNameBean;
import com.leyijf.bean.GetUseMoneyBean;
import com.leyijf.bean.GongGao;
import com.leyijf.bean.HotActiveBean;
import com.leyijf.bean.InItBean;
import com.leyijf.bean.LoginBean;
import com.leyijf.bean.LoginOutBean;
import com.leyijf.bean.LookDetailBean;
import com.leyijf.bean.MortGageInfoBean;
import com.leyijf.bean.MyBorrowMoneyBean;
import com.leyijf.bean.MyInvestBean;
import com.leyijf.bean.MyRedPacketsBean;
import com.leyijf.bean.NewsDetailsBean;
import com.leyijf.bean.PayBean;
import com.leyijf.bean.PersonalInformationBean;
import com.leyijf.bean.QuickRefundBean;
import com.leyijf.bean.RateCouponBean;
import com.leyijf.bean.RedPaketsBean;
import com.leyijf.bean.RegisterBean;
import com.leyijf.bean.RepaymentClasificationBean;
import com.leyijf.bean.RepaymentDetailBean;
import com.leyijf.bean.RepaymentListBean;
import com.leyijf.bean.SetPasswordCodeBean;
import com.leyijf.bean.SetPayPwdBean;
import com.leyijf.bean.StatisticsBean;
import com.leyijf.bean.SureGoodsBean;
import com.leyijf.bean.SureInvestGoods;
import com.leyijf.bean.TouziRenshuBean;
import com.leyijf.bean.TradingRecordBean;
import com.leyijf.bean.TransactionRecordsBean;
import com.leyijf.bean.TrurhNameBean;
import com.leyijf.bean.UpLoadImageBean;
import com.leyijf.bean.UploadPhoneBean;
import com.leyijf.bean.UserInfo;
import com.leyijf.bean.UsesRateBean;
import com.leyijf.bean.VerifyCodeBean;
import com.leyijf.bean.VerifyReponseBean;
import com.leyijf.bean.VersionUpdateBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * For Retrofit
 * Created by wmq on 2017/6/23.
 */
public interface RetrofitService {
//  public static final String BASE_URL = "http://www.uniqgant.com/";
    public  static final String BASEURL="http://leyibank.com//mapi/";
    public  static final String BASEIMG="http://leyibank.com//";
    public  static final String ACT="index.php?";
    public  static final String TYPE="&r_type=1";//返回的数据类型

    //注册---发送验证码
    @GET("index.php?act=send_register_code&r_type=1&i_type=4")
    Observable<BaseEntity<SetPasswordCodeBean>> register(
            @Query("requestData") String requestData
    );
    //注册---验证验证码
    @GET("index.php?act=auth_code_verify&r_type=1&i_type=4")
    Observable<BaseEntity<VerifyCodeBean>> registerVerify(
            @Query("requestData") String requestData

    );
    //注册---提交
    @GET("index.php?act=register&r_type=1&i_type=4")
    Observable<BaseEntity<RegisterBean>> registerComit(
            @Query("requestData") String requestData
    );
    //忘记密码---发送验证码
    @GET("index.php?act=auth_forget_first&r_type=1&i_type=4")
    Observable<BaseEntity<SetPasswordCodeBean>>forgetCode(
            @Query("requestData") String requestData
    );
    //忘记密码--提交
    @GET("index.php?act=auth_forget_confirm&r_type=1&i_type=4")
    Observable<BaseEntity<RegisterBean>>forgetPwdComit(
            @Query("requestData") String requestData
    );
    //登陆
    @GET("index.php?act=login&r_type=1")
    Observable<BaseEntity<LoginBean>> login(
            @Query("email") String userId,
            @Query("pwd") String password
    );
    //设置头像
    @Multipart
    @POST("index.php?act=uc_center_upload_image&r_type=1&i_type=4")
    Observable<BaseEntity<UpLoadImageBean>>upLoadImage(
            @Part() List<MultipartBody.Part> files);

    //实名认证
    @GET("index.php?act=uc_center_certificate&r_type=1&i_type=4")
    Observable<BaseEntity<TrurhNameBean>> trurhName(
            @Query("requestData") String requestData
    );
    //获取实名认证信息
    @GET("index.php?act=uc_info_query&r_type=1&i_type=4")
    Observable<BaseEntity<GetTrurhNameBean>> getTrurhName(
            @Query("requestData") String requestData
    );

    //更换手机号身份验证
    @GET("index.php?act=uc_identify_vertification&r_type=1&i_type=4")
    Observable<BaseEntity<VerifyReponseBean>> verify(
            @Query("requestData") String requestData

    );

    //获取个人信息接口
    @GET("index.php?act=uc_center&r_type=1&i_type=4")
    Observable<BaseEntity<PersonalInformationBean>> getPersonalInformation(
//            @Query("requestData") String requestData
    );
    //修改手机号---检测新手机号是否注册并发送验证码
    @GET("index.php?act=uc_check_mobile&r_type=1&i_type=4")
    Observable<BaseEntity<GetCodeModel>>getCode(
            @Query("requestData") String requestData
    );
    //修改手机
    @GET("index.php?act=uc_change_mobile&r_type=1&i_type=4")
    Observable<BaseEntity<UploadPhoneBean>>uploadPhoneNum(
            @Query("requestData") String requestData
//            @Query("token") String token

    );
    //设置登录密码---发送验证码
    @GET("index.php?act=send_certificate_code&r_type=1&i_type=4")
    Observable<BaseEntity<SetPasswordCodeBean>>getLoginCode(
            @Query("requestData") String requestData
    );
    //设置登录密码---验证验证码
    @GET("index.php?act=verify_certificate_code&r_type=1&i_type=4")

    Observable<BaseEntity<VerifyCodeBean>>verifyCode(
            @Query("requestData") String requestData
    );

    //设置登录密码---提交
    @GET("index.php?act=uc_change_loginpwd&r_type=1&i_type=4")
    Observable<BaseEntity<ComitNewPwdBean>>changeLoginpwd(
            @Query("requestData") String requestData
    );
    //设置交易密码---提交
    @GET("index.php?act=uc_change_tradepwd&r_type=1&i_type=4")
    Observable<BaseEntity<SetPayPwdBean>>setPaypwd(
            @Query("requestData") String requestData
    );
    //交易记录
    @GET("index.php?act=uc_account_log&r_type=1&i_type=4")
    Observable<BaseEntity<TransactionRecordsBean>>tradingRecord(
            @Query("requestData") String requestData
    );
    //我的红包
    @GET("index.php?act=uc_vouchers_list&r_type=1&i_type=4")
    Observable<BaseEntity<MyRedPacketsBean>> redPackets(
            @Query("requestData") String requestData
    );
    //我的加息劵
    @GET("index.php?act=uc_rates_list&r_type=1&i_type=4")
    Observable<BaseEntity<RateCouponBean>>rateCounpon(
            @Query("requestData") String requestData
    );
    //退出登录
    @GET("index.php?act=logout&r_type=1&i_type=4")
    Observable<BaseEntity<LoginOutBean>>loginOut(
            @Query("requestData") String requestData
    );

    //热门活动---列表
    @GET("index.php?act=uc_hot_activity&r_type=1")
    Observable<BaseEntity<HotActiveBean>>hotActivity();
    //公司公告
    @GET("index.php?act=website_notice&r_type=1")
    Observable<BaseEntity<GongGao>>companyNews();
    //公司公告 ---详情
    @GET("index.php?act=website_notice_detail&r_type=1")
    Observable<BaseEntity<NewsDetailsBean>>newsDetails(
            @Query("article_id") String articleId
    );
    //我的投资
    @GET("index.php?act=uc_invest_list&r_type=1&i_type=4")
    Observable<BaseEntity<MyInvestBean>>myInvest(
            @Query("requestData") String requestData
    );
    //我的投资--还款详情
    @GET("index.php?act=uc_invest_quick_refund&r_type=1&i_type=4")
    Observable<BaseEntity<RepaymentDetailBean>>repaymentDetail(
            @Query("requestData") String requestData
    );
    //我的借款
    @GET("index.php?act=uc_borrowed&r_type=1&i_type=4")
    Observable<BaseEntity<MyBorrowMoneyBean>> borrowMoney(
            @Query("requestData") String requestData
    );
    //我的借款--还款详情
    @GET("index.php?act=uc_quick_refund&r_type=1&i_type=4")
    Observable<BaseEntity<B_RepaymentDetailBean>>B_repaymentDetail(
            @Query("requestData") String requestData
    );
    //我的借款--提前还款
    @GET("index.php?act=uc_inrepay_refund&r_type=1&i_type=4")
    Observable<BaseEntity<AdvanceAlsoMoneyBean>>advanceAlsoMoney(
            @Query("requestData") String requestData
    );
    //我的借款--查看明细
    @GET("index.php?act=uc_quick_refund_detail&r_type=1&i_type=4")
    Observable<BaseEntity<LookDetailBean>>lookDetail(
            @Query("requestData") String requestData
    );
    //我的借款--立即还款
    @GET("index.php?act=uc_do_quick_refund&r_type=1&i_type=4")
    Observable<BaseEntity<QuickRefundBean>>quickRefund(
            @Query("requestData") String requestData
    );
    //提前还款---提交
    @GET("index.php?act=uc_do_inrepay_refund&r_type=1&i_type=4")
    Observable<BaseEntity<AlsoMoneyBean>>alsoMoneyComit(
            @Query("requestData") String requestData
    );
    //我的资产--资产详情与收益统计
    @GET("index.php?act=uc_assets_profits&r_type=1&i_type=4")
    Observable<BaseEntity<StatisticsBean>>assetsDetail(
            @Query("requestData") String requestData
    );
    @GET("index.php?act=uc_assets_profits&r_type=1&i_type=4")
    Observable<BaseEntity<AssetsDetailBean>>assetsDetails(
            @Query("requestData") String requestData
    );
    //理财超市--获取借款分类
    @GET("index.php?act=v2_deals_category&r_type=1&i_type=4")
    Observable<BaseEntity<RepaymentClasificationBean>>getRepaymentClassification();
    //理财超市--获取借款列表
    @GET("index.php?act=v2_deals_index&r_type=1&i_type=4")
    Observable<BaseEntity<RepaymentListBean>>getRepaymentList(
            @Query("requestData") String requestData
    );
    //理财超市--获取借款详情
    @GET("index.php?act=deals_detail&r_type=1&i_type=4")
    Observable<BaseEntity<FinancialRepaymentDetailBean>>getRepaymentDetail(
            @Query("requestData") String requestData
    );
//    理财超市---合同与抵押物
    @GET("index.php?act=v2_deals_mortgage_info&r_type=1&i_type=4")
    Observable<BaseEntity<MortGageInfoBean>>getMortgageInfo(
            @Query("requestData") String requestData
    );
    //理财超市--获取还款详情
    @GET("index.php?act=v2_deals_loan_repay_list&r_type=1&i_type=4")
    Observable<BaseEntity<DealsLoanRepayBean>>getDealsloanRepay(
            @Query("requestData") String requestData
    );
    //理财超市--投资人数
    @GET("index.php?act=v2_deals_buy_record&r_type=1&i_type=4")
    Observable<BaseEntity<TouziRenshuBean>>getDealsPeopleNum(
            @Query("requestData") String requestData
    );
    //获取首页轮播及公告
     @GET("index.php?act=best_select_banner&r_type=1")
     Observable<BaseEntity<BannerBean>>getBanner(
             @Query("is_app") int is_app
     );
     //首页借款列表
    @GET("index.php?act=best_select_recomond&r_type=1")
    Observable<BaseEntity<InItBean>>getRecomond();
    //立即投资----确认订单
    @GET("index.php?act=deals_confirm_order&r_type=1&i_type=4")
    Observable<BaseEntity<SureGoodsBean>>sureGoods(
            @Query("requestData") String requestData
    );
    //立即投资----确认投资订单
    @GET("index.php?act=deals_confirm_pay&r_type=1&i_type=4")
    Observable<BaseEntity<SureInvestGoods>>sureInvestGoods(
            @Query("requestData") String requestData
    );
    //立即投资----获取可用红包
    @GET("index.php?act=deals_user_voucher&r_type=1&i_type=4")
    Observable<BaseEntity<RedPaketsBean>>getRedPakets(
            @Query("requestData") String requestData
    );
    //立即投资----获取可用加息券
    @GET("index.php?act=deals_user_rate&r_type=1&i_type=4")
    Observable<BaseEntity<UsesRateBean>>getRate(
            @Query("requestData") String requestData
    );
    //立即投资----提交订单
    @GET("index.php?act=deals_check_paypwd&r_type=1&i_type=4")
    Observable<BaseEntity<ComitGoodsBean>>comitGoods(
            @Query("requestData") String requestData
    );
    //绑定银行卡
    @GET("index.php?act=uc_bind_bank&r_type=1&i_type=4")
    Observable<BaseEntity<BindBankCardBean>> bindBankCard(
            @Query("requestData") String requestData
    );
    //充值----确认用户充值信息
    @GET("index.php?act=uc_recharge_app&r_type=1&i_type=4")
    Observable<BaseEntity<PayBean>>surePay(
            @Query("requestData") String requestData
    );
    //充值---获取富友请求信息
    @GET("index.php?act=uc_recharge_h5&r_type=1")
    Observable<BaseEntity<FyPayBean>>pay(
            @Query("user_id") String user_id,
            @Query("user_real_name") String user_real_name,
            @Query("user_idno") String user_idno,
            @Query("user_bankcard") String user_bankcard,
            @Query("recharge_money") String recharge_money,
            @Query("mchnt_orderid") String mchnt_orderid,
            @Query("back_url") String back_url
    );
    //提现---获取用户可用余额
    @GET("index.php?act=uc_center_current_money&r_type=1&i_type=4")
    Observable<BaseEntity<GetUseMoneyBean>>getUseMoney(
            @Query("requestData") String requestData
    );
    //提现---验证交易密码并提交
    @GET("index.php?act=uc_save_carry&r_type=1&i_type=4")
    Observable<BaseEntity<CurrentMoneyBean>>currentMoney(
            @Query("requestData") String requestData
    );
    //收益计算器
    @GET("index.php?act=calc_bid&r_type=1&i_type=4")
    Observable<BaseEntity<GetPrpfitBean>>getProfit(
            @Query("requestData") String requestData
    );
    //版本更新
    @GET("index.php?act=version&r_type=1&i_type=4")
    Observable<BaseEntity<VersionUpdateBean>>versionUpdate(
            @Query("requestData") String requestData
    );
    //热门活动
//    @GET("index.php?act=uc_hot_activity&r_type=1")
//    Observable<BaseEntity<>>hotActive();
//
//    //第三方登陆
//    //type int: 1:facebook 2:google
//    //access_token string
//    //input_token  string
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("login/userlogin")
//    Observable<ThridLoginBean> thridLogin(
//            @Query("type") int type,
//            @Query("access_token") String accesstoken,
//            @Query("input_token") String input_token,
//            @Query("phone") String phonenum,
//            @Query("Email") String email,
//            @Query("headPortrait") String head
//    );
//
//    //注册
//    @GET("api/User/register")
//    Observable<SimpleBaseBean> register(
//            @Query("email") String email,
//            @Query("password1") String password1,
//            @Query("password2") String password2,
//            @Query("mobile") String mobile,
//            @Query("user_from") String user_from,
//            @Query("deviceid") String deviceid,
//            @Query("devicename") String devicename,
//            @Query("country") String country
//    );
//
//    //主页
//    @Headers("Cache-Control: public, max-age=60")
//    @GET("product/GetProductIndexList")
//    Observable<MainBean> main(
//            @Query("PageSize") int pageSize,
//            @Query("PageIndex") int pageIndex,
//            @Query("userID") String uid,
//            @Query("isCollections") String is
//
//    );
//
//    //产品详情页
//    @GET("product/GetProductDetails")
//    Observable<DetailBean> detail(
//            @Query("productID") int proId
//    );
//
//    //添加购物车
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("product/ProductAddCart")
//    Observable<AddCardBean> addtocard(
//            @Query("usersID") String uid,
//            @Query("productID") int product_id
//    );
//
//
//    //获取购物车列表(設置緩存時間0秒)
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("product/GetProductCardList")
//    Observable<ShopCartStore> getShopCart(
//            @Query("usersID") String uid
//    );
//
//    //修改購物車商品數量
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("orders/ProductCartUpdateNumber")
//    Observable<BaseEntity> numchange(
//            @Query("ordersID") String cart_id,
//            @Query("Number") int num
//    );
//
//    //删除购物车商品
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("orders/ProductCartDelete")
//    Observable<BaseEntity> delCart(
//            @Query("ordersID") String cart_id
//    );
//
//    //确认订单
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("orders/OrdersSubmitPayment")
//    Observable<BaseEntity<List<ComfrimBean>>> confrimOrder(
//            @Query("uid") String uid,
//            @Query("ordersIDs") String cartids
//    );
//
//    //上传頭像图片
//    @Headers("Cache-Control: public, max-age=0")
//    @Multipart
//    @POST("users/UsersUploadPicture")
//    Observable<BaseEntity> uploadAvatar(
//            @Part("UsersID") RequestBody uid,
//            @Part MultipartBody.Part file
//    );
//
//    //添加商品
//    @Multipart
//    @POST("product/ProductAdd")
//    Observable<BaseEntity> uploadProduct(
//            @Part() List<MultipartBody.Part> files
//    );
//
//    //上傳圖片
//    @Multipart
//    @POST("product/ProductUploadImage")
//    Observable<List<UploadImgsBean>> uploadImages(
//            @Part() List<MultipartBody.Part> files
//    );
//
//    //刪除圖片
//    @GET("Api/Product/imgdel")
//    Observable<SimpleBaseBean> delImages(
//            @Query("uid") String uid,
//            @Query("imgurl") String imgurl);
//
//
//    //获取我的商品列表
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("product/GetProductList")
//    Observable<ProductsBean> getMyProducts(
//            @Query("PageSize") int pageSize,
//            @Query("PageIndex") int pageIndex,
//            @Query("userID") String uid
//    );
//
//    //删除商品
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("product/ProductDelete")
//    Observable<UploadProBean> delPro(
//            @Query("productID") String productID
//    );
//
//    //收藏功能
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("product/GetProductCollections")
//    Observable<UploadProBean> collect(
//            @Query("productID") int proid,
//            @Query("usersID") String uid,
//            @Query("Status") int status
//    );
//
//    //获取收藏列表
//    @Headers("Cache-Control: public, max-age=60")
//    @GET("product/GetCollectionsList")
//    Observable<CollectionBean> getCollect(
//            @Query("userID") String uid
//    );
//
//    //添加收货地址
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("orders/AddressAdd")
//    Observable<BaseEntity> addressAdd(
//            @Query("usersID") String usersID,
//            @Query("tel") String tel,
//            @Query("country") String country,
//            @Query("postCode") String postCode,
//            @Query("address") String address,
//            @Query("receiver") String receiver,
//            @Query("isDefault") int is
//    );
//
//    //获取收获地址列表
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("orders/GetShippingAddressList")
//    Observable<AddressListBean> getaddlist(
//            @Query("usersID") String uid
//    );
//
//    //获取确认订单详情页
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("orders/GetOrdersCheckOutList")
//    Observable<ConfimOrderBean> getOrder(
//            @Query("usersID") String uid,
//            @Query("ordersIDs") String ordersID
//    );
//
//    //paypal支付验证
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("orders/GetVerifyPayment")
//    Observable<BaseEntity> verifyPaypal(
//            @Query("Tx") String id
//    );
//
//    //获取买家收货列表查询
//    @Headers("Cache-Control: public, max-age=0")
//    @GET("orders/GetBuyerReceivingList")
//    Observable<ConfimOrderBean> getOrder2(
//            @Query("usersID") String uid
//    );
}
