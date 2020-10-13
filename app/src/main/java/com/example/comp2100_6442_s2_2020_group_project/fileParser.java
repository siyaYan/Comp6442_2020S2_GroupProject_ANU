package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fileParser {
    public JSONObject jsonObject;
    public JSONArray jsonArray;
    ArrayList<Node> nodeLists;
    public RBTreeBarry<String> tree;
    public Map<String,ArrayList<String>> map;
    public ArrayList<String[]> list;

    public class courseDetail {
        String classNumber;
        String Subject;
        String CourseID;
        String Section;
        String CourseName;
        String MinUnits;
        String MaxUnits;
        String Description;
        String College;
        String RequisiteGroupDescription;
        String GradeBase;
        String StartDate;
        String EndDate;
        String CensusDateDeadline;
        String LastDateToEnrol;
        String ModeOfDelivery;
    }

    public ArrayList<String> setList(getDataUtil.courseDetail detail) {
        ArrayList<String> courseDetail=new ArrayList<>();
        courseDetail.add(detail.classNumber);
        courseDetail.add(detail.Subject);
        courseDetail.add(detail.CourseID);
        courseDetail.add(detail.Section);
        courseDetail.add(detail.CourseName);
        courseDetail.add(detail.MinUnits);
        courseDetail.add(detail.MaxUnits);
        courseDetail.add(detail.Description);
        courseDetail.add(detail.College);
        courseDetail.add(detail.RequisiteGroupDescription);
        courseDetail.add(detail.GradeBase);
        courseDetail.add(detail.StartDate);
        courseDetail.add(detail.EndDate);
        courseDetail.add(detail.CensusDateDeadline);
        courseDetail.add(detail.LastDateToEnrol);
        courseDetail.add(detail.ModeOfDelivery);
        return courseDetail;
    }

    public void init(String fileName1,String fileName2,Context context) {
        if (fileName1.contains(".json")) {
            List<Course> courses=parseJson(fileName1,context);
            this.tree = initTree(parserToNodes(courses));
            this.map = initMap(courses);
        } else if (fileName2.contains(".csv")) {
            ArrayList<String[]> major=parseCsv(fileName2,context);
            this.list=this.initList(major);
        } else {
            System.out.println("type error!");
        }
    }
    public void fileParser() {
    }

    public ArrayList<String[]> parseCsv (String fileName, Context context) {
        //string builder
            ArrayList<String[]> getMajor=new ArrayList<>();
            BufferedReader br = null;
            AssetManager assetManager = context.getAssets();
            try
            {
                br = new BufferedReader(new BufferedReader(new InputStreamReader(
                        assetManager.open(fileName))));
                String records;
                //read nextLine
                while((records=br.readLine()) !=null)
                {
                    String[] majorCourses = records.split(",");
                    getMajor.add(majorCourses);
                }
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            return getMajor;
    }

    public List<Course> parseJson (String fileName, Context context) {
        Gson gson = new Gson();
        List<Course> getCourses=new ArrayList<>();
        JsonReader jsonReader = null;
        AssetManager assetManager = context.getAssets();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName))));
            jsonReader = new JsonReader(br);
            ArrayList<getDataUtil.courseDetail> courses=gson.fromJson(jsonReader,new TypeToken<ArrayList<getDataUtil.courseDetail>>(){}.getType());
            for (getDataUtil.courseDetail detail:courses) {
                ArrayList<String> coursedetail=new ArrayList<>();
                coursedetail= setList(detail);
                Course course=new Course();
                //System.out.println(coursedetail.get(12));
                course.courseDetail=coursedetail;
                course.classNumber=coursedetail.get(0);
                //System.out.println(course.classNumber);
                getCourses.add(course);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return getCourses;
    }

    public ArrayList<Node> parserToNodes(List<Course> courses) {
        ArrayList<Node> nodeLists = new ArrayList<>();
        for (Course course:courses) {
            Node node=new Node();
            node.courseID=course.courseDetail.get(1)+course.courseDetail.get(2);
            node.courseName=course.courseDetail.get(4);
            node.classNumber=course.courseDetail.get(0);
            nodeLists.add(node);
        }
        return nodeLists;
    }

    public RBTreeBarry<String> initTree(ArrayList<Node> nodes) {
        tree = new RBTreeBarry<String>();
        for (Node node : nodes) {
            tree.insertValue(node.courseID.toString(),node.classNumber.toString(),node.courseName.toString());
        }
        //display the tree inorder
        //System.out.println(tree.inOrder(tree.root));
        return tree;
    }

    public ArrayList<String[]>  initList(ArrayList<String[]> List) {
        return List;
    }
    public Map<String,ArrayList<String>> initMap(List<Course> courses) {
        map=new HashMap<>();
        for (Course course:courses) {
            map.put(course.classNumber,course.courseDetail);
        }
        return map;
    }
}
