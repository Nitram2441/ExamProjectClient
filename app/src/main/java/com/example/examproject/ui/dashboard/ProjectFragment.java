package com.example.examproject.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.examproject.AppService;
import com.example.examproject.Project;
import com.example.examproject.R;

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
        System.out.println("Project fragment created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_project, container, false);

        //retrieves clicked project
        System.out.println("Project fragment view created: " + AppService.getInstance().getTempProject().getProjectId());

        Project project = AppService.getInstance().getTempProject();



        //TextView projectNumber = getActivity().findViewById(R.id.textViewProjectNumber);
        TextView projectNumber = (TextView) view.findViewById(R.id.textViewProjectNumber);

        TextView projectName = (TextView) view.findViewById(R.id.textViewProjectName);
        TextView projectCustomer = (TextView) view.findViewById(R.id.textViewCustomer);
        TextView projectManager = (TextView) view.findViewById(R.id.textViewProjectManager);
        TextView projectDescription = (TextView) view.findViewById(R.id.textViewDescription);

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







        return view;


    }

}