package com.app.todo;

import android.app.Application;

import com.app.todo.component.ApplicationComponent;
import com.app.todo.component.DaggerApplicationComponent;
import com.app.todo.module.ApplicationModule;
import com.app.todo.module.DatabaseModule;
import com.app.todo.module.NetModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by niranjan on 03/11/16.
 */
public class MaterialApplication extends Application {

    private static final String BASE_URL = "https://dl.dropboxusercontent.com/";

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setApplicationComponent();
    }

    private void setApplicationComponent() {
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule(BASE_URL))
                .databaseModule(new DatabaseModule(this))
                .build();

        mComponent.inject(this);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
