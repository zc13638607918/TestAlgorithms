package com.zc;

public class BinarySearchTree {
    Node<Integer> root;

    BinarySearchTree(Node<Integer> root) {//二叉搜索树
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

        System.out.println("删除根节点\"5\"：");
        bst.delete(5);
        Ergodic ergodic1 = new Ergodic(bst.root);
        ergodic1.order("中序遍历");

        System.out.println("删除叶子节点\"7\":");
        bst.delete(7);
        ergodic1.order("中序遍历");

        System.out.println("删除无右子树的节点\"8\":");
        bst.delete(8);
        ergodic1.order("中序遍历");

        System.out.println("删除无左子树的节点\"6\":");
        bst.delete(6);
        Ergodic ergodic2 = new Ergodic(bst.root);
        ergodic2.order("中序遍历");

        System.out.println("删除有左右子树的节点\"2\":");
        bst.delete(2);
        ergodic2.order("中序遍历");

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

        if (successor == null) {//
            if (node == this.root) {
                this.root = node.getLeft();
                node.setLeft(null);
                return;
            }
            if (node.getLeft() == null) {
                if (isLeft(node)) {
                    fatherOfNode.setLeft(null);
                } else fatherOfNode.setRight(null);
            } else {
                fatherOfNode.setRight(node.getLeft());
            }
            return;
        }
        fatherOfSuccessor = getFather(successor);
        //处理后继节点遗留关系
        if (successor.getRight() == null) {
            if (isLeft(successor)) {
                fatherOfSuccessor.setLeft(null);
            } else fatherOfSuccessor.setRight(null);
        } else {
            if (isLeft(successor)) {
                fatherOfSuccessor.setLeft(successor.getRight());
            } else fatherOfSuccessor.setRight(successor.getRight());
        }

        //后继节点继承被删节点

        if (fatherOfNode == null) {//被删节点为根节点
            successor.setLeft(node.getLeft());
            successor.setRight(node.getRight());
            node.setLeft(null);
            node.setRight(null);
            this.root = successor;
        } else {
            successor.setLeft(node.getLeft());
            successor.setRight(node.getRight());
            if (isLeft(node)) {
                fatherOfNode.setLeft(successor);
            } else fatherOfNode.setRight(successor);
            node.setLeft(null);
            node.setRight(null);
        }

    }

    private Node findSuccessor(Node node) {
        return findSuccessor(node, node);
    }

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

    private Node getFather(Node node) {
        return getFather(this.root, node);
    }

    /**
     * 取node的父节点
     * @param p 辅助节点，用于递归
     * @param node 需取父亲的节点
     * @return
     */
    private Node getFather(Node p, Node node) {
        Node node1 = search((int) node.getData());
        if (node1 == null) {
            //System.out.println(node.getData()+"：该节点不在树中，无父节点！");
            return null;
        } else if (node == this.root) {
            //System.out.println(node.getData()+"：该节点为根节点，无父节点！");
            return null;
        } else if ((int) node.getData() < (int) p.getData()) {
            if (node == p.getLeft()) {
                return p;
            } else {
                return getFather(p.getLeft(), node);
            }
        } else {
            if (node == p.getRight()) {
                return p;
            } else {
                return getFather(p.getRight(), node);
            }
        }
    }

    /**
     * 判断该节点是否为左节点
     * @param node 待判断的节点
     * @return
     */
    private boolean isLeft(Node node) {
        if (node == getFather(node).getLeft()) {
            return true;
        } else
            return false;
    }

}
