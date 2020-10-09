package com.example.comp2100_6442_s2_2020_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/* @author: Xiran Yan
*  @uid: 7167582
* */

public class DetailActivity extends AppCompatActivity {

    TextView textView;
    String courseID;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList details=new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textView=findViewById(R.id.tv);
        Intent intent = getIntent();
        courseID = intent.getStringExtra("courseID");
        //System.out.println(courseID);
        getDetails();

    }
    public void getDetails() {
        //get jsonString
        String JsonData =new getDataUtil().getJson("courses.json",this.getApplicationContext());
        // System.out.println(JsonData);
        try {
            jsonObject = new JSONObject(JsonData);
            jsonArray= jsonObject.getJSONArray("course_details");
            for (int i = 0; i < jsonArray.length();i++) {
                if(courseID.equals(jsonArray.getJSONObject(i).getString("courseID")))
                details.add(jsonArray.getJSONObject(i).getString("courseID")+", "+jsonArray.getJSONObject(i).getString("tutorial")+", "+jsonArray.getJSONObject(i).getString("courseIntroduce")+"https://programsandcourses.anu.edu.au/2020/course/" + courseID);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        textView.setText(details.toString());
    }
}