package ru.academits.dik.tree;

import java.util.ArrayList;

class TreeNode<E> {
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;

    private E value;

    public TreeNode(TreeNode<E> leftNode, TreeNode<E> rightNode, E value) {
        this.leftChild = leftNode;
        this.rightChild = rightNode;
        this.value = value;
    }

     public TreeNode() {
     }

     public TreeNode(E value){
        this.value = value;
    }

    public TreeNode<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }

    public E getValue() {
        return value;
    }

    public ArrayList<TreeNode<E>> getChildren(){
        ArrayList<TreeNode<E>> children = new ArrayList<>();
        children.add(getLeftChild());
        children.add(getRightChild());

        return children;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
