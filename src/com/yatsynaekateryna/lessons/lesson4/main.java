package com.yatsynaekateryna.lessons.lesson4;
//java.util.Random;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        //1. В билете 6 цифр. Вычислить, является ли билет счастливым, не используя цикл.
        // При этом в первом варианте сделать случайную генерацию цифр билета в отдельном методе.
        // Второй вариант: добавить пользователю возможность вводить цифры билета из консоли,
        // в случае неверно введеного количества цифр билета предусмотреть возможность предлагать пользователю ввести данные снова.
        luckyTicket(6);

        //2. Пользователь вводит последовательность цифр. Определить, стоят ли цифры в числе по возрастанию/убыванию.
        // Исключить возможность работы программы с числом, состоящим только из 0.
        orderOfNumbers();

        //3. Ввести с клавиатуры число в диапазоне от 100 до 100 000 000 (введенное число проверяется).
        // Подсчитать количество четных и нечетных цифр в этом числе в процентном отношении.
        evenOddNumbers();

        //4. Пользователь вводит координаты центра окружности и ее радиус.
        //После чего пользователь вводит координаты 5ти точек, определить сколько из них лежат в окружности.
        // (для определения расстояний и проверок сделать отдельные методы)
        findPointsOnCircle();

        //5. Одноклеточная амеба каждые 3 часа делится на 2 клетки.
        //Определить, сколько клеток будет через 3, 6, 9, ..., 24 часа, если первоначально была одна амеба.
        divisionAmoeba(24, 1);
    }

    private static void divisionAmoeba(int hours, int cellsStart) {
        int cells = cellsStart;
        System.out.println("/n" + "Изначально клеток было: " + cellsStart);
        for (int i = 3; i < hours + 1; i += 3) {
            cells *= 2;
            System.out.println("Через " + i + ((i % 10 == 3) ? " часа" : " часов") + " количество клеток будет: " + cells);
        }
    } //5. Деление клеток

    private static void findPointsOnCircle() {

        int[][] points = new int[6][2];
        Scanner givePoint = new Scanner(System.in);
        System.out.print("\n" + "Введите радиус окружности: ");
        int radius = givePoint.nextInt();
        System.out.println("Введите координаты центра окружности: ");
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                if (i > 0 && j == 0) {
                    System.out.println("Введите координаты точки " + i + ": ");
                }
                if (j == 0) {
                    System.out.print("x: ");
                } else {
                    System.out.print("y: ");
                }
                points[i][j] = givePoint.nextInt();
            }
        }
        checkRadius(points, radius);
    }

    private static void checkRadius(int[][] points, int radius) {
        double foundRadius = 0;
        String whatPoints = "";
        int howManyPoints = 0;
        for (int i = 1; i < points.length; i++) {
            foundRadius = findRadius(points[0][0], points[0][1], points[i][0], points[i][1]);
            if (foundRadius == radius) {
                whatPoints = whatPoints + ((whatPoints == "") ? "" : ", ") + i;
                howManyPoints++;
            }
        }
        if (howManyPoints > 0) {
            System.out.println("На окружности лежит " + howManyPoints + " точек. Это точки № " + whatPoints + ".");
        } else {
            System.out.println("К сожалению ни одна из указанных точек не лежит на окружности");
        }
    } // проверить лежат ли точки на окружности

    private static double findRadius(int xCentre, int yCentre, int x, int y) {
        return Math.sqrt(Math.pow(xCentre - x, 2) + Math.pow(yCentre - y, 2));
    } //определить радиус

    private static void evenOddNumbers() {
        System.out.print("\n" + "Введите число от 100 до 100 000 000: ");
        int number = getCheckCorrectInputNumberEvenOdd();
        double evenNumbers = number / 2;
        double oddNumbers = number / 2 + number % 2;
        System.out.println("Четных чисел: " + (evenNumbers / number) * 100 + "%" + "\n" +
                "Нечетных чисел: " + oddNumbers / number * 100 + "%");

    } //3. Ввести с клавиатуры число в диапазоне от 100 до 100 000 000 (введенное число проверяется). Подсчитать количество четных и нечетных цифр в этом числе в процентном отношении.

    private static int getCheckCorrectInputNumberEvenOdd() {
        boolean correct = false;
        int returnYourNumber = 0;
        while (!correct) {
            Scanner giveYourNumber = new Scanner(System.in);
            String yourNumber = giveYourNumber.nextLine();
            if (!checkString(yourNumber)) {
                System.out.print("Введенные значения должны быть только цифры! Повторите попытку." + "\n" + "Введите число от 100 до 100 000 000: ");
            } else if (Integer.parseInt(yourNumber) > 100000000 ||
                    Integer.parseInt(yourNumber) < 100) {
                System.out.print("Введенные значения должны входить в диапазон от 100 до 100 000 000! Повторите попытку." + "\n" + "Введите число от 100 до 100 000 000: ");
            } else {
                correct = true;
                returnYourNumber = Integer.parseInt(yourNumber);
            }
        }
        return returnYourNumber;
    } //получить, проверив число на диапазон от 100 до 100 000 000

    private static void orderOfNumbers() {
        System.out.print("\n" + "Введите последовательность цифр: ");
        String number = getCheckCorrectInputNumber();
        int[] arrNumber = new int[number.length()];
        convertStringToIntArr(number, arrNumber);
        checkOrderOfNumber(arrNumber);
    } //2. Пользователь вводит последовательность цифр. Определить, стоят ли цифры в числе по возрастанию/убыванию.

    private static void checkOrderOfNumber(int[] arrNumber) {
        boolean ascending = true;              //по возростанию
        boolean descending = true;             //по убыванию
        boolean withoutOrder = false;           //без упорядочивания
        for (int i = 0, j = 1; i < arrNumber.length - 1; i++, j++) {
            if (arrNumber[i] == arrNumber[j]) {
                continue;
            } else if (arrNumber[i] < arrNumber[j] && ascending) {
                descending = false;
            } else if (arrNumber[i] > arrNumber[j] && descending) {
                ascending = false;
            } else {
                withoutOrder = true;
                break;
            }
        }
        String text = "";
        if (withoutOrder) {
            text = "без упорядочивания";
        } else if (ascending) {
            text = "по возростанию";
        } else {
            text = "по убыванию";
        }
        System.out.println("Ваше число введено " + text);
        ;
    } //Проверить числа по возростанию, по убыванию

    private static String getCheckCorrectInputNumber() {
        boolean correct = false;
        String returnYourNumber = "";
        while (!correct) {
            Scanner giveYourNumber = new Scanner(System.in);
            String yourNumber = giveYourNumber.nextLine();
            if (!checkString(yourNumber)) {
                System.out.print("Введенные значения должны быть только цифры! Повторите попытку." + "\n" + "Введите последовательность цифр: ");
            } else if (yourNumber.matches("0+")) {
                System.out.print("Введенные значения не должны быть 0! Повторите попытку." + "\n" + "Введите последовательность цифр: ");
            } else {
                correct = true;
                returnYourNumber = yourNumber;
            }
        }
        return returnYourNumber;
    } //получить введенное число пользователя для числа по возростанию, по убыванию

    private static void luckyTicket(int lenght) {
        int[] ticket = new int[lenght];

        System.out.println("---Заполняется билет случайными числами:---");
        fillTheTicketWithRandomNumbers(ticket);         //заполнить билет случайными числами
        checkTicket(ticket, "");                //проверить билет

        System.out.println("\n" + "---Заполняется билет пользователем:---");
        System.out.print("Введите Ваш билет: ");
        String yourTicket = getCheckCorrectInputTicket(lenght);    //получить введенный номер, проверив корретность введение данных пользователя
        convertStringToIntArr(yourTicket, ticket);              //заполнить массив билета числами с введенных строковых данных
        checkTicket(ticket, yourTicket);                           //проверить билет
    } //1. В билете 6 цифр. Вычислить, является ли билет счастливым, не используя цикл.

    private static void convertStringToIntArr(String fromString, int[] toInt) {
        char[] arrYourTicketChar = fromString.toCharArray();
        for (int i = 0; i < arrYourTicketChar.length; i++) {
            toInt[i] = Integer.parseInt(String.valueOf(arrYourTicketChar[i]));
        }
    }//конвертация строки в числовой массив

    private static String getCheckCorrectInputTicket(int lenght) {
        boolean correct = false;
        String returnYourTicket = "";
        while (!correct) {
            Scanner giveYourTicket = new Scanner(System.in);
            String yourTicket = giveYourTicket.nextLine();
            if (!checkString(yourTicket)) {
                System.out.print("Введенные значения должны быть только цифры! Повторите попытку." + "\n" + "Введите Ваш билет: ");
            } else if (yourTicket.length() != lenght) {
                System.out.print("Количество цифр должно быть равным " + lenght + "! Повторите попытку." + "\n" + "Введите Ваш билет: ");
            } else {
                correct = true;
                returnYourTicket = yourTicket;
            }
        }
        return returnYourTicket;
    } //получить введенное значение, проверив корректность введенных данных (только цифры и только длина = 6)

    private static boolean checkString(String yourTicket) {
        try {
            Long.parseLong(yourTicket);
        } catch (Exception e) {
            return false;
        }
        return true;
    } //проверить в строке только цифры

    private static void checkTicket(int[] ticket, String strTicket) {
        if (ticket[0] + ticket[1] + ticket[2] ==
                ticket[3] + ticket[4] + ticket[5]) {
            System.out.println("Поздравляем! Ваш билет " + ((strTicket == "") ? stringTicket(ticket) : strTicket) + " счастливый!");
        } else {
            System.out.println("К сожалению, Ваш билет " + ((strTicket == "") ? stringTicket(ticket) : strTicket) + " не является счастливым. В следующий раз обязательно повезет!");
        }
    }//проверить билет счастливый или нет

    private static String stringTicket(int[] ticket) {
        String stringTicket = "";
        for (int i = 0; i < ticket.length; i++) {
            stringTicket += ticket[i];
        }
        return stringTicket;
    }//преобразовать числовой массив в строку

    private static void fillTheTicketWithRandomNumbers(int[] ticket) {
        Random randomNumber = new Random();
        for (int i = 0; i < ticket.length; i++) {
            ticket[i] = randomNumber.nextInt(9);
        }
    } //заполнить массив случайными числами
}
