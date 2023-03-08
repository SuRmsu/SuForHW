package newod.case1.logic;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 单词倒序
 * 题目描述
 * 输入单行英文句子，里面包含英文字母，空格以及,.?三种标点符号，请将句子内每个单词进行倒序，并输出倒序后的语句。
 *
 * 输入描述
 * 输入字符串S，S的长度 1 ≤ N ≤ 100
 *
 * 输出描述
 * 输出倒序后的字符串
 *
 * 备注
 * 标点符号左右的空格 ≥ 0，单词间空格＞0
 *
 * 解法：
 * 从左到右遍历每一个字符，如果字符是 , . ? 或者空格，则看成一个分界符，将分界符之间的单词片段进行倒序
 * 用双指针手动倒序
 */
public class OD64 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        System.out.println(getResult(str));
    }

    public static String getResult(String str) {
        ArrayList<Integer> idxs = new ArrayList<>();

        idxs.add(-1);
        for (int i = 0; i < str.length(); i++) {
            if (",.? ".indexOf(str.charAt(i)) != -1) {
                idxs.add(i);
            }
        }
        idxs.add(str.length());

        char[] chars = str.toCharArray();

        for (int i = 0; i < idxs.size() - 1; i++) {
            int l = idxs.get(i) + 1;
            int r = idxs.get(i + 1) - 1;

            while (l < r) {
                char tmp = chars[l];
                chars[l] = chars[r];
                chars[r] = tmp;
                l++;
                r--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }

}
