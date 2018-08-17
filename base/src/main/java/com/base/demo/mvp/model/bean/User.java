package com.base.demo.mvp.model.bean;

import android.text.TextUtils;

import com.google.gson.JsonElement;

/**
 *
 */
public class User {
    private String id;
    private String token;
    private String email;
    private String mobile;
    private String tradeNo;
    private String serverType;
    private String nickName;
    private String userName;
    private String headImgUrl;
    private String tgt;
    private String cascookie;
    private boolean isBindingWb;
    private boolean isBindingWx;
    private boolean isBindingQq;
    private JsonElement tradeNos;

    public JsonElement getTradeNos() {
        return tradeNos;
    }

    public void setTradeNos(JsonElement tradeNos) {
        this.tradeNos = tradeNos;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTgt() {
        return tgt;
    }

    public void setTgt(String tgt) {
        this.tgt = tgt;
    }

    public String getCascookie() {
        return cascookie;
    }

    public void setCascookie(String cascookie) {
        this.cascookie = cascookie;
    }

    public boolean isBindingWb() {
        return isBindingWb;
    }

    public void setIsBindingWb(boolean isBindingWb) {
        this.isBindingWb = isBindingWb;
    }

    public boolean isBindingWx() {
        return isBindingWx;
    }

    public void setIsBindingWx(boolean isBindingWx) {
        this.isBindingWx = isBindingWx;
    }

    public boolean isBindingQq() {
        return isBindingQq;
    }

    public void setIsBindingQq(boolean isBindingQq) {
        this.isBindingQq = isBindingQq;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    /**
     * 获取加密的手机号
     * @return
     */
    public String getMobileSealed() {
        return changeToSealedMobile(mobile);
    }

    public static String changeToSealedMobile(String mobileNumber) {
        if (TextUtils.isEmpty(mobileNumber) || mobileNumber.length() < 11) {
            return mobileNumber;
        } else {
            return mobileNumber.substring(0, 3) + "****" + mobileNumber.substring(7, 11);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", token='" + token + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", serverType='" + serverType + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userName='" + userName + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", tgt='" + tgt + '\'' +
                ", cascookie='" + cascookie + '\'' +
                ", isBindingWb=" + isBindingWb +
                ", isBindingWx=" + isBindingWx +
                ", isBindingQq=" + isBindingQq +
                '}';
    }
}
