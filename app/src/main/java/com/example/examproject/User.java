package com.example.examproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
    String userId;
    String eMail;
    Boolean atWork;
    JSONArray groups;

    public User(){

    }

    public User(JSONObject jsonObject) throws JSONException {
        setUserId(jsonObject.getString("userid"));
        if (jsonObject.has("email")){
            seteMail(jsonObject.getString("email"));
        }
        if (jsonObject.has("atWork")){
            setAtWork(jsonObject.getBoolean("atWork"));
        }
        if(jsonObject.has("groups")){
            setGroups(jsonObject.getJSONArray("groups"));
        }
    }

    public JSONArray getGroups() {
        return groups;
    }

    public void setGroups(JSONArray groups) {
        this.groups = groups;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getAtWork() {
        return atWork;
    }

    public void setAtWork(Boolean atWork) {
        this.atWork = atWork;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}