package com.example.examproject.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examproject.AbstractAsyncTask;
import com.example.examproject.AppService;
import com.example.examproject.PostProjectTask;
import com.example.examproject.Project;
import com.example.examproject.R;
import com.example.examproject.User;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProjectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectFragment newInstance(String param1, String param2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_project, container, false);

        //retrieves clicked project
        System.out.println("Project fragment view created: " + AppService.getInstance().getTempProject().getProjectId());

        Project project = AppService.getInstance().getTempProject();

        //collect the ui elements that the code should alter
        TextView projectNumber = (TextView) view.findViewById(R.id.textViewProjectNumber);
        TextView projectName = (TextView) view.findViewById(R.id.textViewProjectName);
        TextView projectCustomer = (TextView) view.findViewById(R.id.textViewCustomer);
        TextView projectManager = (TextView) view.findViewById(R.id.textViewProjectManager);
        TextView projectDescription = (TextView) view.findViewById(R.id.textViewDescription);

        final Button startStop = (Button) view.findViewById(R.id.button);
        if(AppService.getInstance().getUser().getAtWork()){
            //Might have to add this to a method that is on resume or something, check it later. works after restarted app
            startStop.setText(("End Job"));
        }
        else{
            startStop.setText(("Start Job"));
        }

        //alter said ui elements
        if(project.getProjectId() >= 0){
            projectNumber.setText((project.getProjectId() + ""));
        }
        else{
            projectNumber.setText(("Field not specified"));
        }

        if(project.getTitle() != null){
            projectName.setText(project.getTitle());
        }
        else{
            projectName.setText(("Field not specified"));
        }

        if(project.getCustomer() != null){
            projectCustomer.setText(project.getCustomer());
        }
        else{
            projectCustomer.setText(("Field not specified"));
        }

        if(project.getProjectManagerId() != null){
            projectManager.setText(project.getProjectManagerId());
        }
        else{
            projectManager.setText(("Field not specified"));
        }

        if(project.getDescription() != null){
            projectDescription.setText(project.getDescription());
        }
        else{
            projectDescription.setText(("Field not specified"));
        }




        startStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                User user = AppService.getInstance().getUser();
                if (!user.getAtWork()){
                    AppService.getInstance().sendWorkHourCreate();
                    //need to implement a check to see that it created
                    AppService.getInstance().sendSetWorkStatus("true");
                    //startStop.setText(("End Job"));
                    



                    //this is a temporary thing, at some point i'll have to figure out how to make sure
                    //it is added to the server before navigating back
                    long timer = System.currentTimeMillis() + 200;
                    while(timer > System.currentTimeMillis()){

                    }
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.navigation_dashboard);

                }
                else{
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.navigation_end_work);
                }


            }


            protected void onException(Throwable throwable) {
            }
        });


        return view;
    }




}