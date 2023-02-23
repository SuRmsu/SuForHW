package newod.case1.dp;

import java.util.Arrays;
import java.util.Collections;

public class LC698 {
    public static void main(String[] args) {
        int[] nums1 = {2, 2, 2, 2, 3, 4, 5};//{4, 3, 2, 3, 5, 2, 1};
        Integer[] nums = new Integer[nums1.length];
        int k = 4;
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            target += nums1[i];
            nums[i] = nums1[i];
        }
        Arrays.sort(nums, Collections.reverseOrder());
        if (target % k != 0) {
            // 不能整除，则直接排除
            System.out.println(false);
        } else {
            // 桶，用来表示能否放下
            int[] bucket = new int[k];
            System.out.println(process(nums, k, 0, target / k, bucket));
        }
    }

    public static boolean process(Integer[] input, int count, int startIndex, int target, int[] bucket) {
        // 终止条件
        if (startIndex == input.length) {
                return true;
        }
        // 处理
        for (int i = 0; i < count; i++) {
            //不剪枝会超时
            //前一个桶放不下，当前桶肯定也放不下
            if (i > 0 && bucket[i] == bucket[i-1]) {
                continue;
            }
            //如果当前桶放得下
            if (input[startIndex] + bucket[i] <= target) {
                bucket[i] += input[startIndex];
                //放下一个球去
                if (process(input, count, startIndex + 1, target, bucket)) {
                    return true;
                }
                bucket[i] -= input[startIndex];
            }
        }
        return false;
    }

}
