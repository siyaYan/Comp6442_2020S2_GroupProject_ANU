package com.example.comp2100_6442_s2_2020_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * the main searching page for ANU courses
 * @author: Xiran Yan
 * @uid: 7167582
 */
//Todo
// not exactly String match can also find something
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
    Token token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.lv_results);
        input=findViewById(R.id.ev_input);
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
        token=new Token("someCourses.json","majors.csv",this);
        System.out.println(this.toString());
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
        androidFileParser androidFileParser =new androidFileParser();
        androidFileParser.init("someCourses.json","majors.csv",context);
        this.tree= androidFileParser.tree;
        this.map= androidFileParser.map;
        this.majorList= androidFileParser.list;
        //System.out.println(androidFileParser.tree.inOrder(androidFileParser.tree.root));
    }

    /**
     * process:
     * 1/clear the data
     * 2/use tokenizer&parser
     * 3/use search() get nodes(one node or nodes)
     * 4/ choose some information to display in the list
     * 5/adapt change
     * @author Xiran Yan
     */
    public void onSearch(View v) {
        displayList.clear();
        myInputTokenizer = new InputTokenizer(input.getText().toString());
        parsed = new Parser(myInputTokenizer).parseInput();
        if (parsed.size() > 0) {
            System.out.println(parsed.get(1));
            Search search = new Search();
            if (parsed.get(1).matches("major")) {
                //major engine
                newNodes = search.searchMajor(parsed, tree, majorList);
            } else {
                //college&courseId&courseName engine
                newNodes = search.searchTree(parsed, tree);
                if (!newNodes.isEmpty()) {
                    if (parsed.get(1).matches("courseId") || parsed.get(1).matches("courseName")) {
                        Node node = newNodes.get(0);
                        if (parsed.size() == 3) {
                            displayList.add(search.searchPre(node, map));
                        }
                    }
                } else {
                    Toast.makeText(this,"don't have the information",(int)100).show();
                    System.out.println("user input error or don't have the data!");
                }
            }
        if (newNodes != null&&parsed.size()<3) {
            this.courseDetail = search.searchMap(newNodes, map);
            for (Course course : courseDetail) {
                String item = course.courseDetail.get(1) + course.courseDetail.get(2) + "\t\t\t\t\t\t\t\t\t\t\t\t" + "section:" + course.courseDetail.get(3) + "\n" + course.courseDetail.get(4);
                displayList.add(item);
            }
        }
    }
        else {
            System.out.println("user input error or don't have the data!");
            Toast.makeText(this,"input error or don't have the information",(int)100).show();
        }
        listAdapter.notifyDataSetChanged();
    }
}