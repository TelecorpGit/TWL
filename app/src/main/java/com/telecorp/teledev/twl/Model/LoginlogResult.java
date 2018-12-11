package com.telecorp.teledev.twl.Model;

import com.google.gson.annotations.Expose;

public class LoginlogResult {

    @Expose
    int respondCode;
    @Expose
    String respondDescription;


    public int getRespondCode() {
        return respondCode;
    }

    public void setRespondCode(int respondCode) {
        this.respondCode = respondCode;
    }

    public String getRespondDescription() {
        return respondDescription;
    }

    public void setRespondDescription(String respondDescription) {
        this.respondDescription = respondDescription;
    }
}
