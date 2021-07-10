package com.fast.structure.binarytree;

/**
 * 二叉搜索树
 */
public class Main {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.put(50);
        binaryTree.put(32);
        binaryTree.put(20);
        binaryTree.put(35);
        binaryTree.put(33);
        binaryTree.put(34);
        binaryTree.put(38);
        binaryTree.put(80);
        binaryTree.put(90);


        System.out.println("先序遍历");
        binaryTree.recursionPreOrder(binaryTree.root);
        System.out.println("\n中序遍历");
        binaryTree.recursionMiddleOrder(binaryTree.root);
        System.out.println("\n后序遍历");
        binaryTree.recursionPostOrder(binaryTree.root);

        System.out.println("\n对二叉搜索树元素进行删除");
//        binaryTree.remove(20);
        binaryTree.remove(32);
//        binaryTree.remove(80);
        System.out.println("先序遍历");
        binaryTree.recursionPreOrder(binaryTree.root);
        System.out.println("\n判断二叉搜索树是否存在某个元素");
        System.out.println(binaryTree.exist(80));
        ;

    }
}
