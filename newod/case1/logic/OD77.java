package newod.case1.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 回文字符串
 * 题目描述
 * 如果一个字符串正读和反渎都一样（大小写敏感），则称它为一个「回文串」，例如：
 *
 * leVel是一个「回文串」，因为它的正读和反读都是leVel；同理a也是「回文串」
 * art不是一个「回文串」，因为它的反读tra与正读不同
 * Level不是一个「回文串」，因为它的反读leveL与正读不同（因大小写敏感）
 * 给你一个仅包含大小写字母的字符串，请用这些字母构造出一个最长的回文串，若有多个最长的，返回其中字典序最小的回文串。
 *
 * 字符串中的每个位置的字母最多备用一次，也可以不用。
 *
 * 输入描述
 * 无
 *
 * 输出描述
 * 无
 *
 * 解法：
 * 对于剩余无法成对的字母，我们记录字典序最小的到mid中（题目要求返回其中字典序最小的回文串）
 *
 * 对于可以平均分配到左，右部分的字母，我们可以将可以成对的字母记录到ans数组，将ans字典序升序，拼接字符串即为回文串左边部分，ans字典序降序，拼接字符串即为回文串右边部分。
 *
 * 最终拼接：回文串左边部分 + 中间部分 + 回文串右边部分，即为题解。
 */
public class OD77 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(getResult(str));
    }

    private static String getResult(String str) {
        HashMap<Character, Integer> count = new HashMap<>();

        // 统计各字母个数
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        ArrayList<Character> half = new ArrayList<>();
        String mid = "";

        for (char c : count.keySet()) {
            int n = count.get(c);
            // 如果字母数量大于等于2，则可以成对出现，
            if (n >= 2) {
                for (int i = 0; i < n / 2; i++) half.add(c);
            }

            // 如果字母数量只有1个，或者字母数量大于2但是为奇数，则最后必然只剩单个字母可用，此时我们应该在这些无法成对的单字母中选择一个字典序最小的
            if (n % 2 != 0 && ("".equals(mid) || mid.compareTo(c + "") > 0)) {
                mid = c + "";
            }
        }

        half.sort((a, b) -> a - b);
        StringBuilder sb = new StringBuilder();
        for (Character c : half) sb.append(c);

        return sb + mid + sb.reverse(); // 回文串左边部分 + 中间单字母 + 回文串右边部分

    }
}