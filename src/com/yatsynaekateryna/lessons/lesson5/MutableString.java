package com.yatsynaekateryna.lessons.lesson5;


import java.util.Arrays;

public class MutableString implements IString {

    String string;
    char[] arr;

    public MutableString(char[] arr) {
        this.arr = arr;
        this.string = String.valueOf(arr);
    }

    public MutableString(String string) {
        this.string = string;
        this.arr = string.toCharArray();
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public IString concat(char c) {
        string = string + c;
        arr = string.toCharArray();
        return this;
    }

    @Override
    public IString concat(String str) {
        string = string + str;
        arr = string.toCharArray();
        return this;
    }

    @Override
    public IString replaceAll(char toReplace, char value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == toReplace) {
                arr[i] = value;
            }
        }
        this.string = String.valueOf(arr);
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
        this.string = String.valueOf(arr);
        return this;
    }

    @Override
    public IString set(int ind, char val) {
        arr[ind] = val;
        this.string = String.valueOf(arr);
        return this;
    }

    @Override
    public char get(int ind) {
        return arr[ind];
    }

    @Override
    public IString reverse() {
        for (int i = 0, j = string.length() - 1; i < j; i++, j--) {
            char temp;
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        this.string = String.valueOf(arr);
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
        this.string = String.valueOf(arr);
        return this;
    }

    @Override
    public IString subString(int from, int upTo) {
        if (from > upTo || 0 < from || from > arr.length - 1 || upTo < 0 || upTo > arr.length - 1) {
            return this;
        }
        char[] arrNew = new char[upTo - from + 1];
        for (int i = from, j = 0; i < upTo + 1; i++, j++) {
            arrNew[j] = arr[i];
        }
        arr = arrNew;
        this.string = String.valueOf(arr);
        return this;
    }
}
