package com.app.todo.presenter;

import android.content.Context;
import android.util.Log;

import com.app.todo.executor.ParsingExecutor;
import com.app.todo.model.Data;
import com.app.todo.model.TaskResponse;
import com.app.todo.retrofit.ApiController;
import com.app.todo.retrofit.ApiDataReceiveCallback;
import com.app.todo.retrofit.NetworkConstants;
import com.app.todo.retrofit.RequestBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by niranjan on 03/11/16.
 */

public class HomePresenter extends BasePresenterImpl<HomeView> implements ApiDataReceiveCallback {

    @Inject
    ApiController apiController;

    @Inject
    ParsingExecutor parsingExecutor;

    @Inject
    HomePresenter(Context baseContext) {
        super(baseContext);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // getNotesFromDB();
        syncAllTaskfromServer();
    }

    private void getNotesFromDB(){
        try {
            Realm realm = Realm.getDefaultInstance();
            RealmQuery<Data> query = realm.where(Data.class);
            RealmResults<Data> savedNotes = query.findAll();
            if (savedNotes.size() > 0 && null != view) {
                List<Data> pendingTasks = new ArrayList<>();
                List<Data> completedTasks = new ArrayList<>();

                for (Data note: savedNotes) {
                    if (note.state == 0)
                        pendingTasks.add(note);
                    else
                        completedTasks.add(note);
                }
                view.updatePendingTasks(pendingTasks);
                view.updateCompletedTasks(completedTasks);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void syncAllTaskfromServer() {
        RequestBuilder requestBuilder = new RequestBuilder(NetworkConstants.API_FETCH_TASKS_FROM_SERVER);
        apiController.hitApi(requestBuilder,this);
    }

    @Override
    public void onDataReceived(String response, int type) {
        Log.i("Response",response);
        parseResponseUsingExecutor(response);
    }

    private void parseResponseUsingExecutor(String response) {
        parsingExecutor.execute(TaskResponse.class, response, new ParsingExecutor.ParsingCallback<TaskResponse>() {
            @Override
            public void onParsingCompleted(TaskResponse result) {
                try {
                    List<Data> pendingTasks = new ArrayList<>();
                    List<Data> completedTasks = new ArrayList<>();

//                    Realm realm = Realm.getDefaultInstance();
//                    realm.beginTransaction();
                    for (int i = 0; i < result.data.size(); i++) {
                        //Data data = realm.copyToRealmOrUpdate(result.data.get(i));
                        Data note = result.data.get(i);
                        //realm.copyToRealmOrUpdate(data);
                        if (note.state == 0) {
                            pendingTasks.add(note);
                        } else {
                            completedTasks.add(note);
                        }
                    }
                    //realm.commitTransaction();


                    view.updatePendingTasks(pendingTasks);
                    view.updateCompletedTasks(completedTasks);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onError(int type) {

    }
}
