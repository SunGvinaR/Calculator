import java.io.IOException;
import java.util.Scanner;

public class Main {

    static NumberSystem firstDigit;
    static NumberSystem secondDigit;

    static int a;
    static int b;



    public static void main(String[] args) throws IOException {

        while (true) {
            Scanner sc = new Scanner(System.in);
            String text = sc.nextLine();
            System.out.println(calc(text));
        }
    }

    static  void Exceptions () throws IOException {
        throw new IOException();
    }

    public static String calc (String input) throws IOException {
        int result = 0;
        String[] splitText = input.split(" ");
        if (splitText.length != 3) Exceptions();

        check(splitText, 0);
        check(splitText, 2);
        String out = "";

        if (firstDigit == secondDigit) {
            switch (splitText[1]) {
                case "+" -> result = a + b;
                case "-" -> result = a - b;
                case "*" -> result = a * b;
                case "/" -> result = a / b;
                default -> Exceptions();
            }
            if (firstDigit == NumberSystem.RIM) {
                if (result > 0) out = RimResult(result);
                else Exceptions();
            } else {
                out = String.valueOf(result);
            }

        } else {
            Exceptions();
        }
        return out;
    }

    static String RimResult(int res) {
        int unit = res%10;
        int tens = (res%100)/10;
        int hundred = (res%1000)/100;

        String s_unit;
        String s_tens;
        String s_hundred;
        switch (unit) {
            case 1 -> s_unit = "I";
            case 2 -> s_unit = "II";
            case 3 -> s_unit = "III";
            case 4 -> s_unit = "IV";
            case 5 -> s_unit = "V";
            case 6 -> s_unit = "VI";
            case 7 -> s_unit = "VII";
            case 8 -> s_unit = "VIII";
            case 9 -> s_unit = "IX";
            default -> s_unit = "";
        }
        switch (tens){
            case 1 -> s_tens = "X";
            case 2 -> s_tens = "XX";
            case 3 -> s_tens = "XXX";
            case 4 -> s_tens = "XL";
            case 5 -> s_tens = "L";
            case 6 -> s_tens = "LX";
            case 7 -> s_tens = "LXX";
            case 8 -> s_tens = "LXXX";
            case 9 -> s_tens = "XC";
            default -> s_tens = "";
        }
        switch (hundred){
            case 1 -> s_hundred = "C";
            default -> s_hundred = "";
        }
        return s_hundred.concat(s_tens.concat(s_unit));
    }

    static void check(String[] splitText, int position) throws IOException {
        NumberSystem numberSystem;
        int digit = 0;
        if (splitText[position].matches("[1-9]0?")){
            numberSystem = NumberSystem.ARAB;
            digit = Integer.parseInt(splitText[position]);
            if (digit > 10) Exceptions();

        }
        else {
            numberSystem = NumberSystem.RIM;
            switch (splitText[position]) {
                case "I" -> digit = 1;
                case "II" -> digit = 2;
                case "III" -> digit = 3;
                case "IV" -> digit = 4;
                case "V" -> digit = 5;
                case "VI" -> digit = 6;
                case "VII" -> digit = 7;
                case "VIII" -> digit = 8;
                case "IX" -> digit = 9;
                case "X" -> digit = 10;
                default -> Exceptions();
            }
        }
        if (position == 0){
            firstDigit = numberSystem;
            a = digit;
        } else {
            secondDigit = numberSystem;
            b = digit;
        }
    }
}