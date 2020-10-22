package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;



/**
 * Reads user input from registeration, and writes it to a xml file so it can be used for log in
 *
 *  @author So Young Kwon
 *  * @uid: 6511277
 */

public class UserRegister extends AppCompatActivity {
    EditText username;
    EditText userID;
    EditText password;
    EditText passwordConfrim;

    Button signUp;
    Button backLog;

    UserDatabase ud = new UserDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.acitiviy_registeration);
        username = (EditText)findViewById(R.id.userName);
        userID = (EditText)findViewById(R.id.userID);
        password = (EditText)findViewById(R.id.userPassword);
        passwordConfrim = (EditText)findViewById(R.id.userPassword);
        signUp = (Button) findViewById(R.id.initialsignupButton);
        backLog = (Button) findViewById(R.id.backtoLogin);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String un = username.getText().toString();
                String id = userID.getText().toString();
                String pw = password.getText().toString();
                String pwc = passwordConfrim.getText().toString();

//                BufferedReader re = null;

                if(un.isEmpty() || id.isEmpty() || pw.isEmpty() || pwc.isEmpty()){
                    Toast.makeText(UserRegister.this,"Make sure you fill up all the required entries",Toast.LENGTH_SHORT).show();
                }else if(!pw.equals(pwc)){
                    Toast.makeText(UserRegister.this,"Password does not match",Toast.LENGTH_SHORT).show();
                }else if(ud.userExists(id) == true){
                    Toast.makeText(UserRegister.this,"User ID already exists",Toast.LENGTH_SHORT).show();

                }else{
                    ud.registerUser(id,un,pw);
                }

            }
        });


        backLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(UserRegister.this,UserLogin.class);
                startActivity(login);

            }
        });
    }


}
