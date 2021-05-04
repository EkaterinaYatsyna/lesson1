package com.yatsynaekateryna.lessons.lesson13;

public class ArrayStream {

    int[] arr;
    int ind;

    public ArrayStream(int[] arr) {
        this.arr = arr;
        ind = 0;
    }

    public int fill(int[] buffer) {

        return fill(buffer, ind, arr.length);
    }

    public int fill(int[] buffer, int fromInclusive, int toExclusive) {
        if (buffer == null
                || fromInclusive > arr.length - 1 || toExclusive > arr.length
                || fromInclusive < 0 || toExclusive < 0 || toExclusive < fromInclusive) {
            return -1;
        }

        ind = fromInclusive;
        int cur = 0;
        for (int i = 0; i < buffer.length; i++) {
            if (ind == toExclusive) {
                return cur;
            }
            buffer[i] = arr[ind++];
            cur++;

        }
        return cur;
    }

}
