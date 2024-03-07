package com.sene4ka.studylab;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class ViewCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);
        Bundle arguments = getIntent().getExtras();
        Course course;
        if(arguments != null){
            course = (Course) arguments.getSerializable(Course.class.getSimpleName());
            VideoView video = findViewById(R.id.videoView);
            assert course != null;
            if(course.video_url != null) {
                System.out.println("должно быть видео");
                video.setVideoURI(Uri.parse(course.video_url));
                System.out.println(Uri.parse(course.video_url));
                video.setMediaController(new MediaController(this));
                video.requestFocus();
                video.start();
            }
            else{
                video.setVisibility(View.INVISIBLE);
            }
            TextView course__name = findViewById(R.id._course_name_text);
            TextView course__fulldesc = findViewById(R.id._course_fulldesc_text);
            course__name.setText(course.name);
            course__fulldesc.setText(course.full_desc);
        }
    }
}