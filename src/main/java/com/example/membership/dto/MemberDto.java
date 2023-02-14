package com.example.membership.dto;

import com.example.membership.entity.Member;

public class MemberDto {
    private String userID;
    private String userPW;

    public MemberDto(String userID, String userPW){
        this.userID = userID;
        this.userPW = userPW;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPW() {
        return userPW;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "userID='" + userID + '\'' +
                ", userPW='" + userPW + '\'' +
                '}';
    }

    public Member toEntity() {
        return new Member(userID, userPW);
    }
}
