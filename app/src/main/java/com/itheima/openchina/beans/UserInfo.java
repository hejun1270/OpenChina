package com.itheima.openchina.beans;

import java.io.Serializable;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/6 <p/>
 * Time: 10:28 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.beans <p/>
 * Desc:
 */

public class UserInfo implements Serializable {
    public String name;
    public String portrait;
    public String jointime;
    public String gender;
    public String score;
    public String from;
    public String devplatform;
    public String expertise;
    public String favoritecount;
    public String fans;
    public String followers;
    public String atmeCount;
    public String msgCount;
    public String reviewCount;
    public String newFansCount;
    public String newLikeCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getJointime() {
        return jointime;
    }

    public void setJointime(String jointime) {
        this.jointime = jointime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDevplatform() {
        return devplatform;
    }

    public void setDevplatform(String devplatform) {
        this.devplatform = devplatform;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getFavoritecount() {
        return favoritecount;
    }

    public void setFavoritecount(String favoritecount) {
        this.favoritecount = favoritecount;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
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
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", portrait='" + portrait + '\'' +
                ", jointime='" + jointime + '\'' +
                ", gender='" + gender + '\'' +
                ", score='" + score + '\'' +
                ", from='" + from + '\'' +
                ", devplatform='" + devplatform + '\'' +
                ", expertise='" + expertise + '\'' +
                ", favoritecount='" + favoritecount + '\'' +
                ", fans='" + fans + '\'' +
                ", followers='" + followers + '\'' +
                ", atmeCount='" + atmeCount + '\'' +
                ", msgCount='" + msgCount + '\'' +
                ", reviewCount='" + reviewCount + '\'' +
                ", newFansCount='" + newFansCount + '\'' +
                ", newLikeCount='" + newLikeCount + '\'' +
                '}';
    }
}
