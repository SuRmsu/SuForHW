package newod.case1.dandiaozhan;

import java.util.LinkedList;
import java.util.Scanner;

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
    /*
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

     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        System.out.println(getResult(str));
    }

    public static String getResult(String str) {
        LinkedList<String> stack = new LinkedList<>();
        // idxs记录 { 出现的索引位置
        LinkedList<Integer> idxs = new LinkedList<>();
        StringBuilder repeat = new StringBuilder();
        str += " ";

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') { // 如果压栈遇到数字
                // 如果出现A13,或者{ABC}99这种情况，我们需要把多位数解析出来
                repeat.append(c);
                continue;
            }

            if (repeat.length() > 0) {
                int n = Integer.parseInt(repeat.toString());
                repeat = new StringBuilder();
                if ("}".equals(stack.getLast())) { // 如果此时栈顶是 } , 则需要将{,} 中间的内容整体重复repeat次
                    int left = idxs.removeLast();
                    stack.remove(left); // 去掉 {
                    stack.removeLast(); // 去掉 }
                    updateStack(stack, left, n); // 将 {,} 中间部分重复 repeat次后重新压栈
                } else { // 如果此时栈顶不是 }，则只需要将栈顶元素重复repeat次即可。
                    updateStack(stack, stack.size() - 1, n);
                }
            }

            // 记录 { 出现的索引位置
            if (c == '{') {
                idxs.addLast(stack.size());
            }

            // 数字外的字符都压入栈中，其中{,}需要再重复操作时删除
            stack.addLast(c + "");
        }

        StringBuilder sb = new StringBuilder();
        for (String c : stack) {
            sb.append(c);
        }
        return sb.toString().trim();
    }

    // 将stack，从left索引开始到最后的内容，弹栈，并整体重复repeat次后，再重新压入栈
    public static void updateStack(LinkedList<String> stack, int left, int repeat) {
        int count = stack.size() - left;

        // frag用于存储弹栈数据
        String[] frag = new String[count];

        while (count-- > 0) {
            frag[count] = stack.removeLast();
        }

        // 由于重复的是弹栈内容的整体，而不是每个，因此需要将弹栈内容合并
        StringBuilder sb = new StringBuilder();
        for (String s : frag) {
            sb.append(s);
        }

        // 将弹栈内容合并后重复repeat次，再重新压入栈中
        String fragment = sb.toString();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < repeat; i++) {
            ans.append(fragment);
        }

        stack.addLast(ans.toString());
    }

}
