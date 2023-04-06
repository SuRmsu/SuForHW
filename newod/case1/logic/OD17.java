package newod.case1.logic;

import java.math.BigInteger;

/**
 * 计算数组中心位置
 * 题目描述
 * 给你一个整数数组nums，请计算数组的中心位置，数组的中心位置是数组的一个下标，其左侧所有元素相乘的积等于右侧所有元素相乘的积。数组第一个元素的左侧积为1，最后一个元素的右侧积为1。
 * <p>
 * 如果数组有多个中心位置，应该返回最靠近左边的那一个，如果数组不存在中心位置，返回-1。
 * <p>
 * 输入描述
 * 输入只有一行，给出N个正整数用空格分隔：nums = 2 5 3 6 5 6
 * <p>
 * 1 <= nums.length <= 1024
 * <p>
 * 1 <= nums[i] <= 10
 * <p>
 * 输出描述
 * 输出：3
 * <p>
 * 解释：中心位置是3
 *
 * 解法：可能会超出最大值，需要使用BigInteger
 * 不用暴力解，计算出所有元素的积，然后处于左边的和当前的，就是右边的值了
 */

public class OD17 {
    public static void main(String[] args) {
//        int[] input = {2, 5, 3,2,6,2, 5, 6};
        int[] input = {1,2};
        BigInteger temp = BigInteger.valueOf(1);
        for (int i : input) {
            temp = temp.multiply(BigInteger.valueOf(i));
        }
        BigInteger left = BigInteger.valueOf(1);
        BigInteger right = temp.divide(BigInteger.valueOf(input[0])) ;
        if (left.equals(right)){
            System.out.println(0);
            return;
        }
        for (int i = 1; i < input.length; i++){
            left = left.multiply(BigInteger.valueOf(input[i - 1]));
            right = right.divide(BigInteger.valueOf(input[i])) ;
            if (left.equals(right)){
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}
