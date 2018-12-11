package com.telecorp.teledev.twl.Service.ConnectionManager;

import android.content.Context;

import com.telecorp.teledev.twl.Model.CheckUserRequest;
import com.telecorp.teledev.twl.Model.CheckUserResult;
import com.telecorp.teledev.twl.Service.RetrofitProvider;
import com.telecorp.teledev.twl.Service.ServiceMobile.CheckUserServiceMobile;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CheckUserConnectionManager {

    private Context context;

    public CheckUserConnectionManager(Context context) {
        this.context = context;
    }

    public void GetCheckUser(final CheckUserServiceMobile.OnNetworkCallbackListener listener, CheckUserRequest checkUserRequest){
        final Retrofit retrofit = RetrofitProvider.getRetrofit(this.context);
        CheckUserServiceMobile git = retrofit.create(CheckUserServiceMobile.class);
        Call call  = git.checkuser(checkUserRequest);
        call.enqueue(new Callback<CheckUserResult>() {
            @Override
            public void onResponse(Call<CheckUserResult> call, Response<CheckUserResult> response) {
                CheckUserResult checkUserResult = response.body();

                if (checkUserResult == null){
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null){
                        listener.onBodyError(responseBody);
                    }else {
                        listener.onBodyErrorIsNull();
                    }
                }else {
                    listener.onResponse(checkUserResult ,retrofit);
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listener.onFailure(t);
            }
        });

    }

    public void UpdateUser(final CheckUserServiceMobile.OnNetworkCallbackListenerUpdate listenerUpdate, CheckUserRequest checkUserRequest){
        final Retrofit retrofit = RetrofitProvider.getRetrofit(this.context);
        CheckUserServiceMobile git = retrofit.create(CheckUserServiceMobile.class);
        Call call = git.updateuser(checkUserRequest);
        call.enqueue(new Callback<CheckUserResult>() {
            @Override
            public void onResponse(Call<CheckUserResult> call, Response<CheckUserResult> response) {
                CheckUserResult checkUserResult = response.body();

                if (checkUserResult == null){
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null){
                        listenerUpdate.onBodyError(responseBody);
                    }else {
                        listenerUpdate.onBodyErrorIsNull();
                    }
                }else {
                    listenerUpdate.onResponse(checkUserResult ,retrofit);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listenerUpdate.onFailure(t);
            }
        });
    }
}
