

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt() + 1;
        int m = sc.nextInt() + 1;
        int[][] dp =new int[n][m];
        dp[0][0] = 0;
        for ( int i = 1; i < n ; i++){
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for ( int i = 1; i < m ; i++){
            dp[0][i] = dp[0][i - 1] + 1;
        }
        for ( int i = 1; i < n; i++){
            for ( int j = 1; j < m; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        System.out.print(dp[n - 1][m - 1]);

    }
}



