package com.example.gara.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Employee extends Member{
    private String role;

    public Employee() {
    }

    public Employee(int id, String username, String password, String name, Date dob, String email, String phonenumber, String role) {
        super(id, username, password, name, dob, email, phonenumber);
        this.role = role;
    }

    public Employee(String role) {
        this.role = role;
    }
}
