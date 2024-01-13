import java.io.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        try {
            userData();
            System.out.println("Файл сохранен");
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void userData() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите фамилию, имя, отчество; " +
                "дату рождения, разделяя точкой; " +
                "номер телефона без разделителей; " +
                "пол: f или m, разделяя все пробелом");
        String dataUser = scanner.nextLine();

        String[] inputArray = dataUser.split(" ");

        // Проверка на кол-во данных
        if (inputArray.length < 6) {
            throw new IOException("Введено данных меньше, чем требуется");
        }
        if (inputArray.length > 6) {
            throw new IOException("Введено данных больше, чем требуется");
        }

        String lastname = inputArray[0];
        String name = inputArray[1];
        String patronymic = inputArray[2];
        String birthDate = inputArray[3];
        String phoneNumber = inputArray[4];
        String sex = inputArray[5];

        // Проверка на корректность ввода даты рождения
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        format.setLenient(false);
        try {
            Date birthDateStr = format.parse(birthDate);
        } catch (ParseException e) {
            throw new ParseException("Неверный формат даты рождения", 0);
        }

        // Проверка на корректность ввода пола
        if (!sex.equals("f") && !sex.equals("m")) {
            throw new IOException("Неверный формат пола");
        }

        // Запись введенных данных в файл и его сохранение
        String fileName = lastname + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("<" + lastname + "><" + name + "><"  + patronymic + "><"  +
                    birthDate + "><"   + phoneNumber + "><"  + sex + ">");
            writer.newLine();
        }
    }
}