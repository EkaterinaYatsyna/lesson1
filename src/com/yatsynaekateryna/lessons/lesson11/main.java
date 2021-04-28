package com.yatsynaekateryna.lessons.lesson11;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {

        //1.Вывести самый распространенный символ в строке. Например для “abcdabbb” вывести b.
        // Если несколько символов встречаются одинаковое кол раз - вывести любой.
        String str = "abcdabaabb";
        IMap hashTable = new HashTable();
        char[] chars = str.toCharArray();
        char symbol = chars[0];
        int max = 1;
        for (char c : chars) {
            hashTable.add(Character.toString(c));
            int temp = hashTable.get(Character.toString(c));
            if (temp > max) {
                symbol = c;
                max = temp;
            }
        }
        System.out.println("Cамый распространенный символ в строке \"" + str + "\" - " + symbol);

        //2.Вывести индексы самого распространенного символа в строке. Например для “abcdabbb” вывести b : {0, 5, 6, 7}.
        // Если несколько символов встречаются одинаковое кол раз - вывести любой.

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (Objects.equals(chars[i], symbol)) {
                if ((ans.length() != 0)) {
                    ans.append(", ");
                }
                ans.append(i);
            }
        }
        System.out.println("Он находится в индексах - {" + ans + "}");

        //3. Вывести топ 10 самый частых слов в романе на англ. языке. (В английском нет падежей, так проще). Роман на выбор.
        Scanner scanner = new Scanner(new File("EnglishStory.txt"));
        scanner.useDelimiter("[ ,.\":!?;\r\n]+");
        HashTable hashMap = new HashTable();
        while (scanner.hasNext()) {
            hashMap.add(scanner.next().toLowerCase());
        }
        scanner.close();

        int numWords = 10;
        int[] arrVal = new int[numWords];
        String[] arrWords = new String[numWords];
        for (String cur : hashMap) {
            int val = hashMap.get(cur);
            if (arrVal[arrVal.length - 1] < val) {
                for (int i = 0; i < arrVal.length; i++) {
                    if (arrVal[i] <= val) {
                        insert(arrWords, cur, i);
                        insert(arrVal, val, i);
                        break;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(arrWords));
        System.out.println(Arrays.toString(arrVal));
    }

    private static void insert(String[] arr, String val, int ind) {
        if (arr.length - 1 - ind >= 0) System.arraycopy(arr, ind, arr, ind + 1, arr.length - 1 - ind);
        arr[ind] = val;
    }

    private static void insert(int[] arr, int val, int ind) {

        if (arr.length - 1 - ind >= 0) System.arraycopy(arr, ind, arr, ind + 1, arr.length - 1 - ind);
        arr[ind] = val;

    }


}
