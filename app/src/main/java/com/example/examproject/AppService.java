package com.example.examproject;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppService implements Response.ErrorListener{
    static AppService SINGELTON;

    User user;

    String token;
    RequestQueue requestQueue;


    public static AppService initialize(Context context, String token){
        SINGELTON = new AppService(context, token);
        return SINGELTON;
    }

    public static AppService getInstance(){
        return SINGELTON;
    }

    public AppService(Context context, String token){
        this.token = token;
        this.requestQueue = Volley.newRequestQueue(context);
        //loadUser();
    }

    public void getProjects(Callback<List<Project>> onPostExecute){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "fakeurl", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Project> result = new ArrayList<>();
                try{
                    for(int i = 0; i < response.length(); i++){
                        Project project = new Project(response.getJSONObject(i));
                        System.out.println(project.getTitle());
                        result.add(new Project(response.getJSONObject(i)));
                    }
                } catch (JSONException e){
                    //onError.onErrorResponse(new VolleyError(e));
                    e.printStackTrace();
                }
                onPostExecute.onPostExecute(result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //cyka
            }
        });
    }


    public interface Callback<Result>{
        void onPostExecute(Result result);
    }


    public HttpURLConnection getSecureConnection(String url) throws IOException {
        HttpURLConnection result = (HttpURLConnection) new URL(url).openConnection();
        result.setRequestProperty("Authorization", "Bearer " + token);
        result.setConnectTimeout(3000);
        return result;
    }

    @Override
    public void onErrorResponse(VolleyError error){
        System.out.println("Error: " + error);
    }


    protected Map<String, String> getHeaders(){
        HashMap<String, String> result = new HashMap<>();
        System.out.println("Token: " + token);
        result.put("Authorization", "Bearer " + token);
        return result;
    }





    class SecuredJsonArrayRequest extends JsonArrayRequest{
        public SecuredJsonArrayRequest(int method, String url, @Nullable JSONArray jsonRequest,
                                       Response.Listener<JSONArray> listener, @Nullable Response.ErrorListener errorListener){
            super(method, url, jsonRequest, listener, errorListener);
        }



        @Override
        public Map<String, String> getHeaders(){
            return AppService.this.getHeaders();
        }
    }
}