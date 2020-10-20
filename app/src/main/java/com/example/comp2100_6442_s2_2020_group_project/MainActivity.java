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

import java.io.InputStream;
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

// this is the searching engine page (textbox,button and listview)
public class MainActivity extends AppCompatActivity {
    ListView listView;
    EditText input;
    ArrayAdapter listAdapter;
    List<List<String>> parsed;
    ArrayList<Course> courseDetail;
    ArrayList<String> displayList=new ArrayList<>();

    RBTreeBarry<String> tree;
    Map<String,ArrayList<String>> map;
    ArrayList<String[]> majorList;
    ArrayList<User> userList;

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
        init("courses.json","majors.csv",this);
        /*for (Node node:this.tree.searchNodes(this.tree.root,"COMP",new ArrayList<Node>()) ) {
            displayList.add(node.courseName.toString());
        }*/

        for (User user : userList) {
            System.out.println(user.userName);
        }
        token=new Token("courses.json","majors.csv",this);
        //bind view to the list
        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,displayList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                //the position is not pre
                if (displayList.get(position).contains("section:")) {
                    if (courseDetail.get(position).courseDetail != null) {
                        intent.putStringArrayListExtra("courseDetail", courseDetail.get(position).courseDetail);
                        startActivity(intent);
                    }
                }
            }
        });
        listAdapter.notifyDataSetChanged();
    }

    public void init(String file1,String file2,Context context) {
        InputStream inputStream = this.getResources().openRawResource(R.raw.users);
        androidFileParser androidFileParser =new androidFileParser();
        Initialization init=new Initialization();
        init.Initialization(androidFileParser.parseJson("courses.json",context),androidFileParser.parseCsv("majors.csv",context),androidFileParser.parseXML(inputStream));
        this.tree= init.tree;
        this.map= init.map;
        this.majorList= init.majorList;
        this.userList= init.userList;
        //System.out.println(androidFileParser.tree.inOrder(androidFileParser.tree.root));
    }

    /**
     * process:
     * 1/clear the data
     * 2/use tokenizer&parser
     * 3/use search() get nodes(one node or nodes)
     * 4/choose some information to display in the list
     * 5/adapt change
     * @author Xiran Yan
     */

    public void onSearch(View v) {
        displayList.clear();
        courseDetail = new ArrayList<>();
        myInputTokenizer = new InputTokenizer(input.getText().toString());
        parsed = new Parser(myInputTokenizer).parseInput();
        if (parsed.size() > 0) {
            for(List<String> oneparse:parsed) {
                Search search = new Search();
                System.out.println(parsed.size());
                System.out.println(oneparse.get(0));
                if (oneparse.get(1).matches("major")) {
                    //major engine
                    newNodes = search.searchMajor(oneparse, tree, majorList);
                }
                else {
                    //college&courseId&courseName engine
                    newNodes = search.searchTree(oneparse, tree);
                    if (!newNodes.isEmpty()) {
                        //condition with pre operation
                        if (oneparse.get(1).matches("courseId") || oneparse.get(1).matches("courseName")) {
                            Node node = newNodes.get(0);
                            if (oneparse.size() == 3) {
                                displayList.add(search.searchPre(node, map));
                                //coursedetail is null,pre to keep the same number with displaylist
                                Course course=new Course("pre",null);
                                courseDetail.add(course);
                            }
                        }
                    } else {
                        Toast.makeText(this, "don't have the information", (int) 100).show();
                        System.out.println("user input error or don't have the data!");
                    }
                }
            //conditon without the pre operation
            if (newNodes != null&&oneparse.size()<3) {
                ArrayList<Course> courses=search.searchMap(newNodes, map);
                courseDetail.addAll(courses);
                List<String> items=new ArrayList<>();
                for (Course course : courses) {
                    if(course.courseDetail!=null) {
                        String item = course.courseDetail.get(1) + course.courseDetail.get(2) + "\t\t\t\t\t\t\t\t\t\t\t\t" + "section:" + course.courseDetail.get(3) + "\n" + course.courseDetail.get(4);
                        items.add(item);
                    }
                }
                displayList.addAll(items);
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