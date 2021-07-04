package com.fast.structure.binarytree;

public class Main {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.put(5);
        binaryTree.put(3);
        binaryTree.put(1);
        binaryTree.put(4);
        binaryTree.put(6);


        System.out.println("先序遍历");
        binaryTree.recursionPreOrder(binaryTree.root);
        System.out.println("\n中序遍历");
        binaryTree.recursionMiddleOrder(binaryTree.root);
        System.out.println("\n后序遍历");
        binaryTree.recursionPostOrder(binaryTree.root);

    }
}
