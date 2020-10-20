package com.example.comp2100_6442_s2_2020_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * display the detail information for ANU courses
 * @author: Xiran Yan
 * @uid: 7167582
 */

//todo change the layout & UI
public class DetailActivity extends AppCompatActivity {

    TextView textView;
    Map<String,ArrayList<String>> map;
    ArrayList<String> courseDetail = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textView=findViewById(R.id.tv);
        Intent intent = getIntent();
        courseDetail =  intent.getStringArrayListExtra("courseDetail");
        System.out.println(courseDetail.get(0));
        displayDetails();

        //TODO create a button to go to course website via WebsiteActivity.
        //putExtra key must be "courseID".

    }


    public void displayDetails() {
        // how to desp
        textView.setText("classNUmber:"+courseDetail.get(0) +"\n"+"courseID:"+courseDetail.get(1)+courseDetail.get(2)+"\n"+"section:"+courseDetail.get(3)+"\n"+"courseName:"+courseDetail.get(4)+"\n"+"Min units:"+courseDetail.get(5)+"\n"+"Max units:"+courseDetail.get(6));
    }


}