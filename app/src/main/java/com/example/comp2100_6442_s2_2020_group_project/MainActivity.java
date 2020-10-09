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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*  @author: Xiran Yan
 *  @uid: 7167582
 * */

public class MainActivity extends AppCompatActivity {
    List<String> parsed = new ArrayList<>();
    ArrayList<Node> courseLists = new ArrayList<>();
    ListView listView;
    EditText input;
    //Button button_search;
    ArrayAdapter listAdapter;
    //String courseid;

    String course;
   // String type;
   // String command;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.lv_results);
        input=findViewById(R.id.ev_input);
       // button_search=findViewById(R.id.b_search);

        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,courseLists);
        listView.setAdapter(listAdapter);
       // nodes=fromJsonToNodes("courseTest.json");
        File file = new File("courseTest.json");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                course=listView.getItemAtPosition(position).toString()+"";
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                //intent.putExtra("courseID", courseid);
                startActivity(intent);
            }
        });
    }

    
    public void onSearch(View v) {
        courseLists.clear();
        //tokenizer
        parsed = new Parser( new InputTokenizer("ComputerScience")).parseInput();
            for (String s : parsed) {
                System.out.print(s + " ");
            }

        listAdapter.notifyDataSetChanged();
    }
}