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

//       MutableSet test = new MutableSet(new int[]{5,3,0,3,1});
//        System.out.println(test);
//       test.add(7);
//        MutableSet test2 = new MutableSet(5);
//        System.out.println(test2);
//        System.out.println(test);
//        System.out.println(test.len);
//        System.out.println(test.val);

        MutableSet test =  new MutableSet();

        test.add(5);
        System.out.println(test.val);
        DynamicSet test2 =  new DynamicSet(test);

//        System.out.println(test);
//        int [] arr = {15,6,12,32,0,96};
//        DynamicSet test2 =  new DynamicSet(arr);
//        System.out.println(test2.arr);











    }
}
