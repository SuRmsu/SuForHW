package Important;

import java.util.Scanner;
import java.util.Stack;

//字符串转四则运算
public class HJ50 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.replace("{", "(");
        input = input.replace("[", "(");
        input = input.replace("}", ")");
        input = input.replace("]", ")");
        System.out.println(process(input));
    }

    public static int process(String input) {
        Stack<Integer> stack = new Stack<Integer>();
        int length = input.length();
        //char[] chs=input.toCharArray();
        int index = 0;
        char sign = '+';
        int tempNumber = 0;
        for (int i = 0; i < length; i++) {
            char tempInput = input.charAt(i);
            if (tempInput == ' ')continue;
            if (Character.isDigit(tempInput)) {
                tempNumber = tempNumber * 10 + tempInput - '0';
            }
            if (tempInput == '(') {
                int signCount = 1;
                int j = i + 1;
                while (signCount > 0) {
                    if (input.charAt(j) == ')') {
                        signCount--;
                    }
                    if (input.charAt(j) == '(') {
                        signCount++;
                    }
                    j++;
                }
                tempNumber = process(input.substring(i + 1, j - 1));
                i = j - 1;
            }
            if (!Character.isDigit(tempInput) || i == length - 1) {
                if (sign == '+') {
                    stack.push(tempNumber);
                } else if (sign == '-') {
                    stack.push(-1 * tempNumber);
                } else if (sign == '*') {
                    stack.push(stack.pop() * tempNumber);
                } else if (sign == '/') {
                    stack.push(stack.pop() / tempNumber);
                }
                sign = tempInput;
                tempNumber = 0;
            }
        }
        int output = 0;
        while (!stack.isEmpty()) {
            output += stack.pop();
        }
        return output;
    }
}
