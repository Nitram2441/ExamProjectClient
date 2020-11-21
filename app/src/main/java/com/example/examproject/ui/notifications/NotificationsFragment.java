package com.example.examproject.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examproject.AppService;
import com.example.examproject.ProjectAdapter;
import com.example.examproject.R;
import com.example.examproject.WorkHourAdapter;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment implements WorkHourAdapter.ItemClickListener{


    private NotificationsViewModel notificationsViewModel;
    WorkHourAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");



        recyclerView = view.findViewById(R.id.rvWorkHours);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new WorkHourAdapter(view.getContext(), animalNames);
        AppService.getInstance().getWorkHours(adapter::setWorkHours);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;




        /*
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

         */
    }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(view.getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();


        //store the project that has been clicked on, because i can't find a good way to send it along
        AppService.getInstance().setTempWorkHour(adapter.getItem(position));
        System.out.println(AppService.getInstance().getTempWorkHour().getComment());
        System.out.println(AppService.getInstance().getTempWorkHour().getStart());
        System.out.println(AppService.getInstance().getTempWorkHour().getUser());
        System.out.println(AppService.getInstance().getTempWorkHour().getWorkHourId());
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.navigation_work_hour);

    }

}