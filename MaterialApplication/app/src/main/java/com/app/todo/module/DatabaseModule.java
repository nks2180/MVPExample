package com.app.todo.module;

import android.content.Context;

import com.app.todo.DBAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohitesh on 04/07/16.
 */
@Module
public class DatabaseModule {

    private final Context context;

    public DatabaseModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    DBAdapter.DatabaseHelper provideDBHelper() {
        return new DBAdapter.DatabaseHelper(context);
    }


    @Provides
    @Singleton
    DBAdapter providesDBAdapter(DBAdapter.DatabaseHelper databaseHelper) {
        return new DBAdapter(context,databaseHelper);
    }


}
