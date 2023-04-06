package newod.case1.tanxing;

/**
 * 题目描述
 * 商人经营一家店铺，有number种商品，
 * 由于仓库限制每件商品的最大持有数量是item[index]
 * 每种商品的价格是item-price[item_index][day]
 * 通过对商品的买进和卖出获取利润
 * 请给出商人在days天内能获取的最大的利润
 * 注：同一件商品可以反复买进和卖出
 * <p>
 * 输入描述
 * 第一行输入商品的数量number，比如3
 * 第二行输入商品售货天数 days，比如3
 * 第三行输入仓库限制每件商品的最大持有数量是item[index]，比如4 5 6
 * <p>
 * 后面继续输入number行days列，含义如下：
 * 第一件商品每天的价格，比如1 2 3
 * 第二件商品每天的价格，比如4 3 2
 * 第三件商品每天的价格，比如1 5 3
 * <p>
 * 输出描述
 * 输出商人在这段时间内的最大利润。
 * <p>
 * 解法：先贪心,后续考虑dp
 * 第一天买，第二天卖了再买，第三天再卖；和第一天买，到第三天卖利润是一样的，所以ALL IN就完事了
 * 注意要对输入的行数进行校验，挺离谱的。
 */
public class OD34 {
    public static void main(String[] args) {
        int[][] temp = {{1,2,3},{4,3,2},{1,5,2}};
        int[] limit = {4,5,6};
        int max = 0;
        for (int i = 0; i < temp.length; i++) {
            max += process(temp[i],limit[i]);
        }
        System.out.println(max);
    }

    static int process(int[] prices, int limit) {
        int count = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if ( prices[i] < prices[i + 1]) {
                count += limit * (prices[i + 1] - prices[i]) ;
            }
        }
        return count;
    }
}
