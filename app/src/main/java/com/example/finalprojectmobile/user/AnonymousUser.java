package com.example.finalprojectmobile.user;

import java.io.Serializable;

public class AnonymousUser implements Serializable {

    private String AnonymousUsername;

    public AnonymousUser() {
        AnonymousUsername = "AnonymousUser";
    }

    public String getAnonymousUsername(){
        return AnonymousUsername;
    }
}
