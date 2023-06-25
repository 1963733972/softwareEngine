import java.util.*;

public class HighPrecisionArithmetic {

    // 高精度加法
    public static String add(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j--) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }

    // 高精度乘法
    public static String multiply(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();
        int[] pos = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int digit1 = num1.charAt(i) - '0';
                int digit2 = num2.charAt(j) - '0';
                int product = digit1 * digit2;
                int p1 = i + j, p2 = i + j + 1;
                int sum = product + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            if (!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    // 高精度除法
    public static String divide(String dividend, String divisor) {
        if (divisor.equals("0")) {
            throw new ArithmeticException("/ by zero");
        }

        if (dividend.equals("0")) {
            return "0";
        }

        if (dividend.equals(divisor)) {
            return "1";
        }

        StringBuilder quotient = new StringBuilder();
        int n = dividend.length();
        int m = divisor.length();

        String remainder = dividend.substring(0, m);
        int i = m - 1;
        while (i < n) {
            int count = 0;
            while (compare(remainder, divisor) >= 0) {
                remainder = subtract(remainder, divisor);
                count++;
            }
            quotient.append(count);
            i++;
            if (i < n) {
                remainder += dividend.charAt(i);
            }
        }

        while (quotient.length() > 0 && quotient.charAt(0) == '0') {
            quotient.deleteCharAt(0);
        }

        return quotient.length() == 0 ? "0" : quotient.toString();
    }

    // 高精度减法
    private static String subtract(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int n1 = num1.length() - 1;
        int n2 = num2.length() - 1;
        int borrow = 0;

        while (n1 >= 0 || n2 >= 0) {
            int digit1 = n1 >= 0 ? num1.charAt(n1--) - '0' : 0;
            int digit2 = n2 >= 0 ? num2.charAt(n2--) - '0' : 0;
            int diff = digit1 - digit2 - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            sb.append(diff);
        }

        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '0') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.reverse().toString();
    }

    // 高精度比较大小
    private static int compare(String num1, String num2) {
        if (num1.length() != num2.length()) {
            return num1.length() - num2.length();
        }
        for (int i = 0; i < num1.length(); i++) {
            if (num1.charAt(i) != num2.charAt(i)) {
                return num1.charAt(i) - num2.charAt(i);
            }
        }
        return 0;
    }