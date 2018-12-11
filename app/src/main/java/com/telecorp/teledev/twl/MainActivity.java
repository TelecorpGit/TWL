package com.telecorp.teledev.twl;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends ToolbarActivity {

    WebView webView;
    String Username,Password;
    String platform;
    String token;
    private static final String TAG = "MainActivity";
    String txtUrl ,txtRefrigUID , txtCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);
        webView = findViewById(R.id.webView);

        //เอาไว้คอยรับค่าที่ถูกส่งมาจากการกดที่ Notification
        if (getIntent().getExtras() != null) { // if เอาไว้คอยรับค่าที่ถูกส่งมาจากการกดที่ Notification
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }

            txtUrl = getIntent().getStringExtra("link"); //เปลี่ยน เป็น Pushnotify แทน
            txtRefrigUID = getIntent().getStringExtra("RefrigUID");
            txtCode = getIntent().getStringExtra("code");
        }


        //รับค่ามาจากการกรอก Username and Password
        SharedPreferences shPresfLogin = getSharedPreferences("PREFS_LOGIN",MODE_PRIVATE);
        Username = shPresfLogin.getString("LOGIN_USERNAME", "");
        Password = shPresfLogin.getString("LOGIN_PASSWORD", "");

        //หมายเลขเครื่อง
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token : " + token);
        platform = getString(R.string.str_platform);

        //สร้าง SharedPreferences เพื่อเก็บค่าว่ามีการเข้าสู่ระบบสำเร็จ
        SharedPreferences sharedCheck = getSharedPreferences("PREFS_CHECK",MODE_PRIVATE);
        SharedPreferences.Editor edCheck = sharedCheck.edit();
        edCheck.putInt("PREF_CHECK_EVER",1);
        edCheck.putString("PREF_CHECK_EVER_USERNAME", Username);
        edCheck.putString("PREF_CHECK_EVER_PASSWORD", Password);
        edCheck.putString("TOKEN",token);
        edCheck.commit();


        webViewManage();

    }

    private void webViewManage() {
//        Toast.makeText(MainActivity.this, "Token : " + token, Toast.LENGTH_SHORT).show();

        if (Username.equals("") && Password.equals("")){
            Toast.makeText(this, "คุณไม่ได้ลงชื่อเข้าใช้งาน", Toast.LENGTH_SHORT).show();
            Intent backLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(backLogin);
        }else {
            webView.setWebViewClient(new WebViewClient());
            if(txtUrl == null) {
                webView.loadUrl("http://thaisee.com/twl/Login/?username=" + Username + "&password=" + Password + " ");
            }
            else
            {

                webView.loadUrl("http://thaisee.com/twl/Login/?username=" + Username + "&password=" + Password + " ");
                webView.loadUrl("http://thaisee.com/twl/Home/Index?RefrigUID=" +Integer.parseInt(txtRefrigUID.replaceAll("[\\D]", ""))+ "&code=" +Integer.parseInt(txtCode.replaceAll("[\\D]", ""))+ " ");

            }
        }

//        webView.loadUrl("http://thaisee.com/lk/Home/Index?RefrigUID=160&code=0215" );

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        //สร้าง SharedPreferences เพื่อเก็บค่าว่ามีการเข้าสู่ระบบสำเร็จ ใน Loginlog
        SharedPreferences sharedLoginlog= getSharedPreferences("LOGIN_LOG",MODE_PRIVATE);
        SharedPreferences.Editor edLoginlog = sharedLoginlog.edit();
        edLoginlog.putInt("USERUID_LOG",spUid);
        edLoginlog.putString("DEVICEID_LOG", token);
        edLoginlog.putString("PLATFORM_LOG", platform);
        edLoginlog.putString("STATUSLOGIN_LOG","Online");
        edLoginlog.putString("FROMAPP_LOG","TWL");
        edLoginlog.commit();

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            return;
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        // Save the state of the WebView
        webView.saveState(outState);
    }



}
