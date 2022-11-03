


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String args[]){
        AVLTree tree = new AVLTree();

        Scanner sc = new Scanner(System.in);

        int queries = sc.nextInt();


        for(int q = 0; q < queries; q++){

            int t = sc.nextInt();
            switch(t){
                case 1:q1(sc.next(),sc.nextInt(),tree);break;
                case 2:q2(sc.next(),sc.next(),sc.nextInt(),tree);break;
                case 3:q3(sc.next(),tree);
            }
        }}

    public static void q1(String name,int value,AVLTree tree){tree.insert(name, value); }
    public static void q2(String a,String b, int k,AVLTree tree){
        String min,max;
        if(a.compareTo(b)<=0) {min=a;max=b;}else {min=b;max=a;}
        tree.rangeUpdate(tree.root, min, max, k);}
    public static void q3(String name,AVLTree tree){System.out.println(tree.doFind(tree.root,name));}





}

class AVLNode{
    public String key;
    public String max,min;

    public int carry;
    public int value;
    public AVLNode left;
    public AVLNode right;
    public int height;
    AVLNode(String key, int value){
        this.key=key;
        this.value=value;
        this.height=1;
        this.min=this.key;
        this.max=this.key;
        this.carry=0;

    }



}

class AVLTree{
    public AVLNode root;





    //range update
    public void rangeUpdate(AVLNode v, String min, String max ,int k){

        if((min.compareTo(v.min)<=0 && v.max.compareTo(max)<=0)){
            v.carry+=k;
        }
        else if((max.compareTo(v.min)<0 || v.max.compareTo(min)<0)){
            return;
        }
        else {

            if((min.compareTo(v.key)<=0&&v.key.compareTo(max)<=0)) {
                v.value+=k;
            }



            if(v.left!=null) {rangeUpdate(v.left,min,max,k);}
            if(v.right!=null){rangeUpdate(v.right,min,max,k);}
        }



    }
    /**
     * push down
     * @param v
     */
    public static void pushDown(AVLNode v) {
        if(v!=null) {

            v.value+=v.carry;
            if(v.left!=null) {v.left.carry+=v.carry;}
            if(v.right!=null) {v.right.carry+=v.carry;}
            v.carry=0;
        }
    }

    public int getHeight(AVLNode node){
        if(node == null)
            return 0;
        return node.height;
    }
    //update min and max
    public void updateInfo(AVLNode node){
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        if(node.left!=null) {
            node.min=node.left.min;}
        if(node.right!=null) {
            node.max=node.right.max;
        }
    }

    public AVLNode rightRotate(AVLNode node) {
        AVLNode nodel = node.left;
        AVLNode nodelr = nodel.right;
        nodel.right = node;
        node.left = nodelr;
        updateInfo(node);
        updateInfo(nodel);


        return nodel;
    }


    public AVLNode leftRotate(AVLNode node) {

        AVLNode noder = node.right;
        AVLNode noderl = noder.left;
        noder.left = node;
        node.right = noderl;
        updateInfo(node);
        updateInfo(noder);

        return noder;
    }

    public int getBalance(AVLNode node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public AVLNode doInsert(AVLNode node, String key, int value) {
        if(node!=null) {pushDown(node);}

        if (node == null)
            return new AVLNode(key, value);
        if (key.compareTo(node.key) < 0)
            node.left = doInsert(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = doInsert(node.right, key, value);
        else {
            node.value = value;
            return node;
        }

        updateInfo(node);
        int balance = getBalance(node);
        if (balance > 1 && key.compareTo(node.left.key) < 0)
            return rightRotate(node);
        if (balance < -1 && key.compareTo(node.right.key) > 0)
            return leftRotate(node);
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }


        return node;
    }

    public void insert(String key, int value) {
        root = doInsert(root, key, value);
    }



    public int doFind(AVLNode node, String key) {
        pushDown(node);
        if (node == null)
            return -1;
        if (key.equals(node.key))
            return node.value;

        else if (key.compareTo(node.key) < 0)
            return doFind(node.left, key);
        return doFind(node.right, key);
    }

    public AVLTree(){
        root = null;
    }
}