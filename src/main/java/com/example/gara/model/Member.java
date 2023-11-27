package com.example.gara.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Member {
    private int id;
    private String username;
    private String password;
    private String name;
    private Date dob;
    private String email;
    private String phonenumber;

    public Member(int id, String username, String password, String name, Date dob, String email, String phonenumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public Member() {
    }
}
