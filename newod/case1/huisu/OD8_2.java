package newod.case1.huisu;

import java.util.Arrays;

/**
 * 给定一个数组nums，可以将元素分为若干个组，使得每组和相等，求出满足条件的所有分组中，
 * 最大的平分组个数。
 * 解法：先sort，从小到大分布，然后从2组依次升序分组，每次都直接加，记录个数或者直接添到列表里。
 * 想错了，返回的是最大的平分组个数，直接返回组数就行
 */
public class OD8_2 {
    //static int max = 0;
    public static void main(String[] args) {
        Integer[] input = {5,2,1,5,2,1,5,2,1,1,1,1};
        // 不sort应该也行
        Arrays.sort(input,(o1, o2)-> o2 - o1);
        System.out.println(process(input));
        //System.out.println(max);
    }
    public static int process(Integer[] input){
        int length = input.length;
        int sum = 0;
        for (int i : input) {
            sum += i;
        }
        for(int i = length; i > 1; i--){
            if (sum % i != 0 ) {
                continue;
            }
            int [] bucket = new int[i];
            if (isValid(input, i , sum / i ,0,bucket)){
                return i;
            }

        }
        return 1;
    }

    public static boolean isValid(Integer[] input , int k , int target, int startIndex, int[] bucket){
        if (startIndex == input.length ) {
            //max = Math.max(max,maxBucket(bucket));
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
