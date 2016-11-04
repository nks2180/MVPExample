package com.app.todo.presenter;

import com.app.todo.model.Data;

import java.util.List;

/**
 * Created by niranjan on 03/11/16.
 */

public interface HomeView extends BaseView{
    void updatePendingTasks(List<Data> pendingTasks);

    void updateCompletedTasks(List<Data> completedTasks);

    void initAdapter();
}
