

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        double count = 0;
        int avg = 0;
        int countInt = 0;
        for (int i = 0; i < times; i++) {
            int input = sc.nextInt();
            if (input > 0) {
                countInt++;
                avg += input;
            } else if (input < 0) {
                count++;
            }
            if (input == 0) {
                countInt += 0;

            }

        }
        double output;
        if (countInt != 0) {
            output = avg / countInt;
        } else {
            output = 0;
        }
        System.out.print(count + " " + String.format("%.1f", output));
    }
}



