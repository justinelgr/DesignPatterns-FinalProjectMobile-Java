package com.example.finalprojectmobile.user;

public class AdministratorUser extends AdministratorUserDecorator {

    public AdministratorUser(User user){
        super(user);
    }

    public void decorate(){
        super.decorate();
    }
}
