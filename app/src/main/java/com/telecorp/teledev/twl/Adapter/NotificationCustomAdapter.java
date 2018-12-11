package com.telecorp.teledev.twl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.telecorp.teledev.twl.Model.NotificationResult;
import com.telecorp.teledev.twl.R;

import java.util.List;

public class NotificationCustomAdapter extends BaseAdapter {

    private Context mContext;
    private List<NotificationResult> notificationResultList;

    public NotificationCustomAdapter(Context mContext, List<NotificationResult> notificationResultList) {
        this.mContext = mContext;
        this.notificationResultList = notificationResultList;
    }

    @Override
    public int getCount() {
        return notificationResultList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = mInflater.inflate(R.layout.listview_notification, parent, false);

        NotificationResult notificationResult = notificationResultList.get(position);

        TextView txtContent = convertView.findViewById(R.id.content_notific);
        txtContent.setText(notificationResult.getMessagealarm());

        TextView txtAlarm = convertView.findViewById(R.id.datetime_notific);
        txtAlarm.setText(notificationResult.getCwhen());


        return convertView;
    }

    public List<NotificationResult> getNotificationResultList() {
        return notificationResultList;
    }

}
