/*
�������� ����������, ������� ����� ����������� � ������������ ��������� ������
� ������������ �������, ���������� ��������:
������� ��� �������� ������������ ������������� ���.

������� ������:
�������, ���, �������� - ������.
������������ - ������ ������� dd.mm.yyyy
������������� - ����� ����������� ����� ��� ��������������
��� - ������ ��������� f ��� m.

���������� ������ ���������� ���������� (���������) ������ �� ����������.
���� ���������� �� ��������� � ���������, ������� ��� ������,
���������� ��� � �������� ������������ ���������,
��� �� ���� ������ ��� ������ ������, ��� ���������.

���������� ������ ���������� ���������� ���������� ��������
� �������� �� ��� ��������� ���������.
���� ������� ������ �� ���������, ����� ������� ����������,
��������������� ���� ��������.
����� ������������ ��������� ���� ava ��� ������� ����.
���������� ������ ���� ��������� ����������,
������������ �������� ��������� � �����������, ��� ������ �������.

���� �� ������� � ���������� �����, ������ ��������� ���� � ���������,
������ �������. � ���� � ���� ������ ������ ���������� ���������� ������ ����:
<�������><���><��������><������������><�������������><���>

������������ ������ ���������� � ���� � ��� �� ����, � ��������� ������.

�� ������ ������� ���������� � ������.

��� ������������� �������� � �������-������� � ����,
���������� ������ ���� ��������� ����������,
������������ ������ ������ ��������� ������.
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

        // ����������� ������
        String correctInput = "������� ������ �������� 24.08.1967 79876543210 m";
        String correctInput2 = "������ ���������� ���������� m 79876543211 11.04.1968";
        String correctInput3 = "���������� �������� �������� 79876543212 08.08.1967 m";
        String correctInput4 = "������� ���� ��������� 09.01.1969 79876543213 m";
        String correctInput5 = "�������� ����� ��������� 09.01.1969 79876543214 f";

        String inCorrectInput = "������� �������� 09.01.1969 79876543213 m"; // ��� �����
        String inCorrectInput2 = "������� ������ 09.01.1969 79876543213 m"; // ��� ��������
        String inCorrectInput3 = "������ �������� 24.08.1967 79876543210 m"; // ��� �������
        String inCorrectInput4 = "������� ������ �������� 79876543210 m"; // ��� ���� ��������
        String inCorrectInput5 = "������� ������ �������� 24.08.1967 m"; // ��� ��������
        String inCorrectInput6 = "������� ������ �������� 24.08.1967 79876543210"; // ��� ����
        String inCorrectInput7 = "������� ������ ���� �������� 24.08.1967 79876543210 m"; // ��� �����
        String inCorrectInput8 = "������� ������ �������� 24.08.1967 79876543210 m f"; // ����������� ������ � corrextInput
        String inCorrectInput9 = "������� ������ �������� 24.08.1967 79876543210555 m"; // ������� ������ ������ ��� �����
        String inCorrectInput10 = "������� ������ �������� 35.08.1967 79876543210 m"; // ���� � ���� �������� ������, ��� �����
        String inCorrectInput11 = "������� ������ �������� 24.15.1967 79876543210 m"; // ����� � ���� �������� ������, ��� �����
        String inCorrectInput12 = "������� ������ �������� 24.08.1767 79876543210 m"; // ��� � ���� �������� ������, ��� �����
        String inCorrectInput13 = "������� ������ �������� 24.08.1967 79876543210 l"; // �������������� ���

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
        System.out.println("���� ������� ������� � " + fileName);
    }

    public static String[] userInput() throws DataFormatException, IOException {
        System.out.println("������� ������, ���������� � ��������� �������:\n" +
                "�������, ���, ��������, ���� ��������, ����� ��������, ���\n\n" +
                "����������:\n" +
                "������� � ����� ������� �������� ����� �����, ��� ��������. ��������: �����������-����\n" +
                "������� � ����� ����� �������� ����� �����, ��� ��������. �������� �����-������\n" +
                "�������� ������� �� ������ �����, �������� ���� (����, ����, ���� � ������) �������� ����� �����, �������� �����-����" +
                "���� �������� �������� � ������� dd.mm.yyyy,\n" +
                "dd - ���� ������ � ������� ����������� ����� ��������, 04,\n" +
                "mm - ����� � ������� ����������� �����, �������� 02,\n" +
                "yyyy - ��� � ������� �������������� �����, �������� 2000.\n\n" +
                "����� �������� �������� � ������� 79xxxxxxxxx,\n" +
                "���, xxxxxxxxxx - ��� ������ ���� ������ ��������.\n\n" +
                "��� �������� ������� ����� ��������� ����� f ��� m,\n" +
                "��� f - �������, m - �������.\n>>:");
        String userInput = scanner.nextLine();
        System.out.println(userInput);
        //String userInput = userInput;
        String[] correctUserInput = new String[6];
        String[] userInputArr = userInput.split(" ");

        while (userInputArr.length != 6) {
            throw new DataFormatException("��������, �������� ���������� �������� ������.\n");
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
                throw new DataFormatException("������ ��� ��������� ������.");
        }

        return correctUserInput;
    }

    public static String checkedSurname(String surname) {
        String resSurname = "";
        String answer = "";
        System.out.println("�� ���� �����������, ����� ������.");
        while (true) {
            System.out.print(surname + " - ��� �������?\n Y/y (��) ��� N/n (���) : ");
            answer = scanner.nextLine().toLowerCase();
            if (answer.equals("y")) {
                resSurname = surname;
                break;
            } else if (answer.equals("n")) {
                break;
            } else {
                System.out.println("�������� ������ ������.");
            }
        }
        return resSurname;
    }

    public static String checkedName(String name) {
        String resName = "";
        String answer = "";
        System.out.println("�� ���� �����������, ����� ������.");
        while (true) {
            System.out.print(name + " - ��� ���?\n Y/y (��) ��� N/n (���) : ");
            answer = scanner.nextLine().toLowerCase();
            if (answer.equals("y")) {
                resName = name;
                break;
            } else if (answer.equals("n")) {
                break;
            } else {
                System.out.println("�������� ������ ������.");
            }
        }
        return resName;
    }

    public static String checkedSecondname(String secondname) {
        String resSecondname = "";
        String answer = "";
        System.out.println("�� ���� �����������, ����� ������.");
        while (true) {
            System.out.print(secondname + " - ��� ��������?\n Y/y (��) ��� N/n (���) : ");
            answer = scanner.nextLine().toLowerCase();
            if (answer.equals("y")) {
                resSecondname = secondname;
                break;
            } else if (answer.equals("n")) {
                break;
            } else {
                System.out.println("�������� ������ ������.");
            }
        }
        return resSecondname;
    }

    public static String checkedBirthdate(String birthdate) throws DataFormatException {
        try {
            LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException ex) {
            throw new DataFormatException("�������� ������ ���� ��������", ex);
        }
        return birthdate;
    }

    public static String checkedPhoneNumber(String phoneNumber) throws DataFormatException {
        try {
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException ex) {
            throw new DataFormatException("�������� ������ ������ ��������", ex);
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
            throw new DataFormatException("�������� �������� ����.");
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