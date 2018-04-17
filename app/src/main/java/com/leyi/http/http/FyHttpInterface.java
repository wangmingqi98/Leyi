package com.leyi.http.http;

public interface FyHttpInterface {

	/**
	 * 发送请求成功 应答码为0000的回调
	 * 
	 * @param resData
	 */
	public void requestSuccess(FyHttpResponse resData);

	/**
	 * 应答码非0000的回调
	 * 
	 * @param resData
	 */
	public void requestFailed(FyHttpResponse resData);

	/**
	 * 
	 * 
	 * @param resData
	 */
	public void executeFailed(FyHttpResponse resData);
}
