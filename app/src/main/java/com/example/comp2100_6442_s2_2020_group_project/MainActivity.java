package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * the main searching page for ANU courses
 *
 * @author: Xiran Yan
 * @uid: 7167582
 */
//Todo

// this is the searching engine page (textbox,button and listview)
public class MainActivity extends AppCompatActivity {
    UserHistoryDatabase userHistoryDatabase;

    ListView listView;
    MultiAutoCompleteTextView input;
    ArrayAdapter listAdapter;
    List<List<String>> parsed;
    ArrayList<Course> coursedetail;
    ArrayList<String> displayList = new ArrayList<>();

    RBTree<String> tree;
    Map<String, ArrayList<String>> map;
    ArrayList<String[]> majorList;
    ArrayList<User> userList;
    User user = new User();
    String currentUser = "user";
    String userHistory = "";

    ArrayList<Node> newNodes = new ArrayList<>();
    InputTokenizer myInputTokenizer;
    Token token;

    int curHint = 0;
    Thread hintRefreshThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv_results);
        input = findViewById(R.id.ev_input);

        //set current user from login activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            currentUser = intent.getStringExtra("userID");
        }

        //initialise database
        userHistoryDatabase = new UserHistoryDatabase(this);


        /* main process
            1. step1/2 get tree,map,majorlist
            2. onSearch()
            3. update
            4. go to detail by classNumber(in Node)
        */
        init("courses.json", "majors.csv", this);
        /*for (Node node:this.tree.searchNodes(this.tree.root,"COMP",new ArrayList<Node>()) ) {
            displayList.add(node.courseName.toString());
        }*/

        for (User user : userList) {
            System.out.println(user.userName);
        }
        token = new Token("courses.json", "majors.csv", this);
        //bind view to the list
        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                if (coursedetail.get(position).courseDetail != null) {
                    String course = coursedetail.get(position).courseDetail.get(1) + coursedetail.get(position).courseDetail.get(2);
                    //update history on click, does not work for default user
                    if (!currentUser.equals("user")) {
                        if (!userHistoryDatabase.userExists(currentUser)) {
                            userHistoryDatabase.addToDB(currentUser, course);
                        } else {
                            userHistoryDatabase.updateHistory(currentUser, course);
                        }
                        //TODO use variable userHistory to see history
                        //updates userHistory variable on each click.
                        userHistory = userHistoryDatabase.getHistory(currentUser);
                    }
                    intent.putStringArrayListExtra("courseDetail", coursedetail.get(position).courseDetail);
                    startActivity(intent);
                }
            }
        });
        listAdapter.notifyDataSetChanged();

        setUpHintRefresh();

        setUpAutoComplete();

    }

    /**
     * Input hint refreshes every 1.5 seconds
     *
     * @author Xinyu Zheng
     */
    private void setUpHintRefresh() {
        final List<String> HINTS = new ArrayList<>(Arrays.asList("search here", "try comp", "try comp2100",
                "try comp2100, pre", "try comp2100, comp2400", "try computer science", "try course name"));
        hintRefreshThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!hintRefreshThread.isInterrupted()) {
                        Thread.sleep(1500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                input.setHint(HINTS.get(curHint++));
                                if (curHint == HINTS.size()) curHint = 0;
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        hintRefreshThread.start();
    }

    /**
     * Autocomplete course full name for each input separated by comma
     *
     * @author Xinyu Zheng
     */
    private void setUpAutoComplete() {
        final List<String> COURSE_NAMES = new ArrayList<>(Token.nameSet);
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COURSE_NAMES);
        input.setAdapter(autoCompleteAdapter);
        input.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    public void init(String file1, String file2, Context context) {
        InputStream inputStream = this.getResources().openRawResource(R.raw.users);
        androidFileParser androidFileParser = new androidFileParser();
        Initialization init = new Initialization();
        init.Initialization(androidFileParser.parseJson("courses.json", context), androidFileParser.parseCsv("majors.csv", context), androidFileParser.parseXML(inputStream));
        this.tree = init.tree;
        this.map = init.map;
        this.majorList = init.majorList;
        this.userList = init.userList;
        user.User("Eckel", "1234", userList);
        //System.out.println(user.id);
        //System.out.println(androidFileParser.tree.inOrder(androidFileParser.tree.root));
    }

    /**
     * search process:
     * 1/clear the data
     * 2/use tokenizer&parser
     * 3/use search() get nodes(one node or nodes)
     * 4/choose some information to display in the list
     * 5/adapt change
     *
     * @author Xiran Yan
     */

    public void onSearch(View v) {
        displayList.clear();
        coursedetail = new ArrayList<>();
        myInputTokenizer = new InputTokenizer(input.getText().toString());
        parsed = new Parser(myInputTokenizer).parseInput();
        if (parsed.size() > 0) {
            for (List<String> oneparse : parsed) {
                Search search = new Search();
                System.out.println(parsed.size());
                System.out.println(oneparse.get(0));
                if (oneparse.get(1).matches("major")) {
                    //major engine
                    newNodes = search.searchMajor(oneparse, tree, majorList);
                } else {
                    //college&courseId&courseName engine
                    newNodes = search.searchTree(oneparse, tree);
                    if (!newNodes.isEmpty()) {
                        //condition with pre operation
                        if (oneparse.get(1).matches("courseId") || oneparse.get(1).matches("courseName")) {
                            Node node = newNodes.get(0);
                            if (oneparse.size() == 3) {
                                displayList.add(search.searchPre(node, map));
                                //coursedetail is null,pre to keep the same number with displaylist
                                Course course = new Course("pre", null);
                                coursedetail.add(course);
                            }
                        }
                    } else {
                        Toast.makeText(this, "don't have the information", (int) 100).show();
                        System.out.println("user input error or don't have the data!");
                    }
                }
                //conditon without the pre operation
                if (newNodes != null && oneparse.size() < 3) {
                    ArrayList<Course> courses = search.searchMap(newNodes, map);
                    coursedetail.addAll(courses);
                    // System.out.println("rank:"+coursedetail.get(0)+","+coursedetail.get(1));
                    List<String> items = new ArrayList<>();
                    for (Course course : courses) {
                        if (course.courseDetail != null) {
                            String item = course.courseDetail.get(1) + course.courseDetail.get(2) + "\n" + course.courseDetail.get(4);
                            items.add(item);
                        }
                    }
                    displayList.addAll(items);
                }
            }
        } else {
            System.out.println("user input error or don't have the data!");
            Toast.makeText(this, "input error or don't have the information", (int) 100).show();
        }
        displayList = rank(displayList);
        listAdapter.notifyDataSetChanged();
    }

    /**
     * rank process:
     * 1/get the display and courseDetail( their are responseble with each other,same index)
     * 2/loop find the history match(from the old to the newest history)
     * 3/move this find point to the top of the list and delete the origin point (both displaylist&courseDetail)
     * 4/keep doing that ,it will replace by the newest history on the top of  both lists
     *
     * @author Xiran Yan
     */
    public ArrayList<String> rank(ArrayList<String> displayList) {
        //todo get real data

        Search search = new Search();
        //testing rank(user1)
        ArrayList<String> historyCourses = new ArrayList<>();
        historyCourses.add("2183");
        historyCourses.add("8345");
        for (String course : historyCourses) {
            //System.out.println(map.get(course));
            for (int index = 0; index < this.coursedetail.size(); index++) {
                Course courseNum = this.coursedetail.get(index);
                //System.out.println(courseNum.classNumber);
                if (courseNum.classNumber.matches(course)) {
                    this.coursedetail.add(0, new Course(course, map.get(course)));
                    displayList.add(0, displayList.get(index));
                    this.coursedetail.remove(index + 1);
                    displayList.remove(index + 1);
                   /* System.out.println(this.coursedetail.get(0).classNumber);
                    System.out.println(this.coursedetail.get(1).classNumber);*/
                }
            }
        }
        //System.out.println(historyCourses.get(0));
        return displayList;
    }
}
