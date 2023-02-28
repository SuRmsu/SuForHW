package newod.case1.logic;

import java.util.Arrays;

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
 */
public class OD30_2 {
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


}
