package com.yatsynaekateryna.lessons.lesson7;

import java.util.Arrays;

public class ImmutableSet implements ISet {

    int len;
    int val;

    public ImmutableSet() {
        val = 0;
        len = 0;
    }

    public ImmutableSet(int val) {
        this.val = val;
        this.len = Integer.bitCount(val);
    }

    public ImmutableSet(int[] arr) {
        char[] chars = new char[32];
        Arrays.fill(chars, '0');
        for (int i : arr) {
            chars[31 - i] = '1';
        }
        val = Integer.parseInt(String.valueOf(chars), 2);
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
            return null;
        }
        ImmutableSet clone = new ImmutableSet(val);
        clone.val = val | (1 << toAdd);
        clone.len = Integer.bitCount(clone.val);
        return clone;
    }

    @Override
    public ISet remove(int toRemove) {
        if (!contains(toRemove) || toRemove > 31) {
            return null;
        }
        ImmutableSet clone = new ImmutableSet(val);
        clone.val = val ^ (1 << toRemove);
        clone.len = Integer.bitCount(clone.val);
        return clone;
    }

    @Override
    public ISet removeAll() {
        ImmutableSet clone = new ImmutableSet(val);
        clone.val = 0;
        clone.len = 0;
        return clone;
    }

    @Override
    public ISet and(ISet set) {
        ImmutableSet clone = new ImmutableSet(val);
        clone.val = val & set.get();
        clone.len = Integer.bitCount(clone.val);
        return clone;
    }

    @Override
    public ISet or(ISet set) {
        ImmutableSet clone = new ImmutableSet(val);
        clone.val = val | set.get();
        clone.len = Integer.bitCount(clone.val);
        return clone;
    }

    @Override
    public ISet xor(ISet set) {
        ImmutableSet clone = new ImmutableSet(val);
        clone.val = val ^ set.get();
        clone.len = Integer.bitCount(clone.val);
        return clone;
    }

    @Override
    public String toString() {
        return toBinaryString(val);
    }

    private static String toBinaryString(int numb) {
        return String.format("%32s", Integer.toBinaryString(numb)).replace(' ', '0');
    }
}
