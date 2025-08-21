package ru.academits.dik.tree;

class TreeNode<E> {
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;

    private E value;

    public TreeNode(E value) {
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

    public void setValue(E value) {
        this.value = value;
    }
}