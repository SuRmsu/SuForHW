package newod.case1.dp;

/**
 * 题目描述
 * 某云短信厂商，为庆祝国庆，推出充值优惠活动。
 * 现在给出客户预算，和优惠售价序列，求最多可获得的短信总条数。
 * <p>
 * 输入描述
 * 第一行客户预算M，其中 0 ≤ M ≤ 10^6
 * 第二行给出售价表， P1, P2, … Pn , 其中 1 ≤ n ≤ 100 ,
 * Pi为充值 i 元获得的短信条数。1 ≤ Pi ≤ 1000 , 1 ≤ n ≤ 100
 * <p>
 * 输出描述
 * 最多获得的短信条数
 * 解法：完全背包算法
 */
public class OD31 {
    public static void main(String[] args) {
        int money = 6;
        int[] table = {10, 20, 30, 40, 60, 60, 70, 80, 90, 150};
        // dp[i][j] 用j钱，买前i个物品，可重复，最多能得到的价值
        // 买不起当前的，dp[i][j] = dp[i - 1][j]
        // 买得起当前的，买当前的：dp[i-1][j - table[i]] + 当前值 和 不买的取最大值dp[i - 1][j] 这是01背包
        // 完全背包：得先初始化买第一个物品的所有的钱的情况，然后先遍历物品，再遍历钱都可以
        // 并且递推公式应该改为：dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - i] + table[i - 1]);
        int[][] dp = new int[table.length + 1][money + 1];
        // 初始化
        for (int i = 0; i <= table.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < money + 1; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= money; i++) {
            dp[1][i] = dp[1][i - 1] + table[0];
        }

        for (int j = 1; j <= money; j++) {
            for (int i = 2; i <= table.length; i++) {
                if (i > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - i] + table[i - 1]);
                }
            }
        }
        System.out.println(dp[table.length][money]);

    }
}
