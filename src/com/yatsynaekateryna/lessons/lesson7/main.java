package com.yatsynaekateryna.lessons.lesson7;

import java.util.BitSet;

public class main {
    private static Object BitSet;

    public static void main(String[] args) {

        //1. Посчитать кол чисел в диапазоне [1..n] которые делятся ИЛИ на 2 ИЛИ на 3 ИЛИ на 5 ИЛИ на 7 .
        //Использовать long. Решение должно быть за константу.
        {
//            long n = 20;
//            long divisionOfNumbers = n / 2 + n / 3 + n / 5 + n / 7;
//            divisionOfNumbers = divisionOfNumbers - n / (2 * 3) - n / (2 * 5) - n / (2 * 7) - n / (3 * 5) - n / (3 * 7) - n / (5 * 7);
//            divisionOfNumbers = divisionOfNumbers + 3 * (n / (2 * 3 * 5 * 7)) + (n / (2 * 3 * 5)) + (n / (3 * 5 * 7)) + (n / (3 * 5 * 7));
//            System.out.println(divisionOfNumbers);
        }


        System.out.println("\n"+"MutableSet (15)");
        MutableSet mutableSet = new MutableSet(15);
        System.out.println(mutableSet);
        mutableSet.add(2)
                .add(0)
                .add(15)
                .add(16);
        System.out.println("MutableSet.add (2,0,15,16)");
        System.out.println(mutableSet);


        System.out.println("\n" + "ImmutableSet (5,12,0,20,16)");
        ImmutableSet immutableSet = new ImmutableSet(new int[]{5, 12, 0, 20, 16});
        System.out.println(immutableSet);  //00000000000100010001000000100001
        System.out.println("ImmutableSet (4,12,0,20,3)");
        ImmutableSet immutableSet2 = new ImmutableSet(new int[]{4, 12, 0, 20, 3});
        System.out.println(immutableSet2); //00000000000100000001000000011001
        System.out.println("ImmutableSet.or");
        ImmutableSet immutableSet3 = (ImmutableSet) immutableSet.or(immutableSet2);
        System.out.println(immutableSet3); //00000000000100010001000000111001 (0,3,4,5,12,16,20)


        System.out.println("\n" + "DynamicSet {2, 0, 31, 16, 2, 63, 75}");
        DynamicSet dynamicSet = new DynamicSet(new int[]{2, 0, 31, 16, 2, 63, 75});
        System.out.println(dynamicSet);  //[0, 2, 16, 31, 63, 75]

        System.out.println("\n" + "DynamicSet.add (54, 2, 80)");
        dynamicSet.add(54)
                .add(2)
                .add(80);
        System.out.println(dynamicSet);  //[0, 2, 16, 31, 54, 63, 75, 80]

        System.out.println("\n" + "DynamicSet.remove (2)");
        dynamicSet.remove(2);
        System.out.println(dynamicSet);  //[0, 16, 31, 54, 63, 75, 80]
        System.out.println("\n" + "DynamicSet.remove (15)");
        dynamicSet.remove(15);
        System.out.println(dynamicSet);  //[0, 16, 31, 54, 63, 75, 80]

        System.out.println("\n" + "DynamicSet.and (2,0,15,16)");
        dynamicSet.and(mutableSet);
        System.out.println(dynamicSet); //[0, 16]

        System.out.println("\n" + "DynamicSet.or (0,3,4,5,12,16,20)");
        dynamicSet.or(immutableSet3);
        System.out.println(dynamicSet); //[0, 3, 4, 5, 12, 16, 20]

        System.out.println("\n" + "DynamicSet.xor (2,0,15,16)");
        dynamicSet.xor(mutableSet);
        System.out.println(dynamicSet); //[2, 3, 4, 5, 12, 15, 20]

        System.out.println("\n" + "DynamicSet.removeAll");
        dynamicSet.removeAll();
        System.out.println(dynamicSet); //00000000000000000000000000000000


    }
}
