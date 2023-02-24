package newod.case1.dp;

/**
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 */
public class LC718 {
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
