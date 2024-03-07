package com.sene4ka.studylab.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sene4ka.studylab.Course;
import com.sene4ka.studylab.DBConnection;
import com.sene4ka.studylab.R;
import com.sene4ka.studylab.ViewCourse;
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
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String course_name = coursesList.get(position);
                    try {
                        Statement statement = null;
                        statement = connect.createStatement();
                        String query = String.format("SELECT * FROM `subject` WHERE `Name` = '%s'", course_name);
                        ResultSet result = statement.executeQuery(query);
                        statement = connect.createStatement();


                        Course course = new Course();
                        while(result.next()){
                             course.name = course_name;
                             course.short_desc = result.getString(3);
                             course.full_desc = result.getString(4);
                             course.id = result.getInt(1);
                             query = "SELECT * FROM `subject_media` WHERE subject_id = " + course.id;
                             ResultSet result_videos = statement.executeQuery(query);
                             if(result_videos != null) {
                                 while (result_videos.next()) {
                                     course.video_url = result_videos.getString(3);
                                 }
                                 System.out.println("VIDEOOOO");
                             }
                        }
                        Intent intent = new Intent(DashboardFragment.this.getContext(), ViewCourse.class);
                        intent.putExtra(Course.class.getSimpleName(), course);
                        startActivity(intent);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        return root;
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}