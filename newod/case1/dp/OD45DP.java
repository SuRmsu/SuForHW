package newod.case1.dp;

/**
 * 小明负责维护项目下的代码，需要查找出重复代码，用以支撑后续的代码优化，请你帮助小明找出重复的代码。
 * 重复代码查找方法：以字符串形式给定两行代码（字符串长度 1 < length <= 100，由英文字母、数字和空格组成），找出两行代码中的最长公共子串。
 * 注：如果不存在公共子串，返回空字符串
 * <p>
 * 输入描述
 * 输入的参数text1, text2分别表示两行代码
 * <p>
 * 输出描述
 * 输出任一最长公共子串
 * <p>
 * 解法：
 * 1. 双指针，但要注意边界条件，即两个字符串分别遍历到最后一个位置
 * 2. dp
 * [i][j] 表示串1的第i个位置和串2的第j个位置最对有多少相同
 */
public class OD45DP {
    public static void main(String[] args) {
        String b = "public_void_method";
        String a = "private_void_method";

//        String b = "hello123abc4";
//        String a = "hello123world";

//        String b = "hiweborlda";
//        String a = "hiwaaaorld";

        // dp[i][j] 字符串a的前i个字符和字符串b的前j个字符，最大的字串数量
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        // 初始化，其实都是多余步骤
        int maxI = 0;
        int max = 0;
        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= b.length(); i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    // 正斜上方表示两个字符串的上一个字符是否相同
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    // 记录最长的字串
                    max = Math.max(max, dp[i][j]);
                    // 记录最长字串对应的最后一个位置
                    maxI = max == dp[i][j] ? i : maxI;
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        System.out.println(a.substring(maxI-max,maxI));


    }

}
