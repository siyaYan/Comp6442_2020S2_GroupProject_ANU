package com.example.comp2100_6442_s2_2020_group_project;

import java.util.ArrayList;
import java.util.List;

public class RBTreeBarry<T extends Comparable<T>> {
    Node<T> root;

    public enum Side {RIGHT, LEFT};
    public RBTreeBarry() {
        root = null;
    }

    public void insertValue(T courseID, T classNumber, T courseName) {
        Node<T> node = new Node<T>(courseID, classNumber, courseName);
        insertNode(node);
    }

    public void insertNode(Node<T> node) {
        if (root == null) {
            root = node;
            root.colour = Colour.BLACK;
        } else {
            //this is to stop duplicate entries.
            insertRecurse(root, node);
            checkViolations(node);
        }
    }

    //insert node by comparing the courseId
    public void insertRecurse(Node<T> root, Node<T> node) {
        int x = root.courseID.compareTo(node.courseID);

        if (x > 0) {
            if (root.left.courseID == null) {
                root.left = node;
                node.parent = root;
            } else {
                insertRecurse(root.left, node); //insert into left tree
            }
        } else if (x < 0) {
            if (root.right.courseID == null) {
                root.right = node;
                node.parent = root;
            } else {
                insertRecurse(root.right, node); // insert into right tree
            }
        }
        //if have the same courseId just insert in the right side
        else {
            //classNUmber must be unique
            if (root.classNumber.compareTo(node.classNumber) != 0) {
                if (root.right.courseID == null) {
                    root.right = node;
                    node.parent = root;
                } else {
                    insertRecurse(root.right, node); // insert into right tree
                }
            }
            //replace the original Node
            else {
                node.right = root.right;
                node.parent = root.parent;
                node.left = root.left;
                root = node;
            }
        }
    }

    //2 kinds of keywords can searchNode(classNumber go to map)
    public Node<T> searchNode(T course, T type) {
        //result keep the node we want
        Node result = null;
        if (root != null && root.courseID != null) {
            if (type.equals("courseName")) {
                Node<T> target = new Node<T>(null, null, course);
                //int x = root.courseName.compareTo(target.courseName);
                //ignore input (tab) space and case
                if (root.courseName.toString().replaceAll("\\s*", "").equalsIgnoreCase(target.courseName.toString().trim())) {
                    result = root;
                } else {
                    //go to the left subtree
                    result = searchRecurseByName(root.left, target);
                    if (result == null || result.courseID == null)
                        //if can't find in the left subtree go to right
                        result = searchRecurseByName(root.right, target);
                }
            } else if (type.equals("courseId")) {
                Node<T> target = new Node<T>(course, null, null);
                //ignore the case
                if (root.courseID.toString().equalsIgnoreCase(target.courseID.toString().trim())) {
                    result = root;
                    //root.courseID.compareTo(target.courseID.toString()) == 0
                } else {
                    //go to the left subtree
                    result = searchRecurseById(root.left, target);
                    if (result == null || result.courseID == null)
                        //if can't find in the left subtree go to right
                        result = searchRecurseById(root.right, target);
                }
            } else
                return null;//input wrong type
            return result;
        }
        //no tree
        return null;
    }

    //search by courseId
    public Node<T> searchRecurseById(Node<T> root, Node<T> node) {
        //subtree exist
        if (root.courseID != null && root != null) {
            //posNode is greater than targetNode(go to left subtree)
            if (root.courseID.compareTo(node.courseID) > 0) {
                if (root.left.courseID != null) {
                    return searchRecurseById(root.left, node); //go to left tree
                } else return null;
            }
            //posNode is lesser than targetNode(go to right subtree)
            //find what we want
            else if (root.courseID.toString().equalsIgnoreCase(node.courseID.toString().trim())) {
                return root;
            }else {
                if (root.right.courseID != null) {
                    return searchRecurseById(root.right, node); // go to right tree
                } else return null;
            }
        } else {
            return null;
        }
    }

    //search by courseName
    public Node<T> searchRecurseByName(Node<T> root, Node<T> node) {
        //subtree exist
        Node<T> now=new Node();
        if (root.courseID != null && root != null) {
            //int x =root.courseName.compareTo(node.courseName);
            String str=root.courseName.toString();
            //boolean equal=str.replaceAll("\\s*", "").equalsIgnoreCase(node.courseName.toString());
            if (str.replaceAll("\\s*", "").equalsIgnoreCase(node.courseName.toString().trim())){
                return root;
            } else {
                if (root.left.courseName != null || root.right.courseName != null) {
                    now = searchRecurseByName(root.left, node); //go to left tree
                    if (now != null)
                        return now;
                    now = searchRecurseByName(root.right, node);
                    if (now != null)
                        return now;
                }
                else {
                    return null;
                }
            }
        }
        return null;
    }

    //searchBycollege
    public List<Node> searchNodes(Node root,String college,List<Node> result) {
        List<Node> results=result;
        //result keep the nodeList we want
        if (root != null&&root.courseID!=null) {
                if (root.courseID.toString().substring(0,4).matches(college)) {
                    results.add(root);
                }
            if(root.right!=null&&root.right.courseID!=null)
                searchNodes(root.right, college,results);
            if(root.left!=null&&root.left.courseID!=null)
                searchNodes(root.left, college,results);
            return results;
        }
        //no tree
        return results;
    }
    //rules:
    // 1) all newly inserted nodes are RED
    // 2) if a node is RED, both children are BLACK (cant have two consecutive reds)
    // 3) every path from node to leaf has the same number of black nodes.

