package com.yatsynaekateryna.lessons.lesson5;

public interface IArray {

    IArray add(int val);

    IArray set(int ind, int val);

    int get(int ind);

    IArray swap(int first, int second);

    IArray reverse(int from, int upTo);

}
