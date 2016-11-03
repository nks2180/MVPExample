// Generated code from Butter Knife. Do not modify!
package com.app.todo;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskListAdapter$FeedbackViewHolder_ViewBinding<T extends TaskListAdapter.FeedbackViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public TaskListAdapter$FeedbackViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.txtVwName = Utils.findRequiredViewAsType(source, R.id.txtVw_name, "field 'txtVwName'", TextView.class);
    target.frmLyt_main = Utils.findRequiredViewAsType(source, R.id.frmLyt_main, "field 'frmLyt_main'", CardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.txtVwName = null;
    target.frmLyt_main = null;

    this.target = null;
  }
}
