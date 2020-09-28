package com.example.comp2100_6442_s2_2020_group_project;

public class RBTreeBarry<T extends Comparable<T>> {
    Node<T> root;
    public enum Side {RIGHT, LEFT};

    public RBTreeBarry() {
        root = null;
    }

    public void insertValue(T courseID, T classNumber){
        Node<T> node = new Node<T>(courseID,classNumber);
        insertNode(node);
    }

    public void insertNode(Node<T> node) {
        if (root == null) {
            root = node;
        } else {
            //TODO implement search function
            //this is to stop duplicate entries.
            insertRecurse(root, node);
        }
        checkViolations(node);
    }

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
    }

    //rules:
    // 1) all newly inserted nodes are RED
    // 2) if a node is RED, both children are BLACK (cant have two consecutive reds)
    // 3) every path from node to leaf has the same number of black nodes.

    public void checkViolations(Node<T> node) {
        //TODO choose return value
        if (node.courseID == root.courseID) {
            return;
        } else {
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
        boolean isLeft = node.parent.left.courseID == node.courseID; //check if node is a left child of its parent.

        node.left = leftChild.right;
        node.left.parent = node;
        leftChild.right = node;

        if(isRoot){
            root = leftChild;
        }else{
            if(isLeft){
                node.parent.left = leftChild;
                leftChild.parent = node.parent;
            }else{
                node.parent.right = leftChild;
                leftChild.parent = node.parent;
            }
        }
        node.parent = leftChild;
    }

    public void leftRotate(Node<T> node) {
        Node<T> rightChild = node.right;
        boolean isRoot = node.courseID == root.courseID; // check if node is root
        boolean isLeft = node.parent.left.courseID == node.courseID; //check if node is a left child of its parent.

        node.right = rightChild.left;
        node.right.parent = node;
        rightChild.left = node;

        if(isRoot){
            root = rightChild;
        }else{
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


}
