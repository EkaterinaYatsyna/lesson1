package com.yatsynaekateryna.lessons;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


    //1. Вывести фразу “съешь же ещё этих мягких французских булок, да выпей чаю".
	System.out.println("съешь же ещё этих мягких французских булок, да выпей чаю");


	//2. Вывести числа 1 2 4 8 16 32 64 … 1024
    System.out.println();
    for(int i=1; i<=1024; i*=2) {
    System.out.println(i);
    }

    //3. Вывести предыдущее задание в 2 системе счисления.
    System.out.println();
    for(int i=1; i<=1024; i*=2) {
        System.out.println(Integer.toBinaryString(i));
    }

    //4. Вывести числа 0 1 3 7 15 31 63 … MAX_INTEGER
    System.out.println();
   for(int i=0; i<Integer.MAX_VALUE; i=i*2+1){
       System.out.println(i);
   }

   //5. Вывести предыдущее задание в 2 системе счисления.
    System.out.println();
    for(int i=0; i<Integer.MAX_VALUE; i=i*2+1) {
        System.out.println(Integer.toBinaryString(i));
    }

    //6. Написать числом серый цвет, зеленый, голубой, любой какой хотите. Например красный 0xFF0000 в 16 ричной. Выведите все цвета в 10 ричной.
    System.out.println();
    System.out.println("grey: " + 0x7f7f7f );
    System.out.println("blue: " + 0x00c8ff );
    System.out.println("green: " + 0x00ff00 );
    System.out.println("purple: " + 0xff00ff );

    //7. Вывести все числа от 0 до 2000 которые делятся на 3 И на 5.
   System.out.println();
   int num7 = 0;
   for (int i = 0; i<=2000; i++) {
       if (i % 5 == 0 && i % 3 == 0) {
           System.out.print(i + " ");
           num7++;
       }
   }

   //8. Вывести все числа от 0 до 2000 которые делятся на 3 ИЛИ на 5.
   System.out.println();
   int num8 = 0;
   for (int i = 0; i<=2000; i++){
       if(i%5 ==0 || i%3 ==0 ){
           System.out.print(i + " ");
           num8++;}
   }

   //9. Вывести все числа от 0 до 2000 которые делятся ИЛИ ТОЛЬКО на 3 ИЛИ ТОЛЬКО на 5.
   System.out.println();
   int num9 = 0;
    for (int i = 0; i<=2000; i++){
        if((i % 5 == 0 && i % 3 != 0) || (i % 5 != 0 && i % 3 == 0)){
            System.out.print(i + " ");
            num9++;}
    }

    //10.Каких чисел больше? 7 8 или 9?
    System.out.println();
    System.out.println("("+num7 + " "+ num8 + " " + num9+")");
    int Num_MAX = Math.max(Math.max(num7, num8), num9);
    int num_test;
    if (Num_MAX == num7){
        num_test = 7;}
    else if(Num_MAX == num8){
        num_test = 8;}
    else num_test = 9;
    System.out.println("Максимальное количество чисел в задании: " + num_test + " и состоит из " + Num_MAX + " чисел.");

    //11.Вывести английский алфавит.
    System.out.println();
    for(char i='a'; i<='z'; i++){
        System.out.print(i + " ");
    }

    //12. Вывести только гласные из английского алфавита.
    System.out.println();
    for(char i='a'; i<='z'; i++){
        if (i=='a' || i=='e' || i=='i' || i=='o' || i=='u' || i=='y'){
            System.out.print(i + " ");
        }
    }

    //13. Написать степенным литералом (double num = 1e20) числа 1_000_000_000, 1_000_000_000 + 7, 1_000, 0.000_005.
    System.out.println();
    double num =1e9;        //1_000_000_000
    double num2 =1e9+7;   //1_000_000_000 + 7
    double num3 =1e3;       //1_000
    double num4 =5e-6;      //0.000_005
    }
}
