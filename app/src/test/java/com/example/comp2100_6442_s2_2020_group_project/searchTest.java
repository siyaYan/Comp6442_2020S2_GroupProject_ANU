package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class searchTest {
    RBTreeBarry<String> tree;
    ArrayList<Node> nodes;
    ArrayList<Node> preNodes;
    ArrayList<Node> newNodes;
    ArrayList<String[]> majorList;
    Map<String,ArrayList<String>> map;
    InputTokenizer myInputTokenizer;
    List<String> parsed;
    Initialization initial;
    List<Course> courses;
    File file;

    @Test
    public void searchTreeTest() {
        tree= new RBTreeBarry<>();
        nodes=new ArrayList<>();
        myInputTokenizer = new InputTokenizer("Logic");
        parsed = new Parser(myInputTokenizer).parseInput();
        initial=new Initialization();
        courses=new ArrayList<>();
        file = new File("src/main/assets/someCourses.json");
        courses=new getDataUtil().readJSONFile(file);
        nodes=initial.setNodes(courses);
        tree=initial.initTree( nodes);

        newNodes=new Search().searchTree(parsed,tree);
        for(Node node:newNodes)
            System.out.println(node.courseID);

    }

    @Test
    public void searchMajorTest() {
        tree= new RBTreeBarry<>();
        nodes=new ArrayList<>();
        majorList=new ArrayList<>();
        ArrayList<String> oneMajor=new ArrayList<>();
        File file1 = new File("src/main/assets/majors.csv");
        majorList=new getDataUtil().readBespokeFile(file1);
        myInputTokenizer = new InputTokenizer("ComputerScience");//todo parser need to be same with major database
        parsed = new Parser(myInputTokenizer).parseInput();
        initial=new Initialization();
        courses=new ArrayList<>();
        file = new File("src/main/assets/someCourses.json");
        courses=new getDataUtil().readJSONFile(file);
        nodes=initial.setNodes(courses);
        tree=initial.initTree( nodes);

        newNodes=new Search().searchMajor(parsed,tree,majorList);
        for(Node node:newNodes)
            System.out.println(node.courseID);
    }

/*    @Test
    public void searchPreTest() {
        //ArrayList<String> preCourseId=new  ArrayList<>();
        map =new HashMap<>();
        tree= new RBTreeBarry<>();
        nodes=new ArrayList<>();
        preNodes=new ArrayList<>();
        myInputTokenizer = new InputTokenizer("COMP2100 pre");
        parsed = new Parser(myInputTokenizer).parseInput();
        initial=new Initialization();
        courses=new ArrayList<>();
        file = new File("src/main/assets/someCourses.json");
        courses=new getDataUtil().readJSONFile(file);
        nodes=initial.setNodes(courses);
        tree=initial.initTree( nodes);
        map=initial.initMap( courses);

        newNodes=new Search().searchPre(parsed,tree,map);
        for(Node node:newNodes)
            System.out.println(node.courseID);
    }*/

    @Test
    public void searchMapTest() {
        ArrayList<String> courseDetail=new ArrayList<>();
        ArrayList<Course> courseList;
        map =new HashMap<>();
        tree= new RBTreeBarry<>();
        nodes=new ArrayList<>();
        myInputTokenizer = new InputTokenizer("Logic");
        parsed = new Parser(myInputTokenizer).parseInput();
        initial=new Initialization();
        courses=new ArrayList<>();
        file = new File("src/main/assets/someCourses.json");
        courses=new getDataUtil().readJSONFile(file);
        nodes=initial.setNodes(courses);
        tree=initial.initTree( nodes);
        map=initial.initMap(courses);
        ArrayList<Node> getNodes=new Search().searchTree(parsed,tree);

        courseList=new Search().searchMap(getNodes,map);
        for(Course course :courseList)
            System.out.println(course.courseDetail);
    }
}
