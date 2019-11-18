package com.zc;

/**
 * 遍历类
 */
public class Ergodic {
    private NewNode ROOT;//扩展节点，接收需要遍历的二叉树的根
    private NewNode tNN;//tmpNewNode,flag所在的当前新节点
    private Flag f;//当前flag状态

    public Ergodic(Node root) {
        this.ROOT = new NewNode(root, null);
        this.tNN = ROOT;
        this.f = tNN.f1;
    }

    /**
     * 前中后三种遍历的标志位的遍历路径是一样的
     * 前序遍历：遍历到节点的前标志位时输出
     * 中序遍历：遍历到节点的中标志位时输出
     * 后序遍历：遍历到节点的后标志位时输出
     * @param mode
     */
    public void order(String mode) {
        //System.out.println("\n非递归"+mode);
        while (f != ROOT.f3) {//只要没有到达根的后标志位，就继续遍历标志位直到走完
            while (f.value == 1) {
                if (mode=="前序遍历")
                    System.out.print(tNN.node.getData());
                f1();
            }
            while (f.value == 2) {
                if (mode=="中序遍历")
                    System.out.print(tNN.node.getData());
                f2();
            }
            while (f.value == 3) {
                if (mode=="后序遍历")
                    System.out.print(tNN.node.getData());
                if (tNN.node == ROOT.node) {//回到根节点时复原f并结束遍历
                    f = ROOT.f1;
        System.out.println();
                    return;
                }
                f3();
            }
        }
    }

    /**
     * 遍历至前标志位时的动作，只会到左儿子前标志位（有左儿子时）或自己的中标志位（无左儿子时）
     */
    public void f1() {
        if (tNN.node.getLeft() != null) {
            tNN.left = new NewNode(tNN.node.getLeft(), tNN);
            tNN = tNN.left;
            f = tNN.f1;
        } else {
            f = tNN.f2;
        }
    }
    /**
     * 遍历至中标志位时的动作，只会到右儿子前标志位（有右儿子时）或自己的后标志位（无右儿子时）
     */
    public void f2() {
        if (tNN.node.getRight() != null) {
            tNN.right = new NewNode(tNN.node.getRight(), tNN);
            tNN = tNN.right;
            f = tNN.f1;
        } else {
            f = tNN.f3;
        }
    }
    /**
     * 遍历至后标志位时的动作，只会到父亲中标志位（自身为左儿子时）或父亲后标志位（自身为右儿子时）
     */
    public void f3() {
        if (tNN == tNN.parent.left) {
            tNN = tNN.parent;
            f = tNN.f2;
        } else if (tNN == tNN.parent.right) {
            tNN = tNN.parent;
            f = tNN.f3;
        }
    }
}
