package com.bfs.springmvcdemo.domain;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    private int contact_id;
    private String firstname;
    private String lastname;
    private String subject;
    private String message;

    public Contact(String firstname,String lastname,String subject, String message){
        this.firstname = firstname;
        this.lastname = lastname;
        this.subject = subject;
        this.message = message;
    }
}

