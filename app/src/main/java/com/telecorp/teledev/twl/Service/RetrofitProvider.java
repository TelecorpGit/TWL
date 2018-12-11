package com.telecorp.teledev.twl.Service;

import android.content.Context;

import com.telecorp.teledev.twl.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {
    public static Retrofit getRetrofit(Context context){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .build();
        return new  Retrofit.Builder()
                .baseUrl(context.getString(R.string.ip_server_twl))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }

}
