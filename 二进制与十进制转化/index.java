public class BinaryDecimalConversion {
    
    // 二进制转十进制
    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        int power = 0;
        for (int i = binary.length()-1; i >= 0; i--) {
            int digit = binary.charAt(i) - '0';
            decimal += digit * Math.pow(2, power);
            power++;
        }
        return decimal;
    }

    // 十进制转二进制
    public static String decimalToBinary(int decimal) {
        StringBuilder binary = new StringBuilder();
        if (decimal == 0) {
            binary.append(0);
        } else {
            while (decimal > 0) {
                int digit = decimal % 2;
                binary.insert(0, digit);
                decimal /= 2;
            }
        }
        return binary.toString();
    }

    public static void main(String[] args) {
        String binary = "1010";
        int decimal = binaryToDecimal(binary);
        System.out.println(binary + " in decimal is: " + decimal);

        int number = 42;
        String binaryNumber = decimalToBinary(number);
        System.out.println(number + " in binary is: " + binaryNumber);
    }
}