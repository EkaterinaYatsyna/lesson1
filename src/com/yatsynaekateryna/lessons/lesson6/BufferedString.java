package com.yatsynaekateryna.lessons.lesson6;

import com.yatsynaekateryna.lessons.lesson5.IString;

import java.util.Arrays;

public class BufferedString implements IString {

    char[] arr;
    int capacity;
    int length;

    public BufferedString(char[] arr) {
        this.length = arr.length;
        this.capacity = Integer.max(16, length);
        this.arr = new char[capacity];
        for (int i = 0; i < length; i++) {
            this.arr[i] = arr[i];
        }
    }

    public BufferedString(String str) {
        this.length = str.length();
        this.capacity = Integer.max(16, length);
        this.arr = new char[capacity];
        char[] strArr = str.toCharArray();
        for (int i = 0; i < length; i++) {
            this.arr[i] = strArr[i];
        }
    }

    public BufferedString(int capacity) {
        this.length = 0;
        this.capacity = capacity;
        this.arr = new char[capacity];
    }

    @Override
    public String toString() {
        return String.valueOf(Arrays.copyOf(arr, length));
    }

    @Override
    public IString concat(char c) {
        ensureCapacity();
        arr[length] = c;
        length++;
        return this;
    }

    @Override
    public IString concat(String str) {
        char[] strArr = str.toCharArray();
        for (int i = 0; i < strArr.length; i++) {
            ensureCapacity();
            arr[length] = strArr[i];
            length++;
        }
        return this;
    }

    @Override
    public IString replaceAll(char toReplace, char value) {
        for (int i = 0; i < length; i++) {
            if (arr[i] == toReplace) {
                arr[i] = value;
            }
        }
        return this;
    }

    @Override
    public IString replaceFirst(char toReplace, char value) {
        for (int i = 0; i < length; i++) {
            if (arr[i] == toReplace) {
                arr[i] = value;
                break;
            }
        }
        return this;
    }

    @Override
    public IString set(int ind, char val) {
        arr[ind] = val;
        return this;
    }

    @Override
    public char get(int ind) {
        return arr[ind];
    }

    @Override
    public IString reverse() {
        for (int i = 0, j = length - 1; i < j; i++, j--) {
            char temp;
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return this;
    }

    @Override
    public IString reverse(int from, int upTo) {
        for (int i = from, j = upTo - 1; i < j; i++, j--) {
            char temp;
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return this;
    }

    @Override
    public IString subString(int from, int upTo) {
        if (from > upTo || 0 > from || from > arr.length - 1 || upTo < 0 || upTo > arr.length - 1) {
            return null;
        }
        IString arrNew = new BufferedString(new char[upTo - from + 1]);
        for (int i = from, j = 0; i < upTo + 1; i++, j++) {
            arrNew.set(j, arr[i]);
        }
        return arrNew;
    }

    private void ensureCapacity() {
        if (length == capacity) {
            capacity += capacity / 2;
            char[] newArr = new char[capacity];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
    }



}
