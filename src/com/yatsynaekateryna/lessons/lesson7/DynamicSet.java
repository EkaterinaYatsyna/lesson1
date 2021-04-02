package com.yatsynaekateryna.lessons.lesson7;

import java.util.Arrays;

public class DynamicSet implements ISet {

    int[] arr;
    int len;

    public DynamicSet() {
        arr = new int[1];
        len = 0;
    }

    public DynamicSet(int[] val) {
        int max = val[0];
        for (int i = 1; i < val.length; i++) {
            if (val[i] > max) {
                max = val[i];
            }
        }
        arr = new int[max / 32 + 1];
        for (int i : val) {
            arr[i / 32] = arr[i / 32] | 1 << (i % 32);
        }
        updateLen();
    }

    public DynamicSet(ISet set) {
        if (set instanceof MutableSet) {
            arr = new int[]{((MutableSet) set).val};
            updateLen();
        } else if (set instanceof ImmutableSet) {
            arr = new int[]{((ImmutableSet) set).val};
            updateLen();
        } else {
            DynamicSet dSet = (DynamicSet) set;
            arr = dSet.arr;
            len = dSet.len;
        }
    }

    void updateLen() {
        len = 0;
        for (int i = 0; i < arr.length; i++) {
            len += Integer.bitCount(arr[i]);
        }
    }

    int[] ensureCapacity(int ind, int[] arr) {
        if (ind > arr.length - 1) {
            arr = Arrays.copyOf(arr, ind + 1);
        }
        return arr;
    }

    @Override
    public String toString() {
        if (arr.length == 1) {
            return String.format("%32s", Integer.toBinaryString(arr[0])).replace(' ', '0');
        }
        int[] arrEnd = new int[len];
        int IndexArrEnd = 0;
        for (int i = 0; i < arr.length; i++) {
            int indexLen = Integer.toBinaryString(arr[i]).length();
            for (int j = 0, k = 1; j < indexLen; j++, k = k << 1) {
                if ((arr[i] & k) != 0) {
                    arrEnd[IndexArrEnd] = 32 * i + j;
                    IndexArrEnd++;
                }
            }
        }
        return Arrays.toString(arrEnd);
    }

    @Override
    public int len() {
        return len;
    }

    @Override
    public int get() {
        return 0;
    }

    @Override
    public boolean contains(int toCheck) {
        int index = toCheck / 32;
        toCheck %= 32;
        if (index > arr.length - 1) {
            return false;
        }
        return (arr[index] & (1 << toCheck)) > 0;
    }

    @Override
    public ISet add(int toAdd) {
        if (contains(toAdd)) {
            return this;
        }
        int Index = toAdd / 32;
        toAdd %= 32;
        arr = ensureCapacity(Index, arr);
        arr[Index] = arr[Index] | (1 << toAdd);
        updateLen();
        return this;
    }

    @Override
    public ISet remove(int toRemove) {
        if (!contains(toRemove)) {
            return this;
        }

        int Index = toRemove / 32;
        toRemove %= 32;

        arr[Index] = arr[Index] ^ (1 << toRemove);
        updateLen();
        return this;
    }

    @Override
    public ISet removeAll() {
        arr = new int[1];
        len = 0;
        return this;
    }

    @Override
    public ISet and(ISet set) {
        DynamicSet dSet = new DynamicSet(set);
        int maxInd = Math.max(arr.length, dSet.arr.length) - 1;
        arr = ensureCapacity(maxInd, arr);
        dSet.arr = ensureCapacity(maxInd, dSet.arr);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] & dSet.arr[i];
        }

        updateLen();
        return this;
    }

    @Override
    public ISet or(ISet set) {
        DynamicSet dSet = new DynamicSet(set);
        int maxInd = Math.max(arr.length, dSet.arr.length) - 1;
        arr = ensureCapacity(maxInd, arr);
        dSet.arr = ensureCapacity(maxInd, dSet.arr);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] | dSet.arr[i];
        }

        updateLen();
        return this;
    }

    @Override
    public ISet xor(ISet set) {
        DynamicSet dSet = new DynamicSet(set);
        int maxInd = Math.max(arr.length, dSet.arr.length) - 1;
        arr = ensureCapacity(maxInd, arr);
        dSet.arr = ensureCapacity(maxInd, dSet.arr);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] ^ dSet.arr[i];
        }

        updateLen();
        return this;
    }
}
