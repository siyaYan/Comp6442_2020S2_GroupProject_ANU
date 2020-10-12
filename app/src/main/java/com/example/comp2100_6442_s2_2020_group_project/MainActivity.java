package com.example.comp2100_6442_s2_2020_group_project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*  @author: Xiran Yan
 *  @uid: 7167582
 * */

// this is the major engine page (textbox,button and listview)
public class MainActivity extends AppCompatActivity {
    ListView listView;
    EditText input;
    ArrayAdapter listAdapter;
    String course;
    List<Course> courses;
    List<String> parsed;
    ArrayList<Node> courseList;
    ArrayList<Course> courseDetail;
    ArrayList<String[]> displayList=new ArrayList<>();
    RBTreeBarry<String> tree;
    Map<String,ArrayList<String>> map;
    ArrayList<String[]> majorList;

    ArrayList<Node> nodes;
    ArrayList<Node> preNodes;
    ArrayList<Node> newNodes;

    InputTokenizer myInputTokenizer;
    Initialization initial;

    Search search;
    File file1;
    File file2;


   // @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.lv_results);
        input=findViewById(R.id.ev_input);
        // button_search=findViewById(R.id.b_search);
        /* main process
            1. step1/2 get tree,map,majorlist
            2. onSearch()
            3. update
            4. go to detail by classNumber(in Node)
        */
        setInitial();
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

    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setInitial() {
        //getDataUtil getDataUtil=new getDataUtil();
        //initial
        //for courses
        String file1 = "src/main/assets/someCourses.json";
        //for majors
        String file2 = "src/main/assets/majors.csv";
        initial=new Initialization(file1,file2);
        //System.out.println(courses.get(0).courseDetail);
        this.tree=initial.tree;
        this.map=initial.map;
        this.majorList=initial.list;
    }

    public void onSearch(View v) {
        //search() get nodes(one node or nodes) then go to map get courses(detail)(one or more)
        //get courseDetail
        //choose some information to display in the list
        //transfer the courseDetail into displayList

        //courseList.clear();
        //use tokenizer&parser
        myInputTokenizer = new InputTokenizer(input.getText().toString());
        parsed = new Parser(myInputTokenizer).parseInput();
        //ArrayList<String> oneMajor=new ArrayList<>();
        System.out.println(this.tree.inOrder(tree.root));
        newNodes=search.searchMajor(parsed,this.tree,this.majorList);
        for(Node node:newNodes)
            System.out.println(node.courseID);
        if (newNodes != null) {
            courseDetail = search.searchMap(newNodes, map);
            for (Course course : courseDetail) {
                ArrayList<String> display = new ArrayList<>();
                display.add(course.courseDetail.get(0));//classNUmber
                display.add(course.courseDetail.get(1) + course.courseDetail.get(2));//courseId
                display.add(course.courseDetail.get(4));//courseName
                display.add(course.courseDetail.get(3));//section
                display.add(course.courseDetail.get(9));//Pre
                String[] item = (String[]) display.toArray(new String[0]);
                displayList.add(item);
            }
        } else {
            System.out.println("error");
        }
        listAdapter.notifyDataSetChanged();
    }
}