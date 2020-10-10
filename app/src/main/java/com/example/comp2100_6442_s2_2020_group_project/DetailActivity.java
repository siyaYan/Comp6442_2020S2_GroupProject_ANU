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

/* @author: Xiran Yan
*  @uid: 7167582
* */
//display the detail
//todo change the layout & UI
public class DetailActivity extends AppCompatActivity {

    TextView textView;
    ArrayList<? extends Course> courseDetail = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textView=findViewById(R.id.tv);
        Intent intent = getIntent();
        courseDetail =  intent.getParcelableArrayListExtra("courseDetail");
        displayDetails();

    }

    public void displayDetails() {
        //todo display in the list
        for (Course course : courseDetail) {
            textView.setText(course.courseDetail.toString());
        }
    }
}