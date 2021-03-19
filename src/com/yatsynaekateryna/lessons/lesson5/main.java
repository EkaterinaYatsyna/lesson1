package com.yatsynaekateryna.lessons.lesson5;

public class main {
    public static void main(String[] args) {
        MutableString test = new MutableString("Съешь этих мягких французских булок");
        System.out.println(test.concat('!'));
        System.out.println(test);
        System.out.println(test.concat(" ням-ням"));
        System.out.println(test.replaceAll('и', 'А'));
        System.out.println(test.replaceFirst('А', 'и'));
        System.out.println(test.set(1, 'А'));
        System.out.println(test.get(2));
        System.out.println(test.reverse());
        System.out.println(test.reverse());
        System.out.println(test.reverse(0, 4));
        System.out.println(test.subString(0, 6));
        System.out.println(test.replaceFirst('А', 'и'));

        char[] arr = new char[]{'h', 'e', 'l', 'l', 'o'};
        MutableString test2 = new MutableString(arr);
        System.out.println(test2.concat('!'));

        System.out.println("\n" + "ImutableString");
        ImutableString test3 = new ImutableString("Съешь этих мягких французских булок");
        System.out.println(test3.concat('!'));
        System.out.println(test3);
        System.out.println(test3.replaceAll('и', 'А'));
        System.out.println(test3.replaceFirst('А', 'и'));
        System.out.println(test3.set(1, 'А'));
        System.out.println(test3);
        System.out.println(test3.get(2));
        System.out.println(test3.reverse());
        System.out.println(test3.reverse());
        System.out.println(test3.reverse(0, 4));
        System.out.println(test3.subString(0, 6));
        System.out.println(test3.replaceFirst('А', 'и'));
        System.out.println(test3);
    }


}
