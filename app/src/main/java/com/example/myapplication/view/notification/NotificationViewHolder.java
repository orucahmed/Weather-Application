package com.example.myapplication.view.notification;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.notificationCity)
    protected TextView notificationCity;

    @BindView(R.id.notificationCheckBox)
    protected CheckBox notificationCheckBox;

    private final NotificationRecycleViewAdapter.OnClickInterface onClickInterface;

    public NotificationViewHolder(@NonNull View itemView, NotificationRecycleViewAdapter.OnClickInterface onClickInterface) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.onClickInterface = onClickInterface;
        notificationCheckBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        onClickInterface.onChecked(isChecked, getAdapterPosition());
    }
}
