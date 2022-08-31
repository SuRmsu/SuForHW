package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ32 {
    /**
     * 两种可能aba和aa，分别进行讨论，以下代码可以优化。
     * @throws Exception
     */
    public void mySolution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int length = input.length();
        int count = 1;
        int maxCount = 0;
        //aba的形式
        for ( int i = 1,j = 0; i < length - 1; i++ ){
            while (  (i - 1 - j) >= 0 && (i + 1 + j) < length &&
                    input.charAt(i - 1 - j) == input.charAt( i + j + 1)){
                count += 2;
                j++;
            }
            maxCount = Math.max(count,maxCount);
            count = 1;
            j = 0;
        }
        count = 0;
        //aa的形式
        for ( int i = 1,j = 0; i < length; i++ ){
            while (  (i - 1 - j) >= 0 && (i + j) < length &&
                    input.charAt(i - 1 - j) == input.charAt( i + j )){
                count += 2;
                j++;
            }
            maxCount = Math.max(count,maxCount);
            count = 0;
            j = 0;
        }
        System.out.print(maxCount);
    }

    /*同暴力算法，但是代码更加优美 可以借鉴
        private static int solution(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            // ABA型
            int len1 = longest(s, i, i);
            // ABBA型
            int len2 = longest(s, i, i + 1);
            res = Math.max(res, len1 > len2 ? len1 : len2);
        }
        return res;
    }

    private static int longest(String s, int l, int r) {
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
     */

    /**
     * 动态规划，太优美了
     * @throws Exception
     */
    public void secondSolution() throws Exception{
        String s = "inputData";
        int len = s.length();
        // 状态：对比的两个字符索引起始和终止索引位置
        // 定义: 字符串s的i到j字符组成的子串是否为回文子串
        boolean[][] dp = new boolean[len][len];
        int res = 0;
        // base case
        for(int i = 0; i < len - 1; i++) {
            dp[i][i] = true;
        }

        for(int r = 1; r < len; r++) {
            for(int l = 0; l < r; l++) {
                // 状态转移：如果左右两字符相等,同时[l+1...r-1]范围内的字符是回文子串
                // 则 [l...r] 也是回文子串
                if(s.charAt(l) == s.charAt(r) && (r-l <= 2 || dp[l+1][r-1])) {
                    dp[l][r] = true;
                    // 不断更新最大长度
                    res = Math.max(res, r - l + 1);
                }
            }
        }
        System.out.println(res);

    }

}
