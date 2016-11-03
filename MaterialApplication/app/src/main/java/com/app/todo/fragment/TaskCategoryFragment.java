package com.app.todo.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.app.todo.R;
import com.app.todo.SlideInItemAnimator;
import com.app.todo.TaskListAdapter;
import com.app.todo.component.ApplicationComponent;
import com.app.todo.model.Data;
import com.app.todo.model.TaskResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * Created by mohitesh on 03/11/16.
 */

public class TaskCategoryFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, TaskListAdapter.OnItemClickListener {

    private static final String TASK_CATEGORY = "category";

    @BindView(R.id.recyclerVw_items)
    RecyclerView recyclerVwItems;
    @BindView(R.id.swipeLyt_items)
    SwipeRefreshLayout swipeLytItems;
    @BindView(R.id.prgrs_loading)
    ProgressBar prgrsLoading;

    TaskListAdapter taskListAdapter;

    private ActionMode actionMode;

    List<Data> dataList = new ArrayList<>();
    private ActionModeCallback actionModeCallback = new ActionModeCallback();

    public static TaskCategoryFragment newInstance(String category) {
        TaskCategoryFragment taskCategoryFragment = new TaskCategoryFragment();
        Bundle bundle = new Bundle();
        if(!TextUtils.isEmpty(category)) {
            bundle.putString(TASK_CATEGORY,category);
        }

        taskCategoryFragment.setArguments(bundle);

        return taskCategoryFragment;
    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    protected void injectComponent(ApplicationComponent component) {

    }

    @Override
    int getFragmentLayout() {
        return R.layout.fragment_task_category;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpFeedView();
    }

    public void setUpFeedView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerVwItems.setLayoutManager(gridLayoutManager);

        taskListAdapter = new TaskListAdapter(getContext(), (ArrayList<Data>) dataList,this);
        recyclerVwItems.setAdapter(taskListAdapter);

        recyclerVwItems.setItemAnimator(new SlideInItemAnimator());

        swipeLytItems.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(int position) {
        if (actionMode != null) {
            toggleSelection(position);
        }
    }

    @Override
    public void onItemLongClick(int position) {
        if (actionMode == null) {
            actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);
        }

        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        taskListAdapter.toggleSelection(position);
        int count = taskListAdapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    public void refreshData(List<Data> dataList) {
        boolean isPulledToRefresh = false;
        if(!this.dataList.isEmpty()) isPulledToRefresh = true;

        this.dataList.clear();
        this.dataList.addAll(dataList);

        if(isPulledToRefresh) {
            taskListAdapter.notifyDataSetChanged();
        } else {
            taskListAdapter.notifyItemRangeInserted(0,dataList.size());
        }

        prgrsLoading.setVisibility(View.GONE);
    }

    public void addData(Data data) {
        int position = dataList.size();
        dataList.add(data);
        taskListAdapter.notifyItemInserted(position);
    }

    private class ActionModeCallback implements ActionMode.Callback {
        @SuppressWarnings("unused")
        private final String TAG = ActionModeCallback.class.getSimpleName();

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate (R.menu.menu_delete, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    showDeleteAlert(mode);
                    return true;

                default:
                    return false;
            }
        }

        private void showDeleteAlert(ActionMode mode) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("Delete");
            String text = String.format(Locale.getDefault(),"Are your sure you want to delete %d items",taskListAdapter.getSelectedItemCount());
            dialog.setMessage(text);
            dialog.setPositiveButton("Yes", (dialog1, which) -> {
                List<Integer> selectedItemList = taskListAdapter.getSelectedItems();
                List<Data> tempList = new ArrayList<>();
                for(Integer integer : selectedItemList) {
                    tempList.add(dataList.get(integer.intValue()));
                }

                dataList.removeAll(tempList);
                taskListAdapter.notifyDataSetChanged();
                // TODO: actually remove items
                Log.d(TAG, "menu_remove");
                mode.finish();
            });
            dialog.setNegativeButton("No", (dialog12, which) -> dialog12.cancel());
            dialog.show();
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            taskListAdapter.clearSelection();
            actionMode = null;
        }
    }
}
