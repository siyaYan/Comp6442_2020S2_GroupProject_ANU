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

import static org.junit.Assert.assertEquals;

public class initialTest {
/*    @Test
    public void setNodesFromJsonTest() {
        ArrayList<Node> nodeLists = new ArrayList<>();
        String fileName="src/main/assets/courses.json";
        Context context=new Application().getApplicationContext();
        nodeLists=new Initialization().setNodesFromJson(fileName,context);
        for (Node node : nodeLists) {
            System.out.println(node.courseID);
        }
    }*/

    @Test
    public void setNodesTest() {
        String fileName1 = "src/main/assets/courses.json";
        List<Course> courses=new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();
        courses=new getDataUtil().readJSONFile(fileName1);
        nodes=new Initialization().parserToNodes(courses);
        /*for(Node node:nodes)
        System.out.println(node.courseName);*/
        Node firstNode =nodes.get(0);
        assertEquals(firstNode.classNumber, "1205");
    }

    @Test
    public  void initTreeTest() {
        ArrayList<Node> nodes = new ArrayList<>();
        Initialization initial=new Initialization();
        RBTreeBarry<String> tree = new RBTreeBarry<>();
        List<Course> courses=new ArrayList<>();
        String fileName = "src/main/assets/courses.json";
        courses=new getDataUtil().readJSONFile(fileName);
        nodes=initial.parserToNodes(courses);
        tree=initial.initTree( nodes);
        //System.out.println((tree.root.classNumber));
        Node rootNode =tree.root;
        assertEquals(rootNode.classNumber, "3471");
    }

    @Test
    public  void initListTest() {
        ArrayList<String[]> majors=new ArrayList<>();
        Initialization initial=new Initialization();

        String fileName = "src/main/assets/majors.csv";
        majors=new getDataUtil().readBespokeFile(fileName);
        //initial.initList( majors);
        /*for (String[] courses : majors) {
            System.out.println(courses[0]+":"+courses[1]);
        }*/
        String[] lastMajor=majors.get(majors.size()-1);
        assertEquals(lastMajor[0], "Water Science");
    }

    @Test
    public  void initMapTest() {
        ArrayList<Node> nodes = new ArrayList<>();
        Initialization initial=new Initialization();
        RBTreeBarry<String> tree = new RBTreeBarry<>();
        List<Course> courses=new ArrayList<>();
        Map<String,ArrayList<String>> map =new HashMap<>();

        String fileName = "src/main/assets/courses.json";
        courses=new getDataUtil().readJSONFile(fileName);
        map=initial.initMap( courses);

        //Set<String> keys=  map.keySet();
        /*for (String key : keys) {
            System.out.println(map.get(key).get(0));
        }*/
        assertEquals(map.get("1205").get(2), "2114");
    }
}
