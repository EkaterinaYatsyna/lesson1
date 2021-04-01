package com.yatsynaekateryna.lessons.lesson7;

import java.util.Arrays;
import java.util.*;

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
            arr[i / 32] = arr[i / 32] | 1 << i % 32;
        }
        updateLen();
    }

    public DynamicSet(ISet set) {
        if (set instanceof MutableSet || set instanceof ImmutableSet) {
//            Object oSet = ISet.class.cast(set);
//            DynamicSet dSet = new DynamicSet();

            // dSet.add(oSet.val);

            MutableSet mSet = (MutableSet) set;
            DynamicSet dSet = new DynamicSet();
            dSet.add(mSet.val);
            arr = dSet.arr;
            len = mSet.len;

        } else {
            DynamicSet dSet = (DynamicSet) set;
            arr = dSet.arr;
            len = dSet.len;
        }
    }

    public static ISet setOf(int... vals) {
        DynamicSet set = new DynamicSet();
        for (int val : vals) {
            int index = val / 32;

            if (index > set.arr.length - 1) {
                set.arr = Arrays.copyOf(set.arr, index + 1);
            }
            set.arr[index] = set.arr[index] | 1 << val % 32;
        }
        set.len = 0;
        for (int i = 0; i < set.arr.length; i++) {
            set.len += Integer.bitCount(set.arr[i]);
        }
        return set;
    }

    void updateLen() {
        len = 0;
        for (int i = 0; i < arr.length; i++) {
            len += Integer.bitCount(arr[i]);
        }
    }

    void ensureCapacity(int ind) {
        if (ind > arr.length - 1) {
            arr = Arrays.copyOf(arr, ind + 1);
        }
    }

    @Override
    public String toString() {
        int[] arrEnd = new int[len];
        int IndexArrEnd = 0;
        for (int i = 0; i < arr.length; i++) {
            char[] chars = Integer.toString(arr[i], 2).toCharArray();
            for (int j = chars.length - 1, k = 0; j > -1; j--, k++) {
                if (chars[j] == '1') {
                    arrEnd[IndexArrEnd] = 32 * i + k;
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
        ensureCapacity(Index);
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
        arr = new int[0];
        len = 0;
        return this;
    }

    @Override
    public ISet and(ISet set) {
        DynamicSet dSet = new DynamicSet(set);
        ensureCapacity(dSet.arr.length - 1);

        for (int i = 0; i < dSet.arr.length; i++) {
            arr[i] = arr[i] & dSet.arr[i];
        }

        updateLen();
        return this;
    }

    @Override
    public ISet or(ISet set) {
        DynamicSet dSet = new DynamicSet(set);
        ensureCapacity(dSet.arr.length - 1);

        for (int i = 0; i < dSet.arr.length; i++) {
            arr[i] = arr[i] | dSet.arr[i];
        }

        updateLen();
        return this;
    }

    @Override
    public ISet xor(ISet set) {
        DynamicSet dSet = new DynamicSet(set);
        ensureCapacity(dSet.arr.length - 1);

        for (int i = 0; i < dSet.arr.length; i++) {
            arr[i] = arr[i] ^ dSet.arr[i];
        }

        updateLen();
        return this;
    }
}
