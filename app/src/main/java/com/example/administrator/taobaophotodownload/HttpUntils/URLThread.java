package com.example.administrator.taobaophotodownload.HttpUntils;


import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/10.
 */

public class URLThread implements Callable {

    private final OkHttpClient client = new OkHttpClient();
    private String url,Html;
    public URLThread(String url){
        this.url=url;
    }

    @Override
    public Object call() throws Exception {

//
        Html=syncGet();

        return Html;
    }


//同步Get
    public String  syncGet() throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("异常码:" + response);

//        Headers responseHeaders = response.headers();
//        for (int i = 0; i < responseHeaders.size(); i++) {
//            System.out.println("头信息:\n"+responseHeaders.name(i) + ": " + responseHeaders.value(i));
//        }

//        System.out.println("html====:\n"+response.body().string());

        return response.body().string();
    }

    //异步Get
//    public String asycnGet(){
//        final String html;
//        Request request = new Request.Builder()
//                    .url(url)
//                    .build();
//
//            client.newCall(request).enqueue(new Callback() {
//                @Override public void onFailure(Call call, IOException e) {
//                    System.out.println("异常");e.printStackTrace();
//                }
//
//                @Override public void onResponse(Call call, Response response) throws IOException {
//                    if (!response.isSuccessful()) throw new IOException("异常码 " + response);
//
//                    Headers responseHeaders = response.headers();
//                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                    }
//                    html=response.body().toString();
//                }
//            });
//
//        return null;
//    }




}








