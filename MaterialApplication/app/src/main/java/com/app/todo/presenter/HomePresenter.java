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

/**
 * Created by mohitesh on 03/11/16.
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
        syncAllTaskfromServer();
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
                List<Data> pendingTasks = new ArrayList<>();
                List<Data> completedTasks = new ArrayList<>();

                for(Data data : result.data) {
                    if(data.state == 0) {
                        pendingTasks.add(data);
                    } else {
                        completedTasks.add(data);
                    }
                }

                view.updatePendingTasks(pendingTasks);
                view.updateCompletedTasks(completedTasks);
            }
        });
    }

    @Override
    public void onError(int type) {

    }
}
