package com.example.myapplication.data.weather;

public class NotificationList {
    private String name;
    private String checked;
    private String id;

    public NotificationList(String name, String checked, String id) {
        this.name = name;
        this.checked = checked;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
