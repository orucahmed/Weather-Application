package com.example.myapplication.view.notification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import com.example.myapplication.R;
import com.example.myapplication.data.notification.NotificationList;
import com.example.myapplication.di.MyApplication;
import com.example.myapplication.presentation.notification.NotificationPresenter;
import com.example.myapplication.view.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends AppCompatActivity implements NotificationPresenter.NotificationView {

    @Inject
    NotificationPresenter presenter;

    @BindView(R.id.notificatioRecycleView)
    protected RecyclerView recyclerView;

    @BindView(R.id.enable)
    protected RadioButton enableBtn;

    @BindView(R.id.disable)
    protected RadioButton disableBtn;

    private NotificationRecycleViewAdapter adapter;

    @OnClick(R.id.closeBtn)
    public void onClick(){
        presenter.populatePref(adapter.getNotificationList());
        presenter.populatePrefNotification(enableBtn.isChecked());
        Intent myIntent = new Intent(NotificationActivity.this, MainActivity.class);
        NotificationActivity.this.startActivity(myIntent);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        presenter.onCreate(this);
        adapter=new NotificationRecycleViewAdapter();
        presenter.loadPref();
        presenter.pupulateRadioBtn();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void setNotificartion(List<NotificationList> notificationList) {
        adapter.setNotificationList(notificationList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setRadioBtn(boolean enable) {
        enableBtn.setChecked(enable);
        disableBtn.setChecked(!enable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.populatePrefNotification(enableBtn.isChecked());
    }
}
