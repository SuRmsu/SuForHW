package newod.case1.logic;

import java.util.Stack;

/**
 * 简单的解压缩算法
 * 题目描述
 * 现需要实现一种算法，能将一组压缩字符串还原成原始字符串，还原规则如下：
 * <p>
 * 1、字符后面加数字N，表示重复字符N次。例如：压缩内容为A3，表示原始字符串为AAA。
 * 2、花括号中的字符串加数字N，表示花括号中的字符重复N次。例如压缩内容为{AB}3，表示原始字符串为ABABAB。
 * 3、字符加N和花括号后面加N，支持任意的嵌套，包括互相嵌套，例如：压缩内容可以{A3B1{C}3}3
 * <p>
 * 输入描述
 * 输入一行压缩后的字符串
 * <p>
 * 输出描述
 * 输出压缩前的字符串
 * <p>
 * 备注
 * 输入保证，数字不会为0，花括号中的内容不会为空，保证输入的都是合法有效的压缩字符串
 * 输入输出字符串区分大小写
 * 输入的字符串长度范围为[1, 10000]
 * 输出的字符串长度范围为[1, 100000]
 * 数字N范围为[1, 10000]
 *
 * 解法：
 * 从左往右，先处理括号里的；但要注意嵌套，因此将}后面的数字也带着处理，处理完每个括号
 * 内部的数据后，将其合成为一个整体。
 *
 * 待从右往左进行优化
 */
public class OD15_2 {
    public static Stack<String> stack = new Stack<String>();
    public static StringBuilder newSb = new StringBuilder();
    public static void main(String[] args) {
//        String sb = new String("{A3{B}2}2C3");
        String sb = new String("{A3B1{C}3}3");
        process(sb);
        System.out.println(stack.pop());

    }

    public static void process(String sb) {
        for (int i = 0; i < sb.length(); i++) {
            // 先算括号里面的
            if (sb.charAt(i) == '{') {
                int count = 1;
                int j = i + 1;
                while (count != 0) {
                    if (sb.charAt(j) == '{') {
                        count++;

                    } else if (sb.charAt(j) == '}') {
                        count--;
                    }
                    j++;
                }
                // 保存处理后的字符
                process(sb.substring(i + 1,j + 1));
                i = j ;
            } else if (Character.isLetter(sb.charAt(i))) {
                stack.push(String.valueOf(sb.charAt(i)));
            } else if (Character.isDigit(sb.charAt(i))) {
                String tempChar = stack.pop();
                StringBuilder tempSb = new StringBuilder();
                for (int k = 0; k < sb.charAt(i) - '0'; k++) {
                    tempSb.append(tempChar);
                }
                stack.push(tempSb.toString());
            } else if (sb.charAt(i) == '}') {

            }
        }
        Stack<String> newStack = new Stack<String>();
        while (!stack.isEmpty()) {
            newStack.add(stack.pop());
        }
        while (!newStack.isEmpty()) {
            newSb.append(newStack.pop());
        }
        stack.push(newSb.toString());
        newSb = new StringBuilder();

    }
}
