package com.example.leavemanager.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity public class DetailsModel {
    @Id long id;

    String duration,username,dateToday;

    public DetailsModel(String duration, String username, String dateToday) {
        this.duration = duration;
        this.username = username;
        this.dateToday = dateToday;
    }

    public DetailsModel() {
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateToday() {
        return dateToday;
    }

    public void setDateToday(String dateToday) {
        this.dateToday = dateToday;
    }
}
