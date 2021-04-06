package com.yatsynaekateryna.lessons.lesson9;

public class Deque implements IDeque {

    private static class Node {
        int val;
        Node prev;
        Node next;
    }

    Node head;
    Node tail;
    int lenght;

    public Deque() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        lenght = 0;
    }

    @Override
    public int length() {
        return lenght;
    }

    @Override
    public boolean isEmpty() {
        return lenght == 0;
    }

    @Override
    public void addFirst(int val) {
        Node newNode = new Node();
        newNode.val = val;

        Node firstNode = head.next;
        head.next = newNode;
        newNode.prev = head;
        newNode.next = firstNode;
        firstNode.prev = newNode;

        lenght++;
    }

    @Override
    public void addLast(int val) {
        Node newNode = new Node();
        newNode.val = val;

        Node lastNode = tail.prev;
        tail.prev = newNode;
        newNode.next = tail;
        newNode.prev = lastNode;
        lastNode.next = newNode;

        lenght++;
    }

    @Override
    public int getFirst() {
        return head.next.val;
    }

    @Override
    public int getLast() {
        return tail.prev.val;
    }

    @Override
    public int removeFirst() {
        int ans = getFirst();

        head.next = head.next.next;
        head.next.prev = head;

        lenght--;
        return ans;
    }

    @Override
    public int removeLast() {
        int ans = getLast();

        tail.prev = tail.prev.prev;
        tail.prev.next = tail;

        lenght--;
        return ans;
    }
}
