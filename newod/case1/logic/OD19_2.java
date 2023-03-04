package newod.case1.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * 最差产品奖
 * 题目描述
 * A公司准备对他下面的N个产品评选最差奖，
 * 评选的方式是首先对每个产品进行评分，然后根据评分区间计算相邻几个产品中最差的产品。
 * 评选的标准是依次找到从当前产品开始前M个产品中最差的产品，请给出最差产品的评分序列。
 * <p>
 * 输入描述
 * 第一行，数字M，表示评分区间的长度，取值范围是0<M<10000
 * 第二行，产品的评分序列，比如[12,3,8,6,5]，产品数量N范围是-10000 < N <10000
 * <p>
 * 输出描述
 * 评分区间内最差产品的评分序列
 * <p>
 * 解法：滑动窗口，可暴力解，但暴力解会超时
 * <p>
 * 优化，记录区间最小值，然后抛弃旧指针，比较新指针（注意旧指针可能被抛弃了,被抛弃就针对这一种情况排序）
 */
public class OD19_2 {
    public static void main(String[] args) {
        // 先暴力
        int m = 3;
        int[] input = {12, 3, 8, 6, 5, 3, 4, 5, 6};
/*      暴力解法
        for (int i = 0; i <= input.length - m; i++) {
            int temp = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                if ( i + j >= input.length){
                    break;
                }
                temp = Math.min(temp,input[i + j]);
            }
            System.out.println(temp);
        }
        */
        // 优化，仅左指针为最小值的时候需要遍历排序
        List<Integer> storage = new ArrayList<Integer>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            min = Math.min(min, input[i]);
        }
        storage.add(min);
        for (int i = 1; i <= input.length - m; i++) {
            int j = i + m - 1;
            if (input[j] <= min) {
                storage.add(input[j]);
                min = input[j];
            } else if (input[i - 1] != min) {
                storage.add(min);
            } else {
                min = Integer.MAX_VALUE;
                for (int ij = 0; ij < m; ij++) {
                    min = Math.min(input[i + ij], min);
                }
                storage.add(min);
            }
        }
        System.out.println(storage);


    }
}
