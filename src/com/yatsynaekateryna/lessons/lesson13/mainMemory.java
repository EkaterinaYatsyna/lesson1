package com.yatsynaekateryna.lessons.lesson13;

import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class mainMemory {
    public static void main(String[] args) throws IOException {

        //1. Сгенерировать K случайных чисел которые в сумме дают N.
        //например метод int[] generateKRandomNumbersWithGivenSum(10) может вернуть {5, 3, 1, 1}
        int[] arr = new int[]{5, 12, 26, 15, 3, 8, 4, 8, 0, 4, 4, 8, 25, 2, 1, 6};
        int needSum = arr.length;
        System.out.println("\n1. Сгенерироруем K случайных чисел, которые в сумме дают " + needSum + ":");
        System.out.println(Arrays.toString(generateKRandomNumbersWithGivenSum(needSum)));


        //подготовить массив

        int[] k = generateKRandomNumbersWithGivenSum(arr.length);
        System.out.println("\narray: " + Arrays.toString(arr));
        System.out.println("int[] k: " + Arrays.toString(k));

        //3. Разбить массив на К областей. int[][] splitArrayForKParts(int[] k, int[] arr). Использовать ArrayStream.
        System.out.println("\n3. Разбить массив на К областей. int[][] splitArrayForKParts(int[] k, int[] arr):");
        int[][] splitArray = splitArrayForKParts(k, arr);
        for (int[] ints : splitArray) {
            System.out.println(Arrays.toString(ints));
        }

        //4. Разбить массив на к областей. Iterator<int[]> splitArrayForKParts(int[] k, int[] arr). Использовать ArrayStream.
        System.out.println("\n4. Разбить массив на к областей. Iterator<int[]> splitArrayForKParts(int[] k, int[] arr):");
        Iterator<int[]> iter = splitArrayForKPartsIter(k, arr);
        while (iter.hasNext()) {
            System.out.println(Arrays.toString(iter.next()));

        }

//        //5. Сгенерировать файл размером 1ГБ. Использовать буферизированный вывод.
        String nameFile = "C:\\Users\\HP-USER\\Desktop\\javaTest\\myFile.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter
                (new OutputStreamWriter
                        (new FileOutputStream(nameFile), StandardCharsets.UTF_8))) {
            int needSize = 1_024 * 1_024 * 2;  //(1ГБ для моей машины очень много, тестировала на 50Mb)
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
            int quantityMax = 0;
            int quantityMin = 0;
            String line = reader.readLine();
            if (line != null) {
                min = Integer.parseInt(line);
                max = min;
                quantityMax++;
                quantityMin++;
            }

            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                int lineInt = Integer.parseInt(line);
                if (lineInt > max) {
                    max = lineInt;
                    quantityMax = 1;
                } else if (lineInt == max) {
                    quantityMax++;
                }
                if (lineInt < min) {
                    min = lineInt;
                    quantityMin = 1;
                } else if (lineInt == min) {
                    quantityMin++;
                }
            }

            System.out.println("min: " + min);
            System.out.println("max: " + max);
            System.out.println("quantity max: " + quantityMax);
            System.out.println("quantity min: " + quantityMin);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long stopTimeBR = System.currentTimeMillis();


        //8. Замерить скорость работы программы если прошлую задачу решить с использованием Scanner или BufferedReader.

        System.out.println("\n8. Замерить скорость работы программы с использованием Scanner или BufferedReader.");

        long startTimeScanner = System.currentTimeMillis();
        try (Scanner scanner = new Scanner(new File(nameFile))) {

            int min = 0;
            int max = 0;
            int quantityMax = 0;
            int quantityMin = 0;
            if (scanner.hasNext()) {
                max = Integer.parseInt(scanner.next());
                min = max;
                quantityMax++;
                quantityMin++;
            }
            while (scanner.hasNext()) {
                int cur = Integer.parseInt(scanner.next());
                if (cur > max) {
                    max = cur;
                    quantityMax = 1;
                } else if (cur == max) {
                    quantityMax++;
                }
                if (cur < min) {
                    min = cur;
                    quantityMin = 1;
                } else if (cur == min) {
                    quantityMin++;
                }
            }
//            System.out.println("min: " + min);
//            System.out.println("max: " + max);
//            System.out.println("quantity max: " + quantityMax);
//            System.out.println("quantity min: " + quantityMin);
        }
        long stopTimeScanner = System.currentTimeMillis();
        System.out.println("Working time of the BufferedReader: " + (stopTimeBR - startTimeBR));
        System.out.println("Working time of the Scanner: " + (stopTimeScanner - startTimeScanner));

    }

    public static Iterator<int[]> splitArrayForKPartsIter(int[] k, int[] arr) {

        Iterator<int[]> iterator = new Iterator<int[]>() {

            ArrayStream stream = new ArrayStream(arr);

            int ind = 0;
            int[] buf = new int[k[ind]];
            int from = 0;
            int upTo = k[ind];
            int cur = stream.fill(buf, from, upTo);

            @Override
            public boolean hasNext() {
                return cur != -1;
            }

            @Override
            public int[] next() {
                if (hasNext()) {
                    int[] temp = Arrays.copyOf(buf, buf.length);

                    from += k[ind];
                    ind++;
                    if (ind == k.length) {
                        cur = -1;
                        return temp;
                    }
                    upTo += k[ind];
                    buf = new int[k[ind]];
                    cur = stream.fill(buf, from, upTo);

                    return temp;
                } else {
                    throw new NoSuchElementException();
                }

            }
        };

        return iterator;
    }

    private static int[][] splitArrayForKParts(int[] k, int[] arr) {
        int[][] splitArray = new int[k.length][];
        ArrayStream stream = new ArrayStream(arr);
        int indFrom = 0;
        int indUpTo = 0;

        for (int i = 0; i < k.length; i++) {
            indUpTo += k[i];
            int[] buf = new int[k[i]];
            stream.fill(buf, indFrom, indUpTo);
            splitArray[i] = Arrays.copyOf(buf, buf.length);
            indFrom += k[i];
        }
        return splitArray;
    }

    private static int[] generateKRandomNumbersWithGivenSum(int needSum) {
        Random random = new Random();
        ArrayList<Integer> arrList = new ArrayList<>();
        int sum = 0;
        while (sum < needSum) {
            int cur = random.nextInt(needSum / 2) + 1;
            if (sum + cur > needSum) {
                cur = needSum - sum;
            }
            arrList.add(cur);
            sum += cur;
        }
        int[] arr = new int[arrList.size()];
        for (int i = 0; i < arrList.size(); i++) {
            arr[i] = arrList.get(i);
        }
        return arr;

    }
}
