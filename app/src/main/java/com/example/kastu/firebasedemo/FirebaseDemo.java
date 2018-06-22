package com.example.kastu.firebasedemo;

import android.app.Application;

import com.firebase.client.Firebase;

public class FirebaseDemo extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
