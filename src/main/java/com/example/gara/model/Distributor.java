package com.example.gara.model;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class Distributor extends Member{
    public Distributor(int id, String username, String password, String name, Date dob, String email, String phonenumber) {
        super(id, username, password, name, dob, email, phonenumber);
    }

    public Distributor() {
    }
}
