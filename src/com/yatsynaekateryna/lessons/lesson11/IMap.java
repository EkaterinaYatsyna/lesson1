package com.yatsynaekateryna.lessons.lesson11;

public interface IMap {
    boolean contains(String key);

    default boolean add(String key) {
        return put(key, get(key) + 1);
    }

    boolean put(String key, int val);

    int get(String key);

    boolean remove(String key);

    int size();

    boolean isEmpty();
}
