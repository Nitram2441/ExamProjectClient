package com.example.examproject;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class WorkHour {
    int workHourId;
    String start;
    String stop;
    String comment;
    String userId;
    String projectName;

    public WorkHour(JSONObject jsonObject) throws JSONException{
        setWorkHourId(jsonObject.getInt("entityId"));
        if(jsonObject.has("workStart")){
            setStart(jsonObject.getString("workStart"));
        }
        if(jsonObject.has("comment")){
            setComment(jsonObject.getString("comment"));
        }
        if(jsonObject.has("employee")){
            JSONObject u;
            u = jsonObject.getJSONObject("employee");
            setUser(u.getString("userid"));
        }
        if(jsonObject.has("project")){
            JSONObject u;
            u = jsonObject.getJSONObject("project");
            setProjectName(u.getString("name"));
        }

    }
    public String getProjectName(){
        return projectName;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public int getWorkHourId() {
        return workHourId;
    }

    public void setWorkHourId(int workHourId) {
        this.workHourId = workHourId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser(String userid){
        this.userId = userid;
    }

    public String getUser(){
        return userId;
    }
}
