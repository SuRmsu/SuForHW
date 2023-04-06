package newod.case1.huisu;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 题目描述
 * 给你一个整数M和数组N，N中的元素为连续整数，要求根据N中的元素组装成新的数组R，组装规则：
 *
 * R中元素总和加起来等于M
 * R中的元素可以从N中重复选取
 * R中的元素最多只能有1个不在N中，且比N中的数字都要小（不能为负数）
 * 输入描述
 * 第一行输入是连续数组N，采用空格分隔
 * 第二行输入数字M
 *
 * 输出描述
 * 输出的是组装办法数量，int类型
 *
 * 解法：回溯，可重复，不需要去重
 */
public class OD31_2 {
    static List<List<Integer>> storage = new ArrayList<>();
    static Stack<Integer> tempStorage = new Stack<>();
    public static void main(String[] args) {
        int m = 5;
        int[] n = {2,3,4};
        int min = m - n[0];
        process(m,min,n,0,0);
        System.out.println(storage.size());
    }
    public static void process(int m,int min, int[] nums, int startIndex,int sum){
        if ( sum == m || (m - sum < min && m - sum > 0)){
            storage.add(new ArrayList<>(tempStorage));
            return;
        } else if ( sum > m) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
         tempStorage.push(nums[i]);
         sum += nums[i];
         process(m,min,nums,i,sum);
         tempStorage.pop();
         sum -= nums[i];
        }

    }

}
