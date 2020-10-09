package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

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

public class getDataUtil {
    //private List<Course> courses;
    //json transfer to string
    public static String getJson(String fileName, Context context) {
        //string builder
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //getAssetManager
            AssetManager assetManager = context.getAssets();
            //open file by assetManager and read by bufferedReader
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            //read by line
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);//append more
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static List<Course> readBespokeFile(File file) {
        List<Course> getCourses=new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String records;
            //read nextLine
            while((records=br.readLine()) !=null)
            {
                String[] courseDetail = records.split(",");
                Course course=new Course(courseDetail[0], new ArrayList(Arrays.asList(courseDetail)));
                getCourses.add(course);
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        return getCourses;
    }

    public static List<Course> readJSONFile(File file) {
        Gson gson = new Gson();
        JsonReader jsonReader = null;
        List<Course> getCourses=new ArrayList<>();
        try{
            jsonReader = new JsonReader(new FileReader(file));
            getCourses=gson.fromJson(jsonReader, TypeToken.getParameterized(ArrayList.class, Course.class).getType());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return getCourses;
    }

    public static List<Course>  readXMLFile(File file) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        List<Course> getCourses=new ArrayList<>();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse(file); //parse file,the root of the document tree
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


