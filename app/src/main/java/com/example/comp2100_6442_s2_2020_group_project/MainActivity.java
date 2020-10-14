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

// this is the college engine page (textbox,button and listview)
public class MainActivity extends AppCompatActivity {
    ListView listView;
    EditText input;
    ArrayAdapter listAdapter;
    List<String> parsed;
    ArrayList<Course> courseDetail;
    ArrayList<String> displayList=new ArrayList<>();

    RBTreeBarry<String> tree;
    Map<String,ArrayList<String>> map;
    ArrayList<String[]> majorList;

    ArrayList<Node> newNodes = new ArrayList<>();
    InputTokenizer myInputTokenizer;



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
        init("someCourses.json","majors.csv",this);
        /*for (Node node:this.tree.searchNodes(this.tree.root,"COMP",new ArrayList<Node>()) ) {
            displayList.add(node.courseName.toString());
        }*/

        //bind view to the list
        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,displayList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putStringArrayListExtra("courseDetail",courseDetail.get(position).courseDetail);
                startActivity(intent);
            }
        });
        listAdapter.notifyDataSetChanged();
    }

    public void init(String file1,String file2,Context context) {
        fileParser fileParser=new fileParser();
        fileParser.init("someCourses.json","majors.csv",context);
        this.tree=fileParser.tree;
        this.map=fileParser.map;
        this.majorList=fileParser.list;
        //System.out.println(fileParser.tree.inOrder(fileParser.tree.root));
    }

    public void onSearch(View v) {
        //search() get nodes(one node or nodes) then go to map get courses(detail)(one or more)
        //get courseDetail
        //choose some information to display in the list
        //transfer the courseDetail into displayList
        //use tokenizer&parser
        displayList.clear();
        myInputTokenizer = new InputTokenizer(input.getText().toString());
        parsed = new Parser(myInputTokenizer).parseInput();
       // System.out.println(parsed.get(1));
        Search search=new Search();
        //college&courseId&courseName engine
        newNodes=search.searchTree(parsed,tree);
        //major engine
        //newNodes= search.searchMajor(parsed,tree,majorList);
        for(Node node:newNodes)
            System.out.println(node.courseID);
        if (newNodes != null) {
            this.courseDetail = search.searchMap(newNodes, map);
            for (Course course : courseDetail) {
                String item = course.courseDetail.get(1) + course.courseDetail.get(2)+"\t\t\t\t\t\t\t\t\t\t\t\t"+"section:"+course.courseDetail.get(3)+"\n"+course.courseDetail.get(4);
                displayList.add(item);
            }
        } else {
            System.out.println("error");
        }
        listAdapter.notifyDataSetChanged();
    }
}