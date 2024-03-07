package com.sene4ka.studylab;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.StrictMode;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;
public class DBConnection {
    private Connection DB;
    public DBConnection(Context context){
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //Connection db = DriverManager.getConnection("jdbc:mysql://82.209.208.36:3306/StudyLab?characterEncoding=UTF-8&autoReconnect=true&useSSL=false", "mobileApp", "StudyLabMobile");
            Connection db = getConnection(context);
            if (db != null) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>   все ок");
                DB = db;
            }
        }
        catch(SQLException ex){
            System.out.println("|=====>>>> Connection failed...");
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(Context context) throws SQLException, IOException {

        Properties props = new Properties();
        AssetManager assetManager = context.getAssets();

        try (InputStream in = assetManager.open("database.properties")) {
            props.load(in);
        }

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
    public final Connection getDBConnection(){
        return DB;
    }

}
