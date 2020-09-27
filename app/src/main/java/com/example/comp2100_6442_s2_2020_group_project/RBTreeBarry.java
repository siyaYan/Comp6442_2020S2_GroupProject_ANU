package com.example.comp2100_6442_s2_2020_group_project;

public class RBTreeBarry<T extends Comparable<T>> {
    Node<T> root;

    public RBTreeBarry() {
        root = null;
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

    public void checkViolations(Node<T> node) {
        //TODO choose return value
        if (node.courseID == root.courseID) {
            return;
        } else {

            Node<T> uncle = findUncle(node);
            // current node is red, check if parent is also red
            if (node.parent.colour == Colour.RED) {
                // if parent and uncle are red, recolour.
                if(uncle.colour == Colour.RED) {
                    recolour(node, uncle);
                }
                // otherwise do rotations
                else{
                    rotation(node);
                }
            }
        }
    }

    private void rotation(Node<T> node) {
        //LL = right rotate at grandparent
        //LR = right at parent then left at grandparent
        //RL = left at parent then right at grandparent
        //RR = left rotate at grandparent.
        //LR means: node is left of parent, parent is right of grandparent.

        boolean isNodeLeft = node.courseID == node.parent.left.courseID;
        boolean isParentLeft = node.parent.courseID == node.parent.parent.left.courseID;

        if(isNodeLeft){
            if(isParentLeft){ // LL case
                rightRotate(node);
            }else{ // LR case
                rightLeftRotate(node);
            }
        }else {
            if(isParentLeft){ // RL case
                leftRightRotate(node);
            }else{ // RR case
                leftRotate(node);
            }
        }
        //TODO change argument node for rotate function calls
    }

    //TODO implement LL, LR, RL, RR rotations
    private void rightRotate(Node<T> node) {
    }
    private void rightLeftRotate(Node<T> node) {
    }
    private void leftRightRotate(Node<T> node) {
    }
    private void leftRotate(Node<T> node) {
    }







    public Node<T> findUncle(Node<T> node) {
        boolean isParentLeft = node.parent == node.parent.parent.left;
        if (isParentLeft) {
            return node.parent.parent.right;
        } else {
            return node.parent.parent.left;
        }
    }

    private void recolour(Node<T> node, Node<T> uncle) {
        node.parent.colour = Colour.BLACK; // recolour parent to black
        uncle.colour = Colour.BLACK;// recolour uncle to black
        if (node.parent.parent.courseID != root.courseID) { // if grandparent is not root, change to red
            node.parent.parent.colour = Colour.RED;
            checkViolations(node.parent); // check if there is red-red conflict between parent and grandparent
        }
    }



}
