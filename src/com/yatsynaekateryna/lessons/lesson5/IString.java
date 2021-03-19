package com.yatsynaekateryna.lessons.lesson5;

public interface IString {

    IString concat(char c);
    IString concat(String str);
    IString replaceAll(char toReplace, char value);
    IString replaceFirst(char toReplace, char value);
    IString set(int ind, char val);
    char get(int ind);
    IString reverse();
    IString reverse(int from, int upTo);
    IString subString(int from, int upTo);

}
