package com.app.todo.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.app.todo.OnSwipeRefreshListener;
import com.app.todo.R;
import com.app.todo.component.ApplicationComponent;
import com.app.todo.fragment.TaskCategoryFragment;
import com.app.todo.model.Data;
import com.app.todo.presenter.HomePresenter;
import com.app.todo.presenter.HomeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import io.realm.Realm;

public class HomeActivity extends BaseViewPresenterActivity<HomePresenter> implements HomeView, ViewPager.OnPageChangeListener, OnSwipeRefreshListener {

    private static final Integer PENDING_FEEDBACK = 0;
    private static final Integer DONE_FEEDBACK = 1;
    @Inject
    HomePresenter homePresenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager_tab)
    ViewPager viewpagerTab;

    TasCategoryPageAdapter tasCategoryPageAdapter;
    private MenuItem menuItem_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

    }

    //initialize ui fields
    private void setUpUiElements() {
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tasCategoryPageAdapter = new TasCategoryPageAdapter(getSupportFragmentManager());

        TaskCategoryFragment pendingFragment = TaskCategoryFragment.newInstance(PENDING_FEEDBACK);
        pendingFragment.setSwipeRefreshListener(this);
        TaskCategoryFragment doneFragment = TaskCategoryFragment.newInstance(DONE_FEEDBACK);
        doneFragment.setSwipeRefreshListener(this);

        tasCategoryPageAdapter.addFragment(pendingFragment, "PENDING");
        tasCategoryPageAdapter.addFragment(doneFragment, "DONE");

        viewpagerTab.setAdapter(tasCategoryPageAdapter);
        viewpagerTab.addOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewpagerTab);
    }

    @Override
    protected int getMainLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void initializePresenter() {
        super.initializePresenter(homePresenter, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuItem_add = menu.findItem(R.id.action_add);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                showAddTaskDialog();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText editTextTaskName = new EditText(this);
        editTextTaskName.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        editTextTaskName.setOnFocusChangeListener((v, hasFocus) -> {
            showKeyBoard();
        });

        builder.setTitle("Add New Task").setMessage("Please enter the name of task:").setView(editTextTaskName).setPositiveButton("Add", (dialog, which) -> {
            if (editTextTaskName.getText().toString().trim().length() > 0) {
                Data note = new Data();
                note.name = editTextTaskName.getText().toString();
                note.state = 0;
                note.id = new Random().nextInt();

                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(note);
                realm.commitTransaction();
                ((TaskCategoryFragment) tasCategoryPageAdapter.getFragmentForPosition(0)).addData(note);
            } else
                Toast.makeText(HomeActivity.this, "Please enter task Name", Toast.LENGTH_SHORT).show();

        }).setNegativeButton(android.R.string.cancel, (dialog, which) -> {
            dialog.dismiss();
        });


        editTextTaskName.setFocusable(true);
        editTextTaskName.setFocusableInTouchMode(true);
        editTextTaskName.requestFocus();
        builder.show();
    }

    private void showKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    @Override
    public void updatePendingTasks(List<Data> pendingTasks) {
        ((TaskCategoryFragment) tasCategoryPageAdapter.getFragmentForPosition(0)).refreshData((ArrayList<Data>) pendingTasks);
    }

    @Override
    public void updateCompletedTasks(List<Data> completedTasks) {
        ((TaskCategoryFragment) tasCategoryPageAdapter.getFragmentForPosition(1)).refreshData((ArrayList<Data>) completedTasks);
    }

    @Override
    public void initAdapter() {
        setUpUiElements();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            menuItem_add.setVisible(true);
        } else {
            menuItem_add.setVisible(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onRefresh() {
        if (null != homePresenter)
            homePresenter.syncAllTaskfromServer();
    }


    private class TasCategoryPageAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        TasCategoryPageAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public Fragment getFragmentForPosition(int position) {
            return mFragmentList.get(position);
        }
    }
}
