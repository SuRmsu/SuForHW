package newod.case1.tanxing;

import java.util.Arrays;
import java.util.Collections;

/**
 * 题目描述
 * 部门组织绿岛骑行团建活动。租用公共双人自行车，每辆自行车最多坐两人，最大载重M。
 * 给出部门每个人的体重，请问最多需要租用多少双人自行车。
 *
 * 输入描述
 * 第一行两个数字m、n，分别代表自行车限重，部门总人数。
 *
 * 第二行，n个数字，代表每个人的体重，体重都小于等于自行车限重m。
 *
 * 0<m<=200
 * 0<n<=1000000
 * 输出描述
 * 最小需要的双人自行车数量。
 *
 * 解法：
 * 先让体重等于负重的先做，然后使用双指针，让体重大的先坐
 */
public class OD7 {
    public static void main(String[] args) {
        Integer[] nums = {1,3,2,2,1,1};
        int k = 3;
        Arrays.sort(nums, Collections.reverseOrder());
        int count = 0;
        //双指针 + 重的先坐
        int i = 0;
        int j = nums.length - 1;
        while (j >= i  ) {
            if (nums[i] == k){
                count++;
                i++;
                continue;
            }
            if ( i == j){
                count++;
                break;
            }
            if ( nums[i] + nums[j] <= k){
                count++;
                i++;
                j--;
            } else{
                count++;
                i++;
            }
        }
        System.out.println(count);
    }
}
