package String;

import java.util.Scanner;

public class HJ52 {
    public void notMySolution() throws Exception {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String a = sc.nextLine();
            String b = sc.nextLine();
            int[][] dp = new int[a.length() + 1][b.length() + 1];  //定义动规数组

            for (int i = 1; i <= a.length(); i++) {  // 初始化
                dp[i][0] = i;
            }
            for (int i = 1; i <= b.length(); i++) {  // 初始化
                dp[0][i] = i;
            }
            for (int i = 1; i <= a.length(); i++) {
                for (int j = 1; j <= b.length(); j++) {
                    if (a.charAt(i - 1) == b.charAt(j - 1)) {  //第一种情况
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {  //第二种情况
                        dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i - 1][j - 1] + 1, dp[i][j - 1] + 1));  //状态转移方程
                    }
                }
            }
            System.out.println(dp[a.length()][b.length()]);
        }
    }
}
