package com.zc;

public class BinaryTree4 {

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



        Ergodic ergodic = new Ergodic(node1);

        ergodic.order("前序遍历");
        ergodic.order("中序遍历");
        ergodic.order("后序遍历");
    }

}

/**
 * 定义一个NewNode类型，在Node基础上加上父节点指针与3个标志位（前，中，后）
 */
class NewNode {
    Node node;
    NewNode parent;
    NewNode left;
    NewNode right;
    Flag f1 = new Flag(1);
    Flag f2 = new Flag(2);
    Flag f3 = new Flag(3);

    public NewNode(Node node, NewNode parent) {
        this.node = node;
        this.parent = parent;
    }
}


class Flag{
    int value;
    public Flag(int value) {
        this.value = value;
    }
}

