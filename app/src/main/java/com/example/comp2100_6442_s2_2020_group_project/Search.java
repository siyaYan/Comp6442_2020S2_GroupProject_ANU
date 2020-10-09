package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search {
    public ArrayList<Course> courses=new ArrayList<>();

    public ArrayList<Node> searchTree(List<String> parsed) {
        ArrayList<Node> nodes=new ArrayList<>();
        if (parsed.get(1).matches("college")) {

               /* Node target = new Node(course,null,course);
                Boolean equal=root.courseName.toString().contains(target.courseName.toString());
                if (equal) {
                    result = root;
                } else {
                    //go to the left subtree
                    result = searchRecurseByName(root.left, target);
                    if (result == null || result.courseID == null)
                        //if can't find in the left subtree go to right
                        result = searchRecurseByName(root.right, target);
                }
                for()
            */
        }
        //todo major
        else if (parsed.get(1).matches("major")) {

        } else if (parsed.get(1).matches("courseName")) {

        } else if (parsed.get(1).matches("classNumber")) {

        } else {
            //todo error occur
            System.out.println("type error!");
        }
        return nodes;
    }
    //todo searchMap
    public ArrayList<Course> searchMap(ArrayList<Node> nodes) {
        return courses;
    }
}
