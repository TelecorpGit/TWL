package com.telecorp.teledev.twl.Service.ConnectionManager;

import android.content.Context;

import com.telecorp.teledev.twl.Model.NotificationRequest;
import com.telecorp.teledev.twl.Model.NotificationResult;
import com.telecorp.teledev.twl.Service.RetrofitProvider;
import com.telecorp.teledev.twl.Service.ServiceMobile.NotificationServiceMobile;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotificationConnectionManager {
    private Context context;

    public NotificationConnectionManager(Context context) {
        this.context = context;
    }

    public void SelectNotification(final NotificationServiceMobile.OnNetworkCallbackListener listener, NotificationRequest notificationRequest) {
        final Retrofit retrofit = RetrofitProvider.getRetrofit(this.context);
        NotificationServiceMobile git = retrofit.create(NotificationServiceMobile.class);
        Call call = git.selectnotific(notificationRequest);
        call.enqueue(new Callback<List<NotificationResult>> () {
            @Override
            public void onResponse(Call<List<NotificationResult>> call, Response<List<NotificationResult>> response) {
                List<NotificationResult> notificationResult = response.body();

                if (notificationResult == null) {
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    listener.onResponse(notificationResult, retrofit);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}
