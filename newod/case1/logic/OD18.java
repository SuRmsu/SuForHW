package newod.case1.logic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 最长的密码
 * 题目描述
 * 小王在进行游戏大闯关，有一个关卡需要输入一个密码才能通过，密码获得的条件如下：
 *
 * 在一个密码本中，每一页都有一个由26个小写字母组成的若干位密码，每一页的密码不同，需要从这个密码本中寻找这样一个最长的密码，
 *
 * 从它的末尾开始依次去掉一位得到的新密码也在密码本中存在。
 *
 * 请输出符合要求的密码，如果有多个符合要求的密码，则返回字典序最大的密码。
 *
 * 若没有符合要求的密码，则返回空字符串。
 *
 * 输入描述
 * 密码本由一个字符串数组组成，不同元素之间使用空格隔开，每一个元素代表密码本每一页的密码。
 *
 * 输出描述
 * 一个字符串
 *
 * 解法：按字符串长度和字典序排序
 * 再用HashSet存储排序后的字符串数组
 */
public class OD18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] arr = sc.nextLine().split(" ");
        System.out.println(getResult(arr));
    }

    public static String getResult(String[] arr) {
        Arrays.sort(arr, (a, b) -> a.length() != b.length() ? a.length() - b.length() : a.compareTo(b));

        LinkedList<String> link = new LinkedList<>(Arrays.asList(arr));
        HashSet<String> set = new HashSet<>(link);

        while (link.size() > 0) {
            String str = link.removeLast();
            int end = str.length() - 1;

            while (set.contains(str.substring(0, end))) {
                if (end == 1) {
                    return str;
                } else {
                    end--;
                }
            }
        }

        return "";
    }

}
