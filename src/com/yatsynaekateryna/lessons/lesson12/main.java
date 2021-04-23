package com.yatsynaekateryna.lessons.lesson12;

import com.yatsynaekateryna.lessons.lesson10.BinaryTree;
import com.yatsynaekateryna.lessons.lesson10.ISortedStringSet;
import com.yatsynaekateryna.lessons.lesson11.HashTable;
import com.yatsynaekateryna.lessons.lesson9.Deque;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;


public class main {

    public static void main(String[] args) throws FileNotFoundException {


        Scanner scanner = new Scanner(new File("word_rus.txt"));
        String[] arr2 = new String[20];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = scanner.nextLine();
        }
        shuffle(arr2);
        System.out.println(Arrays.toString(arr2));


        System.out.println("\n--- HashTable ---");
        HashTable hashTable1 = new HashTable();
        for (String i : arr2) {
            hashTable1.add(i);
        }
        for (String cur : hashTable1) {
            System.out.print(cur + " ");
        }


        System.out.println("\n\n--- Deque ---");
        Deque deque = new Deque();
        deque.add(5);
        deque.add(8);
        deque.add(16);
        deque.add(13);
        deque.add(0);
        deque.add(8);
        for (int cur : deque) {
            System.out.print(cur + " ");
        }

        System.out.println("\n\n--- BinaryTree ---");
        BinaryTree set = new BinaryTree();
        String[]arr3 = new String[]{"абака", "аббатиса", "абажур", "абаз", "аббревиатура", "абазин", "аббат", "абазинка", "аббатство", "аббатисса"};
        for (String i : arr2) {
            set.add(i);
        }
        System.out.println(set);
        for (String cur : set) {
            System.out.print(cur + " ");
        }

        System.out.println("\n\n--- BinaryTree.subSet ---");
        ISortedStringSet set2 = set.subset("абазинка", "абвер");
        for (String cur2 : set2) {
            System.out.print(cur2 + " ");
        }

    }

    private static void shuffle(String[] arr) {

        //int length = arr.length;
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {

            int r = Math.abs(random.nextInt() >> 1) % arr.length;

            String temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;

        }

    }
}
