package com.yatsynaekateryna.lessons.lesson14;

import com.yatsynaekateryna.lessons.lesson6.BufferedArray;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class main {
    public static void main(String[] args) {

        //1. Добавить в реализацию DynamicArray insert(int index, int value).
        System.out.println("1. Добавить в реализацию DynamicArray insert(int index, int value):");
        BufferedArray bufferedArray = new BufferedArray(new int[]{1, 2, 8, 5, 2, 4, 1});
        int ind = 12;
        int val = 26;
        System.out.println("Array before: " + bufferedArray);
        bufferedArray.insert(ind, val);
        System.out.println("BufferedArray.insert(" + ind + ", " + val + "):");
        System.out.println("Array after:  " + bufferedArray);

        //2. Написать сортировку вставками. Использовать DynamicArray.
        System.out.println("\n2. Написать сортировку вставками. Использовать DynamicArray.");
        int[] arr = new int[]{5, 4, 12, 85, 21, 4, 6, 0, 2, 4};
        System.out.println("Array before: " + Arrays.toString(arr));
        insertionSort(arr);
        System.out.println("Array after:  " + Arrays.toString(arr));

        //3. Посчитать кубический корень числа N с использованием бин поиска с точностью 1e-6
        System.out.println("\n3. Посчитать кубический корень числа N с использованием бин поиска с точностью 1e-6");
        int num = 250;
        double res = powLowerBound(num);
        System.out.println("cube root of a number " + num + " = " + res);
        System.out.println("check: " + Math.round(Math.pow(res, 3)));

        //4. Сыграть в игру со структурой данных GuessNumberGame. И угадать 8192 битное число.
        System.out.println("\n4. Сыграть в игру со структурой данных GuessNumberGame. И угадать 8192 битное число.");
        GuessNumberGame guessNumberGame = new GuessNumberGame();
        System.out.println("you need to guess the number: " + guessNumberGame);
        System.out.println("good luck :)");
        BigInteger answer = guessNumber(guessNumberGame);


    }

    private static BigInteger guessNumber(GuessNumberGame num) {
        BigInteger from = new BigInteger("0");
        BigInteger upTo = new BigInteger("2").pow(8192).subtract(new BigInteger("1"));
        int attempts = 0;
        if (num.guess(from) == 0) {
            System.out.println("number of attempts: " + attempts + 1);
            return from;
        } else if (num.guess(upTo) == 0) {
            System.out.println("number of attempts: " + attempts + 1);
            return upTo;
        } else {
            BigInteger mid = upTo.subtract(from).divide(new BigInteger("2")).add(from);
            while (num.guess(mid) != 0) {
                if (num.guess(mid) < 0) {
                    upTo = mid;
                } else {
                    from = mid;
                }
                attempts++;
                mid = upTo.subtract(from).divide(new BigInteger("2")).add(from);
            }
            System.out.println("number of attempts: " + attempts);
            return mid;
        }
    }


    private static double powLowerBound(int n) {

        double from = 0;
        double upTo = n;
        while (upTo - from > 0.001) {
            double mid = from + (upTo - from) / 2;
            if (Math.pow(mid, 3) > n) {
                upTo = mid;
            } else {
                from = mid;
            }
        }
        return Math.round(upTo * 1000_000.0) / 1000_000.0;
    }

    private static void insertionSort(int[] arr) {

        BufferedArray bufferedArray = new BufferedArray(new int[0]);
        bufferedArray.insert(0, arr[0]);
        for (int i = 1; i < arr.length; i++) {
            int ind = lowerBound(bufferedArray, arr[i]);
            bufferedArray.insert(ind, arr[i]);
        }
        int i = 0;
        for (Integer cur : bufferedArray) {
            arr[i++] = cur;
        }
    }

    private static int lowerBound(BufferedArray arr, int val) {
        if (val <= arr.get(0)) {
            return 0;
        } else if (arr.get(arr.getLength() - 1) < val) {
            return arr.getLength();
        } else {
            int mid;
            int from = 0;
            int upTo = arr.getLength() - 1;
            while (upTo - from > 1) {
                mid = from + (upTo - from) / 2;
                if (val < arr.get(mid)) {
                    upTo = mid;
                } else {
                    from = mid;
                }
            }
            return upTo;
        }

    }
}
