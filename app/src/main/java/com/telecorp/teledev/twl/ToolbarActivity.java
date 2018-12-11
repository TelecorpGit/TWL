package com.telecorp.teledev.twl;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.telecorp.teledev.twl.Adapter.NotificationCustomAdapter;
import com.telecorp.teledev.twl.Model.LoginlogRequest;
import com.telecorp.teledev.twl.Model.LoginlogResult;
import com.telecorp.teledev.twl.Model.NotificationRequest;
import com.telecorp.teledev.twl.Model.NotificationResult;
import com.telecorp.teledev.twl.Service.ConnectionManager.LoginlogConnectionManager;
import com.telecorp.teledev.twl.Service.ConnectionManager.NotificationConnectionManager;
import com.telecorp.teledev.twl.Service.ServiceMobile.LoginlogServiceMobile;
import com.telecorp.teledev.twl.Service.ServiceMobile.NotificationServiceMobile;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class ToolbarActivity extends AppCompatActivity {

    int spUid,spMsorganisationuid;
    String spPrename ,spForename, spSurname, spUsername, spPassword, spType, spDeviceID, spPlatform;
    Dialog dialog, dialogNotification;

    private ListView listNotification;
    private List<NotificationResult> notificationResultList;
    private NotificationCustomAdapter notificationCustomAdapter;
    private ImageButton btnCancleNotif;

   int  useruid;
   String deviceid,platform,statuslogin,fromapp;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sp = getSharedPreferences("DATA_MSUSER", Context.MODE_PRIVATE);
        spUid = sp.getInt("SP_UID", 0);
        spPrename = sp.getString("SP_PRENAME", "");
        spForename = sp.getString("SP_FORENAME", "");
        spSurname = sp.getString("SP_SURNAME", "");
        spUsername = sp.getString("SP_USERNAME", "");
        spPassword = sp.getString("SP_PASSWORD", "");
        spMsorganisationuid = sp.getInt("SP_MSORGANISATIONUID", 0);
        spType =  sp.getString("SP_TYPE", "");
        spDeviceID =  sp.getString("SP_DEVICEID", "");
        spPlatform =  sp.getString("SP_PLATFORM", "");


        actionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_notification:
//                dialogNotification = new Dialog(ToolbarActivity.this);
                dialogNotification = new Dialog(ToolbarActivity.this,
                        android.R.style.Theme_Translucent_NoTitleBar);
                dialogNotification.setContentView(R.layout.activity_notfication);
                btnCancleNotif = dialogNotification.findViewById(R.id.btn_cancle_notif);
                btnCancleNotif.setOnClickListener(clickClose);
                // Setting dialogview
                dialogNotification.show();
                listNotification = dialogNotification.findViewById(R.id.listview_notification);
                NotificationRequest notificationRequest= new NotificationRequest();
                notificationRequest.setMsorganisationuid(spMsorganisationuid);
                new NotificationConnectionManager(ToolbarActivity.this).SelectNotification(networkcallSelectNotification, notificationRequest);

                break;
            case R.id.action_menu:
                dialog = new Dialog(ToolbarActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                // Include dialog.xml file
                dialog.setContentView(R.layout.activity_logout);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);
                dialog.show();
                SharedPreferences spLoginlog = getSharedPreferences("LOGIN_LOG", Context.MODE_PRIVATE);
                useruid = spLoginlog.getInt("USERUID_LOG", 0);
                deviceid = spLoginlog.getString("DEVICEID_LOG", "");
                platform = spLoginlog.getString("PLATFORM_LOG", "");
                statuslogin = spLoginlog.getString("STATUSLOGIN_LOG", "");
                fromapp = spLoginlog.getString("FROMAPP_LOG", "");

                TextView txtPrename = dialog.findViewById(R.id.txt_Prename);
                TextView txtForename = dialog.findViewById(R.id.txt_Forename);
                TextView txtSurname = dialog.findViewById(R.id.txt_Surname);
                txtPrename.setText(spPrename);
                txtForename.setText(spForename);
                txtSurname.setText(spSurname);

                Button declineButton = (Button) dialog.findViewById(R.id.btn_cancel);
                declineButton.setOnClickListener(onClickCancel);
                Button videoButton = (Button) dialog.findViewById(R.id.btn_signout);
                videoButton.setOnClickListener(onClickSignout);

                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void actionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.twl_w54px_h54px);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    View.OnClickListener onClickCancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Close Dialog
            dialog.dismiss();
        }
    };

    View.OnClickListener onClickSignout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // เมื่อกด logout ให้ส่งข้อมูลไป Update ใน tb.Loginlog
            LoginlogRequest loginlogRequest = new LoginlogRequest();
            loginlogRequest.setUseruid(useruid);
            loginlogRequest.setDeviceid(deviceid);
            loginlogRequest.setPlatfrom(platform);
            loginlogRequest.setStatuslogin("Offline");
            loginlogRequest.setFromapp(fromapp);
            new LoginlogConnectionManager(ToolbarActivity.this).UpdateLoginlog(networkCallUpdateLoginlog, loginlogRequest);


            //clear ค่าใน SharedPreferences เมื่อทำการออกจากระบบ
            SharedPreferences spDataMsUser = getSharedPreferences("DATA_MSUSER", Context.MODE_PRIVATE);
            SharedPreferences.Editor editorMsUser = spDataMsUser.edit();
            editorMsUser.clear();
            editorMsUser.commit();

            SharedPreferences sharedCheck = getSharedPreferences("PREFS_CHECK",MODE_PRIVATE);
            SharedPreferences.Editor edCheck = sharedCheck.edit();
            edCheck.clear();
            edCheck.commit();

            SharedPreferences checkRememberShared = getSharedPreferences("PREFS_REMEMBER",MODE_PRIVATE);
            SharedPreferences.Editor edRemember = checkRememberShared.edit();
            edRemember.clear();
            edRemember.commit();

            SharedPreferences shPresfLogin = getSharedPreferences("PREFS_LOGIN",MODE_PRIVATE);
            SharedPreferences.Editor edLogin = shPresfLogin.edit();
            edLogin.clear();
            edLogin.commit();

            //show login form
            Intent i = new Intent(ToolbarActivity.this, LoginActivity.class);
            startActivity(i);


        }
    };

    NotificationServiceMobile.OnNetworkCallbackListener networkcallSelectNotification= new NotificationServiceMobile.OnNetworkCallbackListener() {
        @Override
        public void onResponse(final List<NotificationResult> notificationResult, Retrofit retrofit) {
            notificationResultList = notificationResult;

            notificationCustomAdapter = new NotificationCustomAdapter(ToolbarActivity.this, notificationResultList);
            notificationCustomAdapter.notifyDataSetChanged();
            if (notificationResultList.size() != 0) {
                listNotification.setAdapter(notificationCustomAdapter);
                listNotification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("link","position : "+position);
                        intent.putExtra("RefrigUID", notificationResultList.get(position).getRefriguid());
                        intent.putExtra("code", notificationResultList.get(0).getMacid());
                        startActivity(intent);
                    }
                });
//                    }

            } else {
                listNotification.clearChoices();
            }

        }

        @Override
        public void onBodyError(ResponseBody responseBodyError) {
            Toast.makeText(ToolbarActivity.this, "ไม่สามารถเชื่อมต่อเซอร์วิสได้"
                    , Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBodyErrorIsNull() {

        }

        @Override
        public void onFailure(Throwable t) {

        }
    };


    LoginlogServiceMobile.OnNetworkCallbackListenerUpdate networkCallUpdateLoginlog = new LoginlogServiceMobile.OnNetworkCallbackListenerUpdate() {
        @Override
        public void onResponse(LoginlogResult loginlogResult, Retrofit retrofit) {
            if (loginlogResult.getRespondCode() == 200) {

            }else {
//                Toast.makeText(ToolbarActivity.this, "ไม่สามารถทำการอัพเดต", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onBodyError(ResponseBody responseBodyError) {
            Toast.makeText(ToolbarActivity.this, "ไม่สามารถเชื่อมต่อเซอร์วิสได้"
                    , Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBodyErrorIsNull() {

        }

        @Override
        public void onFailure(Throwable t) {

        }
    };

    View.OnClickListener clickClose = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogNotification.dismiss();
        }
    };


}
