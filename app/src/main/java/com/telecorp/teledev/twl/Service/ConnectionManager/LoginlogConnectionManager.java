package com.telecorp.teledev.twl.Service.ConnectionManager;

import android.content.Context;

import com.telecorp.teledev.twl.Model.LoginlogRequest;
import com.telecorp.teledev.twl.Model.LoginlogResult;
import com.telecorp.teledev.twl.Model.NotificationRequest;
import com.telecorp.teledev.twl.Model.NotificationResult;
import com.telecorp.teledev.twl.Service.RetrofitProvider;
import com.telecorp.teledev.twl.Service.ServiceMobile.LoginlogServiceMobile;
import com.telecorp.teledev.twl.Service.ServiceMobile.NotificationServiceMobile;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginlogConnectionManager {
    private Context context;

    public LoginlogConnectionManager(Context context) {
        this.context = context;
    }

    public void InsertLoginlog(final LoginlogServiceMobile.OnNetworkCallbackListenerInsert listenerInsert, LoginlogRequest loginlogRequest) {
        final Retrofit retrofit = RetrofitProvider.getRetrofit(this.context);
        LoginlogServiceMobile git = retrofit.create(LoginlogServiceMobile.class);
        Call call = git.insertloginlog(loginlogRequest);
        call.enqueue(new Callback<LoginlogResult>() {
            @Override
            public void onResponse(Call<LoginlogResult> call, Response<LoginlogResult> response) {
                LoginlogResult loginlogResult = response.body();

                if (loginlogResult == null) {
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listenerInsert.onBodyError(responseBody);
                    } else {
                        listenerInsert.onBodyErrorIsNull();
                    }
                } else {
                    listenerInsert.onResponse(loginlogResult, retrofit);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listenerInsert.onFailure(t);
            }
        });
    }


    public void IfLoginlog(final LoginlogServiceMobile.OnNetworkCallbackListenerIfLoginlog listenerIfLoginlog, LoginlogRequest loginlogRequest) {
        final Retrofit retrofit = RetrofitProvider.getRetrofit(this.context);
        LoginlogServiceMobile git = retrofit.create(LoginlogServiceMobile.class);
        Call call = git.ifloginlog(loginlogRequest);
        call.enqueue(new Callback<LoginlogResult>() {
            @Override
            public void onResponse(Call<LoginlogResult> call, Response<LoginlogResult> response) {
                LoginlogResult loginlogResult = response.body();

                if (loginlogResult == null) {
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listenerIfLoginlog.onBodyError(responseBody);
                    } else {
                        listenerIfLoginlog.onBodyErrorIsNull();
                    }
                } else {
                    listenerIfLoginlog.onResponse(loginlogResult, retrofit);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listenerIfLoginlog.onFailure(t);
            }
        });
    }


    public void UpdateLoginlog(final LoginlogServiceMobile.OnNetworkCallbackListenerUpdate listenerUpdate, LoginlogRequest loginlogRequest) {
        final Retrofit retrofit = RetrofitProvider.getRetrofit(this.context);
        LoginlogServiceMobile git = retrofit.create(LoginlogServiceMobile.class);
        Call call = git.updateloginlog(loginlogRequest);
        call.enqueue(new Callback<LoginlogResult>() {
            @Override
            public void onResponse(Call<LoginlogResult> call, Response<LoginlogResult> response) {
                LoginlogResult loginlogResult = response.body();

                if (loginlogResult == null) {
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listenerUpdate.onBodyError(responseBody);
                    } else {
                        listenerUpdate.onBodyErrorIsNull();
                    }
                } else {
                    listenerUpdate.onResponse(loginlogResult, retrofit);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listenerUpdate.onFailure(t);
            }
        });
    }
}
