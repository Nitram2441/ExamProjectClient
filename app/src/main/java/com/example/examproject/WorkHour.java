package com.example.examproject;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class WorkHour {
    int workHourId;
    LocalDateTime start;
    LocalDateTime stop;
    String comment;

    public WorkHour(JSONObject jsonObject) throws JSONException{
        setWorkHourId(jsonObject.getInt("id"));
        /* needs to be converted from string to localdatetime
        if(jsonObject.has("start")){
            setStart(jsonObject.getString("start"));
        }
         */
        if(jsonObject.has("comment")){
            setComment(jsonObject.getString("comment"));
        }

    }

    public int getWorkHourId() {
        return workHourId;
    }

    public void setWorkHourId(int workHourId) {
        this.workHourId = workHourId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getStop() {
        return stop;
    }

    public void setStop(LocalDateTime stop) {
        this.stop = stop;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
