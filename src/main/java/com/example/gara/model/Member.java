package com.example.gara.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Table(name = "tblmember")
@Entity
public class Member {
    @Id
    private int id;
    private String username;
    private String password;
    private String name;
    private Date dob;
    private String email;
    private String phonenumber;
    private String address;

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
