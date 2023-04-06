package newod.case1.logic;

import java.util.Scanner;

/**
 * 冗余覆盖
 * 题目描述
 * 给定两个字符串s1和s2和正整数K，其中s1长度为n1，s2长度为n2，在s2中选一个子串，满足:
 *
 * 该子串长度为n1+k
 * 该子串中包含s1中全部字母，
 * 该子串每个字母出现次数不小于s1中对应的字母，
 * 我们称s2以长度k冗余覆盖s1，给定s1，s2，k，求最左侧的s2以长度k冗余覆盖s1的子串的首个元素的下标，如果没有返回-1。
 *
 * 输入描述
 * 输入三行，第一行为s1，第二行为s2，第三行为k，s1和s2只包含小写字母
 *
 * 输出描述
 * 最左侧的s2以长度k冗余覆盖s1的子串首个元素下标，如果没有返回-1。
 *
 * 解法：利用s1中所有字符都是小写字母的特点，使用128长度的int数组，因为小写字母的ASCII码范围是97~122，因此可以对应到0~127的int数组的索引上。
 * total存出现的所有字符的总数，数组存每个字符出现的次数
 * 只有数组-- 且数组>0 的时候，total才--
 */
public class OD33 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.next();
        String s2 = sc.next();
        int k = sc.nextInt();

        System.out.println(getResult(s1, s2, k));
    }

    public static int getResult(String s1, String s2, int k) {
        // 在s2中选一个子串，满足:该子串长度为 n1+k
        int n1 = s1.length();
        int n2 = s2.length();
        if (n2 < n1 + k) return -1;

        // 由于字符串s1中都是小写字母，因此每个字母的ASCII码范围是97~122，因此这里初始化128长度数组来作为统计容器
        int[] count = new int[128];

        // 统计s1中所有每个字符出现的次数到count中
        for (int i = 0; i < n1; i++) {
            int c = s1.charAt(i);
            count[c]++;
        }

        // s1字符总数
        int total = n1;

        // 滑动窗口的左边界从0开始，最大maxI；滑动窗口长度len
        int maxI = n2 - n1 - k;
        // s2子串长度
        int len = n1 + k;

        // 统计s2的0~len范围内出现的s1中字符的次数
        for (int j = 0; j < len; j++) {
            int c = s2.charAt(j);

            // 如果s2的0~len范围内的字符c，在count[c]存在，则说明c是s1内有的字符，
            // 此时我们需要count[c]--，如果自减之前，count[c] > 0，则自减时，total也应该--,否则total不--
            if (count[c]-- > 0) {
                total--;
            }

            // 如果total为0了，则说明在s2的0~len范围内找到了所有s1中字符
            if (total == 0) {
                // 此时可以直接返回起始索引0
                return 0;
            }
        }

        // 下面是从左边界1开始的滑动窗口，利用差异思想，避免重复部分求解
        for (int i = 1; i <= maxI; i++) {
            // 滑动窗口右移一格后，失去了s2[i - 1]，得到了s2[i - 1 + len]，其余部分不变
            int remove = s2.charAt(i - 1);
            int add = s2.charAt(i - 1 + len);

            if (count[remove]++ >= 0) {
                total++;
            }

            if (count[add]-- > 0) {
                total--;
            }

            if (total == 0) {
                return i;
            }
        }
        return -1;
    }
}
