package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Reads user input from registeration, and writes it to a xml file so it can be used for log in
 *
 *  @author So Young Kwon
 *  * @uid: 6511277
 */

public class UserRegisterActivity extends AppCompatActivity {
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

    }

    public void registerID(View view) {

        String un = username.getText().toString();
        String id = userID.getText().toString();
        String pw = password.getText().toString();
        String pwc = passwordConfrim.getText().toString();

        if(un.isEmpty() || id.isEmpty() || pw.isEmpty() || pwc.isEmpty()){
            Toast.makeText(UserRegisterActivity.this,"Make sure you fill up all the required entries",Toast.LENGTH_SHORT).show();
        }else if(!pw.equals(pwc)){
            Toast.makeText(UserRegisterActivity.this,"Password does not match",Toast.LENGTH_SHORT).show();
        }else if(ud.userExists(id) == true){
            Toast.makeText(UserRegisterActivity.this,"User ID already exists",Toast.LENGTH_SHORT).show();

        }else{
            ud.registerUser(id,un,pw);
            Intent mainintet = new Intent(UserRegisterActivity.this,MainActivity.class);
            startActivity(mainintet);
        }

    }


    public void backLogin(View view) {
        Intent login = new Intent(UserRegisterActivity.this, UserLoginActivity.class);
        startActivity(login);

    }


}
