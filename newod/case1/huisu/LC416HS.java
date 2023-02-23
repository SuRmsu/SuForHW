package newod.case1.huisu;

import java.util.Arrays;
import java.util.Collections;

/**
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 会超时
 */

public class LC416HS {
    public static void main(String[] args) {
        Integer[] nums = {
                1,2,3,5
        };
        Arrays.sort(nums, Collections.reverseOrder());
        int k = 2;
        int sum = 0;
        for (Integer num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            System.out.println(false);
        } else {
            int target = sum / 2;
            int[] bucket = new int[k];
            System.out.println(process(nums, k, 0, target, bucket));
        }
    }

    public static boolean process(Integer[] nums, int k, int startIndex, int target, int[] bucket) {
        if (startIndex == nums.length) {
            return true;
        }

        for (int i = 0; i < k; i++) {
            if (i > 0 && bucket[i] == bucket[i - 1]){
                continue;
            }
            if ( bucket[i] + nums[startIndex] <= target ){
                bucket[i] += nums[startIndex];
                if (process(nums, k, startIndex + 1, target, bucket)){
                    return true;
                }
                bucket[i] -= nums[startIndex];
            }
        }
        return false;
    }


}
