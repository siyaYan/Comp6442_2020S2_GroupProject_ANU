package com.example.comp2100_6442_s2_2020_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*  @author: Xiran Yan
 *  @uid: 7167582
 * */

public class MainActivity extends AppCompatActivity {


    ArrayList listResults = new ArrayList<>();
    ListView listView;
    EditText input;
    Button button_search;
    ArrayAdapter listAdapter;
    String courseid;
    String course;
    JSONObject jsonObject;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.lv_results);
        input=findViewById(R.id.ev_input);
        button_search=findViewById(R.id.b_search);

        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listResults);
        listView.setAdapter(listAdapter);
        getResults();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                course=listView.getItemAtPosition(position).toString()+"";
                courseid = course.substring(0,8);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("courseID", courseid);
                startActivity(intent);
            }
        });
    }
    public void getResults() {
        //get jsonString
        String JsonData =new GetJsonDataUtil().getJson("courses.json",this.getApplicationContext());
       // System.out.println(JsonData);
        try {
            jsonObject = new JSONObject(JsonData);
            jsonArray= jsonObject.getJSONArray("course_info");
            for (int i = 0; i < jsonArray.length();i++) {
                listResults.add(jsonArray.getJSONObject(i).getString("courseID")+", "+jsonArray.getJSONObject(i).getString("courseName")+", "+jsonArray.getJSONObject(i).getString("prerequisites")+", "+jsonArray.getJSONObject(i).getString("term"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listAdapter.notifyDataSetChanged();
    }

    public void onSearch(View v) {
        //Todo add some grammar to search the course and get a new listResults
        // fake case 1: input ASTR2013
        listResults.clear();
       // listResults.add(input.getText());
            try {
                for (int i = 0; i < jsonArray.length();i++) {
                    if (input.getText().toString().equals(jsonArray.getJSONObject(i).getString("courseID"))) {
                        listResults.add(jsonArray.getJSONObject(i).getString("courseID")+", "+jsonArray.getJSONObject(i).getString("courseName")+", "+jsonArray.getJSONObject(i).getString("prerequisites")+", "+jsonArray.getJSONObject(i).getString("term"));
                        //System.out.println(listResults);
                    }
                }
                System.out.println(listResults);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        listAdapter.notifyDataSetChanged();
    }
}