package newod.case1.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 二元组个数
 * 题目描述
 * 给定两个数组a，b，若a[i] == b[j] 则称 [i, j] 为一个二元组，求在给定的两个数组中，二元组的个数。
 *
 * 输入描述
 * 第一行输入 m
 * 第二行输入m个数，表示第一个数组
 *
 * 第三行输入 n
 * 第四行输入n个数，表示第二个数组
 *
 * 输出描述
 * 二元组个数。
 *
 * 解法：暴力双循环 可能超时
 *  优化：
 *  统计A数组中B数组有的元素，并记录A中出现了几次
 *  B同样
 *  相同元素出现的次数相乘 其总和就是结果
 *
 */
public class OD15 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        ArrayList<Integer> listM = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            listM.add(sc.nextInt());
        }

        int n = sc.nextInt();
        ArrayList<Integer> listN = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            listN.add(sc.nextInt());
        }

        System.out.println(getResult(listM, listN));
    }

    public static int getResult(ArrayList<Integer> listM, ArrayList<Integer> listN) {
        HashSet<Integer> setM = new HashSet<Integer>(listM);
        HashSet<Integer> setN = new HashSet<Integer>(listN);

        HashMap<Integer, Integer> countM = new HashMap<>();
        for (Integer m : listM) {
            if (setN.contains(m)) {
                countM.put(m, countM.getOrDefault(m, 0) + 1);
            }
        }

        HashMap<Integer, Integer> countN = new HashMap<>();
        for (Integer n : listN) {
            if (setM.contains(n)) {
                countN.put(n, countN.getOrDefault(n, 0) + 1);
            }
        }

        int count = 0;
        for (Integer k : countM.keySet()) {
            count += countM.get(k) * countN.get(k);
        }

        return count;
    }
}
