package com.sene4ka.studylab.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sene4ka.studylab.DBConnection;
import com.sene4ka.studylab.R;
import com.sene4ka.studylab.databinding.FragmentDashboardBinding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ListView list = binding.getRoot().findViewById(R.id.listOfCourses);
        DBConnection db = new DBConnection(binding.getRoot().getContext());
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
                //System.out.print(coursesList.get(i) + " ");
                cList.add(coursesList.get(i));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(binding.getRoot().getContext(), R.layout.samplewhitetext, R.id.list_content, coursesList);
            System.out.println(adapter.getItem(1));
            list.setAdapter(adapter);
        return root;
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}