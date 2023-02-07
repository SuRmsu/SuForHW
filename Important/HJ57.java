package com.surm.questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HJ57 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num1 = br.readLine();
        String num2 = br.readLine();
        String num3 = null;
        if (num1.length() > num2.length()) {
            num3 = num1;
        } else {
            num3 = num2;
            num2 = num1;

        }
        StringBuffer sb1 = new StringBuffer(num3);
        StringBuffer sb2 = new StringBuffer(num2);
        num3 = sb1.reverse().toString();
        num2 = sb2.reverse().toString();
        StringBuilder sb3 = new StringBuilder();

        int count = 0;
        for (int i = 0; i < num3.length(); i++) {

            if ( i > num2.length() - 1) {
                sb3.append((num3.charAt(i) + count - '0') % 10);
                count = (num3.charAt(i)  + count  - '0') / 10;
            } else {
                int temp = (num3.charAt(i) + num2.charAt(i) - '0' - '0' + count) % 10;
                sb3.append(temp);
                count = (num3.charAt(i)  + count + num2.charAt(i) - '0' - '0') / 10;
            }

        }
        if (count == 1) {
            sb3.append(1);
        }
        System.out.println(sb3.reverse());

    }
}
