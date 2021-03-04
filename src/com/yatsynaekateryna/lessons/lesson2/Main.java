package com.yatsynaekateryna.lessons.lesson2;

public class Main {
    public static void main(String[] args) {

        //1.Вывести число в полуинтервале [0, n) циклом
        System.out.println("\n" + "1.Вывести число в полуинтервале [0, n) циклом:");
        int n = 15;
        for (int i = 0; i < n; i++) {
            System.out.print(i);
        }

        //2. Вывести число в полуинтервале [0, n) хвостовой рекурсией.
        System.out.println("\n\n" + "2. Вывести число в полуинтервале [0, n) хвостовой рекурсией:");
        print_test2(0, n);

        //3.Перевернуть строку методом двух указателей.
        System.out.println("\n\n" + "3.Перевернуть строку методом двух указателей:");
        String original_text = "анилорак";
        char[] arrText = original_text.toCharArray();
        for (int a = 0, b = arrText.length - 1; a < b; a++, b--) {
            char temp = arrText[a];
            arrText[a] = arrText[b];
            arrText[b] = temp;
        }
        String new_text = String.valueOf(arrText);
        System.out.println(original_text + " --> " + new_text);

        //4. Вывести строку задом наперед с использованием стековой рекурсии.
        System.out.println("\n" + "4. Вывести строку задом наперед с использованием стековой рекурсии:");
        revers(original_text, 0, original_text.length());

        //5.2. Вывести числа 1 2 4 8 16 32 64 … 1024 с использованием хвостовой рекурсии.
        System.out.println("\n\n" + "5.2. Вывести числа 1 2 4 8 16 32 64 … 1024 с использованием хвостовой рекурсии:");
        print_test_5_2(1, 1025);

        //5.3. Вывести числа 1 2 4 8 16 32 64 … 1024 в 2 системе счисления с использованием хвостовой рекурсии.
        System.out.println("\n\n" + "5.3. Вывести числа 1 2 4 8 16 32 64 … 1024 в 2 системе счисления с использованием хвостовой рекурсии:");
        print_test_5_3(1, 1025);

        //5.4. Вывести числа 0 1 3 7 15 31 63 … MAX_INTEGER с использованием хвостовой рекурсии
        System.out.println("\n" + "5.4. Вывести числа 0 1 3 7 15 31 63 … MAX_INTEGER с использованием хвостовой рекурсии:");
        print_test_5_4(0, Integer.MAX_VALUE);

        //5.5. Вывести числа 0 1 3 7 15 31 63 … MAX_INTEGER в 2 системе счисления с использованием хвостовой рекурсии
        System.out.println("\n" + "5.5. Вывести числа 0 1 3 7 15 31 63 … MAX_INTEGER в 2 системе счисления с использованием хвостовой рекурсии:");
        print_test_5_5(0, Integer.MAX_VALUE);

        //5.11.Вывести английский алфавит с использованием хвостовой рекурсии.
        System.out.println("\n" + "5.11.Вывести английский алфавит с использованием хвостовой рекурсии:");
        print_test_5_6('a', 'z');

    }

    private static void print_test_5_6(char from, char upTo) {
        if (from <= upTo) {
            System.out.print(from + " ");
            print_test_5_6((char) (from + 1), upTo);
        }
    }

    private static void print_test_5_5(int from, int upTo) {
        if (from <= upTo && from >= 0) {
            System.out.println(Integer.toBinaryString(from));
            print_test_5_5(from * 2 + 1, upTo);
        }
    }

    private static void print_test_5_4(int from, int upTo) {
        if (from <= upTo && from >= 0) {
            System.out.println(from);
            print_test_5_4(from * 2 + 1, upTo);

        }
    }

    private static void print_test_5_3(int from, int upTo) {
        if (from < upTo) {
            System.out.println(Integer.toBinaryString(from));
            print_test_5_3(from * 2, upTo);
        }
    }

    private static void print_test_5_2(int from, int upTo) {
        if (from < upTo) {
            System.out.print(from + " ");
            print_test_5_2(from * 2, upTo);
        }
    }

    private static void revers(String text, int a, int b) {
        if (a < b) {
            revers(text, a + 1, b);
            System.out.print(text.charAt(a));
        }
    }

    private static void print_test2(int from, int upTo) {
        if (from < upTo) {
            System.out.print(from);
            print_test2(from + 1, upTo);
        }

    }
}
