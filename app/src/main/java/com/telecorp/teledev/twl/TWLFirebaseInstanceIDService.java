package com.telecorp.teledev.twl;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class TWLFirebaseInstanceIDService extends FirebaseInstanceIdService {

    //คลาสนี้เอาไว้รับค่า token
    public static final String TAG = "FirebaseIDService";

    @Override
    public void onTokenRefresh() { //method onTokenRefresh จะถูกเรียกเมื่อค่า Token มีการเปลี่ยนแปลง ซึ่งเราจะสามารถ handle ค่า token เองได้
        // เมธอด onTokenRefresh() จะไว้สำหรับค่อยรับToken ใหม่หาก Token เดิมหมดอายุแล้วส่งให้มธอด sendRegistrationToServer
        super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);


    }

    private void sendRegistrationToServer(String token) { //เมธอด sendRegistrationToServer เพิ่ม ส่ง Token ใหม่ที่ได้ไปให้กับ Server ของเรา
        // Add custom implementation, as needed.
        Log.d(TAG, "Did obtained token");
        Log.d(TAG, token);
    }
}
