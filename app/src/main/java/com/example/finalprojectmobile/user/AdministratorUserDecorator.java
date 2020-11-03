package com.example.finalprojectmobile.user;

public abstract class AdministratorUserDecorator extends User {

    public final User user;

    protected AdministratorUserDecorator(User user) {
        this.user = user;
    }

    @Override
    public void decorate(){
        user.decorate();
    }
}
