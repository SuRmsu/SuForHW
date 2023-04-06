package newod.case1.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 小明在学习二进制时，发现了一类不含 101的数，也就是：
 *
 * 将数字用二进制表示，不能出现 101 。
 * 现在给定一个整数区间 [l,r] ，请问这个区间包含了多少个不含 101 的数？
 *
 * 输入描述
 * 输入的唯一一行包含两个正整数 l， r（ 1 ≤ l ≤ r ≤ 10^9）。
 *
 * 输出描述
 * 输出的唯一一行包含一个整数，表示在 [l,r] 区间内一共有几个不含 101 的数。
 *
 * 解法：数位DP算法
 * 本质上还是回溯，但是通过记录相同位置满足条件的数组，可以进行大大剪枝
 * limit用来判断是否达到上线
 * 未达到上限，每一个的前面肯定都有0和1的两位数；只有5以后，即三位数字以后的数字才会有101
 * 101是第一个，1011是第二个，1101是第三个，dp[p][pre][ppre]，p表示第几位，pre是前一位为0.ppre是前第二位为1
 * 使用dp进行累计，具体实现逻辑可稍等
 */
public class OD6 {
    /*
    public static void main(String[] args) {
        int a = 1,b = 10;
        System.out.println(process(a, b));
    }
     暴力算法
    public static int process(int a, int b) {
        int count = 0;
        for (int i = a; i <= b ; i++) {
            if (Integer.toBinaryString(i).contains("101")){
                count++;
            }
        }
        return b - a - count + 1;
    }*/
    /**
     * 按位dp
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int L = sc.nextInt();
        int R = sc.nextInt();
        int count = digitSearch(R) - digitSearch(L - 1);
        System.out.println(count);
    }

    public static int digitSearch(int num) {
        Integer[] arr =
                Arrays.stream(Integer.toBinaryString(num).split(""))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);

        int[][][] f = new int[arr.length][2][2];

        return dfs(0, true, f, arr, 0, 0);
    }

    public static int dfs(int p, boolean limit, int[][][] f, Integer[] arr, int pre, int prepre) {
        if (p == arr.length) return 1;

        if (!limit && f[p][pre][prepre] != 0) return f[p][pre][prepre];

        int max = limit ? arr[p] : 1;
        int count = 0;

        for (int i = 0; i <= max; i++) {
            if (i == 1 && pre == 0 && prepre == 1) continue;
            count += dfs(p + 1, limit && i == max, f, arr, i, pre);
        }

        if (!limit) f[p][pre][prepre] = count;

        return count;
    }



}
