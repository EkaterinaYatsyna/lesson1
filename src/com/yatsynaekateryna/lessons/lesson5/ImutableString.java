package com.yatsynaekateryna.lessons.lesson5;

public class ImutableString implements IString {

    String string;
    char[] arr;

    public ImutableString(char[] arr) {
        this.arr = arr;
        this.string = String.valueOf(arr);
    }

    public ImutableString(String string) {
        this.string = string;
        this.arr = string.toCharArray();
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public IString concat(char c) {
        ImutableString clone = new ImutableString(this.string);
        clone.string = clone.string + c;
        clone.arr = clone.string.toCharArray();
        return clone;
    }

    @Override
    public IString concat(String str) {
        ImutableString clone = new ImutableString(this.string);
        clone.string = clone.string + str;
        clone.arr = clone.string.toCharArray();
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
        clone.string = String.valueOf(clone.arr);
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
        clone.string = String.valueOf(clone.arr);
        return clone;
    }

    @Override
    public IString set(int ind, char val) {
        ImutableString clone = new ImutableString(this.arr);
        clone.arr[ind] = val;
        clone.string = String.valueOf(clone.arr);
        return clone;
    }

    @Override
    public char get(int ind) {
        return arr[ind];
    }

    @Override
    public IString reverse() {
        ImutableString clone = new ImutableString(this.arr);
        for (int i = 0, j = clone.string.length() - 1; i < j; i++, j--) {
            char temp;
            temp = clone.arr[i];
            clone.arr[i] = clone.arr[j];
            clone.arr[j] = temp;
        }
        clone.string = String.valueOf(clone.arr);
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
        clone.string = String.valueOf(clone.arr);
        return clone;
    }

    @Override
    public IString subString(int from, int upTo) {
        ImutableString clone = new ImutableString(this.arr);
        if (from > upTo || 0 < from || from > clone.arr.length - 1 || upTo < 0 || upTo > clone.arr.length - 1) {
            return this;
        }
        char[] arrNew = new char[upTo - from + 1];
        for (int i = from, j = 0; i < upTo + 1; i++, j++) {
            arrNew[j] = clone.arr[i];
        }
        clone.arr = arrNew;
        clone.string = String.valueOf(clone.arr);
        return clone;
    }
}
