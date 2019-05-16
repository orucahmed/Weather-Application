package com.example.myapplication.view.notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.data.notification.NotificationList;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationRecycleViewAdapter extends RecyclerView.Adapter<NotificationViewHolder> {

    public interface OnClickInterface{
        void onChecked(boolean checked, int position);
    }

    private final List<NotificationList> notificationList = new ArrayList<>();

    public void setNotificationList(List<NotificationList> notificationList){
        this.notificationList.clear();
        this.notificationList.addAll(notificationList);
    }

    public List<NotificationList> getNotificationList(){
        return notificationList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_view_holder, parent, false);
        return new NotificationViewHolder(mView, new OnClickInterface() {
            @Override
            public void onChecked(boolean checked, int position) {
                notificationList.get(position).setChecked(String.valueOf(checked));
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.notificationCity.setText(notificationList.get(position).getName());
        holder.notificationCheckBox.setChecked(Boolean.valueOf(notificationList.get(position).getChecked()));

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}
