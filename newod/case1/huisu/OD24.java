package newod.case1.huisu;

import java.util.Arrays;
import java.util.Collections;

/**
 * 在星球争霸篮球赛对抗赛中，最大的宇宙战队希望每个人都能拿到MVP，MVP的条件是单场最高分得分获得者。
 * 可以并列所以宇宙战队决定在比赛中尽可能让更多队员上场，并且让所有得分的选手得分都相同，
 * 然而比赛过程中的每1分钟的得分都只能由某一个人包揽。
 *
 * 输入描述
 * 输入第一行为一个数字 t ，表示为有得分的分钟数 1 ≤ t ≤ 50
 * 第二行为 t 个数字，代表每一分钟的得分 p， 1 ≤ p ≤ 50
 *
 * 输出描述
 * 输出有得分的队员都是MVP时，最少得MVP得分。
 */
public class OD24 {
    public static void main(String[] args) {
        Integer[] nums = {5,2,1,5,2,1,5,2,1};
        // 必须从大到小排列
        Arrays.sort(nums, Collections.reverseOrder());
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int k = nums.length;
        for (int i = k; i > 1; i--) {
            if (sum % i != 0) {
                continue;
            } else {
                int target = sum / i;
                int[] members = new int[i];
                if ( process(nums,i,target,members,0)){
                    System.out.println(target);
                    return;
                }
            }
        }
        System.out.println(sum);

    }
    public static boolean process(Integer[] nums, int k, int target, int[] members,int startIndex){
        if (startIndex == nums.length){
            return true;
        }
        for (int i = 0; i < k; i++) {
            //还是剪枝
            if ( i > 0 && members[i] == members[i -1] ){
                continue;
            }
            if ( nums[startIndex] + members[i] <= target){
                members[i] += nums[startIndex];
                if ( process(nums,k,target,members,startIndex+1)){
                    return true;
                }
                members[i] -= members[startIndex];
            }
        }
        return false;
    }
}
