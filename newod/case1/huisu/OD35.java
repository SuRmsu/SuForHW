package newod.case1.huisu;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 挑选字符串
 * 题目描述
 * 给定a-z，26个英文字母小写字符串组成的字符串A和B，其中A可能存在重复字母，B不会存在重复字母，现从字符串A中按规则挑选一些字母可以组成字符串B。
 *
 * 挑选规则如下：
 *
 * 同一个位置的字母只能挑选一次，
 * 被挑选字母的相对先后顺序不能被改变，
 * 求最多可以同时从A中挑选多少组能组成B的字符串。
 * 输入描述
 * 输入为2行，第一行输入字符串a,第二行输入字符串b，行首行尾没有多余空格
 *
 * 输出描述
 * 输出一行，包含一个数字，表示最多可以同时从a中挑选多少组能组成b的字符串，行末没有多余空格
 *
 * 解法：
 *  注意：统计时，a的数量应该小于等于b的数量，c的数量应该小于等于a的数量，这样才能满足顺序要求：
 *
 * 被挑选字母的相对先后顺序不能被改变
 *
 * 因此上面这步统计到的c不应该被计入。
 * 由于c <= b <= a，因此有几个c，字符串a中就能挑选出几个字符串b。
 *
 * 上面算法只需要一次遍历，即可完成题解，时间复杂度O（n）
 *
 */
public class OD35 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String a = sc.next();
        String b = sc.next();

        System.out.println(getResult(a, b));
    }

    public static int getResult(String a, String b) {
        // idxs对象记录字符串b中每个字符的索引
        HashMap<Character, Integer> idxs = new HashMap<>();
        for (int i = 0; i < b.length(); i++) {
            Character c = b.charAt(i);
            idxs.put(c, i); // B不会存在重复字母
        }

        // count对象用于记录遍历字符串a每个字符串过程中，统计到的符合顺序要求的字符串b中字符出现次数
        int[] count = new int[b.length()];
        for (int i = 0; i < a.length(); i++) {
            Character c = a.charAt(i);

            if (idxs.containsKey(c)) {
                int idx = idxs.get(c);
                // 下面判断逻辑请看图解
                if (idx == 0 || count[idx] < count[idx - 1]) {
                    count[idx]++;
                }
            }
        }

        return count[count.length - 1];
    }
}
