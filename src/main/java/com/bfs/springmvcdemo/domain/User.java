package com.bfs.springmvcdemo.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String address;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private boolean admin;
    private boolean active;


    public User(String address,String firstname,String lastname, String email, String password,String phone, boolean admin){
        this.address = address;
        this.firstname = firstname;
        this.lastname =  lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.active = true;
        this.admin = admin;
    }


}
