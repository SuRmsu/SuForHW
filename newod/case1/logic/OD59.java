package newod.case1.logic;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 识图谱新词挖掘
 * 题目描述
 * 小华负责公司知识图谱产品，现在要通过新词挖掘完善知识图谱。
 *
 * 新词挖掘：给出一个待挖掘问题内容字符串Content和一个词的字符串word，找到content中所有word的新词。
 *
 * 新词：使用词word的字符排列形成的字符串。
 *
 * 请帮小华实现新词挖掘，返回发现的新词的数量。
 *
 * 输入描述
 * 第一行输入为待挖掘的文本内容content；
 *
 * 第二行输入为词word；
 *
 * 输出描述
 * 在content中找到的所有word的新词的数量。
 *
 * 备注
 * 0 ≤ content的长度 ≤ 10000000
 * 1 ≤ word的长度 ≤ 2000
 *
 * 解法：利用word和content截取子串的字母数量 比较 来代替  字典序排序后比较。
 *
 */
public class OD59 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String content = sc.next();
        String word = sc.next();

        System.out.println(getResult(content, word));
    }

    public static int getResult(String content, String word) {
        // 如果content长度小于word，则直接返回0
        if (content.length() < word.length()) {
            return 0;
        }

        // ans保存题解
        int ans = 0;

        // total记录word字母总数
        int total = word.length();

        // count统计word中各字母数量
        HashMap<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // 初始滑动窗口内部字母遍历
        for (int i = 0; i < word.length(); i++) {
            Character c = content.charAt(i);
            if (count.containsKey(c)) {
                if (count.get(c) > 0) {
                    total--;
                }
                count.put(c, count.get(c) - 1);
            }
        }

        if (total == 0) ans++;

        // 滑动窗口左指针的移动范围为 0~maxI
        int maxI = content.length() - word.length();
        for (int i = 1; i <= maxI; i++) {
            Character remove_c = content.charAt(i - 1);
            Character add_c = content.charAt(i + word.length() - 1);

            if (count.containsKey(remove_c)) {
                if (count.get(remove_c) >= 0) {
                    total++;
                }
                count.put(remove_c, count.get(remove_c) + 1);
            }

            if (count.containsKey(add_c)) {
                if (count.get(add_c) > 0) {
                    total--;
                }
                count.put(add_c, count.get(add_c) - 1);
            }

            if (total == 0) ans++;
        }
        return ans;
    }
}
