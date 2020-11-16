package com.example.examproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.examproject.ui.dashboard.ProjectFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    //ProjectAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



    //navController.navigate(R.id.navigation_dashboard);
/*
        //data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");
*/
        // set up the RecyclerView

        //RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new ProjectAdapter(this, animalNames);
        //adapter.setClickListener(this);
        //recyclerView.setAdapter(adapter);



    }

/*
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

 */

    @Override
    public boolean onSupportNavigateUp() {
        System.out.println("Back Button Pressed");
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //navController.navigate(R.id.navigation_project);

        navController.navigateUp();
        return super.onSupportNavigateUp();
        // do your stuff here
    }
}