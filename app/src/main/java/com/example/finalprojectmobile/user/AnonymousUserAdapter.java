package com.example.finalprojectmobile.user;

public class AnonymousUserAdapter {

    public final User user;

    public AnonymousUserAdapter(){
        user = new User();
    }

    public void MakeUserAnonymous(AnonymousUser anonymousUser){
        user.setUsername(anonymousUser.getAnonymousUsername());
        user.setEmail(null);
        user.setPassword(null);
        user.setType("free");
    }
}
