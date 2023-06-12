package org.example;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Homework_2 {
    public static void main(String[] args){

        //Float f = hw_2_task_1();
        //System.out.println(f);
        String s = hw_2_task_4();
        System.out.println(s);
    }
    // Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float),
    // и возвращает введенное значение.
    // Ввод текста вместо числа не должно приводить к падению приложения,
    // вместо этого, необходимо повторно запросить у пользователя ввод данных.
    public static float hw_2_task_1(){
        String s = "Please enter float number: ";
        boolean res = check_if_float(enterInfo(s));
        if (res) {
            return Float.parseFloat(enterInfo(s));
        } else {
            return hw_2_task_1();
        }
    }
    public static String enterInfo(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
    public static boolean check_if_float(String s){
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Если необходимо, исправьте данный код
    public static void hw_2_task_2() {
        int[] intArray = new int[]{2,7,12,4,5,9,2,67,43,0};
        try {
            int d = 0;
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Catching exception: " + e);
        }
        catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        }
    }

    // Дан следующий код, исправьте его там, где требуется
    public static void hw_2_task_3(String[] args) throws Exception {
        try {
            int a = 90;
            int b = 3;
            System.out.println(a / b);
            printSum(23, 234);
            int[] abc = { 1, 2 };
            abc[3] = 9;
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Массив выходит за пределы своего размера!");
        } catch (Exception ex) {
            System.out.println("Что-то пошло не так...");
        }
    }
    public static void printSum(Integer a, Integer b){
        System.out.println(a + b);
    }

    // Разработайте программу, которая выбросит Exception,
    // когда пользователь вводит пустую строку.
    // Пользователю должно показаться сообщение, что пустые строки вводить нельзя.

    public static String hw_2_task_4(){
        System.out.println("Please enter some string: ");
        Scanner in = new Scanner(System.in);
        String result = in.nextLine();
        if (result.isEmpty()){
            System.out.println("You can't enter an empty string.");
        }
        return result;
    }
}
