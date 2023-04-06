package newod.case1.erfen;

import java.util.Scanner;

/**
 * 几何平均值最大子数组
 * 题目描述
 * 从一个长度为N的正数数组numbers中找出长度至少为L且几何平均值最大子数组，并输出其位置和大小。
 * （K个数的几何平均值为K个数的乘积的K次方根）
 *
 * 若有多个子数组的几何平均值均为最大值，则输出长度最小的子数组。
 *
 * 若有多个长度相同的子数组的几何平均值均为最大值，则输出最前面的子数组。
 *
 * 输入描述
 * 第一行输入为N、L
 *
 * N表示numbers的大小（1 ≤ N ≤ 100000）
 * L表示子数组的最小长度（1 ≤ L ≤ N）
 * 之后N行表示numbers中的N个数，每个一行（10^-9 ≤ numbers[i] ≤ 10^9）
 *
 * 输出描述
 * 输出子数组的位置（从0开始计数）和大小，中间用一个空格隔开。
 *
 * 备注
 * 用例保证除几何平均值为最大值的子数组外，其他子数组的几何平均值至少比最大值小10^-10倍
 *
 * 解法：几何平均值最大值肯定在单个元素的最小值和最大值之间
 * 首先，求出 0~i 子序列的所有元素乘积 fact
 *
 * 然后，求出 0~0 到 0~i-k 中所有子序列的最小乘积 min_pre_fact
 *
 * 最后，fact / min_pre_fact >= 1的话，说明midVal可能取小了
 *
 * 原理如下：有一个数组nums = [a1, a2, a3, ..., aN]，假设其几何平均值为avg，则有等式如下：
 *
 * N √ （a1 * a2 * a3 * ... * aN） == avg
 *
 * 再转换一下，如下：
 *
 * a1 * a2 * a3 * ... * aN == avg ^ N
 *
 * 再转换一下，如下：
 *
 * (a1 / avg) * (a2 / avg) * (a3 / avg) * ... * (aN / avg) == 1
 *
 * 如果avg取大了，则 (a1 / avg) * (a2 / avg) * (a3 / avg) * ... * (aN / avg)  < 1
 *
 * 如果avg取小了，则 (a1 / avg) * (a2 / avg) * (a3 / avg) * ... * (aN / avg)  > 1
 *
 * 其他子数组的几何平均值 至少比 最大几何平均值 小 10^-10 倍。'
 * 因此一旦二分的两个边界对应的两个几何平均值的距离小于最大几何平均值的10^-10倍数的值，那么就可以终止二分了
 */
public class OD34_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int l = sc.nextInt();

        double[] numbers = new double[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextDouble();
        }

        System.out.println(getResult(n, l, numbers));
    }

    public static String getResult(int n, int l, double[] numbers) {
        double minAvg = Integer.MAX_VALUE;
        double maxAvg = Integer.MIN_VALUE;
        for (double num : numbers) {
            minAvg = Math.min(num, minAvg);
            maxAvg = Math.max(num, maxAvg);
        }

        double diff = maxAvg / Math.pow(10, 10);

        int[] ans = new int[2];
        while (maxAvg - minAvg >= diff) {
            double midAvg = (minAvg + maxAvg) / 2;

            if (check(n, l, numbers, midAvg, ans)) {
                minAvg = midAvg;
            } else {
                maxAvg = midAvg;
            }
        }

        return ans[0] + " " + ans[1];
    }

    public static boolean check(int n, int l, double[] numbers, double avg, int[] ans) {
        double fact = 1;

        for (int i = 0; i < l; i++) {
            fact *= numbers[i] / avg;
        }

        if (fact >= 1) {
            ans[0] = 0;
            ans[1] = l;
            return true;
        }

        double pre_fact = 1;
        double min_pre_fact = Integer.MAX_VALUE;
        int min_pre_fact_end = 0;

        for (int i = l; i < n; i++) {
            fact *= numbers[i] / avg;
            pre_fact *= numbers[i - l] / avg;

            if (pre_fact < min_pre_fact) {
                min_pre_fact = pre_fact;
                min_pre_fact_end = i - l;
            }

            if (fact / min_pre_fact >= 1) {
                ans[0] = min_pre_fact_end + 1;
                ans[1] = i - min_pre_fact_end;
                return true;
            }
        }
        return false;
    }
}
