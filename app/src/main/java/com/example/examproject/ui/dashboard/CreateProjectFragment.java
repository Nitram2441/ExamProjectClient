package com.example.examproject.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.examproject.AbstractAsyncTask;
import com.example.examproject.AppService;
import com.example.examproject.MainActivity;
import com.example.examproject.PostProjectTask;
import com.example.examproject.Project;
import com.example.examproject.R;

import java.util.List;

public class CreateProjectFragment extends Fragment {



    public CreateProjectFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("CreateProject fragment created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_projectcreate, container, false);

        EditText projectName = (EditText) view.findViewById(R.id.textViewProjectName);
        EditText projectCustomer = (EditText) view.findViewById(R.id.textViewCustomer);
        EditText projectDescription = (EditText) view.findViewById(R.id.textViewDescription);



        final Button button = (Button) view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("saaaaaah");
                System.out.println(projectName.getText());
                System.out.println(projectCustomer.getText());
                System.out.println(projectDescription.getText());

                Project project = new Project();
                project.setTitle(projectName.getText().toString());
                project.setProjectManagerId("0");
                project.setCustomer(projectCustomer.getText().toString());
                project.setDescription(projectDescription.getText().toString());

                new PostProjectTask(new AbstractAsyncTask.OnPostExecute<List<Project>>() {
                    @Override
                    public void onPostExecute(List<Project> projects) {
                        System.out.println(project.getTitle());
                    }
                }, this::onException).execute(project);


                //this is a temporary thing, at some point i'll have to figure out how to make sure
                //it is added to the server before navigating back
                long timer = System.currentTimeMillis() + 50;
                while(timer > System.currentTimeMillis()){

                }
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.navigation_dashboard);
            }

            protected void onException(Throwable throwable) {
            }
        });


        return view;
    }



}
