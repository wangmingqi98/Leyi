package com.leyijf.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fuiou.mobile.FyPay;
import com.fuiou.mobile.util.AppConfig;
import com.fuiou.mobile.util.DialogUtils;
import com.fuiou.mobile.util.EncryptUtils;
import com.fuiou.mobile.wxapi.FyWeChatPayCallBack;
import com.leyijf.R;
import com.leyijf.view.MainActivity;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 微信入口，此界面根据微信需要需要在项目重写，商户可以自行重写适应自己的设计，这里只是举例
 * @author fuiou
 *
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler,
		OnClickListener {

	private EditText MchNtCd, UserId, Amt, MchntOrdId;
	private String orderNo;
	private Button weixin_pay;
	private String mMchnt_Key = "fx45crzkmo8akn24plwvrv8ywd10zjuy";
	private String mURL = "";
	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_result);
		mURL = "http://180.168.100.155:14652/mobile_pay/applePay/backNotify.pay";
		FyPay.setDev(false);//商户对接微信时这个必须要设置,true为对接的生产环境

		AppConfig.appid = "wx1a739265f24c673d";//外部設置appid，由商戶从微信方获取设置，目前填的是富友demo自己的appid
		api = WXAPIFactory.createWXAPI(this, AppConfig.appid, true);
		api.handleIntent(getIntent(), WXPayEntryActivity.this);

		api.registerApp(AppConfig.appid);
		initView();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, WXPayEntryActivity.this);
	}

	private void initView() {
		MchNtCd = (EditText) findViewById(R.id.MchNtCd);
		UserId = (EditText) findViewById(R.id.UserId);
		Amt = (EditText) findViewById(R.id.Amt);
		MchntOrdId = (EditText) findViewById(R.id.MchntOrdId);
		
		int n = 0;
		while (n < 100000) {
			n = (int) (Math.random() * 1000000);
		}
		String random = "1234567890" + n;
		MchntOrdId.setText(random);

		weixin_pay = (Button) findViewById(R.id.weixin_pay);
		weixin_pay.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.weixin_pay:
			WeChatPay();
			break;
		}
	}

	/**
	 * 微信支付
	 * 
	 */
	public void WeChatPay() {
		if(!checkValueIsValid()){
			return;
		}

		String sign = MchNtCd.getText().toString() + "|"
				+ MchntOrdId.getText().toString() + "|"
				+ UserId.getText().toString() + "|" + Amt.getText().toString()
				+ "|" + mURL + "|" + "08" + "|" + "2.0" + "|" + mMchnt_Key;

		
		String MD5sign = EncryptUtils.md5Encrypt(sign.trim()).toLowerCase();

		
		//商户需自己拿到交易金额，商户id,用户id,订单号。其他值不变
		Bundle bundle = new Bundle();
		bundle.putString(FyPay.KEY_ACTUAL_MONEY, Amt.getText().toString());//交易金额
		bundle.putString(FyPay.KEY_MCHNT_CD, MchNtCd.getText().toString());//商户id
		bundle.putString(FyPay.KEY_MCHNT_ORDER_NO, MchntOrdId.getText().toString());//商户获取订单号塞到这里
		bundle.putString(FyPay.KEY_MCHNT_USER_ID, UserId.getText().toString());//用户id
		bundle.putString(FyPay.KEY_BACKURL, mURL);
		bundle.putString(FyPay.KEY_VERSION, "2.0");
		bundle.putString(FyPay.KEY_TYPE, "08");
		bundle.putString(FyPay.KEY_SIGN, MD5sign);
		bundle.putString(FyPay.KEY_PAY_TYPE, "weixin");

		int i = FyPay.WeChatPay(this, bundle, new FyWeChatPayCallBack() {


			@Override
			public void onWeChatPayComplete(String rspCode, String rspDesc,
					PayReq req) {
				
				//检测微信版本是否支持微信支付
				boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
				if (isPaySupported == false) {
					DialogUtils.showDialog(WXPayEntryActivity.this,
							"本版本微信不支持微信支付，请升级到最新版本！");
					return;
				}
				api.sendReq(req);//向微信发送请求
			}
		});
	}

	//必传参数校验
	private boolean checkValueIsValid() {
		if (MchNtCd.getText().toString() == null
				|| "".equals(MchNtCd.getText().toString())) {
			DialogUtils.showDialog(this, "请输入正确的商户号");
			return false;
		} else if (Amt.getText().toString() == null
				|| "".equals(Amt.getText().toString())
				|| Amt.getText().toString().contains(".") || Amt.getText().toString().contains("-")) {
			DialogUtils.showDialog(this, "请输入正确的金额");
			return false;
		} else if (MchntOrdId.getText().toString() == null
				|| "".equals(MchntOrdId.getText().toString())) {
			DialogUtils.showDialog(this, "请输入正确的商户订单号");
			return false;
		} else if (MchNtCd.getText().toString() == null
				|| "".equals(MchNtCd.getText().toString())) {
			DialogUtils.showDialog(this, "请输入正确的用户编号");
			return false;
		}
		return true;
	}

	@Override
	public void onReq(BaseReq req) {
		Toast.makeText(this, "openid = " + req.openId, Toast.LENGTH_SHORT)
				.show();
	}

	
	//微信响应信息，商户根据微信的响应信息做后续界面处理,响应代码分为0 -1 -2
	@Override
	public void onResp(BaseResp resp) {
		
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			Intent intent = new Intent(WXPayEntryActivity.this,
					MainActivity.class);
			intent.putExtra("PAYCODE", resp.errCode + "");
			startActivity(intent);
			this.finish();
		}
	}
}
