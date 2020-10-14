package com.example.comp2100_6442_s2_2020_group_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A init function with the tree,mep,list for java backside by filePath
 *
 * @author: Xiran Yan
 * @uid: 7167582
 */
//step2 get List<Course> set tree map
public class Initialization {
    public RBTreeBarry<String> tree;
    public Map<String,ArrayList<String>> map;
    public ArrayList<String[]> list;
    //major/subject/courseId/Name

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
