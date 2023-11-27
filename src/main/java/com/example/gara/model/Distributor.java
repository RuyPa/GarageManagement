package com.example.gara.model;

import java.util.Date;


public class Distributor extends Member{
    public Distributor(int id, String username, String password, String name, Date dob, String email, String phonenumber) {
        super(id, username, password, name, dob, email, phonenumber);
    }

    public Distributor() {
    }
}
