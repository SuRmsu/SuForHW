package newod.case1.dp;

import java.util.*;

/**
 * 题目描述
 * 如果一个数组中出现次数最多的元素出现大于等于K次，被称为 k-优雅数组 ，k也可以被称为优雅阈值。
 * 例如，数组1，2，3，1、2，3，1，它是一个3-优雅数组，因为元素1出现次数大于等于3次，
 * 数组[1, 2, 3, 1, 2]就不是一个3-优雅数组，因为其中出现次数最多的元素是1和2，只出现了2次。
 * <p>
 * 给定一个数组A和k，请求出A有多少子数组是k-优雅子数组。
 * <p>
 * 子数组是数组中一个或多个连续元素组成的数组。
 * <p>
 * 例如，数组[1,2,3,4]包含10个子数组，分别是：
 * [1], [1,2], [1,2,3], [1,2,3,4], [2], [2,3], [2,3,4], [3], [3, 4], [4]。
 * <p>
 * 输入描述
 * 第一行输入两个数字，以空格隔开，含义是：A数组长度 k值
 * <p>
 * 第二行输入A数组元素，以空格隔开
 * <p>
 * 输出描述
 * 输出A有多少子数组是k-优雅子数组
 * <p>
 * 解法：回溯 + 剪枝(双循环 + 剪枝）
 * 动态规划，用hashtable代替dp，双指针，右指针不用回退到左子针位置，且各字符数量已经放在hashtable中
 * 详见https://fcqian.blog.csdn.net/article/details/128385825
 */
public class OD23_2 {
    public static int max = 4;
    public static int k = 3;
    public static int count = 0;

    public static void main(String[] args) {

        Integer[] nums = {1, 2,3, 1, 2,3, 1,2};
/*      暴力双指针
        for (int i = 0; i < nums.length; i++) {

            List<Integer> myList = new ArrayList<Integer>();
            for (int j = i; j < nums.length; j++) {
                myList.add(nums[j]);
                process(myList);
            }
        }*/

        // dp 用hash来做
        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0, j = 0;
        while (i < nums.length && j < nums.length) {
            map.put(nums[j], map.getOrDefault(nums[j],0) + 1);
            int temp = map.get(nums[j]);
            if (temp >= k) {
                count += nums.length - j ;
                map.put(nums[i], map.get(nums[i]) - 1);
                i++;
                map.put(nums[j], map.get(nums[j]) - 1);
                j--;
            }
            j++;
        }


        System.out.println(count);
    }

    public static void process(List<Integer> list) {
        Map<Integer, Integer> map = new Hashtable<>();
        for (Integer integer : list) {
            map.put(integer, map.getOrDefault(integer, 0) + 1);
            if (map.get(integer) >= max) {
                count++;
                return;
            }
        }

    }
}
