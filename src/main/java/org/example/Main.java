package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //System.out.println(Homework_1.hw1_task_1_1(2,0));
        int [] arr = {1,2,3,4,5,6,7};
        int [] arr2 = {1,2,3,4,5,6,7};
        //Homework_1.hw1_task_1_2(arr,5);
        //String part_3 = Homework_1.hw1_task_1_3("Please enter some information: ");
        //System.out.println(part_3);
        //int[] res = Homework_1.hw1_task_2(arr,arr2);
        int[] res = Homework_1.hw1_task_3(arr,arr2);
        for (int i = 0; i<res.length; i++){
            System.out.print(res[i] + " ");
        }
    }

    //task_1: Реализуйте метод, принимающий в качестве аргумента целочисленный массив.
//Если длина массива меньше некоторого заданного минимума, метод возвращает -1 в качестве кода ошибки,
//иначе - длину массива.
    int [] arr = {1,2,3,4,5,6,7};
    int min_length = 9;
    public static int task_1(int[] array, int min_length){
        if (array.length<min_length) return -1;
        else return array.length;
    }
/* task_1_2:
Реализуйте метод, принимающий в качестве аргумента целочисленный массив и некоторое значение. Метод ищет в массиве заданное значение и возвращает его индекс. При этом, например:
    - если длина массива меньше некоторого заданного минимума, метод возвращает -1 в качестве кода ошибки.
    - если искомый элемент не найден, метод вернет -2 в качестве кода ошибки.
    - если вместо массива пришел null, метод вернет -3
    - придумайте свои варианты исключительных ситуаций и верните соответствующие коды ошибок.
    Напишите метод, в котором реализуете взаимодействие с пользователем. То есть, этот метод запросит искомое число у пользователя, вызовет первый метод, обработает возвращенное значение и покажет читаемый результат пользователю. Например, если вернулся -2, пользователю выведется сообщение: “Искомый элемент не найден”.*/

    public static int task_1_2(int[] array, int min_length, int value){
        if (array==null) {
            return -3;
        } else {
            int t = task_1(array, min_length);
            if (t == -1) return -1;

            else {
                t = -2;
                for (int i = 0; i < array.length; i++) {
                    if (array[i] == value) {
                        t = i;
                        break;
                    }
                }
                return t;
            }
        }
    }
    public static void task_1_2_for_user(int result) {
        switch (result) {
            case -1:
                System.out.println("Ошибка: длина массива меньше заданного минимума");
                break;
            case -2:
                System.out.println("Искомый элемент не найден");
                break;
            case -3:
                System.out.println("Массив не задан");
                break;
            case -4:
                System.out.println("Длина массива равна 0");
                break;
            default:
                System.out.println("Индекс найденного элемента: " + result);
        }
    }

    /* task_2: Реализуйте метод, принимающий в качестве аргумента целочисленный двумерный массив.
Необходимо посчитать и вернуть сумму элементов этого массива.
При этом накладываем на метод 2 ограничения: метод может работать только с квадратными массивами (кол-во строк = кол-ву столбцов), и в каждой ячейке может лежать только значение 0 или 1.
Если нарушается одно из условий, метод должен бросить RuntimeException с сообщением об ошибке. */

    public static int task_2 (int[][]array){
        int result = 0;
        boolean is_quarter = true;
        for (int i = 0; i<array.length;i++) {
            if (array.length != array[i].length) {
                is_quarter = false;
                throw new RuntimeException("The array is not quarter.");
            }
        }
        if (is_quarter){
            for (int i= 0; i<array.length;i++){
                for (int j = 0; j<array.length; j++) {
                    if (array[i][j]==0 || array[i][j]==1){
                        result += array[i][j];
                    }
                    else {
                        throw new RuntimeException("Not 0 or 1 elements found");
                    }
                }
            }
        }
        return result;
    }


    /*task_3: Реализуйте метод, принимающий в качестве аргумента целочисленный двумерный массив. Необходимо посчитать и вернуть сумму элементов этого массива.
При этом накладываем на метод 2 ограничения: метод может работать только с квадратными массивами (кол-во строк = кол-ву столбцов), и в каждой ячейке может лежать только значение 0 или 1.
Если нарушается одно из условий, метод должен вернуть код ошибки.
Программа должна корректно обработать код ошибки и вывести соответствующее сообщение пользователю.
Сравнить такой вариант обработки исключений с предыдущим.
Какое преимущество у исключений перед
кодами ошибок вы можете назвать
в данном случае?*/

/* task_4 Реализуйте метод checkArray(Integer[] arr), принимающий в качестве аргумента целочисленный одномерный массив.
Метод должен пройтись по каждому элементу и проверить что он не равен null.
А теперь реализуйте следующую логику:
Если в какой-то ячейке встретился null, то необходимо “оповестить” об этом пользователя
Если null’ы встретились в нескольких ячейках, то идеально было бы все их “подсветить” */

 /*   Integer[] array = {1,null,3,null,};
    List<Integer> nullIndexes = task_4(array);
    int size = nullIndexes.size();
     if (nullIndexes.length() > 0){
        System.out.println("Массив содержит null в ячейках" + nullIndexes);
    } else {
        System.out.println("Массив не содержит null");
    }

    public static List<Integer> task_4(Integer[] array){
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i<array.length; i++){
            if (array[i]==null){
                result.add(i);
            }
        }
        return result;
    }*/
}

