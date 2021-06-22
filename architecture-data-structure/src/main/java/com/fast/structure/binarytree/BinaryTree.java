package com.fast.structure.binarytree;

/**
 * 二叉树
 */
public class BinaryTree {
    TreeNode root;

    public void put(int data) {
        if (root == null) {
            root = new TreeNode(data);
            return;
        }
        TreeNode currentNode = root;

        while (true) {
            if (data == currentNode.data) {
                System.out.println("数据已存在");
                return;
            } else if (data < currentNode.data) {
                if (currentNode.left == null) {
                    currentNode.left = new TreeNode(data);
                    return;
                } else {
                    currentNode = currentNode.left;
                }
            } else {
                if (currentNode.right == null) {
                    currentNode.right = new TreeNode(data);
                    return;
                } else {
                    currentNode = currentNode.right;
                }
            }
        }
    }
}
