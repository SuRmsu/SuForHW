package newod.case1.logic;

import java.util.Arrays;

/**
 * 新学校选址
 * 题目描述
 * 为了解新学期学生暴涨的问题,小乐村要建立所新学校，
 * 考虑到学生上学安全问题,需要所有学生家到学校的距离最短。
 * 假设学校和所有学生家都走在一条直线之上,请问学校建立在什么位置，
 * 能使得到学校到各个学生家的距离和最短。
 * <p>
 * 输入描述
 * 第一行: 整数 n 取值范围 [1 ,1000 ],表示有 n户家庭。
 * 第二行: 一组整数 m 取值范围 [0, 10000 ] ，表示每户家庭的位置，所有家庭的位置都不相同。
 * <p>
 * 输出描述
 * 一个整数，确定的学校的位置。
 * 如果有多个位置，则输出最小的
 * <p>
 * 解法：中位数定理
 * 中位数定理是一个数学概念，它指的是在一组数据中，按顺序排列的居于中间位置的数，代表了数据的集中趋势1。它有一个性质，就是所有数据与中位数的绝对差之和最小
 */
public class OD23 {
    public static void main(String[] args) {
//        int[] input = {0, 10, 20, 30, 40};
//        int[] input = {20};
        int[] input = {0, 20};
        Arrays.sort(input);
        int length = input.length;

        if (length % 2 == 0) {
            System.out.println(input[length / 2 - 1]);
        } else {
            System.out.println(input[length / 2]);
        }
    }
}
