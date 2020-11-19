package com.example.examproject.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examproject.AbstractAsyncTask;
import com.example.examproject.AppService;
import com.example.examproject.MainActivity;
import com.example.examproject.PostProjectTask;
import com.example.examproject.Project;
import com.example.examproject.ProjectAdapter;
import com.example.examproject.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements ProjectAdapter.ItemClickListener {

    private DashboardViewModel dashboardViewModel;

    ProjectAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        Project project = new Project();
        project.setTitle("title");
        project.setDescription("desc");
        //project.setProjectId();
        project.setProjectManagerId("1");

/*used to test that projects can be created
        new PostProjectTask(new AbstractAsyncTask.OnPostExecute<List<Project>>() {
            @Override
            public void onPostExecute(List<Project> projects) {
                System.out.println(project.getTitle());
            }
        }, this::onException).execute(project);


 */
        // set up the RecyclerView

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = view.findViewById(R.id.rvAnimals);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new ProjectAdapter(view.getContext(), animalNames);
        AppService.getInstance().getProjects(adapter::setProjects);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;




/*
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;


 */
    }


    protected void onException(Throwable throwable){

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(view.getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

        //store the project that has been clicked on, because i can't find a good way to send it along
        AppService.getInstance().setTempProject(adapter.getItem(position));
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        //navController.popBackStack();
        navController.navigate(R.id.navigation_project);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menutest, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        /*
        switch (item.getItemId()) {
            case R.id.action_cart:
                Toast.makeText(getActivity(), "Calls Icon Click", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

         */

        if (item.getItemId() == R.id.action_cart){
            System.out.println("+ was clicked :)");

            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_projectcreate);


        }
        return true;
    }


}