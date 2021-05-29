package com.yatsynaekateryna.lessons.lesson15;

import java.util.Arrays;
import java.util.Random;

public class main {
    public static void main(String[] args) {

        //1. У вас есть отсортированный массив {1, 2, 2, 2, 3, 3, 3, 4, 5, 5, 8, 8, 8, 8}.
        // Сделайте сжатие массива in-place (не выделяя дополнительной памяти), чтобы получилось  {1, 2, 3, 4, 5, 8, 3, 4, 5, 5, 8, 8, 8, 8}.
        // Реализовать за O(n). Сделать метод int zip(int[] arr) который возвращает размер сжатого участка.
        System.out.println("---1. сжатие массива in-place---");
        int[] arr = new int[]{1, 2, 2, 2, 3, 3, 3, 4, 5, 5, 8, 8, 8, 8};
        System.out.println("массив: " + Arrays.toString(arr));
        System.out.println("размер сжатого участка: " + zip(arr));
        System.out.println("сжатый массив: " + Arrays.toString(arr));
        System.out.println();


        //2. У вас есть не отсортированный массив {2, 3, 1, 5, 7, 9, 8, 2, 3, 4, 5}.
        // Выделены последний элемент, все кто меньше него и остальные.
        // Придумайте способ получить массив следующего вида {ТЕ КТО МЕНЬШЕ, ПОСЛЕДНИЙ, ОСТАЛЬНЫЕ} in-place.
        // Левая и правая часть не должны быть отсортированы. Сделать метод int partition(int[] arr) который возвращает индекс ПОСЛЕДНЕГО.
        System.out.println("---2. {ТЕ КТО МЕНЬШЕ, ПОСЛЕДНИЙ, ОСТАЛЬНЫЕ}---");
        arr = new int[]{2, 3, 1, 5, 7, 9, 8, 2, 3, 4, 9, 5};
        System.out.println("массив: " + Arrays.toString(arr));
        //System.out.println(partitionFirst(arr)); //my first version, but not so compact
        System.out.println("индекс ПОСЛЕДНЕГО: " + partition(arr));        //better version)
        System.out.println("{ТЕ КТО МЕНЬШЕ, ПОСЛЕДНИЙ, ОСТАЛЬНЫЕ}: " + Arrays.toString(arr));
        System.out.println();


        //3. На основе прошлого задания написать сортировку.
        //Она называется рекурсивная быстрая сортировка (recursive quick sort) (in-place).
        System.out.println("---3. recursive quick sort---");
        arr = new int[]{2, 3, 1, 5, 7, 9, 8, 2, 3, 4, 9, 5, 0, 12};
        System.out.println("массив: " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("отсортированный массив: " + Arrays.toString(arr) + "\n");


        //4. У вас есть не отсортированный массив {2, 3, 1, 5, 7, 9, 8, 2, 3, 4, 5}.  Выделены все элементы в которых 1 бит равен 0.
        // Придумайте способ получить массив следующего вида {1 бит равен 0, 1 бит равен 1} in-place. Левая и правая часть не должны быть отсортированы.
        // Сделать метод int partition(int[] arr) который возвращает количество тех 1 бит которых равен 0.
        System.out.println("---4. {1 бит равен 0, 1 бит равен 1} in-place---");
         arr = new int[]{6, 5, 4, 0, 14, 18, 36, 5, 4, 13, 63, 18, 12, 0, 13, 42, 6, 13, 16, 26, 13, 56, 62, 42, 28};
        System.out.println(Arrays.toString(arr));
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(Integer.toBinaryString(arr[i]) + " ");
//        }
//        System.out.println();
        System.out.println("количество тех 1 бит которых равен 0: " + partitionBit(arr, 1, 0, arr.length));
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            System.out.print(Integer.toBinaryString(arr[i]) + " ");
        }
        System.out.println();
        System.out.println();

        //5. На основе прошлого задания написать сортировку.  Такая сортировка называется битовая поразрядная (bit radix sort). Реализация может не поддерживать отрицательные числа.
        System.out.println("---5. bit radix sort in-place---");

        int[] ans = new int[100];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(ans));

