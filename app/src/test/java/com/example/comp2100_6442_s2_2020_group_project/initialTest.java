package com.example.comp2100_6442_s2_2020_group_project;

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
   /* @Test
    public void setNodesFromJsonTest(String fileName, Context context) {
        ArrayList<Node> nodeLists = new ArrayList<>();

        for (Node node : nodeLists) {
            System.out.println(node.courseID);
        }
    }*/

    @Test
    public void setNodesTest() {
        File file = new File("src/main/assets/someCourses.json");
        List<Course> courses=new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();
        courses=new getDataUtil().readJSONFile(file);
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
        File file = new File("src/main/assets/someCourses.json");
        courses=new getDataUtil().readJSONFile(file);
        nodes=initial.setNodes(courses);
        tree=initial.initTree( nodes);
        System.out.println(tree.preOrder(tree.root));
    }

    @Test
    public  void initListTest() {
        ArrayList<String[]> majors=new ArrayList<>();
        Initialization initial=new Initialization();

        File file = new File("src/main/assets/majors.csv");
        majors=new getDataUtil().readBespokeFile(file);
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

        File file = new File("src/main/assets/someCourses.json");
        courses=new getDataUtil().readJSONFile(file);
        map=initial.initMap( courses);

        Set<String> keys=  map.keySet();
        //System.out.println(map.get("1205"));
        for (String key : keys) {
            System.out.println(map.get(key).get(0));
        }
    }
}
