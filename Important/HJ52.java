package com.surm.questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//wrong
public class HJ52 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] source = br.readLine().toCharArray();
        char[] target = br.readLine().toCharArray();


        int[][] dp = new int[source.length + 1][target.length + 1];
        for (int i = 0; i <= source.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= target.length; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < source.length + 1; i++) {
            for (int j = 1; j < target.length + 1; j++) {
                if (source[i - 1] == target[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1);
                }
            }
        }
        System.out.println(dp[source.length][target.length]);


    }

    public static int min(int a, int b, int c) {
        a = Math.min(a, b);
        a = Math.min(a, c);
        return a;
    }
}
