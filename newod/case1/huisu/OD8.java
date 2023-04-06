package newod.case1.huisu;

import java.util.*;

/**
 * 给定一个数组nums，将元素分为若干个组，使得每组和相等，求出满足条件的所有分组中，组内元素和的最小值。
 *
 * 输入描述
 * 第一行输入 m
 * 接着输入m个数，表示此数组nums
 * 数据范围：1<=m<=50, 1<=nums[i]<=50
 *
 * 输出描述
 * 最小拆分数组和
 *
 * 解法：最好的情况肯定是每个元素自己为一组；不满足就组数减一再试试
 */
public class OD8 {
    static List<List<Integer>> storage = new ArrayList<>();
    static Stack<Integer> tempStorage = new Stack<>();
    public static void main(String[] args) {
        Integer[] input = {4,3,2,3,5,2,1};
        // 不sort应该也行
        Arrays.sort(input,(o1,o2)-> o2 - o1);
        System.out.println(process(input));
    }
    public static int process(Integer[] input){
        int length = input.length;
        int sum = 0;
        for (int i : input) {
            sum += i;
        }
        for(int i = length; i > 0; i--){
            if (sum % i != 0 ) {
                continue;
            }
            int [] bucket = new int[i];
            if (isValid(input, i , sum / i ,0,bucket)){
                return sum / i;
            }

        }
        return sum;
    }

    public static boolean isValid(Integer[] input , int k , int target, int startIndex, int[] bucket){
        if (startIndex == input.length ) {
            return true;
        }

        for (int i = 0; i < k; i++) {
            //剪枝
            if( i > 0 && bucket[i] == bucket[i -1] ){
                continue;
            }
            if (input[startIndex] + bucket[i] <= target){
                bucket[i] += input[startIndex];
                if(isValid(input,k,target,startIndex + 1,bucket)){
                    return true;
                }
                bucket[i] -= input[startIndex];
            }
        }
        return false;
    }
}
