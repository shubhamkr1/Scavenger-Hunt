package com.example.tyler.scavengerhunt1;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Tyler on 02-12-2016.
 */
public class DatabaseLink extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
