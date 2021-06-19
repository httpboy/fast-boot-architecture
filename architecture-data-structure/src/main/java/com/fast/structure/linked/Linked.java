package com.fast.structure.linked;

/**
 * 单向链表
 */
public class Linked<T> {
    private Node<T> head = null; //头节点
    private int size = 0; //链表元素个数

    public Linked() {
        this.head = new Node<>(null);
        this.size = 0;
    }

    /**
     * 链表头部新增元素
     */

    public void addFirst(T data) {
        Node<T> node = new Node<>(data);
        node.next = this.head.next;
        this.head.next = node;
        this.size++;
    }

    /**
     * 链表尾部添加元素
     */

    public void add(T data) {
        Node<T> currentNode = this.head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = new Node<>(data);
        this.size++;
    }


    /**
     * 链表中间任意位置元素添加元素
     */

    public void add(T data, int index) throws Exception {
        if (this.size + 1 < index) {
            throw new Exception("链表越界异常");
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        Node<T> node = new Node<>(data);
        Node<T> preNode = this.head;
        for (int i = 0; i < index - 1; i++) {
            preNode = preNode.next;
        }

        node.next = preNode.next;
        preNode.next = node;
        this.size++;
    }

    /**
     * 链表中首元素的删除
     */

    public T removeFirst() {
        if (this.size < 1) {
            System.out.println("无数据可删除");
            return this.head.data;//头结点数据区为null
        }
        T firstNodeData = this.head.next.data;
        this.head.next = this.head.next.next;
        this.size--;

        return firstNodeData;
    }

    /**
     * 链表中尾元素的删除
     */

    public T removeEnd() {
        if (this.size < 1) {
            System.out.println("无数据可删除");
            return this.head.data;
        }

        Node<T> curNode = this.head;
        Node<T> preNode = this.head;
        while (curNode.next != null) {
            preNode = curNode;
            curNode = curNode.next;
        }

        preNode.next = null;
        this.size--;

        return curNode.data;

    }

    public int size(){
        return this.size;
    }

    public void printLinked() {
        Node<T> currentNode = this.head.next;
        while (currentNode != null) {
            System.out.print(currentNode.data + "，");
            currentNode = currentNode.next;
        }
    }
}
