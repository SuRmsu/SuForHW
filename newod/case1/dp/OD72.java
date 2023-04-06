package newod.case1.dp;

import java.util.Arrays;

/**
 * 最佳对手
 * 题目描述
 * 游戏里面，队伍通过匹配实力相近的对手进行对战。但是如果匹配的队伍实力相差太大，对于双方游戏体验都不会太好。
 *
 * 给定n个队伍的实力值，对其进行两两实力匹配，两支队伍实例差距在允许的最大差距d内，则可以匹配。
 * 要求在匹配队伍最多的情况下匹配出的各组实力差距的总和最小。
 *
 * 输入描述
 * 第一行，n，d。队伍个数n。允许的最大实力差距d。
 *
 * 2<=n <=50
 * 0<=d<=100
 * 第二行，n个队伍的实力值空格分割。
 *
 * 0<=各队伍实力值<=100
 * 输出描述
 * 匹配后，各组对战的实力差值的总和。若没有队伍可以匹配，则输出-1。
 *
 * 解法：
 * 先排序，然后动态规划：打家劫舍
 * 因此，我们需要定义一个二维dp。
 * dp[i][0] 记录： 0 ~ i 中，已选了几个实力差，即实力差数量
 * dp[i][1] 记录： 0 ~ i 中，已选的实力差的和
 * 我们需要首先保证dp[i][0]最大，即选与不选第 i 个实力差时，
 * 选择能够使dp[i][0]最大的，如果选与不选对应的dp[i][0]相同，
 * 则再选择能够使dp[i][1]最小的。
 */
public class OD72 {
    public static void main(String[] args) {
        int n = 6;
        int max = 30;
        int[] input = {81, 87, 47, 59, 81, 18};
//        int n = 6;
//        int max = 20;
//        int[] input = {81, 87, 47, 59, 81, 18};
//        int n = 5;
//        int max = 10;
//        int[] input = {1, 3, 4, 100, 108};
        Arrays.sort(input);
        int[] used = new int[input.length];

        // 从左往右找
        int count = 0;
        int l_nums = 0;
        for (int i = 0; i < n; i++) {
            if (used[i] == 1) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (used[j] == 1) {
                    continue;
                }
                if (input[j] - input[i] <= max) {
                    count += input[j] - input[i];
                    l_nums++;
                    used[j] = 1;
                    used[i] = 1;
                   break;
                }else {
                    break;
                }
            }
        }
        // 从右往左找
        int count_r = 0;
        int r_nums = 0;
        Arrays.fill(used,0);
        for (int i = n - 1; i > 0; i--) {
            if (used[i] == 1) {
                continue;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (used[j] == 1) {
                    continue;
                }
                if (input[i] - input[j] <= max) {
                    count_r += input[i] - input[j];
                    r_nums++;
                    used[j] = 1;
                    used[i] = 1;
                    break;
                }else {
                    break;
                }
            }
        }

        if (l_nums > r_nums){
            System.out.println(count);
        }else if (l_nums < r_nums){
            System.out.println(count_r);
        }else {
            if (count < count_r){
                System.out.println(count);
            } else {
                System.out.println(count_r);
            }
        }

    }
}