    public void checkViolations(Node<T> node) {
        //TODO choose return value
        if (node.courseID != root.courseID && node.parent.courseID != root.courseID) {
            Node<T> uncle = findUncle(node);
            // current node is red, check if parent is also red
            if (node.parent.colour == Colour.RED) {
                // if parent and uncle are red, recolour.
                if (uncle.colour == Colour.RED) {
                    recolour(node, uncle);
                }
                // otherwise do rotations
                else {
                    rotation(node);
                }
            }
        }
    }

    public void rotation(Node<T> node) {
        //LL = right rotate at grandparent
        //LR = right at parent then left at grandparent
        //RL = left at parent then right at grandparent
        //RR = left rotate at grandparent.
        //LR means: node is left of parent, parent is right of grandparent.

        boolean isNodeLeft = node.courseID == node.parent.left.courseID;
        boolean isParentLeft = node.parent.courseID == node.parent.parent.left.courseID;

        if (isNodeLeft) {
            if (isParentLeft) { // LL case
                rightRotate(node.parent.parent); // right rotate at grandparent.
            } else { // LR case
                rightRotate(node.parent);
                leftRotate(node.parent); //current parent = previous grandparent
                changeColour(node, Side.LEFT); // recolour node and left child.
            }
        } else {
            if (isParentLeft) { // RL case
                leftRotate(node.parent);
                rightRotate(node.parent);
                changeColour(node, Side.RIGHT);

            } else { // RR case
                leftRotate(node.parent.parent);
            }
        }
    }

    public void changeColour(Node<T> node, Side s) {
        if (s == Side.RIGHT){
            node.colour = Colour.BLACK;
            node.right.colour = Colour.RED;
        }else if(s == Side.LEFT){
            node.colour = Colour.BLACK;
            node.left.colour = Colour.RED;
        }
    }

    public void rightRotate(Node<T> node) {
        Node<T> leftChild = node.left;
        boolean isRoot = node.courseID == root.courseID; // check if node is root

        node.left = leftChild.right;
        node.left.parent = node;
        leftChild.right = node;

        if(isRoot){
            root = leftChild;
        }else{
            boolean isLeft = node.parent.left.courseID == node.courseID;//check if node is a left child of its parent.
            if(isLeft){
                node.parent.left = leftChild;
            }else{
                node.parent.right = leftChild;
            }
            leftChild.parent = node.parent;
        }
        node.parent = leftChild;
    }

    public void leftRotate(Node<T> node) {
        Node<T> rightChild = node.right;
        boolean isRoot = node.courseID == root.courseID; // check if node is root
        //boolean isLeft = node.parent.left.courseID.equals(node.courseID);//check if node is a left child of its parent.

        node.right = rightChild.left;
        node.right.parent = node;
        rightChild.left = node;

        if(isRoot){
            root = rightChild;
        }else{
            //check if node is a left child of its parent.
            boolean isLeft = node.parent.left.courseID == node.courseID;//check if node is a left child of its parent.
            if(isLeft){
                node.parent.left = rightChild;
                rightChild.parent = node.parent;
            }else{
                node.parent.right = rightChild;
                rightChild.parent = node.parent;
            }
        }
        node.parent = rightChild;
    }

    public Node<T> findUncle(Node<T> node) {
        boolean isParentLeft = node.parent == node.parent.parent.left;
        if (isParentLeft) {
            return node.parent.parent.right;
        } else {
            return node.parent.parent.left;
        }
    }

    public void recolour(Node<T> node, Node<T> uncle) {
        node.parent.colour = Colour.BLACK; // recolour parent to black
        uncle.colour = Colour.BLACK;// recolour uncle to black
        if (node.parent.parent.courseID != root.courseID) { // if grandparent is not root, change to red
            node.parent.parent.colour = Colour.RED;
            checkViolations(node.parent); // check if there is red-red conflict between parent and grandparent
        }
    }

    //display the tree by order of the CourseId
    public String inOrder(Node<T> tree) {
        //because have color,so when node don't have information tree may still not null,so judge the node by id
        String str="";
        String leftStr="";
        String rightStr="";
        if (tree != null && tree.courseID != null) {
                if (tree.left.courseID != null) {
                    leftStr = inOrder(tree.left);
                }
                if (tree.right.courseID != null) {
                    rightStr = inOrder(tree.right);
                }
                if (tree.right.courseID == null && tree.left.courseID == null) {
                     str= "courseID:["+tree.courseID + "] classNumber:["+ tree.classNumber + "] courseName:["+tree.courseName+ "],\n" ;
                     return str;

                }
            str= (leftStr.isEmpty() ? leftStr : "" + leftStr)+ "courseID:["+tree.courseID + "] classNumber:["+ tree.classNumber + "] courseName:["+tree.courseName+ "],\n"  +
                     (rightStr.isEmpty() ? rightStr : "" + rightStr);
        }
        return str;
    }
    //display preOrder
    public String preOrder(Node<T> tree) {
        String str="";
        String leftStr="";
        String rightStr="";
        if (tree != null && tree.courseID != null) {
            if (tree.left.courseID != null) {
                leftStr = inOrder(tree.left);
            }
            if (tree.right.courseID != null) {
                rightStr = inOrder(tree.right);
            }
            if (tree.right.courseID == null && tree.left.courseID == null) {
                str= "courseID:["+tree.courseID + "] classNumber:["+ tree.classNumber + "] courseName:["+tree.courseName+ "],\n" ;
                return str;

            }
            str=  "courseID:["+tree.courseID + "] classNumber:["+ tree.classNumber + "] courseName:["+tree.courseName+ "],\n"  +
                    (leftStr.isEmpty() ? leftStr : "" + leftStr) + (rightStr.isEmpty() ? rightStr : "" + rightStr);
        }
        return str;
    }

}
