package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Reads user registeration xml and
 *
 *  @author So Young Kwon
 *  * @uid: 6511277
 */

public class UserLogin extends AppCompatActivity {

    EditText loginid;
    EditText loginpassword;
    Button backmain;
    Button login;
    Button signup;

    UserDatabase ud = new UserDatabase(this);


    @Override
    protected void onCreate(Bundle saveInstanceStace){
        super.onCreate(saveInstanceStace);

        setContentView(R.layout.activity_login);

        loginid = (EditText)findViewById(R.id.loginUserID);
        loginpassword = (EditText)findViewById(R.id.loginUserPassword);
        backmain = (Button)findViewById(R.id.backtoMain);
        login = (Button) findViewById(R.id.loginButton);
        signup = (Button) findViewById(R.id.initialsignupButton);

        backmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(UserLogin.this,MainActivity.class);
                startActivity(main);

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = loginid.getText().toString();
                String password = loginpassword.getText().toString();
                if(id.isEmpty() || password.isEmpty()){
                    Toast.makeText(UserLogin.this,"Please check if you entered your ID or Password",Toast.LENGTH_SHORT).show();
                }else{
                    if(ud.userExists(id)){
                        //pretend user1
                        if(ud.getUserDetails(id).password.equals(password)){
                            Intent intent = new Intent(UserLogin.this, MainActivity.class);
                            intent.putExtra("userID", id);
                            startActivity(intent);
                        }else{
                            Toast.makeText(UserLogin.this,"Please check your password",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(UserLogin.this,"No matching ID, please check if you entered correct ID or sign up today!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(UserLogin.this,UserRegister.class);
                startActivity(in);
            }
        });
    }




}
