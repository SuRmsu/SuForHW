package newod.case1.dp;

import java.util.Scanner;

/**
 * 称砝码
 * 题目描述
 * 现有n种砝码，重量互不相等，分别为 m1,m2,m3…mn ；
 * 每种砝码对应的数量为 x1,x2,x3...xn 。现在要用这些砝码去称物体的重量(放在同一侧)，问能称出多少种不同的重量。
 *
 * 输入描述
 * 对于每组测试数据：
 * 第一行：n --- 砝码的种数(范围[1,10])
 * 第二行：m1 m2 m3 ... mn --- 每种砝码的重量(范围[1,2000])
 * 第三行：x1 x2 x3 .... xn --- 每种砝码对应的数量(范围[1,10])
 *
 * 输出描述
 * 利用给定的砝码可以称出的不同的重量数
 *
 * 备注
 * 数据范围：每组输入数据满足：
 *
 * 1 ≤ n ≤ 10
 * 1 ≤ mi ≤ 2000
 * 1 ≤ xi ≤ 10
 *
 * 解法；多重背包
 * N种砝码  →  N种物品
 * 每种砝码的重量  → 每种物品的重量
 * 每种砝码的重量  → 每种物品的价值
 * 每种砝码的数量  → 每种物品的数量
 * 所有砝码的重量之和  → 背包承重
 */
public class OD79 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] m = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            m[i] = sc.nextInt();
        }

        int[] x = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            x[i] = sc.nextInt();
        }

        System.out.println(getResult(n, m, x));
    }

    public static int getResult(int n, int[] m, int[] x) {
        int bag = 0;
        for (int i = 1; i <= n; i++) bag += m[i] * x[i];

        boolean[] dp = new boolean[bag + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = bag; j >= m[i]; j--) {
                for (int k = 1; k <= x[i]; k++) {
                    if (j >= m[i] * k) {
                        if (dp[j - m[i] * k]) dp[j] = true;
                    }
                }
            }
        }

        int count = 0;
        for (boolean flag : dp) {
            if (flag) count++;
        }

        return count;
    }
}
