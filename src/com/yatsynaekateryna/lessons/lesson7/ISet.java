package com.yatsynaekateryna.lessons.lesson7;

public interface ISet {
    int len();

    int get();

    boolean contains(int toCheck);

    ISet add(int toAdd);

    ISet remove(int toRemove);

    ISet removeAll();

    ISet and(ISet set);

    ISet or(ISet set);

    ISet xor(ISet set);

}
