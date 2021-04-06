package com.yatsynaekateryna.lessons.lesson9;

public class main {
    public static void main(String[] args) {

        IDeque queue = new Deque();
        queue.addFirst(5);
        queue.addFirst(6);
        queue.addLast(7);
        queue.removeFirst();
        queue.removeLast();
        System.out.println(queue.getFirst());
        System.out.println(queue.getLast());
        int[] arr1 = new int[]{1, 2, 3, 4, 5, 8, 9};
        for (int i = 0; i < arr1.length; i++) {
            queue.addFirst(arr1[i]);
        }
        while (!queue.isEmpty()) {
            System.out.print(queue.removeLast() + " ");
        }
    }
}
