package Important;

import java.util.Scanner;

public class HJ61 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            int[][] dp = new int[a + 1][b + 1];
            for (int i = 0; i < b + 1; i++) {
                dp[0][i] = 1;
            }
            for (int i = 1; i < a + 1; i++) {
                for (int j = 1; j < b + 1; j++) {
                    if (i < j) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        dp[i][j] = dp[i][j - 1] + dp[i - j][j];
                    }
                }
            }
            System.out.println(dp[a][b]);
        }
    }
}
