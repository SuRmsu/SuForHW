

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] surm = new char[26][20];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 20; j++) {
                surm[i][j] = '\n';
            }
        }
        char[] input = br.readLine().toCharArray();
        for (int i = 0; i < input.length; i++) {
            char temp = Character.toLowerCase(input[i]);
            if (temp >= 'a' && temp <= 'z') {
                for (int j = 0; j < 20; j++) {
                    if (surm[temp - 'a'][j] == '\n') {
                        surm[temp - 'a'][j] = input[i];
                        input[i] = '\n';
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < input.length; i++) {
            char temp = Character.toLowerCase(input[i]);
            if (temp == '\n') {
                int flag = 0;
                for (int j = 0; j < 26; j++) {
                    if (flag == 1) {
                        break;
                    }
                    for (int h = 0; h < 20; h++) {
                        if (surm[j][h] == '\n') {
                            continue;
                        } else {
                            input[i] = surm[j][h];
                            surm[j][h] = '\n';
                            flag = 1;
                            break;
                        }
                    }
                }
            }
        }
        System.out.print(String.valueOf(input));


    }
}



