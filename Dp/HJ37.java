package Dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ37 {
    /**
     * 第一次写出的动态优化题目，找规律
     *         dp[0] = 1;
     *         dp[1] = 1;
     *         dp[2] = dp[1] + dp[0];
     *         dp[3] = dp[2] + dp[1];
     *         dp[4] = dp[3] + dp[2];
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int dp[] = new int[num];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < num; i++ ){
            dp[i] = dp[i - 1] + dp [i - 2];
        }
        System.out.print(dp[num - 1]);

    }
}
