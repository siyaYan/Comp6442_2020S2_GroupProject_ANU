package com.example.comp2100_6442_s2_2020_group_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A init function with the tree,map,majorlist,userList
 *
 * @author: Xiran Yan
 * @uid: 7167582
 */
//step2 get List<Course> set tree map
public class Initialization {
    public RBTree<String> tree;
    public Map<String, ArrayList<String>> map;
    public ArrayList<String[]> majorList;
    public ArrayList<User> userList;

    public void Initialization(List<Course> courses, ArrayList<String[]> majorList,ArrayList<User> userList) {
        this.tree = initTree(parserToNodes(courses));
        this.map = initMap(courses);
        this.majorList= initList(majorList);
        this.userList=initUser(userList);
    }

    public Initialization() {
    }
    public ArrayList<Node> parserToNodes(List<Course> courses) {
        ArrayList<Node> nodeLists = new ArrayList<>();
        for (Course course : courses) {
            Node node = new Node();
            node.courseID = course.courseDetail.get(1) + course.courseDetail.get(2);
            node.courseName = course.courseDetail.get(4);
            node.classNumber = course.courseDetail.get(0);
            nodeLists.add(node);
        }
        return nodeLists;
    }

    public RBTree<String> initTree(ArrayList<Node> nodes) {
        tree = new RBTree<String>();
        for (Node node : nodes) {
            tree.insertValue(node.courseID.toString(), node.classNumber.toString(), node.courseName.toString());
        }
        //display the tree inorder
        //System.out.println(tree.inOrder(tree.root));
        return tree;
    }

    public ArrayList<String[]> initList(ArrayList<String[]> List) {
        return List;
    }

    public Map<String, ArrayList<String>> initMap(List<Course> courses) {
        map = new HashMap<>();
        for (Course course : courses) {
            map.put(course.classNumber, course.courseDetail);
        }
        return map;
    }

    public ArrayList<User> initUser(ArrayList<User> userList) {
        return userList;
    }
}
