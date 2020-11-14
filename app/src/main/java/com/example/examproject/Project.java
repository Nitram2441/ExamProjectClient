package com.example.examproject;

import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

public class Project {
    int projectId;
    String title;
    String description;

    public Project(JSONObject jsonObject) throws JSONException {
        setProjectId(jsonObject.getInt("projectNumber"));
        if(jsonObject.has("name")){
            setTitle(jsonObject.getString("name"));
        }
        if(jsonObject.has("description")){
            setDescription(jsonObject.getString("description"));
        }
    }

    public Project(){

    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
