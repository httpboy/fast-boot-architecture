package com.fast.structure.binarytree;

/**
 * 二叉树
 */
public class BinaryTree {
    TreeNode root;

    /**
     * 添加元素
     *
     * @param data
     */
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

    /**
     * 递归先序遍历
     */

    public void recursionPreOrder(TreeNode currentNode) {
        if (currentNode != null) {
            System.out.print(currentNode.data+",");
            recursionPreOrder(currentNode.left);
            recursionPreOrder(currentNode.right);
        }
    }
    /**
     * 递归中序遍历
     */
    public void recursionMiddleOrder(TreeNode currentNode) {
        if (currentNode != null) {
            recursionPreOrder(currentNode.left);
            System.out.print(currentNode.data+",");
            recursionPreOrder(currentNode.right);
        }
    }
    /**
     * 递归后序遍历
     */
    public void recursionPostOrder(TreeNode currentNode) {
        if (currentNode != null) {
            recursionPreOrder(currentNode.left);
            recursionPreOrder(currentNode.right);
            System.out.print(currentNode.data+",");
        }
    }
}
