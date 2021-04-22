package com.yatsynaekateryna.lessons.lesson10;

public interface ISortedStringSet extends IStringSet,Iterable<String> {

    ISortedStringSet subset(String from, String upTo);

    String leftmost();

    String rightmost();

}