        bitRadixSort(ans, 30, 0, ans.length, 20);
        //quickSort(ans, 0, ans.length - 1);
        //quickSortWhithBubble(ans, 0, ans.length, 20);
        //mergeSort(ans, 0, ans.length);
        System.out.println(Arrays.toString(ans));
    }

    private static void bitRadixSort(int[] arr, int bits, int from, int upTo, int opt) {

        if (upTo - from > opt) {
            if (bits > -1) {
                int partition = partitionBit(arr, bits, from, upTo);
                if (partition > 0) {
                    bitRadixSort(arr, bits - 1, from, from + partition, opt);
                }
                if (from + partition < upTo) {
                    bitRadixSort(arr, bits - 1, from + partition, upTo, opt);
                }
            }
        } else bubbleSort(arr, from, upTo);
    }

    private static int partitionBit(int[] arr, int bits, int from, int upTo) {

        int j = from;
        int quantity = 0;

        for (int i = from; i < upTo; i++) {
            if ((arr[i] & 1 << bits) == 0) {
                swap(arr, i, j);
                j++;
                quantity++;
            }
        }
        return quantity;
    }

    private static void quickSort(int[] arr, int from, int upTo) {
        Random random = new Random();
        int randomInd = from + random.nextInt(upTo - from);
        swap(arr, upTo, randomInd);

        int last = arr[upTo];
        int j = from;
        for (int i = from; i < upTo; i++) {
            if (arr[i] < last) {
                swap(arr, j, i);
                j++;
            }
        }
        swap(arr, j, upTo);

        if (j - 1 > from) {
            quickSort(arr, from, j - 1);
        }
        if (j + 1 < upTo) {
            quickSort(arr, j + 1, upTo);
        }

    }

    private static void quickSortWhithBubble(int[] arr, int from, int upTo, int opt) {

        Random random = new Random();
        int randomInd = from + random.nextInt(upTo - from);
        swap(arr, upTo, randomInd);

        int last = arr[upTo];
        int j = from;

        if (upTo - from > opt) {
            for (int i = from; i < upTo; i++) {
                if (arr[i] < last) {
                    swap(arr, j, i);
                    j++;
                }
            }
            swap(arr, j, upTo);


            if (j - 1 > from) {
                quickSortWhithBubble(arr, from, j - 1, opt);
            }
            if (j + 1 < upTo) {
                quickSortWhithBubble(arr, j + 1, upTo, opt);
            }
        } else {
            bubbleSort(arr, from, upTo+1);
        }

    }

    private static int partitionFirst(int[] arr) { //my first version and not so compact
        int last = arr[arr.length - 1];
        int i = 0;
        int j = arr.length - 1;
        ;
        while (i < j) {
            while (arr[i] < last) {
                i++;
            }
            while (arr[j] >= last) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, i, arr.length - 1);
        return i;
    }

    private static int partition(int[] arr) { //better version)

        int last = arr[arr.length - 1];
        int j = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < last) {
                swap(arr, j, i);
                j++;
            }
        }
        swap(arr, j, arr.length - 1);
        return j;
    }

    private static void swap(int[] arr, int indFirst, int indSecond) {
        int temp = arr[indFirst];
        arr[indFirst] = arr[indSecond];
        arr[indSecond] = temp;
    }


    private static int zip(int[] arr) {
        int ind = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[ind++] = arr[i];
            }
        }
        return ind;
    }

    private static void bubbleSort(int[] arr, int from, int upTo) {
        for (int i = from; i < upTo; i++) {
            int minVal = arr[i];
            int minInd = i;
            for (int j = i + 1; j < upTo; j++) {
                if (minVal > arr[j]) {
                    minVal = arr[j];
                    minInd = j;
                }
            }
            swap(arr, minInd, i);
        }
    }

    private static void bubbleSort(int[] arr) {
        bubbleSort(arr, 0, arr.length);
    }


    private static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length);
    }

    private static void mergeSort(int[] arr, int from, int upTo) {
        if (upTo - from > 1) {
            if (upTo - from > 20) {
                int mid = from + (upTo - from) / 2;
                mergeSort(arr, from, mid);
                mergeSort(arr, mid, upTo);
                merge(arr, from, mid, mid, upTo, from, upTo);
            } else {
                bubbleSort(arr, from, upTo);
            }
        }
    }

    //
    private static void merge(int[] arr,
                              int from1, int upto1,
                              int from2, int upto2,
                              int from3, int upTo3) {
        if ((upto1 - from1) + (upto2 - from2) != (upTo3 - from3)) {
            throw new RuntimeException("Something wrong");
        }
        int[] res = new int[upTo3 - from3];

        int ind1 = from1;
        int ind2 = from2;
        int indRes = 0;
        while (ind1 < upto1 && ind2 < upto2) {
            if (arr[ind1] < arr[ind2]) {
                res[indRes++] = arr[ind1++];
            } else {
                res[indRes++] = arr[ind2++];
            }
        }

        while (ind1 < upto1) {
            res[indRes++] = arr[ind1++];
        }
        while (ind2 < upto2) {
            res[indRes++] = arr[ind2++];
        }

        if (upTo3 - from3 >= 0) {
            System.arraycopy(res, 0, arr, from3, upTo3 - from3);
        }


    }
}
