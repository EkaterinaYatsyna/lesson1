package com.yatsynaekateryna.lessons.lesson7;

import java.util.Arrays;

public class MutableSet implements ISet {

    int len;
    int val;

    public MutableSet() {
        val = 0;
        len = 0;
    }

    public MutableSet(int val) {
        this.val = 1 << val;
        this.len = Integer.bitCount(this.val);
    }

    public MutableSet(int[] arr) {
        for (int i : arr) {
            val = val | 1 << i;
        }
        len = Integer.bitCount(val);
    }

    @Override
    public int len() {
        return len;
    }

    @Override
    public int get() {
        return val;
    }

    @Override
    public boolean contains(int toCheck) {
        return (val & (1 << toCheck)) > 0;
    }

    @Override
    public ISet add(int toAdd) {
        if (contains(toAdd) || toAdd > 31) {
            return this;
        }
        val = val | (1 << toAdd);
        len = Integer.bitCount(val);
        return this;
    }

    @Override
    public ISet remove(int toRemove) {
        if (!contains(toRemove) || toRemove > 31) {
            return this;
        }

        val = val ^ (1 << toRemove);
        len = Integer.bitCount(val);
        return this;
    }

    @Override
    public ISet removeAll() {
        val = 0;
        len = 0;
        return this;
    }

    @Override
    public ISet and(ISet set) {
        val = val & set.get();
        len = Integer.bitCount(val);
        return this;
    }

    @Override
    public ISet or(ISet set) {
        val = val | set.get();
        len = Integer.bitCount(val);
        return this;
    }

    @Override
    public ISet xor(ISet set) {
        val = val ^ set.get();
        len = Integer.bitCount(val);
        return this;
    }

    @Override
    public String toString() {
        return toBinaryString(val);
    }

    private static String toBinaryString(int numb) {
        return String.format("%32s", Integer.toBinaryString(numb)).replace(' ', '0');
    }
}
