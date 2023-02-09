package com.surm.questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class HJ54 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        while (!input.isEmpty()) {
            Stack<Integer> stack = new Stack<>();
            input = input.replaceAll("[{\\[]", "(");
            input = input.replaceAll("[}\\]]", "(");
            input = input.replaceAll(" ", "(");
            System.out.println(process(input));
            input = br.readLine();
        }


    }

    public static int process(String input) {
        Stack<Integer> stack = new Stack<>();
        int temp = 0;
        char sign = '+';
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                continue;
            }
            if (Character.isDigit(input.charAt(i))) {
                temp = temp * 10 + input.charAt(i) - '0';
            }
            if (input.charAt(i) == '(') {
                int count = 1;
                int j = i + 1;
                while (count > 0) {
                    if (input.charAt(j) == '(') {
                        count++;
                    }
                    if (input.charAt(j) == ')') {
                        count--;
                    }
                    j++;
                }
                temp = (process(input.substring(i + 1, j - 1)));
                i = j - 1;
            }
            if (!Character.isDigit(input.charAt(i)) || i == input.length() - 1) {
                if (sign == '+') {
                    stack.push(temp);
                } else if (sign == '-') {
                    stack.push(temp * -1);
                } else if (sign == '*') {
                    stack.push(stack.pop() * temp);
                } else if (sign == '/') {
                    stack.push(stack.pop() / temp);
                }
                sign = input.charAt(i);
                temp = 0;
            }
        }
        for (Integer integer : stack) {
            temp += integer;
        }
        return temp;
    }
}
