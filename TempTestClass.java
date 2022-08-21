import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int length = str.length();
        int output = 0;
        for (int i = 0; i < length - 2; i++) {
            output += (int)pow(16,i) * dataprocess(str.charAt(length - i - 1)) ;
        }
        System.out.println(output);
    }
    public static int dataprocess (char temp) {
        switch (temp) {
            case '0' :
                return 0;
            case '1' :
                return 1;
            case '2' :
                return 2;
            case '3' :
                return 3;
            case '4' :
                return 4;
            case '5' :
                return 5;
            case '6' :
                return 6;
            case '7' :
                return 7;
            case '8' :
                return 8;
            case '9' :
                return 9;
            case 'A' :
                return 10;
            case 'B' :
                return 11;
            case 'C' :
                return 12;
            case 'D' :
                return 13;
            case 'E' :
                return 14;
            case 'F' :
                return 15;
        }
        return 0;
    }

}

