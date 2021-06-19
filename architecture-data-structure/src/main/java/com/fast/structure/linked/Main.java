package com.fast.structure.linked;


public class Main {
    public static void main(String[] args) throws Exception {
        //单向链表
        System.out.println("展示单链表..............................");
        Linked<Integer> linked = new Linked<>();
        linked.add(1);//新增元素
        linked.add(2);//新增元素
        linked.add(4);//新增元素
        linked.addFirst(0);//新增首元素
        linked.addFirst(-1);//新增首元素
        linked.add(3, 5);//新增任意位置元素
        linked.printLinked();
        System.out.println();
        Integer first = linked.removeFirst();//删除首元素
        Integer end = linked.removeEnd();//删除尾元素
        linked.printLinked();
        System.out.println();
        System.out.println("当前元素个数为"+linked.size());
        System.out.println("删除的首元素为：" + first);
        System.out.println("删除的尾元素为：" + end);
        //循环单链表
        System.out.println("展示循环单链表..............................");
        CircularLinked<Integer> circularLinked = new CircularLinked<>();
        circularLinked.addFirst(2);
        circularLinked.addFirst(1);
        circularLinked.addEnd(3);
        circularLinked.printLinked();
    }
}
