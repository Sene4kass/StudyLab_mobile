package com.sene4ka.studylab;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.sene4ka.studylab.databinding.ActivityCoursesViewBinding;
import com.sene4ka.studylab.ui.dashboard.DashboardFragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CoursesViewActivity extends AppCompatActivity {

    private ActivityCoursesViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCoursesViewBinding.inflate(getLayoutInflater());
        Bundle arguments = getIntent().getExtras();
        ActiveUser user;
        if(arguments!=null){
            user = (ActiveUser) arguments.getSerializable(ActiveUser.class.getSimpleName());
            assert user != null;
            ((TextView)binding.getRoot().findViewById(R.id.__login_text)).setText(user.getUser_login());
            ((TextView)binding.getRoot().findViewById(R.id.__email_text)).setText(user.getUser_email());
            ((TextView)binding.getRoot().findViewById(R.id.__name_text)).setText(user.getUser_name());
            ((TextView)binding.getRoot().findViewById(R.id.__surname_text)).setText(user.getUser_surname());
        }
        ConstraintLayout root = binding.getRoot();


        ListView courses_list = root.findViewById(R.id.listOfCourses);
        DBConnection db = new DBConnection(this);
        Connection connect = db.getDBConnection();
        ArrayList<String> coursesList = new ArrayList<>();
        try {
            Statement statement = connect.createStatement();
            String query = String.format("SELECT * FROM `subject`");
            ResultSet result = statement.executeQuery(query);
            ArrayList<String> cList = new ArrayList<>();
            while(result.next()){
                coursesList.add(result.getString(2));
            }
            for(int i = 0; i < coursesList.size(); i++){
                cList.add(coursesList.get(i));
            }
            //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, coursesList);
            //System.out.println(adapter.getItem(1));
        } catch (SQLException e){
            e.printStackTrace();
        }




        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_courses_view);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }




}