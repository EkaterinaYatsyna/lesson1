package com.yatsynaekateryna.lessons.lesson9;

import java.sql.Array;
import java.util.Arrays;

public class main {
    public static void main(String[] args) {

        Deque queue = new Deque();

        //addLast
        int[] arr1 = new int[]{1, 2, 3, 4, 5, 8, 9};
        for (int i = 0; i < arr1.length; i++) {
            queue.addLast(arr1[i]);
        }
        System.out.println("addLast " + Arrays.toString(arr1));
        printDeque(queue);

        //addFirst
        queue.addFirst(13);
        System.out.println("addFirst 13");
        printDeque(queue);

        //getFirst
        System.out.println("getFirst");
        System.out.println(queue.getFirst() + "\n");

        //removeFirst
        System.out.println("removeFirst");
        queue.removeFirst();
        printDeque(queue);

        //getLast
        System.out.println("getLast");
        System.out.println(queue.getLast() + "\n");

        //removeLast
        System.out.println("removeLast");
        queue.removeLast();
        printDeque(queue);

        //get
        System.out.println("get(2)");
        System.out.println(queue.get(2) + "\n");

        //set
        System.out.println("set(2,18)");
        queue.set(2, 18);
        printDeque(queue);

        //insert
        System.out.println("insert(2,24)");
        queue.insert(2, 24);
        printDeque(queue);

        //remove
        System.out.println("remove(5)");
        queue.remove(5);
        printDeque(queue);


    }

    private static void printDeque(Deque queue) {
        for (int i = 0; i < queue.length; i++) {
            System.out.print(queue.get(i) + " ");
        }
        System.out.println("\n");
    }

}
