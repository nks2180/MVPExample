package com.app.todo.module;

import android.content.Context;

import com.app.todo.MaterialApplication;
import com.app.todo.executor.ParsingExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by niranjan on 03/11/16.
 */
@Module
public class ApplicationModule {

    private final MaterialApplication lybrate;

    public ApplicationModule(MaterialApplication lybrate) {
        this.lybrate = lybrate;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return lybrate;
    }

    @Provides
    @Singleton
    ParsingExecutor providesParsingExecutor() {
        return new ParsingExecutor();
    }

}
