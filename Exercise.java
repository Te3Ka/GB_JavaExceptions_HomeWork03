/*
Написать приложение, которое будет запрашивать у пользователя следующие данные
в произвольном порядке, разделённые пробелом:
Фамилия Имя Отчество ДатаРождения НомерТелефона Пол.

Форматы данных:
Фамилия, Имя, Отчество - строки.
ДатаРождения - строка формата dd.mm.yyyy
НомерТелефона - целое беззнаковое число без форматирования
Пол - символ латиницей f или m.

Приложение должно попытаться распарсить (разделить) данные по количеству.
Если количество не совпадает с требуемым, вернуть код ошибки,
обработать его и показать пользователю сообщение,
что он ввел меньше или больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения
и выделить из них требуемые параметры.
Если форматы данных не совпадают, нужно бросить исключение,
соответствующее типу проблемы.
Можно использовать строенные типы ava или создать свои.
Исключение должно быть корректно обработано,
пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием,
равным фамилии. В него в одну строку должны записаться полученные данные вида:
<Фамилия><Имя><Отчество><ДатаРождения><НомерТелефона><Пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

Не забыть закрыть соединение с файлом.

При возникновении проблемы с чтением-записью в файл,
исключение должно быть корректно обработано,
пользователь должен увидет стектрейс ошибки.
*/

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Exercise {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, DataFormatException {

        // Проверочные данные
        String correctInput = "Белянин Андрей Олегович 24.08.1967 79876543210 m";
        String correctInput2 = "Сергей Васильевич Лукьяненко m 79876543211 11.04.1968";
        String correctInput3 = "Николаевич Васильев Владимир 79876543212 08.08.1967 m";
        String correctInput4 = "Белянин Олег Андреевич 09.01.1969 79876543213 m";
        String correctInput5 = "Белянина Ольга Андреевна 09.01.1969 79876543214 f";

        String inCorrectInput = "Белянин Олегович 09.01.1969 79876543213 m"; // нет имени
        String inCorrectInput2 = "Белянин Андрей 09.01.1969 79876543213 m"; // нет отчества
        String inCorrectInput3 = "Андрей Олегович 24.08.1967 79876543210 m"; // нет фамилии
        String inCorrectInput4 = "Белянин Андрей Олегович 79876543210 m"; // нет даты рождения
        String inCorrectInput5 = "Белянин Андрей Олегович 24.08.1967 m"; // нет телефона
        String inCorrectInput6 = "Белянин Андрей Олегович 24.08.1967 79876543210"; // нет пола
        String inCorrectInput7 = "Белянин Андрей Олег Олегович 24.08.1967 79876543210 m"; // два имени
        String inCorrectInput8 = "Белянин Андрей Олегович 24.08.1967 79876543210 m f"; // дублирующая запись с corrextInput
        String inCorrectInput9 = "Белянин Андрей Олегович 24.08.1967 79876543210555 m"; // телефон больше знаков чем нужно
        String inCorrectInput10 = "Белянин Андрей Олегович 35.08.1967 79876543210 m"; // день в дате рождения больше, чем нужно
        String inCorrectInput11 = "Белянин Андрей Олегович 24.15.1967 79876543210 m"; // месяц в дате рождения больше, чем нужно
        String inCorrectInput12 = "Белянин Андрей Олегович 24.08.1767 79876543210 m"; // год в дате рождения больше, чем нужно
        String inCorrectInput13 = "Белянин Андрей Олегович 24.08.1967 79876543210 l"; // несуществующий пол

        String[] userInputString = userInput();
        String writeString = "";
        for (int i = 0; i < userInputString.length; i++) {
            writeString += userInputString[i];
            writeString += " ";
        }
        writeString += "\n";

        String fileName = "saveData/" + userInputString[0] + ".txt";
        FileWriter fileWriter = new FileWriter(fileName, true);
        fileWriter.write(writeString);
        fileWriter.close();
        System.out.println("Файл успешно сохранён в " + fileName);
    }

    public static String[] userInput() throws DataFormatException, IOException {
        System.out.println("Введите данные, желательно в следующем порядке:\n" +
                "Фамилия, Имя, Отчество, Дата Рождения, Номер Телефона, Пол\n\n" +
                "Примечание:\n" +
                "Двойные и более фамилии вводятся через дефис, без пробелов. Например: Склодовская-Кюри\n" +
                "Двойные и более имена вводятся через дефис, без пробелов. Например Мария-Ремарк\n" +
                "Отчество состоит из одного слова, указания рода (кызы, угли, огли и прочие) вводятся через дефис, например Абдул-Угли" +
                "Дата Рождения вводится в формате dd.mm.yyyy,\n" +
                "dd - день месяца в формате двузначного числа например, 04,\n" +
                "mm - месяц в формате двузначного числа, например 02,\n" +
                "yyyy - год в формате четырёхзначного числа, например 2000.\n\n" +
                "Номер телефона вводится в формате 79xxxxxxxxx,\n" +
                "где, xxxxxxxxxx - это девять цифр номера телефона.\n\n" +
                "Пол вводится формате одной латинской буквы f или m,\n" +
                "где f - женский, m - мужской.\n>>:");
        String userInput = scanner.nextLine();
        System.out.println(userInput);
        //String userInput = userInput;
        String[] correctUserInput = new String[6];
        String[] userInputArr = userInput.split(" ");

        while (userInputArr.length != 6) {
            throw new DataFormatException("Возможно, Неверное количество введённых данных.\n");
        }

        for (String check : userInputArr) {
            if (correctUserInput[0] == null || correctUserInput[0].equals("")) {
                correctUserInput[0] = checkedSurname(check);
                if (correctUserInput[0] != null && !correctUserInput[0].isEmpty())
                    continue;
            }

            if (correctUserInput[1] == null || correctUserInput[1].equals("")) {
                correctUserInput[1] = checkedName(check);
                if (correctUserInput[1] != null && !correctUserInput[1].isEmpty())
                    continue;
            }

            if (correctUserInput[2] == null || correctUserInput[2].equals("")) {
                correctUserInput[2] = checkedSecondname(check);
                if (correctUserInput[2] != null && !correctUserInput[2].isEmpty())
                    continue;
            }

            if (correctUserInput[3] == null || correctUserInput[3].equals("")) {
                if (check.length() == 10 && check.contains(".")) {
                    correctUserInput[3] = checkedBirthdate(check);
                }
            }

            if (correctUserInput[4] == null || correctUserInput[4].equals("")) {
                if (check.length() == 11 && check.contains("79")) {
                    correctUserInput[4] = checkedPhoneNumber(check);
                }
            }

            if (correctUserInput[5] == null || correctUserInput[5].equals("")) {
                if (check.length() == 1) {
                    correctUserInput[5] = checkedGender(check);
                }
            }
        }

        for (String check : correctUserInput) {
            if (check == null || check.equals(""))
                throw new DataFormatException("Ошибка при обработке данных.");
        }

        return correctUserInput;
    }

    public static String checkedSurname(String surname) {
        String resSurname = "";
        String answer = "";
        System.out.println("Не могу разобраться, нужна помощь.");
        while (true) {
            System.out.print(surname + " - это фамилия?\n Y/y (да) или N/n (нет) : ");
            answer = scanner.nextLine().toLowerCase();
            if (answer.equals("y")) {
                resSurname = surname;
                break;
            } else if (answer.equals("n")) {
                break;
            } else {
                System.out.println("Неверный формат ответа.");
            }
        }
        return resSurname;
    }

    public static String checkedName(String name) {
        String resName = "";
        String answer = "";
        System.out.println("Не могу разобраться, нужна помощь.");
        while (true) {
            System.out.print(name + " - это имя?\n Y/y (да) или N/n (нет) : ");
            answer = scanner.nextLine().toLowerCase();
            if (answer.equals("y")) {
                resName = name;
                break;
            } else if (answer.equals("n")) {
                break;
            } else {
                System.out.println("Неверный формат ответа.");
            }
        }
        return resName;
    }

    public static String checkedSecondname(String secondname) {
        String resSecondname = "";
        String answer = "";
        System.out.println("Не могу разобраться, нужна помощь.");
        while (true) {
            System.out.print(secondname + " - это отчество?\n Y/y (да) или N/n (нет) : ");
            answer = scanner.nextLine().toLowerCase();
            if (answer.equals("y")) {
                resSecondname = secondname;
                break;
            } else if (answer.equals("n")) {
                break;
            } else {
                System.out.println("Неверный формат ответа.");
            }
        }
        return resSecondname;
    }

    public static String checkedBirthdate(String birthdate) throws DataFormatException {
        try {
            LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException ex) {
            throw new DataFormatException("Неверный формат даты рождения", ex);
        }
        return birthdate;
    }

    public static String checkedPhoneNumber(String phoneNumber) throws DataFormatException {
        try {
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException ex) {
            throw new DataFormatException("Неверный формат номера телефона", ex);
        }
        return phoneNumber.toString();
    }

    public static String checkedGender(String gender) throws DataFormatException {
        char charGender;
        gender = gender.toLowerCase();
        charGender = gender.charAt(0);
        if (charGender == 'f' || charGender == 'm') {
            return String.valueOf(charGender);
        } else {
            throw new DataFormatException("Неверное значение пола.");
        }
    }

    static class DataFormatException extends Exception {
        public DataFormatException(String message) {
            super(message);
        }

        public DataFormatException (String message, Throwable ex) {
            super(message, ex);
        }
    }
}