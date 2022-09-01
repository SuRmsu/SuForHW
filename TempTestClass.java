

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            if (i == 0) {
                System.out.print("*");
                System.out.print(input.charAt(i));
            } else if (i == input.length() - 1) {
                System.out.print(input.charAt(i));
                System.out.print("*");
            } else if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                if (input.charAt(i - 1) >= '0' && input.charAt(i - 1) <= '9') {
                    System.out.print(input.charAt(i));
                } else {
                    System.out.print("*" + input.charAt(i));
                }

            } else {

                if (input.charAt(i - 1) >= '0' && input.charAt(i - 1) <= '9') {
                    System.out.print("*");
                    System.out.print(input.charAt(i));
                } else {
                    System.out.print(input.charAt(i));
                }
            }


        }
    }
}



