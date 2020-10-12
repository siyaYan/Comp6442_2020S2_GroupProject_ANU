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
   // public RBTreeBarry<String> tree;
   // public Map<String,ArrayList<String>> map;
    //step1
    public ArrayList<Node> searchTree(List<String> Parsed,RBTreeBarry<String> Tree) {
        ArrayList<Node> nodes=new ArrayList<>();
        Node node;
        //searchByCollege goto searchNodes(return many nodes)
        if (Parsed.get(1).matches("college")) {
               nodes = (ArrayList<Node>) Tree.searchNodes(Tree.root,Parsed.get(0),new ArrayList<Node>());
        }//searchByName/Id goto searchNode(return node)
        else if (Parsed.get(1).matches("courseName")|| Parsed.get(1).matches("courseId")) {
            node = Tree.searchNode(Parsed.get(0),Parsed.get(1));
            if(node!=null)
            nodes.add(node);
        } else {
            //todo error occur
            System.out.println("type error!");
        }
        return nodes;
    }
    //step2
    public ArrayList<Node> searchMajor(List<String> Parsed,RBTreeBarry<String> Tree,ArrayList<String[]> majorList) {
        ArrayList<Node> nodes=new ArrayList<>();
        ArrayList<String> oneMajor=new ArrayList<>();
        Node node;
        if (Parsed.get(1).matches("major")) {
            //get all courses in major
            for (String[] major : majorList) {
                if (major[0].matches(Parsed.get(0))) {
                    oneMajor=new ArrayList(Arrays.asList(major));
                    //the first item store major name,so get sublist
                    oneMajor.subList(1,oneMajor.size()-1);
                }
            }
            //find major in majorList
            if (oneMajor.size() > 0) {
                //System.out.println(oneMajor.get(0));
                //method 1 : get nodeList,then call searchMap in mainActivity
                //Independent !& one function focus on one thing!---based on the principle of designing (High aggregation, low coupling)
                for (String item: oneMajor) {
                    node = Tree.searchNode(item,"courseId");
                    if(node!=null)
                    nodes.add(node);
                }
                //method 2 : directly searchMap in this function
            }
            //don't have this major in majorList
            else {
                return null;
            }
        }
        else {
            //todo error occur
            System.out.println("type error!");
        }
        return nodes;
    }

    //todo searchPre
    //search step3(if have preCourse show preCourse otherwise show course)
    //only input courseName & courseId can have pre operation
    /*public ArrayList<Node> searchPre(List<String> Parsed,RBTreeBarry<String> Tree,Map<String,ArrayList<String>> Map) {
        ArrayList<Node> nodes=new ArrayList<>();
        ArrayList<Node> preNodes=new ArrayList<>();
       // ArrayList<Course> Courses=new  ArrayList<>();
        ArrayList<String> preCourseId=new  ArrayList<>();
        Node node;
        if ((Parsed.get(1).matches("courseName") || Parsed.get(1).matches("courseId")) && Parsed.get(2).matches("pre")) {
            node = Tree.searchNode(Parsed.get(0),Parsed.get(1));
            nodes.add(node);
            //get the course we input(can have two same course,just ignore it)
            courses=searchMap(nodes,Map);
            for (Course course : courses) {
                //todo modify json file, transfer empty preCourse into String null ,otherwise can't find the right Precourse
                //todo find solution about find courseIds in pre sentence
                if (course.courseDetail.get(9).contains("COMP1110")) {
                    //todo extract preCourseId (can be more than one)
                    preCourseId.add(course.courseDetail.get(9).substring(0, 4));
                    //each loop have one courseId(extract from courseDetail)
                    for (String courseId : preCourseId) {
                        preNodes.add(Tree.searchNode(courseId, "courseId"));
                    }
                }
            }
        } else {
            //todo error occur
            System.out.println("type error!");
        }
        return preNodes;
    }*/

    //searchMap final step(one or more courses)
    public ArrayList<Course> searchMap(ArrayList<Node> nodes,Map<String,ArrayList<String>> Map) {
        ArrayList<String> courseDetail=new ArrayList<>();
        //todo use map
        for (Node node : nodes) {
            courseDetail= Map.get(node.classNumber);
            Course course=new Course(courseDetail.get(0),courseDetail);
            courses.add(course);
        }
        return courses;
    }
}
