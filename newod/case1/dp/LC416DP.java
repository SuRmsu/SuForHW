package newod.case1.dp;

/**
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
public class LC416DP {
    public static void main(String[] args) {
        Integer[] nums = {1, 5, 11, 5,6,6};
        int sum = 0;
        int max = 0;
        for (int i = 0; i < nums.length;i++){
            sum += nums[i];
            max = Math.max(nums[i],max);
        }
        int target = sum / 2;
        if (sum % 2 != 0 || nums.length <2|| max > target) {
            System.out.println(false);
            return ;
        }

        // dp[i][j] 选择前i个产品，其和能否达到j
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        //初始化，i为0，仅j为0可选，以及1为1，仅j为num[i - 1]可选
        dp[1][nums[0]] = true;
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i - 1] ){
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i - 1]];
                }else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }
        System.out.println(dp[nums.length][target]);

    }


}
