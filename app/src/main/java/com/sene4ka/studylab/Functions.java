package com.sene4ka.studylab;

import android.content.Context;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Functions {
    public static String hashPassword(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // compute the hash of the input string
            byte[] hash = md.digest(input.getBytes());

            // convert the hash to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public static ArrayList<String> getCoursesList(Context context) {
        DBConnection db = new DBConnection(context);
        Connection connect = db.getDBConnection();
        ArrayList<String> coursesList = new ArrayList<>();
        try {
            Statement statement = connect.createStatement();
            String query = String.format("SELECT * FROM `subject`");
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                coursesList.add(result.getString(2));
            }
            for(int i = 0; i < coursesList.toArray().length; i++){
                System.out.print(coursesList.get(i) + " ");
            }
            return coursesList;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }


    }
}
