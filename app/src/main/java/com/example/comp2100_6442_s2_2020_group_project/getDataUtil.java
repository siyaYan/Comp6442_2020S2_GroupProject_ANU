package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;
import android.content.res.AssetManager;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/*
 * @author: Xiran Yan
 * @uid: 7167582
 *
 * */

//step1 get file return List<Course>(if read major file return ArrayList<String[]> )
public class getDataUtil {
    //private List<Course> courses;
    //json transfer to string
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

    public ArrayList<String> setList(courseDetail detail) {
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

    public String getJson(String fileName, Context context) {
        //string builder
        StringBuilder stringBuilder = new StringBuilder();
        List<Course> getCourses=new ArrayList<>();
        try {
            //getAssetManager
            AssetManager assetManager = context.getAssets();
            //open file by assetManager and read by bufferedReader
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            //read by line
            String line;
            while ((line = bf.readLine()) != null) {
                /*String[] oneline=line.split(",");
                ArrayList<String> coursedetail=new ArrayList<String>(Arrays.asList(oneline));
                Course course=new Course();
                course.courseDetail=coursedetail;
                course.classNumber=coursedetail.get(0);
                getCourses.add(course);*/
               // stringBuilder.append(line);//append more
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
        //return getCourses;
    }

    //read major file for now
    public ArrayList<String[]> readBespokeFile(String fileName){
        ArrayList<String[]> getMajor=new ArrayList<>();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(new File(fileName)));
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

    public List<Course> readJSONFile(String fileName) {
        Gson gson = new Gson();
        JsonReader jsonReader = null;
        List<Course> getCourses=new ArrayList<>();

        try{
            jsonReader = new JsonReader(new FileReader(new File(fileName)));
            ArrayList<courseDetail> courses=gson.fromJson(jsonReader,new TypeToken<ArrayList<courseDetail>>(){}.getType());
            for (courseDetail detail:courses) {
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
        //System.out.println(getCourses.get(1).courseDetail.get(0));
        return getCourses;
    }
    //todo add more
    public List<Course> readXMLFile(String fileName) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        List<Course> getCourses=new ArrayList<>();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse(new File(fileName)); //parse file,the root of the document tree
            d.getDocumentElement().normalize(); //remove the redundancies
            //get a nodeslist by tag name book
            NodeList nodeList = d.getElementsByTagName("course");
            for(int i = 0; i < nodeList.getLength(); i++)
            {
                org.w3c.dom.Node n = nodeList.item(i);
                //each node is an element
                Element element	= (Element) n;
                ArrayList<String> courseDetail = new ArrayList<>();
                String ClassNumber = element.getElementsByTagName("ClassNumber").item(0).getTextContent();
                String Subject = element.getElementsByTagName("Subject").item(0).getTextContent();
                String CourseID = element.getElementsByTagName("CourseID").item(0).getTextContent();
                String Section = element.getElementsByTagName("Section").item(0).getTextContent();
                //add the attributes into courseDetail
                courseDetail.add(0,ClassNumber);
                courseDetail.add(1,Subject);
                courseDetail.add(2,CourseID);
                courseDetail.add(3,Section);
                //Todo haven't finished!!!

                Course course = new Course(courseDetail.get(0),courseDetail);
                //add book into booklist
                getCourses.add(course);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getCourses;
    }
}


