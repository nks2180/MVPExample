package com.app.todo.component;

import com.app.todo.MaterialApplication;
import com.app.todo.activity.HomeActivity;
import com.app.todo.module.ApplicationModule;
import com.app.todo.module.DatabaseModule;
import com.app.todo.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by niranjan on 03/11/16.
 */
@Singleton
@Component(modules={ApplicationModule.class, NetModule.class, DatabaseModule.class})
public interface ApplicationComponent {

    void inject(MaterialApplication materialApplication);

    void inject(HomeActivity homeActivity);
}
