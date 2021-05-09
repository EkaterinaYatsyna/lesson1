package com.yatsynaekateryna.lessons.lesson6;

import com.yatsynaekateryna.lessons.lesson5.IArray;
import com.yatsynaekateryna.lessons.lesson5.MutableArray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

//Inheritance
public class BufferedArray extends MutableArray implements Iterable<Integer> {

    int capacity;
    int length;
    int[] arr;

    public BufferedArray(int[] arr) {
        super(arr);
        capacity = 16;
        capacity = Integer.max(arr.length, capacity);
        this.arr = new int[capacity];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
        this.length = arr.length;
    }

    public BufferedArray(int capacity) {
        super(capacity);
        this.arr = new int[capacity];
        this.capacity = capacity;
        this.length = 0;
    }

    public IArray insert(int index, int value) {

        if (capacity <= index) {
            while (capacity <= index) {
                capacity = growUp(capacity);
            }
            int[] newArray = new int[capacity];
            System.arraycopy(arr, 0, newArray, 0, length);
            arr = newArray;
        }
        if (index < length) {
            System.arraycopy(arr, index, arr, index + 1, length - index);
            length++;
        } else length += index - length + 1;

        arr[index] = value;
        return this;
    }

    public int get(int ind){
        return arr[ind];
    }

    public int getLength(){
        return length;
    }

    @Override
    public IArray add(int val) {

        if (length == capacity) {
            capacity = growUp(capacity);
            int[] newArray = new int[capacity];
            for (int i = 0; i < arr.length; i++) {
                newArray[i] = arr[i];
            }
            arr = newArray;
        }

        arr[length] = val;
        length++;
        return this;

    }

    private int growUp(int oldCapacity) {
        return oldCapacity + oldCapacity / 2;
    }


    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(arr, length));
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BufferedArrayIterator(arr, length);
    }

    private static class BufferedArrayIterator implements Iterator<Integer> {

        int nextInd;
        int[] arr;
        int len;

        public BufferedArrayIterator(int[] arr, int len) {
            this.arr = arr;
            this.len = len;
            this.nextInd = 0;
        }

        @Override
        public boolean hasNext() {
            return nextInd != len;
        }

        @Override
        public Integer next() {
            if (nextInd == len) {
                throw new NoSuchElementException("");
            } else {
                return arr[nextInd++];
            }
        }
    }


}
