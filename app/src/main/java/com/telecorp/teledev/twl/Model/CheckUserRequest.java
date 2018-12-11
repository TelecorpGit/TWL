package com.telecorp.teledev.twl.Model;

import com.google.gson.annotations.Expose;

public class CheckUserRequest {
    @Expose
    String Username;
    @Expose
    String Password;

//    Update
    @Expose
    int UID;
    @Expose
    String DeviceID;
    @Expose
    String Platform;


    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
