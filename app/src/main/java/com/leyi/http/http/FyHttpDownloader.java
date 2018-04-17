package com.leyi.http.http;

import com.fuiou.mobile.util.FileUtils;
import com.fuiou.mobile.util.FileUtils.WRITE_STATUS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public final class FyHttpDownloader {


	/**
	 * 下载网络文件
	 * @param urlStr 文件url地址
	 * @param path	下载后存储地址
	 * @param fileName 文件名
	 * @return
	 */
	public static WRITE_STATUS downFile(String urlStr, String path,
			String fileName) {
		InputStream inputStream = null;
		try {
			inputStream = getInputStreamFromUrl(urlStr);
			WRITE_STATUS writeStatus = FileUtils.writeToDirFromInput(path,
					fileName, inputStream);
			if (WRITE_STATUS.ERROR == writeStatus) {
				return WRITE_STATUS.ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return WRITE_STATUS.ERROR;
		} finally {
			try {
				if(inputStream!=null){
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return WRITE_STATUS.SUCCESS;
	}

	/**
	 * 根据URL得到输入流
	 * 
	 * @param urlStr
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static InputStream getInputStreamFromUrl(String urlStr)
			throws MalformedURLException, IOException {
		URL url = new URL(urlStr);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setConnectTimeout(40 * 1000);
		urlConn.setReadTimeout(5 * 60 * 1000);
		InputStream inputStream = urlConn.getInputStream();
		return inputStream;
	}
	
	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}
}
