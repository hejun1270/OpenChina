package com.itheima.openchina.beans;

import java.io.Serializable;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/5 <p/>
 * Time: 11:25 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.beans <p/>
 * Desc:登录信息
 */

public class LoginInfo implements Serializable {
    private String cookie;
    private String errorCode;
    private String errorMessage;
    private String uid;
    private String location;
    private String name;
    private String followers;
    private String fans;
    private String score;
    private String favoritecount;
    private String gender;
    private String atmeCount;
    private String msgCount;
    private String reviewCount;
    private String newFansCount;
    private String newLikeCount;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFavoritecount() {
        return favoritecount;
    }

    public void setFavoritecount(String favoritecount) {
        this.favoritecount = favoritecount;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAtmeCount() {
        return atmeCount;
    }

    public void setAtmeCount(String atmeCount) {
        this.atmeCount = atmeCount;
    }

    public String getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getNewFansCount() {
        return newFansCount;
    }

    public void setNewFansCount(String newFansCount) {
        this.newFansCount = newFansCount;
    }

    public String getNewLikeCount() {
        return newLikeCount;
    }

    public void setNewLikeCount(String newLikeCount) {
        this.newLikeCount = newLikeCount;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "cookie='" + cookie + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", uid='" + uid + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", followers='" + followers + '\'' +
                ", fans='" + fans + '\'' +
                ", score='" + score + '\'' +
                ", favoritecount='" + favoritecount + '\'' +
                ", gender='" + gender + '\'' +
                ", atmeCount='" + atmeCount + '\'' +
                ", msgCount='" + msgCount + '\'' +
                ", reviewCount='" + reviewCount + '\'' +
                ", newFansCount='" + newFansCount + '\'' +
                ", newLikeCount='" + newLikeCount + '\'' +
                '}';
    }


}
