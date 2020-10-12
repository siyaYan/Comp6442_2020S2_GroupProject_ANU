package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//step2 get List<Course> set tree map
public class Initialization {
    public RBTreeBarry<String> tree;
    public Map<String,ArrayList<String>> map;
    public ArrayList<String[]> list;
    public JSONObject jsonObject;
    public JSONArray jsonArray;
    //major/subject/courseId/Name
  /*  public Initialization(String fileName, Context context) {
        this.tree=initTree(setNodesFromJson(fileName,  context));
        this.map=initMap(new getDataUtil().getJson(fileName,  context));
        this.initList(new getDataUtil().readBespokeFile(fileName));
    }*/
    public Initialization(String fileName1,String fileName2) {
        List<Course> courses=new getDataUtil().readJSONFile(fileName1);
        this.tree = initTree(setNodes(courses));
        this.map = initMap(courses);
        this.list=this.initList(new getDataUtil().readBespokeFile(fileName2));
    }
    public Initialization() {
    }
   /* public Initialization(RBTreeBarry<String> Tree, Map<String,ArrayList<String>> Map,ArrayList<String[]> List) {
        this.tree=Tree;
        this.map=Map;
        this.list=List;
    }*/
    //step1 method1, better choose method2
    public ArrayList<Node> setNodesFromJson(String fileName, Context context) {
        ArrayList<Node> nodeLists = new ArrayList<>();
        //get jsonString
        String JsonData =new getDataUtil().getJson(fileName,context);
        // System.out.println(JsonData);
        try {
            jsonObject = new JSONObject(JsonData);
            jsonArray= jsonObject.getJSONArray("");
            //put the json information into node
            for (int i = 0; i < jsonArray.length();i++) {
                Node node= new Node();
                node.courseID=jsonArray.getJSONObject(i).getString("CourseID");
                node.courseName=jsonArray.getJSONObject(i).getString("CourseName");
                node.classNumber=jsonArray.getJSONObject(i).getString("classNumber");
                nodeLists.add(node);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nodeLists;
    }
    //step1 method2
    public ArrayList<Node> setNodes(List<Course> courses) {
        ArrayList<Node> nodeLists = new ArrayList<>();
        for (Course course:courses) {
            Node node=new Node();
            node.courseID=course.courseDetail.get(1)+course.courseDetail.get(2);
            //System.out.println(node.courseID);
            node.courseName=course.courseDetail.get(4);
            node.classNumber=course.courseDetail.get(0);
            nodeLists.add(node);
        }
        return nodeLists;
    }
    //step2
    public RBTreeBarry<String> initTree(ArrayList<Node> nodes) {
        tree = new RBTreeBarry<String>();
        for (Node node : nodes) {
            tree.insertValue(node.courseID.toString(),node.classNumber.toString(),node.courseName.toString());
        }
        //display the tree inorder
        //System.out.println(tree.inOrder(tree.root));
        return tree;
    }
    //step3
    public ArrayList<String[]>  initList(ArrayList<String[]> List) {
        return List;
    }
    //step4
    public Map<String,ArrayList<String>> initMap( List<Course> courses) {
        map=new HashMap<>();
        for (Course course:courses) {
            map.put(course.classNumber,course.courseDetail);
        }
        return map;
    }
}
