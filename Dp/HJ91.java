package Dp;

import java.util.Scanner;

public class HJ91 {
    /**
     * 经典dp算法
     * @throws Exception
     */
    public void mySolution() throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt() + 1;
        int m = sc.nextInt() + 1;
        int[][] dp =new int[n][m];
        dp[0][0] = 0;
        for ( int i = 1; i < n ; i++){
            dp[i][0] =  1;
        }
        for ( int i = 1; i < m ; i++){
            dp[0][i] =  1;
        }
        for ( int i = 1; i < n; i++){
            for ( int j = 1; j < m; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        System.out.print(dp[n - 1][m - 1]);

    }
    /* 递归
        private static int cal(int m,int n){
            if(m==1 || n== 1){
                return m+n;
            }
            return cal(m-1,n)+cal(m,n-1);
        }
     */
}
