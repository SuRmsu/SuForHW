package newod.case1.logic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 真正的密码
 * 题目描述
 * 在一行中输入一个字符串数组，如果其中一个字符串的所有以索引0开头的子串在数组中都有，那么这个字符串就是潜在密码，
 *
 * 在所有潜在密码中最长的是真正的密码，如果有多个长度相同的真正的密码，那么取字典序最大的为唯一的真正的密码，求唯一的真正的密码。
 *
 * 输入描述
 * 无
 *
 * 输出描述
 * 无
 * 解法：将输入的所有字符串先按照长度升序，如果长度相同，则按照字典序升序。
 *
 * 这样最后一个字符串必然就是长度最长，字典序最大的，我们从最后一个字符串str开始：
 */
public class OD10 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] arr = sc.nextLine().split(" ");
        System.out.println(getResult(arr));
    }

    public static String getResult(String[] arr) {
        HashSet<String> set = new HashSet<>(Arrays.asList(arr));

        Arrays.sort(arr, (a, b) -> a.length() != b.length() ? a.length() - b.length() : a.compareTo(b));

        outer:
        for (int i = arr.length - 1; i >= 0; i--) {
            String str = arr[i];

            for (int j = str.length() - 1; j >= 1; j--) {
                if (!set.contains(str.substring(0, j))) continue outer;
            }

            return str;
        }

        return "";

    }
}