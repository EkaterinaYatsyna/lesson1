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
        deque.add(8);
        for (int cur : deque) {
            System.out.print(cur + " ");
        }

        System.out.println("\n\n--- BinaryTree ---");
        BinaryTree set = new BinaryTree();
        for (String i : arr2) {
            set.add(i);
        }
        for (String cur : set) {
            System.out.print(cur + " ");
        }

        System.out.println("\n\n--- BinaryTree.subSet ---");
        ISortedStringSet set2 = set.subset("абазинка", "абвер");
        for (String cur2 : set2) {
            System.out.print(cur2 + " ");
        }

    }
}
