package com.okorkut.realmgrafikornek;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("exampleRealm").build();
        Realm.setDefaultConfiguration(config);
    }
}
