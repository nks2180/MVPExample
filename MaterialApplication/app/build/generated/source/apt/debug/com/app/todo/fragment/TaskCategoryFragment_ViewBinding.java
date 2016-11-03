// Generated code from Butter Knife. Do not modify!
package com.app.todo.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.todo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskCategoryFragment_ViewBinding<T extends TaskCategoryFragment> implements Unbinder {
  protected T target;

  @UiThread
  public TaskCategoryFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.recyclerVwItems = Utils.findRequiredViewAsType(source, R.id.recyclerVw_items, "field 'recyclerVwItems'", RecyclerView.class);
    target.swipeLytItems = Utils.findRequiredViewAsType(source, R.id.swipeLyt_items, "field 'swipeLytItems'", SwipeRefreshLayout.class);
    target.prgrsLoading = Utils.findRequiredViewAsType(source, R.id.prgrs_loading, "field 'prgrsLoading'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.recyclerVwItems = null;
    target.swipeLytItems = null;
    target.prgrsLoading = null;

    this.target = null;
  }
}
