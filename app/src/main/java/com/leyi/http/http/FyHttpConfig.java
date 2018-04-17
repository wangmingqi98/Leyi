package com.leyi.http.http;

import android.content.Context;

public final class FyHttpConfig {
	private Context context;
	private boolean release = false;
	private static FyHttpConfig instance;

	public static final String RSP_CODE_SUCCESS = "0000";
	public static final String RSP_CODE = "Rcd";
	public static final String RSP_DESC = "RDesc";

	private static synchronized void syncInit() {
		if (instance == null) {
			instance = new FyHttpConfig();
		}
	}

	public static FyHttpConfig getInstance() {
		if (instance == null) {
			syncInit();
		}
		return instance;
	}

	public void setRelease(boolean release) {
		this.release = release;
	}

	public boolean isRelease() {
		return release;
	}

	String getBaseURL() {
//		return "http://192.168.199.188:8080/mobile_pay/findPay/";
//		return "https://mpay.fuiou.com:16128/findPay/";
//		return this.release ? "https://mpay.fuiou.com:16128/sdkpay/"
//		return "http://www-1.fuiou.com:18670/mobile_pay/findPay/";//外网
//		return "http://192.168.8.29:29024/mobile_pay/";//内网

		return this.release?"https://mpay.fuiou.com:16128/"
				:
				"http://www-1.fuiou.com:18670/mobile_pay/"
//				"http://www-1.fuiou.com:18670/mobile_pay/findPay/"
				;//商户测试的生产环境
//		return this.release?"https://mpay.fuiou.com:16128/":"http://192.168.8.28:8670/mobile_pay/findPay/";
	}

}
