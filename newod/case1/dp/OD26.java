package newod.case1.dp;

/**
 * 日志采集是运维系统的的核心组件。日志是按行生成，每行记做一条，由采集系统分批上报。
 * <p>
 * 如果上报太频繁，会对服务端造成压力;
 * 如果上报太晚，会降低用户的体验；
 * 如果一次上报的条数太多，会导致超时失败。
 * 为此，项目组设计了如下的上报策略：
 * <p>
 * 每成功上报一条日志，奖励1分
 * 每条日志每延迟上报1秒，扣1分
 * 积累日志达到100条，必须立即上报
 * 给出日志序列，根据该规则，计算首次上报能获得的最多积分数。
 * <p>
 * 输入描述
 * 按时序产生的日志条数 T1,T2…Tn，其中 1<=n<=1000，0<=Ti<=100
 * <p>
 * 输出描述
 * 首次上报最多能获得的积分数
 */
public class OD26 {
    public static void main(String[] args) {
        int[] nums = {3,7,40,10,60};
        // dp[i] 在第i时刻上报能获得的最大分数
        // dp[i] 和 dp[i - 1]  : 1 累积到100，必须提交， 2 未累积到100，提交当前的，扣除前所有时刻的， 3 前一时刻的最大值
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            if (sum >= 100) {
                System.out.println(Math.max(dp[i - 1], 100 - (sum - nums[i]) + process(nums, i)));
                return;
            } else {
                dp[i] = Math.max(dp[i - 1], process(nums, i) + nums[i]);
            }
        }
        System.out.println(dp[nums.length - 1]);
    }

    // 判断扣多少分
    public static int process(int[] nums, int index) {
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum -= nums[i] * (index - i - 1);
        }
        return sum;
    }
}
