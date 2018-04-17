package com.leyi.bean;

/**
 * Created by Administrator on 2018/3/29.
 */

public class CodeBean {

    public static String payResult = "";

    public static String thePayResult(String code) {
        if (code.equals("0000")) {

            payResult = "支付成功！";
        } else if (code.equals("1004")) {

            payResult = "无签约信息，不能发起付款！";

        } else if (code.equals("1008")) {

            payResult = "短信发送过于频繁，请稍后再试！";

        } else if (code.equals("1010")) {

            payResult = "错误次数超限，请核对卡信息稍后再试！";

        } else if (code.equals("1040")) {

            payResult = "请求的功能，尚不支持！";

        } else if (code.equals("1096")) {

            payResult = "系统异常，失效！";

        } else if (code.equals("5185")) {

            payResult = "无签约信息，不能发起付款！";

        } else if (code.equals("8110")) {

            payResult = "验证码次数超限！";

        } else if (code.equals("8143")) {

            payResult = "验证码是失效错误！";

        } else if (code.equals("9999")) {

            payResult = "证件号错误！";

        } else if (code.equals("0001")) {

            payResult = "发送失败！";

        } else if (code.equals("0020")) {

            payResult = "手机号信息非法！";

        } else if (code.equals("0021")) {

            payResult = "订单号重复！";

        } else if (code.equals("0030")) {

            payResult = "报文内容信息非法！";

        } else if (code.equals("10FC")) {

            payResult = "借记卡单笔交易金额超限！";

        } else if (code.equals("10FD")) {

            payResult = "同卡金额超限！";

        } else if (code.equals("10FE")) {

            payResult = "贷记卡单笔交易金额超限！";

        } else if (code.equals("10M1")) {

            payResult = "超出借记卡同商户单日交易累计金额限制！";

        } else if (code.equals("10M2")) {

            payResult = "超出借记卡同商户当月交易累计金额限制！";

        } else if (code.equals("10SM")) {

            payResult = "超过金额限制！";

        } else if (code.equals("51B3")) {

            payResult = "订单支付中，请勿重复支付！";

        } else if (code.equals("51B3")) {

            payResult = "订单支付中，请勿重复支付！";

        } else if (code.equals("51B3")) {

            payResult = "订单支付中，请勿重复支付！";

        } else if (code.equals("200012")) {

            payResult = "报文格式错误！";

        } else if (code.equals("200013")) {

            payResult = "持卡人身份信息或手机号与银行预留不一致，或未开通银联在线功能！";

        } else if (code.equals("200014")) {

            payResult = "银行卡未开通银联在线功能或为不支持的卡！";

        } else if (code.equals("200015")) {

            payResult = "卡状态不正常！";

        } else if (code.equals("200016")) {

            payResult = "无效卡！";

        } else if (code.equals("200017")) {

            payResult = "银行卡账户余额不足！";

        } else if (code.equals("200023")) {

            payResult = "银联风险受限！";

        } else if (code.equals("200029")) {

            payResult = "交易金额超限！";

        } else if (code.equals("200098")) {

            payResult = "交易超时！";

        } else if (code.equals("999998")) {

            payResult = "交易失败，详情请咨询您的发卡行！";

        } else {
            payResult = "系统异常！";
        }


        return payResult;
    }

}


//       switch (code){
//           case 1004:
//
//               break;
//           case 1005:
//
//               break;
//           case 1008:
//
//               break;
//           case 1010:
//
//               break;
//           case 1040:
//
//               break;
//           case 1096:
//
//               break;
//           case 5185:
//
//               break;
//           case 8110:
//
//               break;
//           case 8143:
//
//               break;
//           case 9999:
//
//               break;
//           case 0001:
//
//               break;
//           case 0020:
//
//               break;
//
//           case 0021:
//
//               break;
//           case 0030:
//
//               break;
//           case 10FC:
//
//               break;
//           case 10FD:
//
//               break;
//           case 10FE:
//
//               break;
//           case 10M1:
//
//               break;
//           case 10M2:
//
//               break;
//           case 10SM:
//
//               break;
//           case 51B3:
//
//               break;
//           case 200012:
//
//               break;
//           case 200013:
//
//               break;
//           case 200014:
//
//               break;
//           case 200015:
//
//               break;
//           case 200016:
//
//               break;
//           case 200017:
//
//               break;
//           case 200023:
//
//               break;
//           case 200029:
//
//               break;
//           case 200098:
//
//               break;
//           case 999998:
//
//               break;
//           case 999999:
//
//               break;







//       }








