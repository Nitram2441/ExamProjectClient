package com.example.examproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.examproject.AppService;
import com.example.examproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView welcome = (TextView) view.findViewById(R.id.text_home);
        TextView tv2 = (TextView) view.findViewById(R.id.text_home2);
        TextView tv3 = (TextView) view.findViewById(R.id.text_home3);
        TextView tv4 = (TextView) view.findViewById(R.id.text_home4);

        welcome.setText(("Welcome " + AppService.getInstance().getUser().getUserId() + "!"));
        tv2.setText(("To start or end a job, head over to the projects tab and select one!"));

        boolean isAdmin = false;
        JSONArray jsonArray = AppService.getInstance().getUser().getGroups();
        JSONObject jsonObject;


        for(int i = 0; i < jsonArray.length(); i++){

            try {
                jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getString("name").equals("admin")){
                    isAdmin = true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        if(isAdmin){
            tv3.setText(("To create a new project, head over to the projects tab and hit the + sign on the toolbar!"));
        }


        return view;
    }
}