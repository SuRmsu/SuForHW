package Others;

import java.util.Scanner;

public class HJ60 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int input = sc.nextInt();
            for (int i = input / 2; i > 1; i--) {
                if (isSpecificNum(i) && isSpecificNum(input - i)) {
                    System.out.println(i);
                    System.out.println(input - i);
                    break;
                }
            }
        }
    }

    public static boolean isSpecificNum(int input) {
        for (int i = 2; i <= Math.sqrt(input); i++) {
            if (input % i == 0) {
                return false;
            }
        }
        return true;
    }

}
