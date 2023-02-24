package newod.case1.dp;

/**
 * 题目描述
 * 某个充电站，可提供n个充电设备，每个充电设备均有对应的输出功率。任意个充电设备组合的输出功率总和，均构成功率集合P的1个元素。功率集合P的最优元素，表示最接近充电站最大输出功率p_max的元素。
 * <p>
 * 输入描述
 * 输入为3行：
 * <p>
 * 第1行为充电设备个数n
 * 第2行为每个充电设备的输出功率
 * 第3行为充电站最大输出功率p_max
 * 输出描述
 * 功率集合P的最优元素
 * <p>
 * 备注
 * 充电设备个数 n > 0
 * 最优元素必须小于或等于充电站最大输出功率p_max
 * 就是找小于最大功率的子集的功率和
 * <p>
 * 回溯和dp应该都可以
 */
public class OD28_2 {
    static int max = 0;

    public static void main(String[] args) {
        int k = 0;
        int[] input = {50,30,30};
        int maxLimit = 90;
        // dp[] 前i个组合在不超过 maxLimit情况下能达到的最大值

        // 先试一下直接回溯, 如果input中的元素过多有可能超时
        //process(input, 0, maxLimit, 0);
        //System.out.println(max);

        // 用dp做
        int[][] dp = new int[input.length + 1][maxLimit + 1];
        for (int i = 1; i <= input.length; i++) {
            for (int j = 1; j <= maxLimit; j++) {
                if (input[i - 1] <= j){
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - input[i - 1]] + input[i - 1]);
                } else {
                    dp[i][j] = dp[ i - 1][j];
                }
            }
        }
        System.out.println(dp[input.length][maxLimit]);

    }

    public static void process(int[] input, int startIndex, int maxLimit, int sum) {
        if (startIndex == input.length ) {
            max = Math.max(max, sum);
            return;
        }
        for (int i = startIndex; i < input.length; i++) {
            if (sum + input[i] <= maxLimit){
                sum += input[i];
                max = Math.max(max, sum);
                process(input,i + 1,maxLimit,sum);
                sum -= input[i];
            }
        }
    }


}
