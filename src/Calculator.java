
import java.io.IOException;
import java.util.Scanner;

class Calc {

    static String[] romNums = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
    static String result;
    static boolean isRom;
    static int arabres;

    public static void main(String[] args) throws IOException, ArithmeticException {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Калькулятор умеет выполнять операции сложeния, вычитания, умножения и деления");
        System.out.println("с двумя арабскими или римскими  числами oт 0 до 10.");
        System.out.println("Введите выражение в формате : a + b или II+IV.");
        String input = scanner.nextLine();
        System.out.println("\n");

        System.out.println("input:\n" + input);
        System.out.println("\n");

        String input1 = input.replaceAll(" ", "").toUpperCase();
        System.out.println("output:\n" + calc(input1));


    }

    public static String calc(String input) throws IOException, ArithmeticException {

        int num1 = 0;
        int num2 = 0;


        String operation = mathOperation(input);

        String[] numberInInput = input.split("[+\\-*/]");
        if (numberInInput.length != 2)
            throw new IOException("Введено больше или меньше двух чисел. Или не поддерживаемая математическая операция.");
        if (numberInInput[0].equals("") && numberInInput[1].equals("")) throw new IOException("Не верный ввод. ");

        // провеляем, что числа римские, переводим в int
        if (isRom(numberInInput[0]) && isRom(numberInInput[1])) {
            num1 = moveToArab(numberInInput[0]);
            num2 = moveToArab(numberInInput[1]);
            isRom = true;

        } else if (!isRom(numberInInput[0]) && !isRom(numberInInput[1])) {// проверяем, что числа арабские, переводим в int
            try {
                num1 = Integer.parseInt(numberInInput[0]);
                num2 = Integer.parseInt(numberInInput[1]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Калькулятор умеет работать только с целыми числами!");
            }
            isRom = false;
        } else {
            throw new IOException("Числа должны быть либо римские либо арабские от 0 до 10.");
        }

        try {
            if (0 <= num1 && num2 <= 10) {
                arabres = switch (operation) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> num1 / num2;
                    default -> throw new IOException("Не правильная операция." + operation);

                };

            } else {
                throw new IOException("Числа должны быть от 0 до 10.");
            }
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            System.out.println("На ноль делить нельзя!");
        }

        if (isRom) {
            if (arabres < 0) {
                throw new IOException("Результат не может быть отрицательным числом.");
            } // переводим результат в римские
            result = moveToRom(arabres);
        } else {
            result = String.valueOf(arabres);
        }
        return result;
    }

    static String mathOperation(String input) {
        if (input.contains("+")) {
            return "+";
        } else if (input.contains("-")) {
            return "-";
        } else if (input.contains("*")) {
            return "*";
        } else if (input.contains("/")) {
            return "/";
        } else return null;
    }

    public static boolean isRom(String simbol) {
        for (String romNum : romNums) {
            if (simbol.equals(romNum)) {
                return true;
            }
        }
        return false;
    }

    public static int moveToArab(String rom) {
        for (int i = 0; i < romNums.length; i++) {
            if (rom.equals(romNums[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String moveToRom(int arab) {

        return (romNums[arab]);
    }
}

