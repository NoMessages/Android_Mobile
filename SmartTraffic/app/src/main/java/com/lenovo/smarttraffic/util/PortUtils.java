package com.lenovo.smarttraffic.util;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class PortUtils {

    public static final String ADD_URL = "http://192.168.31.244:";
    public static final int PORT = 8085;

    public static String getUrl(String url){
        return ADD_URL+PORT+"/"+url;
    }

    public static Call getCall(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
          return okHttpClient.newCall(request);
    }


}
