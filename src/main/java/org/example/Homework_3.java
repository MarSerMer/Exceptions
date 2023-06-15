package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Homework_3 {
    public static void main(String[] args) throws IOException {

        // Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном
        // порядке, разделенные пробелом:
        //Фамилия Имя Отчество датарождения номертелефона пол
        //Форматы данных:
        //фамилия, имя, отчество - строки
        //датарождения - строка формата dd.mm.yyyy
        //номертелефона - целое беззнаковое число без форматирования
        //пол - символ латиницей f или m.
        //
        //Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

        String first_message = "Здравствуйте! \nВведите данные (пожалуйста, разделяйте их пробелом): \nфамилию, имя, отчество, \nдату рождения в формате дд.мм.гггг, \nномер телефона (только цифры), \nпол (буква f или m). \nПожалуйста, вводите данные корректно, чтобы избежать проблем в работе программы :) ";
        String[] sa = ask_for_datas(first_message);
        if (validate_array(sa)){
            HashMap<String,String> map = get_final_data(sa);
            if (validate_final_data(map)){
                for (String s : map.keySet()){
                    System.out.println(s + ": " + map.get(s));
                }
                write_to_file(map);
            } else {
                System.out.println("Проблемы с введенными данными. Пожалуйста, запустите программу заново...");
            }
        } else {
            System.out.println("Пожалуйста, обратите внимание на указанную выше информацию. Запустите программу снова.");
        }

    }
    public static String [] ask_for_datas(String message){
        System.out.println(message);
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String [] result = line.split(" ");
        if (result.length == 6){
            return result;
        } else {
            ask_for_datas("Ошибка ввода данных...их недостаточно, либо где-нибудь пропущен пробел. \n Введите данные корректно, пожалуйста!");
        }
        return result;
    }
    public static boolean validate_array(String[] sa) {
        boolean check_sex = false;
        for (int i = 0; i < 6; i++) {
            if (sa[i].equalsIgnoreCase("f") || sa[i].equalsIgnoreCase("m")) {
                check_sex = true;
                break;
            }
        }
        if (!check_sex) System.out.println("Неверно введен пол.");
        boolean check_phone = false;
        String reg_int = "\\d+";
        for (int i = 0; i < 6; i++) {
            if (sa[i].matches(reg_int)) {
                check_phone = true;
                break;
            }
        }
        if (!check_phone) System.out.println("Неверно введен номер телефона.");
        boolean check_birthday = false;
        String reg_b_day = "\\d{1,2}.\\d{2}.\\d{4}";
        for (int i = 0; i < 6; i++) {
            if (sa[i].matches(reg_b_day)) {
                check_birthday = true;
                break;
            }
        }
        if (!check_birthday) System.out.println("Неверно введен день рождения.");
        return (check_birthday && check_phone && check_sex);
    }
    public static HashMap<String,String> get_final_data(String[] sa){
        HashMap<String,String> result = new HashMap<>();
        // запишем пол
        for (int i = 0; i < 6; i++) {
            if (sa[i].equalsIgnoreCase("f") || sa[i].equalsIgnoreCase("m")) {
                result.put("Пол", sa[i]);
                sa[i] = "$";
                break;
            }
        }
        // запишем телефон
        String reg_int = "\\d+";
        for (int i = 0; i < 6; i++) {
            if (sa[i].matches(reg_int)) {
                result.put("Номер телефона", sa[i]);
                sa[i] = "$";
                break;
            }
        }
        // запишем дату рождения
        String reg_b_day = "\\d{1,2}.\\d{2}.\\d{4}";
        for (int i = 0; i < 6; i++) {
            if (sa[i].matches(reg_b_day)) {
                result.put("Дата рождения", sa[i]);
                sa[i] = "$";
                break;
            }
        }
        // итак, остался массив из шести строк, и три из них заменены на $.
        // Из них надо как-нибудь вытащить фамилию, имя, отчество.
        // Тут придется рассчитывать пока только
        // на некоторое количество обычных русских фамилий, но при необходимости
        // всегда можно добавить что-нибудь.
        boolean last_name = false;
        for (int i = 0; i < 6; i++){
            if (sa[i].endsWith("ко") || sa[i].endsWith("ов") || sa[i].endsWith("ова") ||
                    (sa[i].endsWith("ев") && !sa[i].equals("Лев")) || sa[i].endsWith("ева") ||
                    sa[i].endsWith("ын") || sa[i].endsWith("ына")){
                result.put("Фамилия", sa[i]);
                last_name = true;
                sa[i] = "$";
                break;
            }
        }
        // теперь отчество, принцип тот же
        boolean otchestvo = false;
        for (int i = 0; i < 6; i++){
            if (sa[i].endsWith("вна") || sa[i].endsWith("вич")){
                result.put("Отчество", sa[i]);
                otchestvo = true;
                sa[i] = "$";
                break;
            }
        }

        // вот по идее, если "шалость удалась", теперь в массиве осталось только имя и $
        // Если же "шалость не удалась", то нет
        if (last_name && otchestvo) {
            for (int i = 0; i < 6; i++) {
                if (!sa[i].equals("$")) {
                    result.put("Имя", sa[i]);
                    sa[i] = "$";
                    break;
                }
            }
        } else if (last_name) {
            for (int i = 0; i < 6; i++) {
                if (!sa[i].equals("$")) {
                    result.put("Имя", sa[i]);
                    sa[i] = "$";
                    break;
                }
            }
            for (int i = 0; i < 6; i++) {
                if (!sa[i].equals("$")) {
                    result.put("Отчество", sa[i]);
                    sa[i] = "$";
                    break;
                }
            }
        } else if (otchestvo) {
            for (int i = 0; i < 6; i++) {
                if (!sa[i].equals("$")) {
                    result.put("Фамилия", sa[i]);
                    sa[i] = "$";
                    break;
                }
            }
            for (int i = 0; i < 6; i++) {
                if (!sa[i].equals("$")) {
                    result.put("Имя", sa[i]);
                    sa[i] = "$";
                    break;
                }
            }
        } else {
            for (int i = 0; i < 6; i++) {
                if (!sa[i].equals("$")) {
                    result.put("Фамилия", sa[i]);
                    sa[i] = "$";
                    break;
                }
            }
            for (int i = 0; i < 6; i++) {
                if (!sa[i].equals("$")) {
                    result.put("Имя", sa[i]);
                    sa[i] = "$";
                    break;
                }
            }
            for (int i = 0; i < 6; i++) {
                if (!sa[i].equals("$")) {
                    result.put("Отчество", sa[i]);
                    sa[i] = "$";
                    break;
                }
            }
        }
        return result;
    }
    public static boolean validate_final_data(HashMap<String,String> datas){
        System.out.println("В итоге получены следующие данные: ");
        System.out.println("Фамилия: " + datas.get("Фамилия"));
        System.out.println("Имя: " + datas.get("Имя"));
        System.out.println("Отчество: " + datas.get("Отчество"));
        System.out.println("Дата рождения: " + datas.get("Дата рождения"));
        System.out.println("Номер телефона: " + datas.get("Номер телефона"));
        System.out.println("Пол: " + datas.get("Пол"));
        System.out.println("Всё ли верно? Если да, пожалуйста, введите 1. Если нет, введите 0: ");

        Scanner in = new Scanner(System.in);
        return in.nextLine().equals("1");
    }
    public static void write_to_file(HashMap<String,String> map) throws IOException {
        String path = "C:\\Users\\User\\Desktop\\Geek Brains\\14 ОБРАБОТКА ИСКЛЮЧЕНИЙ\\Exceptions\\src\\main\\java\\org\\example\\" + map.get("Фамилия") + ".txt";
        try {
            Writer fileWriter = new FileWriter(path, true);
            fileWriter.write(map.get("Имя") + " " + map.get("Отчество") + ", " + map.get("Дата рождения")+ ", " + map.get("Номер телефона") + ", " + map.get("Пол")+ "\n");
            fileWriter.close();
        } catch (IOException e){
            System.out.println("Что-то произошло с записью в файл..." + e.getMessage());
        }
    }
}
