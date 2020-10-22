package com.example.comp2100_6442_s2_2020_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * display the detail information for ANU courses
 * @author: So Young Kwon
 * @uid: 6511277
 */

//todo change the layout & UI
public class DetailActivity extends AppCompatActivity {

    TextView courseCode;
    TextView courseNum;
    TextView courseTitle;
    TextView credit;
    TextView prerequsit;
    TextView startdate;
    TextView enddate;

    Button moreInfo;


    Map<String,ArrayList<String>> map;
    ArrayList<String> courseDetail = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        courseCode = findViewById(R.id.courseCode);
        courseNum = findViewById(R.id.coursenum);
        courseTitle =findViewById(R.id.cname);
        credit = findViewById(R.id.credit);
        prerequsit = findViewById(R.id.coursepre);
        startdate = findViewById(R.id.coursestart);
        enddate = findViewById(R.id.courseend);
        moreInfo = findViewById(R.id.officialSite);

        Intent intent = getIntent();
        courseDetail =  intent.getStringArrayListExtra("courseDetail");
        System.out.println(courseDetail.get(0));

        displayDetails();

        //TODO create a button to go to course website via WebsiteActivity.
        //putExtra key must be "courseID".
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String link =  courseDetail.get(1).toLowerCase() + courseDetail.get(2);
                Intent officialWeb = new Intent(DetailActivity.this,WebActivity.class);
                officialWeb.putExtra("courseID",link);
                startActivity(officialWeb);

            }
        });

    }

//Assign information to textview

    public void displayDetails() {
        courseCode.setText(courseDetail.get(1)+courseDetail.get(2));
        courseNum.setText("Class Number: " + courseDetail.get(0));
        courseTitle.setText(courseDetail.get(4));
        credit.setText("Credit: "+ courseDetail.get(6));
        prerequsit.setText(courseDetail.get(9));
        startdate.setText("Start Date: " + courseDetail.get(11));
        enddate.setText("End Date: " + courseDetail.get(12));
    }

}