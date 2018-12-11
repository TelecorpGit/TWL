package com.telecorp.teledev.twl.Service.ServiceMobile;

import com.telecorp.teledev.twl.Model.CheckUserRequest;
import com.telecorp.teledev.twl.Model.CheckUserResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CheckUserServiceMobile {
       @POST("/lukaservice/ServiceSHermes.svc/GetMCheckUser")
        Call<CheckUserResult> checkuser(@Body CheckUserRequest checkUserRequest);

        public interface OnNetworkCallbackListener{
                public void onResponse(CheckUserResult checkUserResult, Retrofit retrofit);
                public void onBodyError(ResponseBody responseBodyError);
                public void onBodyErrorIsNull();
                public void onFailure(Throwable t);

        }

    @POST("/lukaservice/ServiceSHermes.svc/UpdateMSUser")
//    @POST("/ServiceSHermes.svc/UpdateMSUser")
    Call<CheckUserResult> updateuser(@Body CheckUserRequest checkUserRequest);

    public interface OnNetworkCallbackListenerUpdate{
        public void onResponse(CheckUserResult checkUserResult, Retrofit retrofit);
        public void onBodyError(ResponseBody responseBodyError);
        public void onBodyErrorIsNull();
        public void onFailure(Throwable t);

    }


}
