package com.yatsynaekateryna.lessons.lesson5;

import java.util.Arrays;

public class ImutableString implements IString {

    char[] arr;

    public ImutableString(char[] arr) {
        this.arr = arr;
    }

    public ImutableString(String string) {
        this.arr = string.toCharArray();
    }

    @Override
    public String toString() {
        return String.valueOf(arr);
    }

    @Override
    public IString concat(char c) {
        ImutableString clone = new ImutableString(this.arr);
        clone.arr = Arrays.copyOf(arr, arr.length + 1);
        clone.arr[arr.length - 1] = c;
        return clone;
    }

    @Override
    public IString concat(String str) {
        ImutableString clone = new ImutableString(this.arr);
        int len = arr.length;
        clone.arr = Arrays.copyOf(arr, len + str.length());
        char[] strArr = str.toCharArray();
        for (int i = len, j = 0; i < arr.length; i++, j++) {
            clone.arr[i] = strArr[j];
        }
        return clone;
    }

    @Override
    public IString replaceAll(char toReplace, char value) {
        ImutableString clone = new ImutableString(this.arr);
        for (int i = 0; i < clone.arr.length; i++) {
            if (clone.arr[i] == toReplace) {
                clone.arr[i] = value;
            }
        }
        return clone;
    }

    @Override
    public IString replaceFirst(char toReplace, char value) {
        ImutableString clone = new ImutableString(this.arr);
        for (int i = 0; i < clone.arr.length; i++) {
            if (clone.arr[i] == toReplace) {
                clone.arr[i] = value;
                break;
            }
        }
        return clone;
    }

    @Override
    public IString set(int ind, char val) {
        ImutableString clone = new ImutableString(this.arr);
        clone.arr[ind] = val;
        return clone;
    }

    @Override
    public char get(int ind) {
        return arr[ind];
    }

    @Override
    public IString reverse() {
        ImutableString clone = new ImutableString(this.arr);
        for (int i = 0, j = clone.arr.length - 1; i < j; i++, j--) {
            char temp;
            temp = clone.arr[i];
            clone.arr[i] = clone.arr[j];
            clone.arr[j] = temp;
        }
        return clone;
    }

    @Override
    public IString reverse(int from, int upTo) {
        ImutableString clone = new ImutableString(this.arr);
        for (int i = from, j = upTo - 1; i < j; i++, j--) {
            char temp;
            temp = clone.arr[i];
            clone.arr[i] = clone.arr[j];
            clone.arr[j] = temp;
        }
        return clone;
    }

    @Override
    public IString subString(int from, int upTo) {
        //ImutableString clone = new ImutableString(this.arr);
        if (from > upTo || 0 < from || from > arr.length - 1 || upTo < 0 || upTo > arr.length - 1) {
            return null;
        }
        IString arrNew = new MutableString(new char[upTo - from + 1]);
        for (int i = from, j = 0; i < upTo + 1; i++, j++) {
            arrNew.set(j, arr[i]);
        }
        return arrNew;
    }
}
