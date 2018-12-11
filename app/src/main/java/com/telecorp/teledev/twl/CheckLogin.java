package com.telecorp.teledev.twl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;
import com.telecorp.teledev.twl.Model.CheckUserRequest;
import com.telecorp.teledev.twl.Model.CheckUserResult;
import com.telecorp.teledev.twl.Model.LoginlogRequest;
import com.telecorp.teledev.twl.Model.LoginlogResult;
import com.telecorp.teledev.twl.Service.ConnectionManager.CheckUserConnectionManager;
import com.telecorp.teledev.twl.Service.ConnectionManager.LoginlogConnectionManager;
import com.telecorp.teledev.twl.Service.ServiceMobile.CheckUserServiceMobile;
import com.telecorp.teledev.twl.Service.ServiceMobile.LoginlogServiceMobile;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class CheckLogin extends Activity {

    String txtUsername,txtPassword;

    final String DATA_MSUSER = "Data_msUser";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    int Uid;
    // getSharedPreferences("PREFS_REMEMBER");
    String shReUsername;
    String shRePassword;
    Boolean shReCheckbox;

    // getSharedPreferences("PREFS_CHECK");
    int checkLogin;
    String checkUsername;
    String checkPassword;
    String  checkToken;

    private static final String AUTH_KEY = "key=AAAAy6b8CLw:APA91bGmxIie7Eo0NsI4yT2VqECJiInLAK2bBWbtZX42l1Rw5mkbWp3AF1WFmhwDs5e5ZiGNr7H1srMT8mtqUOJJNYZoqKJZ-UAZH1J7QZbNR3JsYzUSwhga6cTVlspQDwa6LbklQvHF";
    private static final String TAG = "LoginActivity";

    ProgressDialog loadingDialog;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getIntent().getExtras();

        SharedPreferences checkRememberShared = getSharedPreferences("PREFS_REMEMBER",MODE_PRIVATE);
        shReUsername = checkRememberShared.getString("RE_USERNAME","");
        shRePassword = checkRememberShared.getString("RE_PASSWORD", "");
        shReCheckbox = checkRememberShared.getBoolean("CHECKBOX", false);

        SharedPreferences sharedCheck = getSharedPreferences("PREFS_CHECK",MODE_PRIVATE);
        checkLogin = sharedCheck.getInt("PREF_CHECK_EVER",-1);
        checkUsername = sharedCheck.getString("PREF_CHECK_EVER_USERNAME","");
        checkPassword = sharedCheck.getString("PREF_CHECK_EVER_PASSWORD", "");
        checkToken =  sharedCheck.getString("TOKEN", "");

        SharedPreferences clearSound = getSharedPreferences("STATUS_SOUND",MODE_PRIVATE);
        SharedPreferences.Editor edClear= clearSound.edit();
        edClear.clear();
        edClear.commit();

        if (shReCheckbox == true){
            loadingDialog = ProgressDialog.show(CheckLogin.this,"จดจำการเข้าสู่ระบบ !",
                    "กำลังเข้าสู่ระบบ, กรุณารอสักครู่...", false, false);
            LoginAuto(shReUsername, shRePassword);

        }else {
            if (checkLogin == 1){
                if (checkUsername != "" && checkPassword != ""){
                    loadingDialog = ProgressDialog.show(CheckLogin.this,"คุณเคยเข้าสู่ระบบไว้แล้ว !",
                            "กำลังเข้าสู่ระบบ, กรุณารอสักครู่...", false, false);
                    LoginAuto(checkUsername, checkPassword);
                }
            }else {

            }
        }



    }



    private void LoginAuto(String usernameAuto, String passwordAuto) {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // ซ่อน Progress Dialog
                loadingDialog.dismiss();
            }
        }, 3000); // 3000 milliseconds delay
        txtUsername = usernameAuto;
        txtPassword = passwordAuto;
        CheckUserRequest checkUserRequest = new CheckUserRequest();
        checkUserRequest.setUsername(usernameAuto);
        checkUserRequest.setPassword(passwordAuto);
        new CheckUserConnectionManager(CheckLogin.this).GetCheckUser(networkCallCheckUser, checkUserRequest);
    }



    CheckUserServiceMobile.OnNetworkCallbackListener networkCallCheckUser = new CheckUserServiceMobile.OnNetworkCallbackListener() {
        @Override
        public void onResponse(CheckUserResult checkUserResult, Retrofit retrofit) {
            if (checkUserResult.getUID() != 0) {
                if (checkUserResult.getRespondCode() == 200) {
                    //success
                    Uid = checkUserResult.getUID();
                    String Prename = checkUserResult.getPrename().toString();
                    String Forename = checkUserResult.getForename().toString();
                    String Surname = checkUserResult.getSurname().toString();
                    final String Username = checkUserResult.getUsername().toString();
                    String Password = checkUserResult.getPassword().toString();
                    int Msorganisationuid = checkUserResult.getMSOrganisationUID();
                    String Type = checkUserResult.getType().toString();
                    String DeviceID = checkUserResult.getDeviceID().toLowerCase();
                    String Platform = checkUserResult.getPlatform().toString();

                    //สร้าง SharedPreferences เพื่อเก็บข้อมูลของผู้ใช้ที่เข้าสู่ระบบสำเร็จ
                    sp = getSharedPreferences("DATA_MSUSER", Context.MODE_PRIVATE);
                    editor = sp.edit();
                    editor.putInt("SP_UID", Uid);
                    editor.putString("SP_PRENAME", Prename);
                    editor.putString("SP_FORENAME", Forename);
                    editor.putString("SP_SURNAME", Surname);
                    editor.putString("SP_USERNAME", Username);
                    editor.putString("SP_PASSWORD", Password);
                    editor.putInt("SP_MSORGANISATIONUID", Msorganisationuid);
                    editor.putString("SP_TYPE", Type);
                    editor.putString("SP_DEVICEID", DeviceID);
                    editor.putString("SP_PLATFORM", Platform);
                    editor.commit();

                    Intent intentWebView = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentWebView);

                    Toast.makeText(getApplicationContext(), "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_SHORT).show();

                    //สร้าง SharedPreferences เพื่อเก็บค่า  Username and Password ที่ Login
                    SharedPreferences shPresfLogin = getSharedPreferences("PREFS_LOGIN", MODE_PRIVATE);
                    SharedPreferences.Editor edLogin = shPresfLogin.edit();
                    edLogin.putString("LOGIN_USERNAME", txtUsername);
                    edLogin.putString("LOGIN_PASSWORD", txtPassword);
                    edLogin.commit();

                    //หมายเลขเครื่อง
                    String token = FirebaseInstanceId.getInstance().getToken();

                    LoginlogRequest loginlogRequest = new LoginlogRequest();
                    loginlogRequest.setUseruid(Uid);
                    loginlogRequest.setDeviceid(token);
                    loginlogRequest.setPlatfrom("Android");
//                        loginlogRequest.setStatuslogin("Online");
                    loginlogRequest.setFromapp("TWL");
                    new LoginlogConnectionManager(CheckLogin.this).IfLoginlog(networkCallIfLoginlog, loginlogRequest);

                } else {
                    //fail
                    Toast.makeText(CheckLogin.this, "เข้าสู่ระบบไม่สำเร็จ", Toast.LENGTH_LONG).show(); // เป็นการสร้าง Object Toast และคำสั่ง toast.show();  เป็นการกำหนด Toast แสดงขึ้นมา
                }
            }else {
                Toast.makeText(CheckLogin.this, "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show();
                SharedPreferences checkRememberShared = getSharedPreferences("PREFS_REMEMBER",MODE_PRIVATE);
                SharedPreferences.Editor edRemember = checkRememberShared.edit();
                edRemember.clear();
                edRemember.commit();
            }
        }

        @Override
        public void onBodyError(ResponseBody responseBodyError) {
            Toast.makeText(CheckLogin.this,"การดึงข้อมูลไม่สำเร็จ",Toast.LENGTH_LONG).show(); // เป็นการสร้าง Object Toast และคำสั่ง toast.show();  เป็นการกำหนด Toast แสดงขึ้นมา
        }

        @Override
        public void onBodyErrorIsNull() {

        }

        @Override
        public void onFailure(Throwable t) {

        }
    };


    @Override
    public void onBackPressed() {
//        Toast.makeText(this, "กรุณาออกจากระบบก่อน!", Toast.LENGTH_SHORT).show();
        return;
    }

    LoginlogServiceMobile.OnNetworkCallbackListenerIfLoginlog networkCallIfLoginlog = new LoginlogServiceMobile.OnNetworkCallbackListenerIfLoginlog() {
        @Override
        public void onResponse(LoginlogResult loginlogResult, Retrofit retrofit) {

        }

        @Override
        public void onBodyError(ResponseBody responseBodyError) {
            Toast.makeText(CheckLogin.this, "ไม่สามารถเชื่อมต่อเซอร์วิสได้"
                    , Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBodyErrorIsNull() {

        }

        @Override
        public void onFailure(Throwable t) {

        }
    };





}
