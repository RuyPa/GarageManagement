package com.example.gara.model;

import java.util.Date;

public class Client extends Member{
    public Client(int id, String username, String password, String name, Date dob, String email, String phonenumber) {
        super(id, username, password, name, dob, email, phonenumber);
    }

    public Client() {
    }
}
