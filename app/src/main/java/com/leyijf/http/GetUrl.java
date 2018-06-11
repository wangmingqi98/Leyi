package com.leyijf.http;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2018/3/13.
 */

public class GetUrl {

    public  static final String BASEURL="http://leyibank.com//mapi/";
    public  static final String BASEIMG="http://leyibank.com//";
    public  static final String ACT="index.php?";
    public  static final String TYPE="&r_type=1";




    public static String getUrl(Map<String,String> map){


        String params="";
        Set<String> key = map.keySet();
        String beginLetter="";


        for (Iterator<String> it = key.iterator(); it.hasNext();) {
            String s = (String) it.next();
            if (params.equals(""))
            {
                params += beginLetter + s + "=" + map.get(s);
            }
            else
            {
                params += "&" + s + "=" + map.get(s);
            }
        }

        String url=BASEURL+ACT+params+TYPE;
        Log.d("url", "getUrl: "+url);

        return url;
    }

    public  static String toJson(Map requestBody){

        String json = JSON.toJSONString(requestBody);
        return json;
    }

}
