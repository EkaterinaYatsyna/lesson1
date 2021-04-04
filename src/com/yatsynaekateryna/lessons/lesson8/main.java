package com.yatsynaekateryna.lessons.lesson8;

import java.sql.SQLOutput;

public class main {
    public static void main(String[] args) {

        //2.Вывести строку задом наперед с использованием стека.

        String string = "Hello world!";
        System.out.println("2.Вывести строку \"" + string+ "\" задом наперед с использованием стека.");
        CharStack stack = new CharStack();
        for (char i : string.toCharArray()) {
            stack.add(i);
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.remove());
        }


        System.out.println("\n");
        //3.Определить является ли строка “{()}(){}[]<>((({}[])))” правильной скобочной последовательностью (ПСП).
        System.out.println("3.Определить является ли строка “{()}(){}[]<>((({}[])))” правильной скобочной последовательностью (ПСП).");
        String checkString = "({})(){}[]<>((({}[])))";
        System.out.println("string " + checkString + " is " + (correct(checkString) ? "" : "not ") + "correct.");

        //4. * Если да то вывести ее в таком виде:
        if (correct(checkString)) {
            printString(checkString);
        }
    }

    private static boolean correct(String string) {
        CharStack stack = new CharStack();
        for (char i : string.toCharArray())
            if (i == '(' || i == '<' || i == '{' || i == '[') {
                stack.add(i);
            } else if (stack.isEmpty() ||
                    (i == ')' && stack.get() != '(') ||
                    (i == '>' && stack.get() != '<') ||
                    (i == '}' && stack.get() != '{') ||
                    (i == ']' && stack.get() != '[')) {
                return false;

            } else {
                stack.remove();
            }
        return stack.isEmpty();
    }

    private static void printString(String string) {
        CharStack tab = new CharStack();
        System.out.println("НАЧАЛО ВЫВОДА");
        for (char i : string.toCharArray()) {

            if (i == '(' || i == '<' || i == '{' || i == '[') {
                for (int j = 0; j < tab.length(); j++) {
                    System.out.print(tab.get());
                }
                tab.add('\t');
            } else {
                tab.remove();
                for (int j = 0; j < tab.length(); j++) {
                    System.out.print(tab.get());
                }
            }
            System.out.println(i);
        }
        System.out.println("КОНЕЦ ВЫВОДА");
    }

}


