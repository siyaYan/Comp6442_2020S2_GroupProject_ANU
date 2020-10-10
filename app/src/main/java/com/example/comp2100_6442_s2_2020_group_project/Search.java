package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Search {
    public ArrayList<Course> courses=new ArrayList<>();
    public RBTreeBarry<String> tree;
    public Map<String,ArrayList<String>> map;
    //step1
    public ArrayList<Node> searchTree(List<String> Parsed,RBTreeBarry<String> Tree) {
        ArrayList<Node> nodes=new ArrayList<>();
        Node node;
        if (Parsed.get(1).matches("college")) {
               nodes = (ArrayList<Node>) Tree.searchNodes(Tree.root,Parsed.get(0),null);
        }
        else if (Parsed.get(1).matches("courseName")) {
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
    //searchMap step3
    public ArrayList<Course> searchMap(ArrayList<Node> nodes,Map<String,ArrayList<String>> Map) {
        ArrayList<String> courseDetail=new ArrayList<>();
        //todo how to use map
       /* for (Node node : nodes) {
            courseDetail= Map.get(node.classNumber);
            Course course=new Course(courseDetail.get(0),courseDetail);
            courses.add(course);
        }*/
        return courses;
    }
    //todo major
    //step2
    public ArrayList<Node> searchMajor(List<String> Parsed,RBTreeBarry<String> Tree,ArrayList<String[]> majorList) {
        ArrayList<Node> nodes=new ArrayList<>();
        ArrayList<String> oneMajor=new ArrayList<>();
        Node node;
        if (Parsed.get(1).matches("major")) {
            for (String[] major : majorList) {
                if (major[0].matches(Parsed.get(0))) {
                    oneMajor=new ArrayList(Arrays.asList(major));
                    oneMajor.subList(1,oneMajor.size()-1);
                }
            }
            //find major
            if (oneMajor.size() > 0) {
                System.out.println(oneMajor.get(0));
                for (String item: oneMajor) {
                    node = Tree.searchNode(item,"courseId");
                    nodes.add(node);
                }
            } else {
                return null;
            }
        }else {
            //todo error occur
            System.out.println("type error!");
        }
        return nodes;
    }

}
