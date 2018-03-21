package com.prudent.busoftadmin;

import android.app.Application;



import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Dharmik Patel on 22-May-17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);


    }
    }

