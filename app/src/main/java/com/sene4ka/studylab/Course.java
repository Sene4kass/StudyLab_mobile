package com.sene4ka.studylab;

public class Course {
    public String name;
    public String short_desc;
    public String full_desc;
    private boolean status = false;

    void changeStatus(boolean status){
        this.status = status;
    }
}
