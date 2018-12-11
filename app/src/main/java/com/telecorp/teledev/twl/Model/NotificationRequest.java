package com.telecorp.teledev.twl.Model;

import com.google.gson.annotations.Expose;

public class NotificationRequest {

    @Expose
    int msorganisationuid;

    public int getMsorganisationuid() {
        return msorganisationuid;
    }

    public void setMsorganisationuid(int msorganisationuid) {
        this.msorganisationuid = msorganisationuid;
    }
}
