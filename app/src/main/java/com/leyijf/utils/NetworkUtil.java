package com.leyijf.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetworkUtil {
	private static NetworkUtil instance;

	private NetworkUtil() {
	}

	public static synchronized NetworkUtil getInstance() {
		NetworkUtil networkUtil;
		synchronized (NetworkUtil.class) {
			if (instance == null) {
				instance = new NetworkUtil();
			}
			networkUtil = instance;
		}
		return networkUtil;
	}

	public static boolean isFastMobileNetwork(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		switch (telephonyManager.getNetworkType()) {
		case TelephonyManager.NETWORK_TYPE_1xRTT:
			return false; // ~ 50-100 kbps
		case TelephonyManager.NETWORK_TYPE_CDMA:
			return false; // ~ 14-64 kbps
		case TelephonyManager.NETWORK_TYPE_EDGE:
			return false; // ~ 50-100 kbps
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
			return true; // ~ 400-1000 kbps
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
			return true; // ~ 600-1400 kbps
		case TelephonyManager.NETWORK_TYPE_GPRS:
			return false; // ~ 100 kbps
		case TelephonyManager.NETWORK_TYPE_HSDPA:
			return true; // ~ 2-14 Mbps
		case TelephonyManager.NETWORK_TYPE_HSPA:
			return true; // ~ 700-1700 kbps
		case TelephonyManager.NETWORK_TYPE_HSUPA:
			return true; // ~ 1-23 Mbps
		case TelephonyManager.NETWORK_TYPE_UMTS:
			return true; // ~ 400-7000 kbps
		case TelephonyManager.NETWORK_TYPE_EHRPD:
			return true; // ~ 1-2 Mbps
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
			return true; // ~ 5 Mbps
		case TelephonyManager.NETWORK_TYPE_HSPAP:
			return true; // ~ 10-20 Mbps
		case TelephonyManager.NETWORK_TYPE_IDEN:
			return false; // ~25 kbps
		case TelephonyManager.NETWORK_TYPE_LTE:
			return true; // ~ 10+ Mbps
		case TelephonyManager.NETWORK_TYPE_UNKNOWN:
			return false;
		default:
			return false;
		}
	}

	public NetworkTypeEnum getNetWorkType(Context context) {
		NetworkTypeEnum networkType = NetworkTypeEnum.INVALID;
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
			networkType = NetworkTypeEnum.UNKNOWN;
			switch (networkInfo.getType()) {
			case ConnectivityManager.TYPE_WIFI:
				networkType = NetworkTypeEnum.WIFI;
				break;

			case ConnectivityManager.TYPE_MOBILE:
				networkType = NetworkTypeEnum.MONET;
				break;
			}

		} else {
			networkType = NetworkTypeEnum.INVALID;
		}
		return networkType;
	}

	public NetworkSubTypeEnum getMonetSubType(Context context) {

		NetworkTypeEnum networkType = NetworkTypeEnum.INVALID;
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
			networkType = NetworkTypeEnum.UNKNOWN;
			switch (networkInfo.getType()) {
			case ConnectivityManager.TYPE_WIFI:
				networkType = NetworkTypeEnum.WIFI;
				break;

			case ConnectivityManager.TYPE_MOBILE:
				networkType = NetworkTypeEnum.MONET;
				break;
			}

		} else {
			networkType = NetworkTypeEnum.INVALID;
		}

		switch (networkType) {
		case INVALID:
		case UNKNOWN:
		case WIFI:
			return NetworkSubTypeEnum.WIFI;
		case MONET:
		default:
			break;
		}

		switch (networkInfo.getSubtype()) {
		case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
		case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
		case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
		case TelephonyManager.NETWORK_TYPE_1xRTT:
		case TelephonyManager.NETWORK_TYPE_IDEN:
			return NetworkSubTypeEnum.G2;
		case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
		case TelephonyManager.NETWORK_TYPE_UMTS:
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
		case TelephonyManager.NETWORK_TYPE_HSDPA:
		case TelephonyManager.NETWORK_TYPE_HSUPA:
		case TelephonyManager.NETWORK_TYPE_HSPA:
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
		case TelephonyManager.NETWORK_TYPE_EHRPD:
		case TelephonyManager.NETWORK_TYPE_HSPAP:
			return NetworkSubTypeEnum.G3;
		case TelephonyManager.NETWORK_TYPE_LTE:
			return NetworkSubTypeEnum.G4;
		default:
			return NetworkSubTypeEnum.UNKNOWN;
		}

	}

	public boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public String getNetworkStateDesc(NetworkTypeEnum type) {
		switch (type) {
		case UNKNOWN:
			return "UNKOWN";
		case INVALID:
			return "INVALID";
		case WIFI:
			return "WIFI";
		case MONET:
			return "MONET";
		default:
			return "UNKOWN";
		}
	}

	public enum NetworkTypeEnum {
		INVALID, UNKNOWN, WIFI, MONET;

	}

	public enum NetworkSubTypeEnum {
		WIFI, UNKNOWN, G2, G3, G4
	}

}
