
package com.app.todo;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.todo.model.Data;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohitesh on 21/09/16.
 */

public class TaskListAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private final ArrayList<Data> feedbackArrayList;
    private final OnItemClickListener onItemClickListener;

    public TaskListAdapter(Context mContext, ArrayList<Data> feedbackArrayList, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.feedbackArrayList = feedbackArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_task_item, parent, false);
        return new FeedbackViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Data data = feedbackArrayList.get(position);

        FeedbackViewHolder feedbackViewHolder = (FeedbackViewHolder) holder;

        if(!TextUtils.isEmpty(data.name)) {
            feedbackViewHolder.txtVwName.setText(data.name);
        }

        if(isSelected(position)) {
            feedbackViewHolder.frmLyt_main.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorAccent));
        } else {
            feedbackViewHolder.frmLyt_main.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return feedbackArrayList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    static class FeedbackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private WeakReference<OnItemClickListener> onItemClickListenerWeakReference;

        @BindView(R.id.txtVw_name)
        TextView txtVwName;
        @BindView(R.id.frmLyt_main)
        CardView frmLyt_main;

        FeedbackViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnLongClickListener(this);
            view.setOnClickListener(this);

            if(onItemClickListener != null) onItemClickListenerWeakReference = new WeakReference<>(onItemClickListener);
        }

        @Override
        public void onClick(View v) {
            OnItemClickListener onItemClickListener = onItemClickListenerWeakReference.get();
            if(onItemClickListener != null) {
                onItemClickListener.onItemClick(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            OnItemClickListener onItemClickListener = onItemClickListenerWeakReference.get();
            if(onItemClickListener != null) {
                onItemClickListener.onItemLongClick(getAdapterPosition());
            }
            return true;
        }
    }
}
