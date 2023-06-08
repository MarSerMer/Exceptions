package org.example;

import java.util.Scanner;

public class Homework_1 {

    //Реализуйте 3 метода, чтобы в каждом из них получить разные исключения
    public static float hw1_task_1_1(int a, int b){
        if (b==0){
            throw new RuntimeException("You can not divide by zero.");
        } else {
            return a/b;
        }
    }
    public static void hw1_task_1_2(int[] array, int index){
        if (index>=array.length){
            throw new RuntimeException("There's no such index in the array");
        } else {
            System.out.println(array[index]);
        }
    }

    public static String hw1_task_1_3(String message){
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        String result = in.nextLine();
        if (result.isEmpty()){
            throw new RuntimeException("Nothing was entered");
        } else {
            return result;
        }
    }

    //Посмотрите на код, и подумайте сколько разных типов исключений вы тут сможете получить?
    public static int sum2d(String[][]arr){
        int sum = 0;
        for (int i=0; i<arr.length; i++){
            for(int j = 0; j<5; j++){ //вот тут может быть OutOfBoundsException
                int val = Integer.parseInt(arr[i][j]); //здесь может быть NumberFormatException
                sum+=val; // ну и в принципе может получиться слишком большая сумма, которая не уложится в int
            }
        }
        return sum;
    }

    // Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив, каждый элемент которого равен разности элементов двух входящих массивов в той же ячейке. Если длины массивов не равны, необходимо как-то оповестить пользователя.

    public static int[] hw1_task_2(int[] a, int[] b){
        if (a.length!=b.length){
            throw new RuntimeException("These arrays do not match by length");
        }
        else {
            int[] result = new int[a.length];
            for (int i = 0; i<a.length; i++){
                result[i] = a[i] + b[i];
            }
            return result;
        }
    }

    // * Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив, каждый элемент которого равен частному элементов двух входящих массивов в той же ячейке. Если длины массивов не равны, необходимо как-то оповестить пользователя. Важно: При выполнении метода единственное исключение, которое пользователь может увидеть - RuntimeException, т.е. ваше.
    public static int[] hw1_task_3(int[] a, int[] b){
        if (a.length!=b.length){
            throw new RuntimeException("These arrays do not match by length");
        }
        else {
            int[] result = new int[a.length];
            for (int i = 0; i<a.length; i++){
                if (b[i]==0){
                    throw new RuntimeException("One of the elements is zero. We can't divide by zero");
                } else {
                    result[i] = a[i]/b[i];
                }
            }
            return result;
        }
    }

}
