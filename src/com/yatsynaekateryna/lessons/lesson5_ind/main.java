package com.yatsynaekateryna.lessons.lesson5_ind;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        System.out.println("1. Вывести матрицу в output");
        int[][] mtx = {
                {1, 2, 3},
                {4, 5, 6}
        };
        StringBuilder output = new StringBuilder();
        printMatrix(mtx, output);
        System.out.println(output);

        //Прочитать про то как работает ArrayList. Big O time complexity
        //Прочитать про амортизацию. Лучшее/Среднее/Хучшее время работы.
        System.out.println("\n"+"2. Написать свою реализацию MutableString, протестировать ее и написать асимптотическую сложность запросов (методов).");


        ArrayList<String> name = new ArrayList<>();
        System.out.println("3. Вывести матрицу рекурсивно в MutableString");


        System.out.println("4. Реализовать метод swap(MutableString a, MutableString b)");
        MutableString str1 = new MutableString();
        MutableString str2 = new MutableString();
        str1.concat("val 1");
        str2.concat("val 1");
        System.out.printf("str1 = [%s], str2 = [%s]", str1, str2);
        swap(str1, str2);
        System.out.printf("str1 = [%s], str2 = [%s]", str1, str2);

        System.out.println("5. Добавить поддержку Appendable. Отнаследоваться и реализовать методы");
        Appendable appendable;// = new MutableString();


        System.out.println("6*. Решить задачу по ссылке");
        //https://acm.timus.ru/problem.aspx?space=1&num=1149
        int n;
        //Для ввода в консоль использовать
        boolean testing = false;

        if (testing) {
            n = 5;
        } else {
            System.out.print("Введи число n + Enter: ");
            Scanner scanner = new Scanner(System.in);
            n = scanner.nextInt();
        }

        StringBuilder sinExp = new StringBuilder();
        solveSin(n, sinExp);

        if (testing) {
            System.out.println(sinExp);
        } else {
            //Для быстрого вывода в консоль использовать
            try (Writer out = new BufferedWriter(new OutputStreamWriter(System.out))) {
                out.write(sinExp.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Посмотреть как работает ArrayDeque
        System.out.println("7*. Добавить возможность конкатинации в начало строки. И тоже написать сложность новых методов");

    }

    private static void solveSin(int n, StringBuilder output) {
        // TODO: 11.03.2021 Implement me
    }

    private static void swap(MutableString a, MutableString b) {
        // TODO: 11.03.2021 Implement me
    }


    private static class MutableString {

        public int length() {
            // TODO: 11.03.2021 Implement me
            return -1;
        }

        public int trimToSize() {
            int optimizedSize = -1;
            // TODO: 11.03.2021 Implement me
            return optimizedSize;
        }

        public void clear() {
            // TODO: 11.03.2021 Implement me
        }

        public MutableString concat(MutableString toConcat) {
            // TODO: 11.03.2021 Implement me
            throw new IllegalStateException();
        }

        public MutableString concat(char toConcat) {
            // TODO: 11.03.2021 Implement me
            throw new IllegalStateException();
        }

        public MutableString concat(char[] toConcat) {
            // TODO: 11.03.2021 Implement me
            throw new IllegalStateException();
        }

        public MutableString concat(String toConcat) {
            // TODO: 11.03.2021 Implement me
            throw new IllegalStateException();
        }

        public MutableString concat(CharBuffer toConcat) {
            // TODO: 11.03.2021 Implement me
            throw new IllegalStateException();
        }

        @Override
        public String toString() {
            // TODO: 11.03.2021 Implement me
            return super.toString();
        }
    }

    private static void printMatrix(int[][] mtx, StringBuilder output) {
        for (int i = 0; i < mtx.length; i++) {
            for (int j = 0; j < mtx[i].length; j++) {
                output.append(mtx[i][j]);
               if (j < mtx[i].length - 1) {
                    output.append(" ");
                } else if (i < mtx.length - 1) {
                    output.append("\n");
                }
            }
        }
    }

    private static void printRow(int[] row, int curCol, int upTo, StringBuilder output) {
        // TODO: 11.03.2021 Implement me
    }
}