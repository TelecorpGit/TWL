package com.telecorp.teledev.twl.Model;

import com.google.gson.annotations.Expose;

public class CheckUserResult {

    @Expose
    int UID;
    @Expose
    String Prename;
    @Expose
    String Forename;
    @Expose
    String Surname;
    @Expose
    String Username;
    @Expose
    String Password;
    @Expose
    int MSOrganisationUID;
    @Expose
    String type;
    @Expose
    String DeviceID;
    @Expose
    String Platform;

    @Expose
    int respondCode;
    @Expose
    String respondDescription;

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getPrename() {
        return Prename;
    }

    public void setPrename(String prename) {
        Prename = prename;
    }

    public String getForename() {
        return Forename;
    }

    public void setForename(String forename) {
        Forename = forename;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
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

    public int getMSOrganisationUID() {
        return MSOrganisationUID;
    }

    public void setMSOrganisationUID(int MSOrganisationUID) {
        this.MSOrganisationUID = MSOrganisationUID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
