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
import java.util.Map;

/*  @author: Xiran Yan
 *  @uid: 7167582
 * */

// this is the engine page (textbox,button and listview)
public class MainActivity extends AppCompatActivity {
    List<String> parsed = new ArrayList<>();
    ArrayList<Node> courseList = new ArrayList<>();
    ArrayList<Course> courseDetail = new ArrayList<>();
    ArrayList<String[]> displayList = new ArrayList<>();
    RBTreeBarry<String> tree;
    Map<String,ArrayList<String>> map;
    ArrayList<String[]> majorList;
    ListView listView;
    EditText input;
    //Button button_search;
    ArrayAdapter listAdapter;
    String course;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.lv_results);
        input=findViewById(R.id.ev_input);
       // button_search=findViewById(R.id.b_search);

        File file1 = new File("courseTest.json");
        File file2 = new File("majors.csv");

        /*
            1. step1/2 get tree,map,majorlist
            2. onSearch()
            3. update
            4. go to detail by classNumber(in Node)
        */

        //bind view to the list
        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,displayList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                course=listView.getItemAtPosition(position).toString()+"";
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("courseDetail", courseDetail);
                startActivity(intent);
            }
        });
    }

    
    public void onSearch(View v) {
        courseList.clear();
        //use tokenizer&parser
        parsed = new Parser( new InputTokenizer("ComputerScience")).parseInput();
            for (String s : parsed) {
                System.out.print(s + " ");
            }

        //search() get nodes then go to map get course(detail)
        //get courseDetail
        //choose some information to display in the list
        //transfer the courseDetail into displayList
        for (Course course : courseDetail) {
            ArrayList<String> display = new ArrayList<>();
            display.add(course.courseDetail.get(0));//classNUmber
            display.add(course.courseDetail.get(1)+course.courseDetail.get(2));//courseId
            display.add(course.courseDetail.get(4));//courseName
            display.add(course.courseDetail.get(3));//section
            display.add(course.courseDetail.get(9));//Pre
            String[] item= (String[]) display.toArray(new String[0]);
            displayList.add(item);
        }
        listAdapter.notifyDataSetChanged();
    }
}