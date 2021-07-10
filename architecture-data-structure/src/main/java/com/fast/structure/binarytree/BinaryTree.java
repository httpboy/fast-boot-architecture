package com.fast.structure.binarytree;

/**
 * 二叉搜索树
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
     * 删除元素
     *
     * @param data
     */
    public void remove(int data) {
        //找到删除的节点
        TreeNode parentNode = root;
        TreeNode current = root;
        boolean isLeftChild = true;
        while (current.data != data) {
            parentNode = current;
            if (data < current.data) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
            if (current == null) {
                System.out.println("该元素不存在");
                return;
            }
        }
        if (current.left == null && current.right == null) {
            //如果是叶子节点
            if (current == parentNode) {
                root = null;
            } else if (isLeftChild) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
        } else if (current.right == null) {
            //如果不是叶子节点-存在左孩子
            if (isLeftChild) {
                parentNode.left = current.left;
            } else {
                parentNode.right = current.left;
            }

        } else if (current.left == null) {
            //如果不是叶子节点-存在右孩子的情况
            if (isLeftChild) {
                parentNode.left = current.right;
            } else {
                parentNode.right = current.right;
            }
        } else {
            //如果不是叶子节点-同时存在左右孩子
            //找到中序后继节点
            TreeNode middleNext = getMiddleNext(current);
            if (isLeftChild) {
                //如果当前节点是左孩子，将父亲节点左指针指向中序后继节点
                parentNode.left = middleNext;
            } else {
                //如果当前节点是右孩子，将父亲节点右指针指向中序后继节点
                parentNode.right = middleNext;
            }
            //如果当前节点存在左孩子，则将中序后继节点的左指针指向当前节点的左孩子
            if (current.left != null) {
                middleNext.left = current.left;
            }
        }

    }

    private TreeNode getMiddleNext(TreeNode current) {
        //中序后继节点父亲节点
        TreeNode parentNode = current;
        //中序后继节点
        TreeNode middleNext = current.right;
        while (middleNext.left != null) {
            //记录父亲节点
            parentNode = middleNext;
            //记录中序后继节点
            middleNext = middleNext.left;
        }
        //如果存在中序后继节点，则将父亲节点指向中序后继节点的右孩子且将中序后继节点右孩子指向当前节点的右孩子
        if (middleNext != current.right) {
            parentNode.left = middleNext.right;
            middleNext.right = current.right;
        }

        return middleNext;
    }

    /**
     * 递归先序遍历
     */

    public void recursionPreOrder(TreeNode currentNode) {
        if (currentNode != null) {
            System.out.print(currentNode.data + ",");
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
            System.out.print(currentNode.data + ",");
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
            System.out.print(currentNode.data + ",");
        }
    }
}
