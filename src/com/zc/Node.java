package com.zc;

public class Node<T> {
    private T data;
    private Node left;
    private Node right;

    Node(T data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    Node getLeft() {
        return left;
    }

    void setLeft(Node left) {
        this.left = left;
    }

    Node getRight() {
        return right;
    }

    void setRight(Node right) {
        this.right = right;
    }


}
