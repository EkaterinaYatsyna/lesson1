package com.yatsynaekateryna.lessons.lesson3;

public class Main {
    public static void main(String[] args) {

        //1. Вывести матрицу циклом for.
        System.out.println("1. Вывести матрицу циклом for:");
        int[][] Matrix = {
                {3, 5, 6, 4},
                {1, 6, 7, 2},
                {8, 6, 4, 7},
                {9, 1, 22, 5}
        };
        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix[i].length; j++) {
                System.out.print(Matrix[i][j] + " ");
            }
            System.out.println();
        }

        //2. Вывести матрицу циклом while.
        System.out.println("\n" + "2. Вывести матрицу циклом while:");
        int a = 0;
        int b = 0;
        while (a < Matrix.length) {
            while (b < Matrix[a].length) {
                System.out.print(Matrix[a][b] + " ");
                b++;
            }
            a++;
            b = 0;
            System.out.println();
        }

        //3. Вывести матрицу хвостовой рекурсией.
        System.out.println("\n" + "3. Вывести матрицу хвостовой рекурсией:");
        print_test_3(0, 0, Matrix);

        //4. Вывести матрицу перевернутой по горизонтали стековой рекурсией.
        System.out.print("\n" + "4. Вывести матрицу перевернутой по горизонтали стековой рекурсией:");
        print_test_4(Matrix.length - 1, 0, Matrix);

        //5. Вывести матрицу перевернутой по вертикали стековой рекурсией.
        System.out.print("\n\n" + "5. Вывести матрицу перевернутой по вертикали стековой рекурсией:");
        print_test_5(0, Matrix[0].length - 1, Matrix);

    }

    private static void print_test_5(int i, int j, int[][] arr) {
        if (i < arr.length) {
            if (j >= 0) {
                print_test_5(i, j - 1, arr);
                System.out.print(arr[i][j] + " ");
            } else {
                print_test_5(i + 1, arr[i].length - 1, arr);
                System.out.println();
            }
        }
    }

    private static void print_test_4(int i, int j, int[][] arr) {
        if (i >= 0) {
            if (j < arr[i].length) {
                print_test_4(i, j + 1, arr);
                System.out.print(arr[i][j] + " ");
            } else {
                print_test_4(i - 1, 0, arr);
                System.out.println();
            }
        }
    }

    private static void print_test_3(int i, int j, int[][] arr) {
        if (i < arr.length) {
            if (j < arr[i].length) {
                System.out.print(arr[i][j] + " ");
                print_test_3(i, j + 1, arr);
            } else {
                System.out.println();
                print_test_3(i + 1, 0, arr);
            }
        }
    }
}
