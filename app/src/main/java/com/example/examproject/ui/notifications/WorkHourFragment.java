package com.example.examproject.ui.notifications;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.examproject.AppService;
import com.example.examproject.Project;
import com.example.examproject.R;
import com.example.examproject.WorkHour;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkHourFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkHourFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WorkHourFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment workHourFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkHourFragment newInstance(String param1, String param2) {
        WorkHourFragment fragment = new WorkHourFragment();
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
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_work_hour, container, false);


        WorkHour workHour = AppService.getInstance().getTempWorkHour();

        //collect the ui elements that the code should alter
        TextView projectName = (TextView) view.findViewById(R.id.textViewWorkHourProjectName);
        TextView employeeId = (TextView) view.findViewById(R.id.textViewWorkHourEmployee);
        TextView workStart = (TextView) view.findViewById(R.id.textViewWorkStart);
        TextView workEnd = (TextView) view.findViewById(R.id.textViewWorkStop);
        TextView comment = (TextView) view.findViewById(R.id.textViewWorkHourComment);

        //alter said ui elements
        if(workHour.getProjectName() != null){
            projectName.setText((workHour.getProjectName()));
        }
        else{
            projectName.setText(("Field not specified"));
        }
        if(workHour.getUser() != null){
            employeeId.setText((workHour.getUser()));
        }
        else{
            employeeId.setText(("Field not specified"));
        }
        if(workHour.getStart() != null){
            workStart.setText((workHour.getStart()));
        }
        else{
            workStart.setText(("Field not specified"));
        }
        if(workHour.getStop() != null){
            workEnd.setText((workHour.getStop()));
        }
        else{
            workEnd.setText(("Field not specified"));
        }
        if(workHour.getComment() != null){
            comment.setText((workHour.getComment()));
        }
        else{
            comment.setText(("Field not specified"));
        }

        return view;
    }
}