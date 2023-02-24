package newod.case1.dp;

/**
 * 题目描述
 * 定义字符串完全由 ‘A’ 和 ‘B’组成，当然也可以全是’A’或全是’B’。如果字符串从前往后都是以字典序排列的，那么我们称之为严格递增字符串。
 * 给出一个字符串s，允许修改字符串中的任意字符，即可以将任何的’A’修改成’B’，也可以将任何的’B’修改成’A’，
 * 求可以使s满足严格递增的最小修改次数。
 * <p>
 * 0 < s的长度 < 100000。
 * <p>
 * 输入描述
 * 输入一个字符串： “AABBA”
 * <p>
 * 输出描述
 * 输出：1
 * <p>
 * 解法：
 * 嗯解，解不了
 * dp：
 * 前i为a，剩余为b，遍历第一到最后
 * 总共所需的变化次数：i + 总共的 a -前i个已有a * 2；
 * dp[i]为前i个实际有多少个苹果
 *
 */
public class OD63 {
    public static void main(String[] args) {
//        String input = "AAABAAABBBBABB";
        String input = "AAAAA";
        // dp[i]：前i个有多少个a 可以不用数组，变量即可。
        int dp = 0;
        int max = 0;

        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'A') {
                count += 1;
            }
        }
        if (count == 0 || count == input.length() ){
            System.out.println(max);
            return;
        }

        max = input.length();

        for (int i = 0; i < input.length(); i++) {
            dp += input.charAt(i) == 'A' ? 1 : 0;
            int temp = process(dp, i, count);
            max = Math.min(max, temp);
        }
        System.out.println(max);

        //

    }

    // 推理出的公式 总共所需的变化次数：i + 总共的 a -前i个已有a * 2；
    public static int process(int dp, int index, int total) {
        return index + 1 + total - 2 * dp;
    }


}
