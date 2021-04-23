package com.yatsynaekateryna.lessons.lesson9;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque implements IDeque, IArray, Iterable<Integer> {

    private static class Node {
        int val;
        Node prev;
        Node next;
    }

    Node head;
    Node tail;
    int length;

    public Deque() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        length = 0;
    }

    @Override //O(1)
    public int length() {
        return length;
    }

    @Override //O(1)
    public boolean isEmpty() {
        return length == 0;
    }

    @Override //(1)
    public void addFirst(int val) {
        Node newNode = new Node();
        newNode.val = val;

        Node firstNode = head.next;
        head.next = newNode;
        newNode.prev = head;
        newNode.next = firstNode;
        firstNode.prev = newNode;

        length++;
    }

    @Override //O(1)
    public void addLast(int val) {
        Node newNode = new Node();
        newNode.val = val;

        Node lastNode = tail.prev;
        tail.prev = newNode;
        newNode.next = tail;
        newNode.prev = lastNode;
        lastNode.next = newNode;

        length++;
    }

    @Override //O(1)
    public int getFirst() {
        return head.next.val;
    }

    @Override //O(1)
    public int getLast() {
        return tail.prev.val;
    }

    @Override //O(1)
    public int removeFirst() {
        int ans = getFirst();

        head.next = head.next.next;
        head.next.prev = head;

        length--;
        return ans;
    }

    @Override //O(1)
    public int removeLast() {
        int ans = getLast();

        tail.prev = tail.prev.prev;
        tail.prev.next = tail;

        length--;
        return ans;
    }

    @Override //O(1)
    public void add(int val) {
        addLast(val);
    }

    @Override //O(n)
    public int get(int ind) {
        if (ind >= length) {
            return 0;}
        Node get = find(ind);
        return get.val;
    }

    @Override //O(n)
    public void set(int ind, int val) {
        if (ind < length) {
            Node set = find(ind);
            set.val = val;
        }
    }

    @Override //O(n)
    public void insert(int ind, int val) {
        if (ind < length) {
            Node insert = find(ind);

            Node addNode = new Node();
            addNode.val = insert.val;
            insert.val = val;

            Node firstNode = insert.next;

            insert.next = addNode;
            addNode.prev = insert;
            addNode.next = firstNode;
            firstNode.prev = addNode;

            length++;
        }
    }

    @Override //O(n)
    public void remove(int ind) {
        if (ind < length) {
            Node remove = find(ind);
            Node removeNext = remove.next;
            remove.prev.next = removeNext;
            remove.next.prev = remove.prev;

            length--;
        }

    }

     private Node find(int ind){ //O(n)
        int middle = length / 2;
        Node start;
        Node get = new Node();

        if (ind < middle) {
            start = head;
            for (int i = 0; i < ind + 1; i++) {
                get = start.next;
                start = start.next;
            }
        } else {
            start = tail;
            for (int i = 0; i < length - ind; i++) {
                get = start.prev;
                start = start.prev;
            }
        }
        return get;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new DequeIterator(head,tail);
    }

    private static class DequeIterator implements Iterator<Integer>{

        Node cur;
        Node tail;

        public DequeIterator(Node head,Node tail) {
            this.cur = head.next;
            this.tail = tail;
        }

        @Override
        public boolean hasNext() {
            return cur != tail;
        }

        @Override
        public Integer next() {
            if (this.hasNext()) {
                int val = cur.val;
                cur = cur.next;
                return val;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

}


