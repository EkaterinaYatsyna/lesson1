package com.yatsynaekateryna.lessons.lesson8;

public class CharStack implements ICharStack {

    int nextInd;
    char[] arr;
    int capacity = 16;

    public CharStack() {
        arr = new char[capacity];
        nextInd = 0;
    }

    private void ensureCapacity() {
        if (nextInd == capacity) {
            capacity += capacity / 2;
            char[] arrBigger = new char[capacity];
            for (int i = 0; i < arr.length; i++) {
                arrBigger[i] = arr[i];
            }
            arr = arrBigger;
        }
    }

    @Override
    public int length() {
        return nextInd;
    }

    @Override
    public boolean isEmpty() {
        return nextInd == 0;
    }

    @Override
    public void add(char val) {
        ensureCapacity();
        arr[nextInd++] = val;
    }

    @Override
    public char get() {
        return arr[nextInd - 1];
    }

    @Override
    public char remove() {
        return arr[--nextInd];
    }
}
