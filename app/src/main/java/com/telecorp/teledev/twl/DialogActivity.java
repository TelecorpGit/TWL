package com.telecorp.teledev.twl;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class DialogActivity extends Activity {

    private String msgTitle;
    private String msgBody;
    private String msgLink;
    private String msgRefrigUID;
    private String msgCode;
    private int statusSound;
    private MediaPlayer mediaPlayer;
    private Dialog dialog;
    private Button btnOk;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        msgTitle = getIntent().getStringExtra("title");
        msgBody = getIntent().getStringExtra("body");
        msgLink = getIntent().getStringExtra("link");
        msgRefrigUID = getIntent().getStringExtra("RefrigUID");
        msgCode = getIntent().getStringExtra("code");

        SharedPreferences shCheckSound = getSharedPreferences("STATUS_SOUND",MODE_PRIVATE);
        statusSound = shCheckSound.getInt("firstTime", 0);

        if (statusSound == 0){
            displayDialog();
        }else {
            mediaPlayer.stop();
            displayDialog();
            
        }

    }

    private void displayDialog() {
        dialog = new Dialog(DialogActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_notification);
        dialog.setCancelable(false);
        mediaPlayer = MediaPlayer.create(this,R.raw.emergencyalarm);
        mediaPlayer.setLooping(true);

        btnOk = dialog.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
                Intent i = new Intent(DialogActivity.this, MainActivity.class);
                i.putExtra("link", msgLink);
                i.putExtra("RefrigUID", msgRefrigUID);
                i.putExtra("code", msgCode);
                startActivity(i);
                dialog.cancel();
            }
        });

        TextView title  = dialog.findViewById(R.id.txt_title);
        title.setText(msgTitle);
        TextView body = dialog.findViewById(R.id.txt_body);
        body.setText(msgBody);

        dialog.show();

        SharedPreferences shCheckSound = getSharedPreferences("STATUS_SOUND",MODE_PRIVATE);
        statusSound = shCheckSound.getInt("firstTime", 0);

        if (statusSound == 0){
            playSound();
            SharedPreferences shStatusSound = getSharedPreferences("STATUS_SOUND", MODE_PRIVATE);
            SharedPreferences.Editor edSound = shStatusSound.edit();
            edSound.putInt("firstTime", 1);
            edSound.commit();
        }else {
            SharedPreferences shStatusSound = getSharedPreferences("STATUS_SOUND", MODE_PRIVATE);
            SharedPreferences.Editor edSound = shStatusSound.edit();
            edSound.putInt("firstTime", 0);
            edSound.commit();
            refreshDisplay();
        }
    }

    private void playSound() {
        mediaPlayer.start();
    }

    private void refreshDisplay(){
        try {
            stopSound();
            mediaPlayer.prepare();
            playRestart();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void playRestart(){
        displayDialog();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
        SharedPreferences clearSound = getSharedPreferences("STATUS_SOUND", MODE_PRIVATE);
        SharedPreferences.Editor edClear = clearSound.edit();
        edClear.clear();
        edClear.commit();
        stopSound();
    }

    private void stopSound() {
        mediaPlayer.stop();
    }
}
