package com.zc;

public class BinarySearchTree {
    Node<Integer> root;

    BinarySearchTree(Node<Integer> root) {
        this.root = root;
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree(new Node<Integer>(5));
        bst.insert(4);
        bst.insert(2);
        bst.insert(6);
        bst.insert(1);
        bst.insert(8);
        bst.insert(3);
        bst.insert(7);

        Ergodic ergodic = new Ergodic(bst.root);
        System.out.println("初始二叉树：");
        ergodic.order("中序遍历");

        System.out.println("删除节点\"5\"：");
        bst.delete(5);
        Ergodic ergodic1 = new Ergodic(bst.root);
        ergodic1.order("中序遍历");

        System.out.println("删除节点\"4\":");
        bst.delete(4);
        ergodic1.order("中序遍历");

        System.out.println("删除节点\"7\":");
        bst.delete(7);
        ergodic1.order("中序遍历");

        System.out.println("删除节点\"8\":");
        bst.delete(8);
        ergodic1.order("中序遍历");

        System.out.println("删除节点\"6\":");
        bst.delete(6);
        Ergodic ergodic2 = new Ergodic(bst.root);
        ergodic2.order("中序遍历");

        System.out.println("删除节点\"2\":");
        bst.delete(2);
        Ergodic ergodic3 = new Ergodic(bst.root);
        ergodic3.order("中序遍历");

        System.out.println("删除节点\"3\":");
        bst.delete(3);
        Ergodic ergodic4 = new Ergodic(bst.root);
        ergodic4.order("中序遍历");

    }

    private void insert(int data) {
        this.insert(this.root, data);
    }

    private void insert(Node<Integer> node, int data) {
        Node node1 = search(data);
        if (node1 != null) {
            System.out.println(data + ":数据已存在！");
        } else if (data < node.getData()) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(data));
            } else {
                insert(node.getLeft(), data);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new Node<>(data));
            } else {
                insert(node.getRight(), data);
            }
        }
    }

    private Node search(int data) {
        return this.search(this.root, data);
    }

    private Node search(Node<Integer> node, int data) {
        if (data == node.getData()) {
            return node;
        } else if (data < node.getData()) {
            if (node.getLeft() != null) {
                return search(node.getLeft(), data);
            } else {
                return null;
            }
        } else {
            if (node.getRight() != null) {
                return search(node.getRight(), data);
            } else {
                return null;
            }
        }

    }

    private void delete(int data) {
        Node node = search(data);//找到数据对应的节点
        Node fatherOfNode = getFather(node);//找到该节点的父亲
        Node successor = findSuccessor(node);//找到后继节点
        Node fatherOfSuccessor;//后继节点的父亲

        //如被删节点无后继，说明该节点无右子树
        if (successor == null) {
            if (node == this.root) {//且被删节点为根时（无父节点）
                this.root = node.getLeft();
                node.setLeft(null);
                return;
            }
            if (isLeft(node)) {//无后继且被删节点不为根时需连接爷爷与孙子
                fatherOfNode.setLeft(node.getLeft());
            } else fatherOfNode.setRight(node.getLeft());
            return;
        }

        //如被删节点有后继，处理后继节点遗留关系
        fatherOfSuccessor = getFather(successor);
        if (isLeft(successor)) {
            fatherOfSuccessor.setLeft(successor.getRight());
        } else fatherOfSuccessor.setRight(successor.getRight());

        //后继节点继承被删节点关系
        successor.setLeft(node.getLeft());
        successor.setRight(node.getRight());
        if (fatherOfNode == null) {//被删节点为根节点
            this.root = successor;
        } else {
            if (isLeft(node)) {
                fatherOfNode.setLeft(successor);
            } else fatherOfNode.setRight(successor);
        }

        //销毁被删节点
        node.setLeft(null);
        node.setRight(null);
    }

    /**
     * 找到node的后继节点并返回
     *
     * @param p    辅助节点，用于递归
     * @param node 待寻找后继的节点
     * @return
     */
    private Node findSuccessor(Node p, Node node) {
        if (node.getRight() == null)
            return null;
        if (p.getRight() == null) {
            return p;
        } else if (p.getRight().getLeft() == null) {
            return p.getRight();
        } else {
            return findSuccessor(p.getRight().getLeft(), node);
        }
    }

    private Node findSuccessor(Node node) {
        return findSuccessor(node, node);
    }

    /**
     * 取node的父节点
     *
     * @param p    辅助节点，用于递归
     * @param node 需取父亲的节点
     * @return
     */
    private Node getFather(Node p, Node node) {
        Node node1 = search((int) node.getData());
        if (node1 == null) {//该节点不在树中，无父节点！
            return null;
        } else if (node == this.root) {//该节点为根节点，无父节点！
            return null;
        } else if (node == p.getLeft() | node == p.getRight()) {//找到父节点
            return p;
        } else if ((int) node.getData() < (int) p.getData()) {//递归
            return getFather(p.getLeft(), node);
        } else {
            return getFather(p.getRight(), node);
        }
    }

    private Node getFather(Node node) {
        return getFather(this.root, node);
    }

    /**
     * 判断该节点是否为左节点
     *
     * @param node 待判断的节点
     * @return true为左，false为右
     */
    private boolean isLeft(Node node) {
        if (node == getFather(node).getLeft()) {
            return true;
        } else
            return false;
    }

}
