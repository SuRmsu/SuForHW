package newod.case1.erfen;

import java.util.ArrayList;

/**
 * 开放日活动、取出尽量少的球
 * 题目描述
 * 某部门开展Family Day开放日活动，其中有个从桶里取球的游戏，游戏规则如下：
 * <p>
 * 有N个容量一样的小桶等距排开，
 * <p>
 * 且每个小桶都默认装了数量不等的小球，
 * <p>
 * 每个小桶装的小球数量记录在数组 bucketBallNums 中，
 * <p>
 * 游戏开始时，要求所有桶的小球总数不能超过SUM，
 * <p>
 * 如果小球总数超过SUM，则需对所有的小桶统一设置一个容量最大值 maxCapacity，
 * <p>
 * 并需将超过容量最大值的小球拿出来，直至小桶里的小球数量小于 maxCapacity;
 * <p>
 * 请您根据输入的数据，计算从每个小桶里拿出的小球数量。
 * <p>
 * 限制规则一：
 * <p>
 * 所有小桶的小球总和小于SUM，则无需设置容量值maxCapacity，并且无需从小桶中拿球出来，返回结果[]
 * <p>
 * 限制规则二：
 * <p>
 * 如果所有小桶的小球总和大于SUM，则需设置容量最大值maxCapacity，并且需从小桶中拿球出来，返回从每个小桶拿出的小球数量组成的数组；
 * <p>
 * 输入描述
 * 第一行输入2个正整数，数字之间使用空格隔开，其中第一个数字表示SUM，第二个数字表示bucketBallNums数组长度；
 * 第二行输入N个正整数，数字之间使用空格隔开，表示bucketBallNums的每一项；
 * <p>
 * 输出描述
 * 找到一个maxCapacity，来保证取出尽量少的球，并返回从每个小桶拿出的小球数量组成的数组。
 *
 * 解法：同OD70 二分
 * 注意，middle = （max+min) / 2，可能会遗漏，建议到这一步的时候左右都验证了一下
 */
public class OD41 {
    static int totalMax = 0;

    public static void main(String[] args) {
/*        int[] input = {2, 3, 2, 5, 5, 1, 4};
        int n = 7;
        int sum = 14;*/
        int[] input = {3,2};
        int n = 2;
        int sum = 6;
        int max = 0;
        int totalSum = 0;
        for (int i : input) {
            totalSum += i;
            max = Math.max(max, i);
        }
        if (totalSum <= sum) {
            System.out.println(new ArrayList<>());
            return;
        }
        // 最小值应该是sum进行除个数
        int min = sum / n;
        process(input,sum,min,max);
        ArrayList<Integer> output = new ArrayList<>();
        for (int i : input) {
            if (i > totalMax ){
                output.add(i - totalMax);
            } else {
                output.add(0);
            }
        }
        System.out.println(output);
    }

    public static void process(int[] input, int sum, int min, int max) {
        if (min == max) {
            if (isValid(input, sum, min)) {
                totalMax = Math.max(totalMax, min);
            }
            return;
        }
        int middle = (int) Math.ceil((double) (min + max) / 2);
        boolean flag = isValid(input, sum, middle);
        if (flag) {
            totalMax = Math.max(totalMax,middle);
        }
        if (middle == min) {
            return;
        }
        // 这一段也许完全不需要
        if (middle == max) {
            if (isValid(input,sum,max - 1)){
                totalMax = Math.max(totalMax, max - 1);
            }
            return;
        }
        if (flag) {
            process(input, sum, middle, max);
        } else {
            process(input, sum, min, middle);
        }


    }

    public static boolean isValid(int[] input, int sum, int current) {
        int temp = 0;
        for (int i : input) {
            if (i <= current) {
                temp += i;
            } else {
                temp += current;
            }
        }
        if (temp > sum) {
            return false;
        }
        return true;
    }
}
