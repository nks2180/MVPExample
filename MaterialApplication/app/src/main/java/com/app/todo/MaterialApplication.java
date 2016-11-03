package com.app.todo;

import android.app.Application;

import com.app.todo.component.ApplicationComponent;
import com.app.todo.component.DaggerApplicationComponent;
import com.app.todo.module.ApplicationModule;
import com.app.todo.module.DatabaseModule;
import com.app.todo.module.NetModule;

/**
 * Created by mohitesh on 03/11/16.
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
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
