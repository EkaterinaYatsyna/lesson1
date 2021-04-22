package com.yatsynaekateryna.lessons.lesson10;

import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        //1. Создать случайную перестановку (random permutation).
        //Например прямая перестановка длины 5 будет {0, 1, 2, 3, 4}. Случайная будет {1, 0, 4, 3, 2}.

        int[] arrRandom = randomPermutation(5);
        System.out.println("randomPermutation(5):");
        System.out.println(Arrays.toString(arrRandom));

        // 2. Создать функцию которая проверяет может ли массив быть перестановкой.
        // Например {0, 1}, {2, 3, 0, 1}, {0} правильные. {2}, {1, 2}, {3, 8} неправильные.

        int[] checkArrPermutation = new int[]{ 1, 0, 2, 4, 5, 3};
        System.out.println("массив "+ Arrays.toString(checkArrPermutation) +" является перестановкой: "+ isPermutation(checkArrPermutation));

        //3. Создать метод applyPermutation(int[] arr, int[] perm); Который будет применять перестановку.
        int[] arr = new int[]{26, 5, 13, 4, 7};
        System.out.println("new arr:");
        System.out.println(Arrays.toString(arr));

        applyPermutation(arr, arrRandom);
        System.out.println("applyPermutation:");
        System.out.println(Arrays.toString(arr));


        if (true) {
            Scanner scanner = new Scanner(new File("word_rus.txt"));
            String[] arr2 = new String[34_010];
            for (int i = 0; i < arr2.length; i++) {
                arr2[i] = scanner.nextLine();
            }
            shuffle(arr2);

//BinaryTreeRecursive.add
            BinaryTreeRecursive rusSetRecursive = new BinaryTreeRecursive();
            long start = System.currentTimeMillis();
            for (int i = 0; i < 34_010; i++) {
                rusSetRecursive.add(arr2[i]);
            }
            long end = System.currentTimeMillis();
            System.out.println("Time BinaryTreeRecursive.add = " + (end - start));

//BinaryTree.add
            BinaryTree rusSet = new BinaryTree();
            start = System.currentTimeMillis();
            for (int i = 0; i < 34_010; i++) {
                rusSet.add(arr2[i]);
            }
            end = System.currentTimeMillis();
            System.out.println("Time BinaryTree.add = " + (end - start));
            System.out.println("максимальное число узлов в ветке: " + rusSet.maxDepth());


//BinaryTreeRecursive.remove
            // BinaryTreeRecursive rusSetRecursive = new BinaryTreeRecursive();
            start = System.currentTimeMillis();
            for (int i = 0; i < 34_005; i++) {
                rusSetRecursive.remove(arr2[i]);
            }
            end = System.currentTimeMillis();
            System.out.println("Time BinaryTreeRecursive.remove = " + (end - start));
            System.out.println(rusSetRecursive);

//BinaryTree.remove
//            IStringSet rusSet = new BinaryTree();
            start = System.currentTimeMillis();
            for (int i = 0; i < 34_005; i++) {
                rusSet.remove(arr2[i]);
            }
            end = System.currentTimeMillis();
            System.out.println("Time BinaryTree.remove = " + (end - start));
            System.out.println(rusSet);

        }
    }

    private static void shuffle(String[] arr) {

        int length = arr.length;
        Random random = new Random();

        for (int i = 0; i < length; i++) {

            int r = Math.abs(random.nextInt() >> 1) % length;

            String temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;

        }

    }

    private static int[] randomPermutation(int len) {
        int[] arrRandom = new int[len];
        for (int i = 0; i < len; i++) {
            arrRandom[i] = i;
        }
        for (int i = 0; i < len; i++) {
            int ind = (int) (Math.random() * len);
            int temp = arrRandom[i];
            arrRandom[i] = arrRandom[ind];
            arrRandom[ind] = temp;
        }
        return arrRandom;
    }//1. Создать случайную перестановку (random permutation). Например прямая перестановка длины 5 будет {0, 1, 2, 3, 4}. Случайная будет {1, 0, 4, 3, 2}.

    private static boolean isPermutation(int[] arr) {
        BinaryTree tree = new BinaryTree();
        for (int i = 0; i < arr.length; i++) {
            tree.add(Integer.toString(arr[i]));
        }
        if (arr.length == tree.size() && Integer.parseInt(tree.rightmost()) == arr.length - 1) {
            return true;
        }
        return false;
    }//2. Создать функцию которая проверяет может ли массив быть перестановкой. Например {0, 1}, {2, 3, 0, 1}, {0} правильные. {2}, {1, 2}, {3, 8} неправильные.

    private static boolean applyPermutation(int[] arr, int[] perm) {
        if (!isPermutation(perm) || arr.length != perm.length) {
            System.out.println("operation is not possible.");
            return false;
        }
        int[] arrTemp = arr.clone();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrTemp[perm[i]];
        }
        return true;
    } //3. Создать метод applyPermutation(int[] arr, int[] perm); Который будет применять перестановку.

}
