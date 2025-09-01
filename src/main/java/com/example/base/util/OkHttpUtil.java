package com.example.base.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {

    private static final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8,TimeUnit.SECONDS)
            .writeTimeout(8,TimeUnit.SECONDS).build();

    //GET请求
    public static String sendGet(String url) throws IOException {
        //如果需要在request的的header添加参数，就可在.build()前加： .header("键","值")
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        try {
            String str = response.body().string();
            return str;
        }finally {
            response.close();
        }
    }

    //POST请求，数据类型application/x-www-form-urlencoded
    public static String sendPost(String url,String name,Object data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        //post方式提交的数据
        FormBody formBody = new FormBody.Builder()
                .add(name, mapper.writeValueAsString(data))
                .build();
        Request request = new Request.Builder()
                .url(url)//请求的url
                .post(formBody)//设置请求方式，post()
                .build(); //构建一个请求Request对象
        //创建/Call
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        try {
            String str = response.body().string();
            return str;
        }finally {
            response.close();
        }
    }

    //POST请求，数据类型Json
    public static String sendPost(String url, Object data) throws Exception {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        //post方式提交的数据
        okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, mapper.writeValueAsString(data));
        final Request request = new Request.Builder()
                .url(url)//请求的url
                .post(body)//设置请求方式，get()/post()  查看Builder()方法知，在构建时默认设置请求方式为GET
                .build(); //构建一个请求Request对象
        //创建/Call
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        try {
            String str = response.body().string();
            return str;
        }finally {
            response.close();
        }
    }
}

