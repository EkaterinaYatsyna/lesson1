package com.yatsynaekateryna.lessons.lesson13;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.ArrayList;
import java.io.*;

public class mainMemory {
    public static void main(String[] args) throws IOException {

        //1. Сгенерировать K случайных чисел которые в сумме дают N.
        //например метод int[] generateKRandomNumbersWithGivenSum(10) может вернуть {5, 3, 1, 1}

        int needSum = 50;
        System.out.println("\n1. Сгенерироруем K случайных чисел, которые в сумме дают " + needSum + ":");
        System.out.println(Arrays.toString(generateKRandomNumbersWithGivenSum(needSum)));


        //подготовить массив
        int[] arr = new int[]{5, 12, 26, 15, 3, 8, 4, 8, 0, 4, 4, 8, 25, 2, 1, 6};
        int[] buf = new int[5];
        System.out.println("\narray: " + Arrays.toString(arr));
        System.out.println("array length: " + buf.length);

        //3. Разбить массив на К областей. int[][] splitArrayForKParts(int[] k, int[] arr). Использовать ArrayStream.
        System.out.println("\n3. Разбить массив на К областей. int[][] splitArrayForKParts(int[] k, int[] arr):");
        int[][] splitArray = splitArrayForKParts(buf, arr);
        for (int[] ints : splitArray) {
            System.out.println(Arrays.toString(ints));
        }


        //4. Разбить массив на к областей. Iterator<int[]> splitArrayForKParts(int[] k, int[] arr). Использовать ArrayStream.
        System.out.println("\n4. Разбить массив на к областей. Iterator<int[]> splitArrayForKParts(int[] k, int[] arr):");
        Iterator<int[]> iter = splitArrayForKPartsIter(buf, arr);
        while (iter.hasNext()) {
            System.out.println(Arrays.toString(iter.next()));

        }

        //5. Сгенерировать файл размером 1ГБ. Использовать буферизированный вывод.
        String nameFile = "C:\\Users\\HP-USER\\Desktop\\javaTest\\myFile.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter
                (new OutputStreamWriter
                        (new FileOutputStream(nameFile), StandardCharsets.UTF_8))) {
            int needSize = 1_024 * 1_024 * 50;  //(1ГБ для моей машины очень много, тестировала на 50Mb)
            int curSize = 0;
            Random random = new Random();
            while (curSize < needSize) {
                String str = random.nextInt(Integer.MAX_VALUE) + "";
                bufferedWriter.write(str);
                bufferedWriter.newLine();
                curSize += str.getBytes(StandardCharsets.UTF_8).length + 2;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //6. Ограничить память Java VM до 2MB. Вывести минимальное и максимальное число в файле, а также их количество.
        // Использовать файл из предыдущего задания.

        System.out.println("\n6. Вывести минимальное и максимальное число в файле, а также их количество.");
        long startTimeBR = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader
                (new InputStreamReader
                        (new FileInputStream(nameFile)))) {

            int min = 0;
            int max = 0;
            int quantity = 0;
            String line = reader.readLine();
            if (line != null) {
                min = Integer.parseInt(line);
                max = min;
                quantity++;
            }

            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                int lineInt = Integer.parseInt(line);
                if (lineInt > max) {
                    max = lineInt;
                }
                if (lineInt < min) {
                    min = lineInt;
                }
                quantity++;
            }

            System.out.println("min: " + min);
            System.out.println("max: " + max);
            System.out.println("quantity: " + quantity);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long stopTimeBR = System.currentTimeMillis();


        //8. Замерить скорость работы программы если прошлую задачу решить с использованием Scanner или BufferedReader.

        System.out.println("\n8. Замерить скорость работы программы с использованием Scanner или BufferedReader.");
        System.out.println("Working time of the BufferedReader: " + (stopTimeBR-startTimeBR));
        long startTimeScanner = System.currentTimeMillis();
        try (Scanner scanner = new Scanner(new File(nameFile))) {

            int min = 0;
            int max = 0;
            int quantity = 0;
            if (scanner.hasNext()) {
                max = Integer.parseInt(scanner.next());
                min = max;
                quantity++;
            }
            while (scanner.hasNext()) {
                int cur = Integer.parseInt(scanner.next());
                if (cur > max) {
                    max = cur;
                }
                if (cur < min) {
                    min = cur;
                }
                quantity++;
            }
//            System.out.println("min: " + min);
//            System.out.println("max: " + max);
//            System.out.println("quantity: " + quantity);
        }
        long stopTimeScanner = System.currentTimeMillis();
        System.out.println("Working time of the Scanner: " + (stopTimeScanner-startTimeScanner));

    }

    public static Iterator<int[]> splitArrayForKPartsIter(int[] k, int[] arr) {

        Iterator<int[]> iterator = new Iterator<int[]>() {

            ArrayStream stream = new ArrayStream(arr);
            int cur = stream.fill(k);

            @Override
            public boolean hasNext() {
                return cur != -1;
            }

            @Override
            public int[] next() {
                if (hasNext()) {
                    int[] temp = Arrays.copyOf(k, k.length);
                    cur = stream.fill(k);
                    return temp;
                } else {
                    throw new NoSuchElementException();
                }

            }
        };

        return iterator;
    }

    private static int[][] splitArrayForKParts(int[] k, int[] arr) {
        int[][] splitArray = new int[arr.length / k.length + 1][k.length];
        ArrayStream stream = new ArrayStream(arr);
        int ind = 0;
        while (stream.fill(k) != -1) {
            splitArray[ind++] = Arrays.copyOf(k, k.length);
        }

        return splitArray;
    }

    private static Object[] generateKRandomNumbersWithGivenSum(int needSum) {
        Random random = new Random();
        ArrayList<Integer> arrList = new ArrayList<>();
        int sum = 0;
        while (sum < needSum) {
            int cur = random.nextInt(needSum / 2);
            if (sum + cur > needSum) {
                cur = needSum - sum;
            }
            arrList.add(cur);
            sum += cur;
        }
        return arrList.toArray();

    }
}
