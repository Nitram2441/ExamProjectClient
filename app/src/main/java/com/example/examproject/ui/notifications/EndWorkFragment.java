package com.example.examproject.ui.notifications;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.examproject.AbstractAsyncTask;
import com.example.examproject.AppService;
import com.example.examproject.PostProjectTask;
import com.example.examproject.Project;
import com.example.examproject.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EndWorkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EndWorkFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EndWorkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EndWorkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EndWorkFragment newInstance(String param1, String param2) {
        EndWorkFragment fragment = new EndWorkFragment();
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

        View view = inflater.inflate(R.layout.fragment_end_work, container, false);
        // Inflate the layout for this fragment

        final Button sendButton = (Button) view.findViewById(R.id.buttonSendEndWork);
        EditText commentField = (EditText) view.findViewById(R.id.editTextWriteComment);

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                AppService.getInstance().sendWorkHourEnd(AppService.getInstance().getUser().getUserId(), commentField.getText().toString());
                //this is a temporary thing, at some point i'll have to figure out how to make sure
                //it is added to the server before navigating back
                long timer = System.currentTimeMillis() + 200;
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