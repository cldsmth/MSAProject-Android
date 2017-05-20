package com.android.msaproject.api.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("user_ID")
    @Expose
    private String userID;
    @SerializedName("user_fname")
    @Expose
    private String userFname;
    @SerializedName("user_lname")
    @Expose
    private String userLname;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_img")
    @Expose
    private String userImg;
    @SerializedName("user_img_thmb")
    @Expose
    private String userImgThmb;
    @SerializedName("user_login_via")
    @Expose
    private String userLoginVia;
    @SerializedName("user_reg_via")
    @Expose
    private String userRegVia;
    @SerializedName("user_auth_code")
    @Expose
    private String userAuthCode;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public String getUserLname() {
        return userLname;
    }

    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserImgThmb() {
        return userImgThmb;
    }

    public void setUserImgThmb(String userImgThmb) {
        this.userImgThmb = userImgThmb;
    }

    public String getUserLoginVia() {
        return userLoginVia;
    }

    public void setUserLoginVia(String userLoginVia) {
        this.userLoginVia = userLoginVia;
    }

    public String getUserRegVia() {
        return userRegVia;
    }

    public void setUserRegVia(String userRegVia) {
        this.userRegVia = userRegVia;
    }

    public String getUserAuthCode() {
        return userAuthCode;
    }

    public void setUserAuthCode(String userAuthCode) {
        this.userAuthCode = userAuthCode;
    }

}