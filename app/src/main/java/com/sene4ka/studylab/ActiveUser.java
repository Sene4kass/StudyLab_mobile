package com.sene4ka.studylab;

public class ActiveUser implements User{
    String user_name = "";
    String user_password = "";
    String user_surname = "";
    String user_lastname = "";
    String user_login = "";
    String user_email = "";
    ActiveUser(String name, String surname, String lastname, String login, String password, String email) {
        setUser_name(name);
        setUser_surname(surname);
        setUser_lastname(lastname);
        setUser_login(login);
        setUser_password(password);
        setUser_email(email);
    }

    ActiveUser(){
        setUser_name("Not defined");
        setUser_surname("Not defined");
        setUser_lastname("Not defined");
        setUser_login("Not defined");
        setUser_password("Not defined");
        setUser_email("Not defined");
    }
    @Override
    public String getUser_name() {
        return user_name;
    }

    @Override
    public String getUser_surname() {
        return user_surname;
    }

    @Override
    public String getUser_password() {
        return user_password;
    }

    @Override
    public String getUser_lastname() {
        return user_lastname;
    }

    @Override
    public String getUser_email() {
        return user_email;
    }

    @Override
    public String getUser_login() {
        return user_login;
    }

    @Override
    public void setUser_name(String arg) {
        this.user_name = arg;
    }

    @Override
    public void setUser_surname(String arg) {
        this.user_surname = arg;
    }

    @Override
    public void setUser_lastname(String arg) {
        this.user_lastname = arg;
    }

    @Override
    public void setUser_login(String arg) {
        this.user_login = arg;
    }

    @Override
    public void setUser_email(String arg) {
        this.user_email = arg;
    }

    @Override
    public void setUser_password(String arg) {
        this.user_password = arg;
    }
}
