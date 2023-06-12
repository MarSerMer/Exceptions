package org.example;

import java.io.*;
import java.util.HashMap;

public class Seminar_2 {
    public static void main(String[] args){
        HashMap<String,Integer> map = readData("C:\\Users\\User\\Desktop\\Geek Brains\\14 ОБРАБОТКА ИСКЛЮЧЕНИЙ\\Exceptions\\src\\main\\java\\org\\example\\names.txt");
        write_data("C:\\Users\\User\\Desktop\\Geek Brains\\14 ОБРАБОТКА ИСКЛЮЧЕНИЙ\\Exceptions\\src\\main\\java\\org\\example\\names.txt",map);

    }

    // Обработайте возможные исключительные ситуации. “Битые” значения в
    //исходном массиве считайте нулями. Можно внести в код правки, которые считаете
    //необходимыми.
    //Исходный код:
    /*public static int sum2d(String[][]arr){
        int sum = 0;
        for (int i=0; i<arr.length; i++){
            for(int j = 0; j<5; j++){ //вот тут может быть OutOfBoundsException
                int val = Integer.parseInt(arr[i][j]); //здесь может быть NumberFormatException
                sum+=val; // ну и в принципе может получиться слишком большая сумма, которая не уложится в int
            }
        }
        return sum;
    }*/
    public static int sum2d(String[][]arr){
        int sum = 0;
        for (int i=0; i<arr.length; i++){
                for (int j = 0; j < arr[i].length; j++) {
                    if (check_if_numeric(arr[i][j])){
                        int val = Integer.parseInt(arr[i][j]);
                        sum += val;
                    }
                }
        }
        return sum;
    }
    public static boolean check_if_numeric(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    // Задание №3
    //Запишите в файл следующие строки:
    //Анна=4
    //Елена=5
    //Марина=6
    //Владимир=?
    //Константин=?
    //Иван=4
    //Реализуйте метод, который считывает данные из файла и сохраняет в двумерный массив (либо HashMap).
    //В отдельном методе нужно будет пройти по структуре данных.
    //Если сохранено значение ?, заменить его на соответствующее число.
    //Если на каком-то месте встречается символ, отличный от числа или ?, бросить подходящее исключение.
    //Записать в тот же файл данные с замененными символами ?.
    public static void write_data(String FileName, HashMap<String,Integer>map){
        try{
            File file = new File(FileName);
            FileWriter writer = new FileWriter(file);

            for (String name : map.keySet()){
                int value = map.get(name);
                writer.write(name + "=" + value + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи!");
        }
    }
    public static HashMap<String,Integer> readData (String FileName){
        HashMap<String,Integer> map = new HashMap<>();
        try {
            File file = new File(FileName);
            FileReader fr = new FileReader(file); // создаем объект FIleReader для объекта file
            BufferedReader reader = new BufferedReader(fr); // для построчного считывания создаем BufferedReader с существующего FileReader
            String line = "";

            while (line!=null) {
                line = reader.readLine(); //считываем первую строку
                if (line != null) {
                    String[] parts = line.split("="); //считанную строку делим по знаку равно, и результат кладем в массив строк (получается, он из двух элементов будет состоять)
                    int value = 0; //для числового значения (которое идет после "=")
                    if (parts[1].equals("?")) {
                        value = parts[0].length();
                    } else {
                        try {
                            value = Integer.parseInt(parts[1]);
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Неверный формат данных: " + line, e);
                        }
                    }
                    map.put(parts[0], value);
                }
            }
            fr.close();
        } catch (IOException e){
            System.out.println("File not found.");;
        }
        return map;
    }
}
