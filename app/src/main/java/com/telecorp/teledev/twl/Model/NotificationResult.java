package com.telecorp.teledev.twl.Model;

import com.google.gson.annotations.Expose;

public class NotificationResult {

    @Expose
    int uid;
    @Expose
    int userid;
    @Expose
    String macid;
    @Expose
    String result;
    @Expose
    String cwhen;
    @Expose
    int msorganisationuid;
    @Expose
    String messagealarm;
    @Expose
    String refriguid;
    @Expose
    int respondCode;
    @Expose
    String respondDescription;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getMacid() {
        return macid;
    }

    public void setMacid(String macid) {
        this.macid = macid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCwhen() {
        return cwhen;
    }

    public void setCwhen(String cwhen) {
        this.cwhen = cwhen;
    }

    public int getMsorganisationuid() {
        return msorganisationuid;
    }

    public void setMsorganisationuid(int msorganisationuid) {
        this.msorganisationuid = msorganisationuid;
    }

    public String getMessagealarm() {
        return messagealarm;
    }

    public void setMessagealarm(String messagealarm) {
        this.messagealarm = messagealarm;
    }

    public String getRefriguid() {
        return refriguid;
    }

    public void setRefriguid(String refriguid) {
        this.refriguid = refriguid;
    }

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
