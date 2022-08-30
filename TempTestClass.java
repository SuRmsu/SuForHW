

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] storage = new int[36];
        Arrays.fill(storage, 0);
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if (temp >= 'a' && temp <= 'z') {
                storage[temp - 'a' + 10] += 1;
            } else {
                storage[temp - '0'] += 1;
            }
        }
        for (int i = 0; i < 36; i++) {
            int maxNum = Arrays.stream(storage).max().getAsInt();
            for (int j = 0; j < 36; j++) {
                if (storage[j] == maxNum && maxNum != 0) {
                    if (j >= 0 && j <= 9) {
                        System.out.print((char) (j + '0'));
                        storage[j] = 0;
                        break;
                    } else {
                        System.out.print((char) (j + 'a' - 10));
                        storage[j] = 0;
                        break;
                    }

                }
            }
        }


    }
}



