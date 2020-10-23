package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author: Xiran Yan
 * @uid: 7167582
 */
public class searchTest {
    RBTree<String> tree;
    ArrayList<Node> nodes;
    ArrayList<Node> preNodes;
    ArrayList<Node> newNodes;
    ArrayList<String[]> majorList;
    Map<String,ArrayList<String>> map;
    InputTokenizer myInputTokenizer;
    List<List<String>> parsed;
    List<String> oneparse;
    Initialization initial;
    List<Course> courses;
    File file;

    @BeforeClass
    public static void setup() {
        Token.loadData();
    }
    @Test
    public void searchTreeTest() {
        tree= new RBTree<>();
        nodes=new ArrayList<>();
        myInputTokenizer = new InputTokenizer("comp1100,Comp 2100");
        parsed = new Parser(myInputTokenizer).parseInput();
        //System.out.println(parsed );
        initial=new Initialization();
        courses=new ArrayList<>();
        String fileName1="src/main/assets/courses.json";
        courses=new GetDataUtil().readJSONFile(fileName1);
        nodes=initial.parserToNodes(courses);
        tree=initial.initTree( nodes);
        String nodeIds="";
        for (List<String> oneparse : parsed) {
            newNodes=new Search().searchTree(oneparse,tree);
            for(Node node:newNodes)
                nodeIds +=node.courseID+",";
        }
        assertEquals(nodeIds, "COMP1100,COMP2100,");
    }
    //can only search for one major
    @Test
    public void searchMajorTest() {
        tree= new RBTree<>();
        nodes=new ArrayList<>();
        majorList=new ArrayList<>();
        ArrayList<String> oneMajor=new ArrayList<>();
        String fileName2= "src/main/assets/majors.csv";
        majorList=new GetDataUtil().readBespokeFile(fileName2);
        myInputTokenizer = new InputTokenizer("Water science");
        parsed = new Parser(myInputTokenizer).parseInput();
        initial=new Initialization();
        courses=new ArrayList<>();
        String fileName1="src/main/assets/courses.json";
        courses=new GetDataUtil().readJSONFile(fileName1);
        nodes=initial.parserToNodes(courses);
        tree=initial.initTree( nodes);
        List<String> oneparse=parsed.get(0);
        newNodes=new Search().searchMajor(oneparse,tree,majorList);
        String courses ="";
        for(Node node:newNodes)
             courses += node.courseID+",";
        assertEquals(courses, "EMSC1006,ENVS2020,ENVS3005,EMSC3025,CHEM1101,EMSC2024,EMSC3014,EMSC3050,");
    }

    @Test
    public void searchPreTest() {
        map =new HashMap<>();
        tree= new RBTree<>();
        nodes=new ArrayList<>();
        preNodes=new ArrayList<>();
        myInputTokenizer = new InputTokenizer("COMP2100 pre");
        parsed = new Parser(myInputTokenizer).parseInput();
        oneparse=parsed.get(0);
        initial=new Initialization();
        courses=new ArrayList<>();
        String fileName1 = "src/main/assets/courses.json";
        courses=new GetDataUtil().readJSONFile(fileName1);
        nodes=initial.parserToNodes(courses);
        tree=initial.initTree( nodes);
        map=initial.initMap( courses);
        ArrayList<Node> node=new Search().searchTree(oneparse,tree);
        String preRequire=new Search().searchPre(node.get(0),map);
        assertEquals(preRequire, " To enrol in this course you must have successfully completed COMP1100 or COMP1130, and COMP1110 or COMP1140 or COMP1510, and 6 units of 1000 level MATH. You are not able to enrol in this course if you have completed COMP2500 or COMP6442.");
    }

    @Test
    public void searchMapTest() {
        ArrayList<String> courseDetail=new ArrayList<>();
        ArrayList<Course> courseList;
        map =new HashMap<>();
        tree= new RBTree<>();
        nodes=new ArrayList<>();
        myInputTokenizer = new InputTokenizer("Logic,Cognition");
        parsed = new Parser(myInputTokenizer).parseInput();
        initial=new Initialization();
        courses=new ArrayList<>();
        String fileName1="src/main/assets/courses.json";
        courses=new GetDataUtil().readJSONFile(fileName1);
        nodes=initial.parserToNodes(courses);
        tree=initial.initTree( nodes);
        map=initial.initMap(courses);
        String mapId="";
        for (List<String> oneparse : parsed) {
            ArrayList<Node> getNodes=new Search().searchTree(oneparse,tree);
            courseList=new Search().searchMap(getNodes,map);
            for(Course course :courseList)
               mapId += course.courseDetail.get(0)+",";
        }
        assertEquals(mapId, "2578,8916,");
    }
}
