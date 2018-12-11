package com.telecorp.teledev.twl.Model;

import com.google.gson.annotations.Expose;

public class LoginlogRequest {

    @Expose
    int useruid;
    @Expose
    String deviceid;
    @Expose
    String platfrom ;
    @Expose
    String statuslogin ;
    @Expose
    String fromapp ;

    public int getUseruid() {
        return useruid;
    }

    public void setUseruid(int useruid) {
        this.useruid = useruid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getPlatfrom() {
        return platfrom;
    }

    public void setPlatfrom(String platfrom) {
        this.platfrom = platfrom;
    }

    public String getStatuslogin() {
        return statuslogin;
    }

    public void setStatuslogin(String statuslogin) {
        this.statuslogin = statuslogin;
    }

    public String getFromapp() {
        return fromapp;
    }

    public void setFromapp(String fromapp) {
        this.fromapp = fromapp;
    }
}
