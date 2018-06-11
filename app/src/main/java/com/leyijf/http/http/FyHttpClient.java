package com.leyijf.http.http;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.fuiou.mobile.util.FyLogUtil;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


/**
 * User: MengHX Date: 1/7/13 Time: 6:12 PM
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public final class FyHttpClient {

	private final static int RESPONSE_TYPE_TEXT = 0;
	private final static int RESPONSE_TYPE_JSON = 1;
	private final static int RESPONSE_TYPE_XML = 2;
	/** 返回流数据 */
	public final static int RESPONSE_TYPE_INPUTSTREAM = RESPONSE_TYPE_XML + 1;

	public static final String TAG = "tag";

	private static DefaultHttpClient client;
	private static BasicHttpContext httpContext;

	private static final int CONN_TIMEOUT = 40 * 1000;
	private static final int RECV_TIMEOUT = 80 * 1000;
	private static final int POOL_TIMEOUT = 10 * 1000;
	private static final String CHARSET = HTTP.UTF_8;
	private static final int PER_ROUTE = 100;
	private static final int MAX_CONNECT = 100;
	private static String Key;
	private static String  data_0;
	private static String data_1;
	private byte[] data_b_0;
	private byte[] data_b_1;
	private byte[]  rsaResult1;
	private static String rsaResult2;
	static {
		BasicHttpParams params = new BasicHttpParams();
		// http协议参数
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);
		// http连接参数
		HttpConnectionParams.setSoTimeout(params, RECV_TIMEOUT);
		HttpConnectionParams.setConnectionTimeout(params, CONN_TIMEOUT);
		// 注册http/https scheme
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory
				.getSocketFactory(), 443));
		// Conmanger参数设置
		ConnManagerParams.setMaxTotalConnections(params, MAX_CONNECT);
		ConnManagerParams.setMaxConnectionsPerRoute(params,
				new ConnPerRouteBean(PER_ROUTE));
		ConnManagerParams.setTimeout(params, POOL_TIMEOUT);
		// 实例化ClientManager
		ThreadSafeClientConnManager connManager = new ThreadSafeClientConnManager(
				params, schemeRegistry);
		client = new DefaultHttpClient(connManager, params);
		CookieStore cookieStore = new BasicCookieStore();
		client.setCookieStore(cookieStore);
		httpContext = new BasicHttpContext();
	}

	public static void getXMLWithPostUrl(String uri, HashMap datas,
			FyHttpInterface callback) {
		FyHttpClient.postURL(uri, FyHttpClient.RESPONSE_TYPE_XML, datas, callback);
	}

	public static void getJsonWithPostUrl(String uri, HashMap datas,
			FyHttpInterface callback) {
		FyHttpClient.postURL(uri, FyHttpClient.RESPONSE_TYPE_JSON, datas, callback);
	}

	public static void getTextWithPostUrl(String uri, HashMap datas,
			FyHttpInterface callback) {
		FyHttpClient.postURL(uri, FyHttpClient.RESPONSE_TYPE_TEXT, datas, callback);
	}

	public static void getInputstreamWithPostUrl(String uri, HashMap datas,
			FyHttpInterface callback) {
		FyHttpClient.postURL(uri, FyHttpClient.RESPONSE_TYPE_INPUTSTREAM, datas,
				callback);
	}

	public static void postURL(String uri, int responseType, HashMap datas,FyHttpInterface callback) {
		String tag = null;
		if (datas.containsKey(TAG)) {
			tag = (String) datas.get(TAG);
			datas.remove(TAG);
		}
		FyLogUtil.d(FyLogUtil.TAG_LOGCAT, "uri = " + FyHttpConfig.getInstance().getBaseURL()
				+"findPay/"+ (uri == null ? "" : uri));
		HttpPost post = new HttpPost(FyHttpConfig.getInstance().getBaseURL()
				+"findPay/"
				+ (uri == null ? "" : uri));
		try {
			post.setEntity(new UrlEncodedFormEntity(FyHttpClient
					.mapToNamePairs(datas), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			FyHttpResponse resData = new FyHttpResponse();
			resData.setHttpStatus(0000);
			FyHttpClient.executeCallBack(-1, responseType, resData, callback);
			e.printStackTrace();
		}
		String action = "";
		FyHttpClient.asyncSendRquest((uri == null ? "" : uri), post, action,
				responseType, callback, tag);
	}

	private static List<NameValuePair> mapToNamePairs(HashMap maps) {
		List list = new ArrayList(maps.size());
		if (maps == null) {
			return list;
		}
		Set set = maps.entrySet();
		Iterator it = set.iterator();
		StringBuffer sb = new StringBuffer();
		sb.append("<FM>");
		while (it.hasNext()) {
			HashMap.Entry<String, String> entry = (Map.Entry<String, String>) it
					.next();
			sb.append("<" + entry.getKey().toString() + ">");
			sb.append(entry.getValue() == null ? "" : entry.getValue()
					.toString());
			sb.append("</" + entry.getKey().toString() + ">");
		}
		sb.append("</FM>");
		FyLogUtil.d(FyLogUtil.TAG_LOGCAT, "FM = " + sb.toString());
		BasicNameValuePair pair = new BasicNameValuePair("FM", sb.toString());
		list.add(pair);
		return list;
	}

	private static void asyncSendRquest(final String uri,
										final HttpUriRequest request, final String action,
										final int responseType, final FyHttpInterface callBack,
										final String tag) {

		HandlerThread thread = new HandlerThread("request");
		thread.start();
		Handler handler = new Handler(thread.getLooper()) {
			@Override
			public void handleMessage(Message msg) {
				FyHttpClient.sendRequest(uri, request, action, responseType,
						callBack, tag);
			}
		};
		handler.sendEmptyMessage(0);
	}

	private static void asyncSendRquest(final String uri,
			final HttpUriRequest request, final String action,
			final int responseType, final FyHttpInterface callBack) {
		asyncSendRquest(uri, request, action, responseType, callBack);
	}

	private static void sendRequest(String uri, HttpUriRequest request,
			String action, int responseType, final FyHttpInterface callBack,
			String tag) {
		FyHttpResponse resData = new FyHttpResponse();
		try {
			org.apache.http.HttpResponse response = client.execute(request,
					httpContext);
			resData.setHttpStatus(response.getStatusLine().getStatusCode());
			resData.setUri(uri);
			if (tag != null) {
				resData.setTag(tag);
			}
			if (response.getStatusLine().getStatusCode() != 200) {
				
				Log.i("wyl", " == " + resData.getText());
				Log.i("wyl", " response.getStatusLine().getStatusCode()== " + response.getStatusLine().getStatusCode());
				FyHttpClient.executeCallBack(-1, responseType, resData, callBack);
			} else {
				if (FyHttpClient.RESPONSE_TYPE_INPUTSTREAM == responseType) {
					resData.setInputStream(response.getEntity().getContent());
				} else {
					String reponseString = EntityUtils.toString(response
							.getEntity());
					FyLogUtil.d(FyLogUtil.TAG_LOGCAT, "reponseString = "
							+ reponseString);
					Log.i("zls", "response:"+reponseString);
					switch (responseType) {
					case FyHttpClient.RESPONSE_TYPE_TEXT:
						resData.setText(reponseString);
						break;
					case FyHttpClient.RESPONSE_TYPE_JSON:
						JSONObject jsonObject = new JSONObject(reponseString);
						resData.setJson(jsonObject);
						break;
					case FyHttpClient.RESPONSE_TYPE_XML:
						FyXmlNodeData resXml = FyHttpClient
								.xmlStringToHashMap(reponseString);
						resData.setXml(resXml);
						break;
					}
				}
				FyHttpClient.executeCallBack(0, responseType, resData, callBack);
			}
		} catch (Exception e) { // 不分错误类型，统一捕获
			FyHttpClient.executeCallBack(-1, responseType, resData, callBack);
			e.printStackTrace();
		}
	}

	private static void executeCallBack(final int isSuccess,
			final int responseType, final FyHttpResponse resData,
			final FyHttpInterface callBack) {
		Handler handler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message msg) {
				if (isSuccess == 0) {
					if (responseType == RESPONSE_TYPE_XML) {
						if (!FyHttpConfig.RSP_CODE_SUCCESS.equals(resData
								.getXml().get(FyHttpConfig.RSP_CODE))) {
							callBack.executeFailed(resData);
							return;
						}
					}
					callBack.requestSuccess(resData);
				} else {
					callBack.requestFailed(resData);
				}

			}
		};
		handler.sendMessage(new Message());
	}

	// 解析xml成hashMap
	public static FyXmlNodeData xmlStringToHashMap(String responseString)
			throws ParserConfigurationException, IOException, SAXException {
		FyXmlNodeData hashMap = new FyXmlNodeData();
		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder()
				.parse(new ByteArrayInputStream(responseString.getBytes()));
		NodeList nodeList = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			FyHttpClient.paserNode(node, hashMap);
		}
		return hashMap;
	}

	public static void paserNode(Node pNode, FyXmlNodeData hashMap) {
		NodeList nodeList = pNode.getChildNodes();
		if (nodeList.getLength() == 1
				&& nodeList.item(0).getNodeType() == Node.TEXT_NODE) {
			FyHttpClient.addNodeValue(hashMap, pNode.getNodeName(), nodeList
					.item(0).getNodeValue());
		} else {
			FyXmlNodeData inHashMap = new FyXmlNodeData();
			FyHttpClient.addNodeValue(hashMap, pNode.getNodeName(), inHashMap);
			for (int i = 0; i < nodeList.getLength(); i++) {
				FyHttpClient.paserNode(nodeList.item(i), inHashMap);
			}
		}
	}

	public static void addNodeValue(FyXmlNodeData hashMap, String key,
			Object value) {
		if (hashMap.get(key) == null) {
			hashMap.put(key, value);
		} else if ((hashMap.get(key)) instanceof FyXmlNodeArray) {
			((FyXmlNodeArray) hashMap.get(key)).add(value);
		} else {
			FyXmlNodeArray array = new FyXmlNodeArray();
			array.add(hashMap.get(key));
			array.add(value);
			hashMap.remove(key);
			hashMap.put(key, array);
		}
	}

}
