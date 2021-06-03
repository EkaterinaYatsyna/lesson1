package com.yatsynaekateryna.lessons.tests;

import java.util.Arrays;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        //1.
        youngPhysicist();

        //2.
        beautifulMatrix();

        //3.
        queueAtSchool();

    }

    private static void queueAtSchool() {
        String str = "BGGBG";
        int time = 2;
        char[] arr = str.toCharArray();
        while (time > 0) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] == 'B' && arr[i + 1] == 'G') {
                    swap(arr, i, i + 1);
                    i++;
                }
            }
            time--;
        }
        str = String.valueOf(arr);
        System.out.println(str);
    }

    private static void beautifulMatrix() {
        int[][] arr = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    while (arr[2][2] != 1) {
                        if (i < 2) {
                            swap(arr, i, j, i + 1, j);
                            i++;
                        } else if (i > 2) {
                            swap(arr, i, j, i - 1, j);
                            i--;
                        } else if (j < 2) {
                            swap(arr, i, j, i, j + 1);
                            j++;
                        } else {
                            swap(arr, i, j, i, j - 1);
                            j--;
                        }
                        ans++;
                    }
                }
            }
        }
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println(ans);
    }

    private static void swap(int[][] arr,
                             int iFirst, int jFirst,
                             int iSecond, int jSecond) {
        int temp = arr[iFirst][jFirst];
        arr[iFirst][jFirst] = arr[iSecond][jSecond];
        arr[iSecond][jSecond] = temp;

    }

    private static void swap(char[] arr, int iFirst, int iSecond) {
        char temp = arr[iFirst];
        arr[iFirst] = arr[iSecond];
        arr[iSecond] = temp;
    }

    private static void youngPhysicist() {
        int n = 3;
//                {4, 1, 7},
//                {-2, 4, -1},
//                {1, -5, -3}

//                {3, -1, 7},
//                {-5, 2, -4},
//                {2, -1, -3}

        int[][] arr = new int[n][3];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        boolean ans = true;
        for (int j = 0; j < arr[0].length; j++) {
            int sum = 0;
            for (int[] ints : arr) {
                sum += ints[j];
            }
            if (sum != 0) {
                ans = false;
                break;
            }
        }
        System.out.println(ans ? "YES" : "NO");
    }
}
