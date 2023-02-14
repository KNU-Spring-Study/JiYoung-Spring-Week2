package com.example.membership.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "membership")
public class Member {

//    private Long num;

    @Id
    @Column(nullable = false, unique = true)
    private String userID;
    @Column(nullable = false)
    private String userPW;

    public Member(String userID, String userPW) {
//        this.num = num;
        this.userID = userID;
        this.userPW = userPW;

    }

    public Member() {

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
        return "Member{" +
                "userID='" + userID + '\'' +
                ", userPW='" + userPW + '\'' +
                '}';
    }
}
