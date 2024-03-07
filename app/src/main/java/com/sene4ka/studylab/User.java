package com.sene4ka.studylab;

import java.io.Serializable;

public interface User {


    String getUser_name();
    String getUser_surname();
    String getUser_password();
    String getUser_lastname();
    String getUser_email();
    String getUser_login();

    void setUser_name(String arg);
    void setUser_surname(String arg);
    void setUser_lastname(String arg);
    void setUser_login(String arg);
    void setUser_email(String arg);
    void setUser_password(String arg);
}
