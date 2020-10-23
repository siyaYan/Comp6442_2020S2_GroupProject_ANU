package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A util function with 2 ways to parse the file(json&csv) by fileName
 * (files are in assets,using assetManger)
 * useing for the Android
 * Init the map,tree,list dataSet for further using from the file database
 *
 * @author: Xiran Yan
 * @uid: 7167582
 */
public class AndroidFileParser {

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

    public ArrayList<String> setList(GetDataUtil.courseDetail detail) {
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

    public ArrayList<String[]> parseCSV(String fileName, Context context) {
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
                    String[] majorCourses = records.trim().split(",");
                    getMajor.add(majorCourses);
                }
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            return getMajor;
    }

    public List<Course> parseJSON(String fileName, Context context) {
        Gson gson = new Gson();
        List<Course> getCourses=new ArrayList<>();
        JsonReader jsonReader = null;
        AssetManager assetManager = context.getAssets();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName))));
            jsonReader = new JsonReader(br);
            ArrayList<GetDataUtil.courseDetail> courses=gson.fromJson(jsonReader,new TypeToken<ArrayList<GetDataUtil.courseDetail>>(){}.getType());
            for (GetDataUtil.courseDetail detail:courses) {
                ArrayList<String> coursedetail=new ArrayList<>();
                coursedetail = setList(detail);
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

//    public ArrayList<User> parseXML(InputStream inputStream) {
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        ArrayList<User> getUsers=new ArrayList<>();
//        try {
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document d = db.parse(inputStream); //parse file,the root of the document tree
//            d.getDocumentElement().normalize(); //remove the redundancies
//            NodeList nodeList = d.getElementsByTagName("user");
//            for(int i = 0; i < nodeList.getLength(); i++)
//            {
//                org.w3c.dom.Node n = nodeList.item(i);
//                Element element	= (Element) n;
//                String id = element.getElementsByTagName("id").item(0).getTextContent();
//                String userName = element.getElementsByTagName("userName").item(0).getTextContent();
//                String password= element.getElementsByTagName("password").item(0).getTextContent();
//                User user=new User();
//                user.id=id;
//                user.userName=userName;
//                user.password=password;
//                getUsers.add(user);
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return getUsers;
//    }

       /*public List<Course> getJson(String fileName, Context context) {
        //string builder
        //StringBuilder stringBuilder = new StringBuilder();
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
                String[] oneline=line.split(",");
                ArrayList<String> coursedetail=new ArrayList<String>(Arrays.asList(oneline));
                Course course=new Course();
                course.courseDetail=coursedetail;
                course.classNumber=coursedetail.get(0);
                getCourses.add(course);
               // stringBuilder.append(line);//append more
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return stringBuilder.toString();
        return getCourses;
    }*/

}
