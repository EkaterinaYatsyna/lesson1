package com.yatsynaekateryna.lessons.lesson5;


import java.util.Arrays;

public class MutableString implements IString {
    char[] arr;

    public MutableString(char[] arr) {
        this.arr = arr;
    }

    public MutableString(String string) {
        this.arr = string.toCharArray();
    }

    @Override
    public String toString() {
        return String.valueOf(arr);
    }

    @Override
    public IString concat(char c) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = c;
        return this;
    }

    @Override
    public IString concat(String str) {
        int len = arr.length;
        arr = Arrays.copyOf(arr, len + str.length());
        char[] strArr = str.toCharArray();
        for (int i = len, j = 0; i < arr.length; i++, j++) {
            arr[i] = strArr[j];
        }
        return this;
    }

    @Override
    public IString replaceAll(char toReplace, char value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == toReplace) {
                arr[i] = value;
            }
        }
        return this;
    }

    @Override
    public IString replaceFirst(char toReplace, char value) {
        for (int i = 0; i < arr.length; i++) {
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
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
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
        IString arrNew = new MutableString(new char[upTo - from + 1]);
        for (int i = from, j = 0; i < upTo + 1; i++, j++) {
            arrNew.set(j, arr[i]);
        }
        return arrNew;
    }
}
