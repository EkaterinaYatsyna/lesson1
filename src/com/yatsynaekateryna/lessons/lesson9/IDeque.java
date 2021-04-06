package com.yatsynaekateryna.lessons.lesson9;

public interface IDeque {

    int length();

    boolean isEmpty();

    void addFirst(int val);

    void addLast(int val);

    int getFirst();

    int getLast();

    int removeFirst();

    int removeLast();

}
