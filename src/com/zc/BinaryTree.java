package com.zc;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class BinaryTree {

    public static void main(String[] args) {
        Node<String> node9 = new Node<>("J", null, null);
        Node<String> node8 = new Node<>("H", null, null);
        Node<String> node7 = new Node<>("G", null, null);
        Node<String> node6 = new Node<>("F", null, null);
        Node<String> node5 = new Node<>("E", null, null);
        Node<String> node4 = new Node<>("D", node8, node9);
        Node<String> node3 = new Node<>("C", node6, node7);
        Node<String> node2 = new Node<>("B", node4, node5);
        Node<String> node1 = new Node<>("A", node2, node3);

        System.out.println("层次遍历");
        levelOrder(node1);
        System.out.println("\n非递归前序遍历");
        preOrder(node1);
        System.out.println("\n非递归中序遍历");
        middleOrder(node1);
        System.out.println("\n非递归后序遍历");
        postOrder(node1);
    }

    public static void postOrder(Node root) {
        Vector<String> res = new Vector<>();
        Stack<Node> stack = new Stack<>();
        Node p = root;
        while (p != null | !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                res.add((String) p.getData());
                p = p.getRight();
            }
            p = stack.peek();
            stack.pop();
            p = p.getLeft();
        }
        for (int i = res.size()-1; i>=0; i--) {
            System.out.print(res.get(i));
        }
    }

    public static void middleOrder(Node root) {
        Vector<String> res = new Vector<>();
        Stack<Node> stack = new Stack<>();
        Node p = root;
        while (p != null | !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.getLeft();
            }
            p = stack.peek();
            stack.pop();
            res.add((String) p.getData());
            p = p.getRight();
        }
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i));
        }
    }

    public static void preOrder(Node root) {
        Vector<String> res = new Vector<>();
        Stack<Node> stack = new Stack<>();
        Node p = root;
        while (p != null | !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                res.add((String) p.getData());
                p = p.getLeft();
            }
            p = stack.peek();
            stack.pop();
            p = p.getRight();
        }
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i));
        }
    }

    public static void preOrder1(Node root) {
        Stack<Node> stack = new Stack<>();
        Node pop;//栈顶取出的元素；
        stack.push(root);
        while (!stack.isEmpty()) {
            //出栈
            pop = stack.pop();
            System.out.print(pop.getData());
            //入栈
            if (pop.getRight() != null)
                stack.push(pop.getRight());
            if (pop.getLeft() != null) {
                stack.push(pop.getLeft());
            }
        }
    }

    public static void levelOrder(Node root) {
        Deque<Node> deque = new LinkedList<>();
        deque.add(root);
        Node n;
        while (!deque.isEmpty()) {
            n = deque.poll();
            System.out.print(n.getData());
            if (n.getLeft() != null)
                deque.offer(n.getLeft());
            if (n.getRight() != null)
                deque.offer(n.getRight());
        }
    }
}

