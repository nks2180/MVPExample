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
        view.initAdapter();
        syncAllTaskfromServer();
    }


    public void syncAllTaskfromServer() {
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

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    for (int i = 0; i < result.data.size(); i++) {
                        Data note = realm.copyToRealmOrUpdate(result.data.get(i));
                        realm.copyToRealmOrUpdate(note);
                        if (note.state == 0) {
                            pendingTasks.add(note);
                        } else {
                            completedTasks.add(note);
                        }
                    }
                    realm.commitTransaction();


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
