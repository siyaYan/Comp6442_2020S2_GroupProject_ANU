package com.example.comp2100_6442_s2_2020_group_project;

import android.app.Application;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class initialTest {
/*    @Test
    public void setNodesFromJsonTest() {
        ArrayList<Node> nodeLists = new ArrayList<>();
        String fileName="src/main/assets/someCourses.json";
        Context context=new Application().getApplicationContext();
        nodeLists=new Initialization().setNodesFromJson(fileName,context);
        for (Node node : nodeLists) {
            System.out.println(node.courseID);
        }
    }*/

    @Test
    public void setNodesTest() {
        String fileName1 = "src/main/assets/someCourses.json";
        List<Course> courses=new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();
        courses=new getDataUtil().readJSONFile(fileName1);
        nodes=new Initialization().setNodes(courses);
        for(Node node:nodes)
        System.out.println(node.courseName);
    }

    @Test
    public  void initTreeTest() {
        ArrayList<Node> nodes = new ArrayList<>();
        Initialization initial=new Initialization();
        RBTreeBarry<String> tree = new RBTreeBarry<>();
        List<Course> courses=new ArrayList<>();
        String fileName = "src/main/assets/someCourses.json";
        courses=new getDataUtil().readJSONFile(fileName);
        nodes=initial.setNodes(courses);
        tree=initial.initTree( nodes);
        System.out.println(tree.preOrder(tree.root));
    }

    @Test
    public  void initListTest() {
        ArrayList<String[]> majors=new ArrayList<>();
        Initialization initial=new Initialization();

        String fileName = "src/main/assets/majors.csv";
        majors=new getDataUtil().readBespokeFile(fileName);
        //initial.initList( majors);
        for (String[] courses : majors) {
            System.out.println(courses[0]+":"+courses[1]);
        }
    }

    @Test
    public  void initMapTest() {
        ArrayList<Node> nodes = new ArrayList<>();
        Initialization initial=new Initialization();
        RBTreeBarry<String> tree = new RBTreeBarry<>();
        List<Course> courses=new ArrayList<>();
        Map<String,ArrayList<String>> map =new HashMap<>();

        String fileName = "src/main/assets/someCourses.json";
        courses=new getDataUtil().readJSONFile(fileName);
        map=initial.initMap( courses);

        Set<String> keys=  map.keySet();
        //System.out.println(map.get("1205"));
        for (String key : keys) {
            System.out.println(map.get(key).get(0));
        }
    }
}
