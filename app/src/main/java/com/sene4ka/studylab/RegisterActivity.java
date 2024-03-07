package com.sene4ka.studylab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EventListener;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button register_button = (Button) findViewById(R.id.button_regUser);
        register_button.setOnClickListener(v -> {
            String user_login = ((TextView)findViewById(R.id.__edit_login)).getText().toString();
            String user_password = ((TextView)findViewById(R.id.__edit_password)).getText().toString();
            String user_confirm_password = ((TextView)findViewById(R.id.__edit_confirm_pass)).getText().toString();
            String user_email = ((TextView)findViewById(R.id.__edit_email)).getText().toString();
            String user_name = ((TextView)findViewById(R.id.__edit_name)).getText().toString();
            String user_surname = ((TextView)findViewById(R.id.__edit_surname)).getText().toString();
            String user_lastname = ((TextView)findViewById(R.id.__edit_lastname)).getText().toString();
            if(user_password.equals(user_confirm_password)) {
                String query = String.format("INSERT INTO `user`(`First_name`, `Surname`, `Patronymic`, `Status`, `Login`, `Password`, `User_photo`, `E_mail`, `id_Role`) VALUES ('%s','%s','%s','active','%s','%s',NULL,'%s',1)", user_name, user_surname, user_lastname, user_login, Functions.hashPassword(user_password), user_email);
                try {
                    Statement statement = DBConnection.getConnection(this).createStatement();
                    statement.executeUpdate(query);
                    System.out.println("======> Query has entered");
                    super.finish();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}