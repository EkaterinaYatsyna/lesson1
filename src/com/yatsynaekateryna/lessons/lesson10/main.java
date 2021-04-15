package com.yatsynaekateryna.lessons.lesson10;

import java.sql.Array;
import java.util.Arrays;

public class main {
    public static void main(String[] args) {
//        //1. Создать случайную перестановку (random permutation).
//        //Например прямая перестановка длины 5 будет {0, 1, 2, 3, 4}. Случайная будет {1, 0, 4, 3, 2}.
//
//        int[] arrRandom = randomPermutation(5);
//        System.out.println("randomPermutation(5):");
//        System.out.println(Arrays.toString(arrRandom));
//
//        // 2. Создать функцию которая проверяет может ли массив быть перестановкой.
//        // Например {0, 1}, {2, 3, 0, 1}, {0} правильные. {2}, {1, 2}, {3, 8} неправильные.
//
//        //int[] checkArrPermutation = new int[]{1, 1, 0, 2, 6, 5, 3};
//        //System.out.println(isPermutation(checkArrPermutation));
//
//        //3. Создать метод applyPermutation(int[] arr, int[] perm); Который будет применять перестановку.
//        int[] arr = new int[]{26, 5, 13, 4, 7};
//        System.out.println("new arr:");
//        System.out.println(Arrays.toString(arr));
//
//        applyPermutation(arr, arrRandom);
//        System.out.println("applyPermutation:");
//        System.out.println(Arrays.toString(arr));


        BinaryTreeRecursive treeRecursive = new BinaryTreeRecursive();
        treeRecursive.add("молоко");
        treeRecursive.add("авто");
        treeRecursive.add("речка");
        treeRecursive.add("автомобиль");
        treeRecursive.add("река");
        System.out.println(treeRecursive);
        treeRecursive.remove("молоко");
        System.out.println(treeRecursive);


        BinaryTree tree = new BinaryTree();
        tree.add("молоко");
        tree.add("авто");
        tree.add("речка");
        tree.add("автомобиль");
        tree.add("река");
        System.out.println(tree);
//        System.out.println(tree.size());
//        tree.remove("молоко");
//        System.out.println(tree);
        System.out.println(tree.leftmost());
        System.out.println(tree.rightmost());
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
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= arr.length) {
                return false;
            }
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return false;
                }
            }
        }
        return true;
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
