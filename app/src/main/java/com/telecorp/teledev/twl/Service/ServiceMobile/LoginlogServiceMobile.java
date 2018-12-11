package com.telecorp.teledev.twl.Service.ServiceMobile;

import com.telecorp.teledev.twl.Model.LoginlogRequest;
import com.telecorp.teledev.twl.Model.LoginlogResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginlogServiceMobile {

    @POST("lukaservice/ServiceSHermes.svc/InsertLoginlog")
    Call<LoginlogResult> insertloginlog(@Body LoginlogRequest loginlogRequest);

    public interface OnNetworkCallbackListenerInsert{
        public void onResponse(LoginlogResult loginlogResult, Retrofit retrofit);
        public void onBodyError(ResponseBody responseBodyError);
        public void onBodyErrorIsNull();
        public void onFailure(Throwable t);

    }

    // ส่งข้อมูลไป service จะแยกเองว่าจะ insert or update
    @POST("lukaservice/ServiceSHermes.svc/MSaveLoginLog")
    Call<LoginlogResult> ifloginlog(@Body LoginlogRequest loginlogRequest);

    public interface OnNetworkCallbackListenerIfLoginlog{
        public void onResponse(LoginlogResult loginlogResult, Retrofit retrofit);
        public void onBodyError(ResponseBody responseBodyError);
        public void onBodyErrorIsNull();
        public void onFailure(Throwable t);

    }

    @POST("lukaservice/ServiceSHermes.svc/UpdateLoginlog")
    Call<LoginlogResult> updateloginlog(@Body LoginlogRequest loginlogRequest);

    public interface OnNetworkCallbackListenerUpdate{
        public void onResponse(LoginlogResult loginlogResult, Retrofit retrofit);
        public void onBodyError(ResponseBody responseBodyError);
        public void onBodyErrorIsNull();
        public void onFailure(Throwable t);

    }
}
