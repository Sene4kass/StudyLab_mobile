package com.sene4ka.studylab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button __register_button = (Button)findViewById(R.id.__button_register);
        Button __login_button = (Button)findViewById(R.id.__button_login);
        DBConnection DBConnect = new DBConnection(this);
        __login_button.setOnClickListener(v -> {
            EditText login_edittext = (EditText)findViewById(R.id.__edit_login);
            EditText password_edittext = (EditText)findViewById(R.id.__edit_password);
            String login = login_edittext.getText().toString();
            String password = password_edittext.getText().toString();
            String hash_password = null;
            hash_password = Functions.hashPassword(password);
            Statement statement = null;
            Connection db = DBConnect.getDBConnection();
            try {
                statement = db.createStatement();
                String query = String.format("SELECT * FROM `user` WHERE `Login` = '%s' AND `Password` = '%s'", login, hash_password);
                ResultSet result = statement.executeQuery(query);
                result.last();
                System.out.println(login + "   " + hash_password);
                if(result.getRow()>0){
                    ActiveUser user = new ActiveUser(result.getString(2), result.getString(3), result.getString(4), result.getString(6),result.getString(7), result.getString(9));
                    System.out.println(user);
                    System.out.println("=============>>> АВТОРИЗАЦИЯ УСПЕШНАЯ");
                    Intent intent = new Intent(MainActivity.this, CoursesViewActivity.class);
                    intent.putExtra(ActiveUser.class.getSimpleName(), user);
                    startActivity(intent);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
        __register_button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }




}