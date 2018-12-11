package com.telecorp.teledev.twl.Service.ServiceMobile;

import com.telecorp.teledev.twl.Model.NotificationRequest;
import com.telecorp.teledev.twl.Model.NotificationResult;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NotificationServiceMobile {
    @POST("lukaservice/ServiceSHermes.svc/SelectNotification")
    Call<List<NotificationResult>> selectnotific(@Body NotificationRequest notificationRequest);

    public interface OnNetworkCallbackListener{
        public void onResponse(List<NotificationResult> notificationResult, Retrofit retrofit);
        public void onBodyError(ResponseBody responseBodyError);
        public void onBodyErrorIsNull();
        public void onFailure(Throwable t);

    }
}
