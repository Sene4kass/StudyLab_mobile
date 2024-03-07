package com.sene4ka.studylab;

import java.io.Serializable;

public class Course implements Serializable {
    public String name;
    public String short_desc;

    public int id;
    public String full_desc;

    public String video_url;
    private boolean status = false;

    void changeStatus(boolean status){
        this.status = status;
    }
}
