package com.example.myapplication.view.cityList;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private CityListRecycleViewAdapter mAdapter;
    private final DeleteItemSwipeListener listener;
    public SwipeToDeleteCallback(CityListRecycleViewAdapter adapter, DeleteItemSwipeListener listener) {
        super(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.listener=listener;
        mAdapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onItemDeleted(viewHolder.itemView,viewHolder.getAdapterPosition());
        mAdapter.deleteItem(viewHolder.getAdapterPosition());
    }
}