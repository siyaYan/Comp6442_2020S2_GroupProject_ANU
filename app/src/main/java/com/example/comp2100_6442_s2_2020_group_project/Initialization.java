package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Initialization {
    public RBTreeBarry<String> tree;
    public Map<String,ArrayList<String>> map;
  //  public ArrayList<Node> nodes = new ArrayList<>();
    public Node node= new Node();
    public JSONObject jsonObject;
    public JSONArray jsonArray;
    public Initialization(RBTreeBarry<String> Tree,Map<String,ArrayList<String>> Map) {
        this.tree=Tree;
        this.map=Map;
    }
    public ArrayList<Node> setNodesFromJson(String fileName, Context context) {
        ArrayList<Node> nodeLists = new ArrayList<>();
        //get jsonString
        String JsonData =new getDataUtil().getJson(fileName,context);
        // System.out.println(JsonData);
        try {
            jsonObject = new JSONObject(JsonData);
            jsonArray= jsonObject.getJSONArray("courses");
            //put the json information into node
            for (int i = 0; i < jsonArray.length();i++) {
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

    public ArrayList<Node> setNodes(List<Course> courses) {
        ArrayList<Node> nodeLists = new ArrayList<>();
        for (Course course:courses) {
            node.courseID=course.courseDetail.get(1)+course.courseDetail.get(2);
            node.courseName=course.courseDetail.get(4);
            node.classNumber=course.courseDetail.get(0);
            nodeLists.add(node);
        }
        return nodeLists;
    }

    public RBTreeBarry<String> initTree(ArrayList<Node> nodes) {
        for (Node node : nodes) {
            tree.insertValue(node.courseID.toString(),node.classNumber.toString(),node.courseName.toString());
        }
        //display the tree inorder
        System.out.println(tree.inOrder(tree.root));
        return tree;
    }
    //TODO how to use map
    public Map<String,ArrayList<String>> initMap( List<Course> courses) {
        for (Course course:courses) {
            map.put(course.classNumber,course.courseDetail);
        }
        return map;
    }
}
