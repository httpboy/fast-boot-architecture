package com.fast.email;

/**
 * @Author: lyq
 * @Date: 2020/9/20 0:37
 */
public class CommonConstant {
    public final static int STATUS_IN_USE = 1;
    public final static int STATUS_OUT_USE = 0;
    public final static int STATUS_USER_OUT_USE = 0;
    public final static int STATUS_USER_IN_USE = 1;
    public final static int STATUS_PIC_HAS_READ = 1;
    public final static int STATUS_PIC_NO_READ = 0;

    public final static int TYPE_CLIENT_XCX = 0;//客户端-微信小程序
    public final static int TYPE_CLIENT_APP = 1;//客户端-app
    public final static int TYPE_CARD_NORMAL = 0;//图片增强类型：普通增强
    public final static int TYPE_CARD_PRO = 1;//图片增强类型：Pro卡增强
    public final static int TYPE_PIC_DEAL_SUCCESS = 2;
    public final static int TYPE_PIC_DEAL_FAIL = 1;
    public final static int TYPE_PIC_DEAL_ING = 0;
    public final static int TYPE_PAY_DEAL_SUCCESS = 2;
    public final static int TYPE_PAY_DEAL_FAIL = 1;
    public final static int TYPE_PAY_DEAL_ING = 0;
    public final static int TYPE_CARD_DELETE = 0;
    public final static int TYPE_CARD_ADD = 1;
    public final static int TYPE_PIC_BANNER = 0;//Banner图
    public final static int TYPE_PIC_EXAMPLE = 1;//首页示例图
    public final static int TYPE_PIC_SUB_PAGE_EXAMPLE = 2;//二级页面示例对比图
    public final static int TYPE_PIC_SUB_PAGE_EXAMPLE_SUIT = 3;//二级页面示例图（适合智能修复）
    public final static int TYPE_PIC_SUB_PAGE_EXAMPLE_NO_SUIT = 4;//二级页面示例图（不适合只能修复）
    public final static int TYPE_PIC_MASTER_EXAMPLE = 5;//大师精修首页对比图示例图
    public final static int TYPE_RECHARGE_OPTION_PRO = 0;//充值选项-Pro卡
    public final static int TYPE_RECHARGE_OPTION_NORMAL = 1;//充值选项-普通卡
    public final static int NUM_FREE_CARD = 10;//默认免费额度（若配置表没有配置就用默认的值）
    public final static int NUM_FREE_CARD_USE_PER_TIME = 2;//默认普通增强每次消费额度（若配置表没有配置就用默认的值）
    public final static int NUM_PRO_CARD_USE_PER_TIME = 3;//默认Pro卡增强每次消费额度（若配置表没有配置就用默认的值）
    public final static int NUM_SMS_SEND_PER_DAY = 100;//限制每日同个手机号最多发送短信的条数
    public final static int TYPE_DETAIL_PAY_WECHAT = 0;//Pro卡微信支付
    public final static int TYPE_DETAIL_PAY_ZFB = 1;//Pro卡支付宝支付
    public final static int TYPE_DETAIL_PAY_PAYPAL = 22;//Pro卡支付宝支付
    public final static int TYPE_DETAIL_PAY_GOOGLE = 33;//Pro卡支付宝支付
    public final static int TYPE_DETAIL_PAY_APPLE = 44;//Pro卡支付宝支付
    public final static int TYPE_DETAIL_PAY_APPLE_INNER = 5;//苹果内购支付
    public final static int TYPE_DETAIL_USE_PRO_CARD = 2;//Pro卡消耗
    public final static int TYPE_DETAIL_USE_NORMAL_CARD = 3;//消耗免费额度
    public final static int TYPE_DETAIL_USE_FAIL_RETURN = 4;//增强失败返还额度
    public final static int STATUS_MASTER_HANDLE_ORDER_ING = 0;//大师精修订单未完成
    public final static int STATUS_MASTER_HANDLE_ORDER_END = 1;//大师精修订单已完成
    public final static int STATUS_MASTER_HANDLE_ORDER_CANCEL = 2;//大师精修订单已取消
    public final static int TYPE_MASTER_HANDLE_ORDER_ORIGIN = 0;//用户上传的需要大师精修的原图
    public final static int TYPE_MASTER_HANDLE_ORDER_WATERMARK = 1;//含有水印的精修图
    public final static int TYPE_MASTER_HANDLE_ORDER_FINAL = 2;//最终没有水印的精修图
    public final static int TYPE_PAY_BUSINESS_AI_RECHARGE = 0;//支付的业务类型 ：普通卡充值
    public final static int TYPE_PAY_BUSINESS_PRO_RECHARGE = 1;//支付的业务类型 ：Pro卡充值
    public final static int TYPE_PAY_BUSINESS_ORDER_PRICE = 2;//支付的业务类型 ： 2：大师精修支付订金
    public final static int TYPE_PAY_BUSINESS_LEFT_PRICE = 3;//支付的业务类型 ： 3：大师精修支付尾款
    public final static int STATUS_MASTER_HANDLE_PAY_NOT_START = 0;//大师精修订金或尾款支付未开始
    public final static int STATUS_MASTER_HANDLE_PAY_NOT_PAY = 1;//大师精修订金或尾款支付未支付
    public final static int STATUS_MASTER_HANDLE_PAY_HAS_PAY = 2;//大师精修订金或尾款支付已支付
    public final static int STATUS_MASTER_HANDLE_NOT_OFFER_PRICE = 0;//大师精修订单未报价
    public final static int STATUS_MASTER_HANDLE_HAS_OFFER_PRICE = 1;//大师精修订单已报价
    public final static int RESULT_ENHANCE_SUCCESS = 1;//图片增强成功
    public final static int RESULT_ENHANCE_FAIL = 0;//图片增强失败
    public final static int RESULT_RETURN_FREECARD = 1;//已将失败的额度返还
    public final static int RESULT_NOT_RETURN_FREECARD = 0;//未将失败的额度返还
    public final static String TIP_USE_PRO_CARD = "Use the Pro card to enhance consumption %s credit";
    //public final static String TIP_USE_NORMAL_CARD="使用普通图片增强消耗%s额度";
    public final static String TIP_USE_NORMAL_CARD = "Use AI card to enhance consumption %s credit";
    public final static String TIP_ENHANCE_FAIL_RETURN = "Enhancement failure returns %s credit";
    public final static String TIP_BUY_PRO_CARD = "buy %s Pro card";
    public final static String TIP_BUY_COMMON_CARD = "buy %s AI card";
    public final static String REDIS_KEY_EMAIL_CODE_FOR_FORGET_PWD_VERIFY_CODE = "#forgetPwdEmailCode";
    public final static String REDIS_KEY_EMAIL_CODE_CREATE_USER_BY_APP_VERIFY_CODE = "#createUserByAppEmailCode";
    public final static String REDIS_KEY_EMAIL_CODE_MAX_TIME_PER_DAY = "#email#count";
    public final static String REDIS_KEY_EMAIL_CODE_LAST_TIME_GET_VERIFY_CODE = "#lastTimeGetVerifyCode";//上次获取验证码的时间
    public final static String CALLBACK_TASK = "CALLBACK_TASK";
    public final static String REDIS_LOCK_DEAL_UNDO = "LOCK_DEAL_UNDO";
    public final static String REDIS_LOCK_DELETE_OVER_TIME = "LOCK_DELETE_OVER_TIME";
    public final static String REDIS_LOCK_PAYMENT_FAIL_TASK = "LOCK_PAYMENT_FAIL_TASK";//paypal支付的补偿操作
    public final static Integer REDIS_VERIFICATION_CODE_TIME_OUT = 30 * 60;//邮箱验证码过期时间为30分钟
    public final static Integer REDIS_TOKEN_TIME_OUT = 30 * 24 * 60 * 60;//TOKEN 设置过期时间为一个月
    public final static Integer REDIS_KEY_EMAIL_MAX_TIME_SEND_PER_DAY = 10;//邮箱每日发送次数

    public final static String EMAIL_REGISTER_TITLE = "PicMa registration verification";//发送注册验证码邮箱标题
    public final static String EMAIL_RESET_PASS_TITLE = "Reset your account's password";//发送忘记验证码邮箱标题
    //发送注册验证码邮箱内容
    public final static String EMAIL_REGISTER_CONTENT_TITLE = "Thank you for registering PicMa.";
    public final static String EMAIL_REGISTER_CONTENT_SUB_TITLE = "Please use the following code to confirm your email address.";
    //发送忘记验证码邮箱内容
    public final static String EMAIL_RESET_PASS_CONTENT_TITLE = "You are resetting your PicMa account's password.";
    public final static String EMAIL_RESET_PASS_CONTENT_SUB_TITLE = "Please find the verification code below and put it in the app.";

}
