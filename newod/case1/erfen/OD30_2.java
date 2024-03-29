package newod.case1.erfen;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 农场施肥
 * 题目描述
 * 某农场主管理了一大片果园，fields[i]表示不同果林的面积，单位：m^2，现在要为所有的果林施肥且必须在n天之内完成，否则影响收成。小布是果林的工作人员，他每次选择一片果林进行施肥，且一片果林施肥完后当天不再进行施肥作业。
 * <p>
 * 假设施肥机的能效为k，单位：m^2/day，请问至少租赁能效 k 为多少的施肥机才能确保不影响收成？如果无法完成施肥任务，则返回-1。
 * <p>
 * 输入描述
 * 第一行输入为m和n，m表示fields中的元素个数，n表示施肥任务必须在n天内（含n天）完成；
 * <p>
 * 第二行输入为fields，fields[i]表示果林 i 的面积，单位：m^2
 * <p>
 * 输出描述
 * 对于每组数据，输出最小施肥机的能效 k ，无多余空格。
 * <p>
 * 备注
 * 1 ≤ fields.length ≤ 10^4
 * 1 ≤ n ≤ 10^9
 * 1 ≤ fields[i] ≤ 10^9
 * 用例
 *
 * 解法：
 * spend > n：说明k能效太低了，花费的时间超过了n，因此要提高k，即min = k，这样下次二分查找的值就会增大k
 * spend < n：说明k能效太高了，花费的时间少于n，因此要降低k，即max = k，这样下次二分查找的值就会减小k
 * spend === n：说明k能效刚刚好，花费的时间等于n，但是此时可能并非最优解，我们还需继续找更小的k，即max = k
 *
 * * 施肥任务可以少于n天时间完成，因此spend < n时的k也是符合要求的k。因此spend < n和spend == n的情况可以合并处理，
 * 即当 spend <= n时，说明k能效足够了，花费的时间小于等于n，但是此时可能并非最优解，我们还需继续找更小的k，即max = k
 */
public class OD30_2 {
    /*
    static int bigMin = Integer.MAX_VALUE;

    public static void main(String[] args) {
//        int[] fields = {2, 3, 4};
//        int k = 1;
        int[] fields = {5, 7, 9, 15, 10};
        int k = 7;
//        int[] fields = {9};
//        int k = 10;
        Arrays.sort(fields);
        process(fields, 1, fields[fields.length - 1], k);

        if (isValid(fields, k, bigMin)) {
            System.out.println(bigMin);
        } else {
            System.out.println(-1);
        }
    }

    public static void process(int[] fields, int min, int max, int k) {
        if (min == max) {
            return;
        }

        int middle = (min + max) / 2;
        boolean flag = isValid(fields, k, middle);
        if (middle == min) {
            if (flag == true) {
                bigMin = Math.min(bigMin, middle);
            } else {
                bigMin = Math.min(bigMin, max);
            }
            return;
        }

        if (flag) {
            bigMin = Math.min(bigMin, middle);
            process(fields, min, middle, k);
        } else {
            process(fields, middle, max, k);
        }

    }

    public static boolean isValid(int[] fields, int k, int n) {
        int days = 0;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] <= n) {
                days++;
            } else {
                days += (int) Math.ceil((double) fields[i] / n);
            }
        }
        if (days <= k) {
            return true;
        }
        return false;
    }

     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[] fields = new int[m];
        for (int i = 0; i < m; i++) {
            fields[i] = sc.nextInt();
        }

        System.out.println(getResult(n, fields));
    }

    /**
     * @param n 表示施肥任务必须在n天内（含n天）
     * @param fields fields[i]表示果林 i 的面积
     * @return 最小施肥机的能效 k
     */
    public static int getResult(int n, int[] fields) {
        double min = 1; // 最小果林面积
        double max = Arrays.stream(fields).max().orElse(0); // 最大果林面积

        int ans = -1;

        // 我们需要找min,max中间值作为k效率，如果min,max间距小于1，则没有中间值，循环结束
        while (min <= max) {
            int k = (int) Math.ceil((min + max) / 2);

            int res = check(k, n, fields);

            if (res > 0) min = k + 1; // k的效率太低，导致花费的时间超过了n天，因此要提高k效率
            else {
                ans = k; // k的效率太高或刚好，花费的时间<=n天，则此时k效率就是一个题解，但可能不是最优解
                max = k - 1; // 继续尝试找更小的k
            }
        }

        return ans;
    }

    /**
     * @param k 能效k
     * @param n 指定天数
     * @param fields 所有果林面积
     * @return 基于能效k需要spend天施肥完所有果林，返回spend - n
     */
    public static int check(int k, int n, int[] fields) {
        int spend = 0;
        for (int field : fields) {
            if (k >= field) spend++; // k效率比field果林面积大，则field果林只需要一天
            else spend += Math.ceil(field / (double) k); // k效率比field果林面积小，则需要Math.ceil(field / k)天
        }

        return spend - n;
    }


}
