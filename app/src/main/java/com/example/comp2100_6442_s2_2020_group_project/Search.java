package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Search {
    public ArrayList<Course> courses=new ArrayList<>();
    public RBTreeBarry<String> tree;
    public Map<String,ArrayList<String>> map;

    public ArrayList<Node> searchTree(List<String> Parsed,RBTreeBarry<String> Tree) {
        ArrayList<Node> nodes=new ArrayList<>();
        Node node;
        if (Parsed.get(1).matches("college")) {
               nodes = (ArrayList<Node>) Tree.searchNodes(Tree.root,Parsed.get(0),null);
        }
        //todo major
        else if (Parsed.get(1).matches("major")) {

        } else if (Parsed.get(1).matches("courseName")) {
            node = Tree.searchNode(Parsed.get(0),Parsed.get(1));
            nodes.add(node);
        } else if (Parsed.get(1).matches("classNumber")) {
            node = Tree.searchNode(Parsed.get(0),Parsed.get(1));
            nodes.add(node);
        } else {
            //todo error occur
            System.out.println("type error!");
        }
        return nodes;
    }
    //searchMap
    public ArrayList<Course> searchMap(ArrayList<Node> nodes,Map<String,ArrayList<String>> Map) {
        ArrayList<String> courseDetail=new ArrayList<>();
        for (Node node : nodes) {
            courseDetail= Map.get(node.classNumber);
            Course course=new Course(courseDetail.get(0),courseDetail);
            courses.add(course);
        }
        return courses;
    }
}
