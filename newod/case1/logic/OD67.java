package newod.case1.logic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 网上商城优惠活动
 * 题目描述
 * 某网上商场举办优惠活动，发布了满减、打折、无门槛3种优惠券，分别为：
 *
 * 每满100元优惠10元，无使用数限制，如100~199元可以使用1张减10元，200~299可使用2张减20元，以此类推；
 * 92折券，1次限使用1张，如100元，则优惠后为92元；
 * 无门槛5元优惠券，无使用数限制，直接减5元。
 * 优惠券使用限制
 *
 * 每次最多使用2种优惠券，2种优惠可以叠加（优惠叠加时以优惠后的价格计算），以购物200元为例，可以先用92折券优惠到184元，再用1张满减券优惠10元，最终价格是174元，也可以用满减券2张优惠20元为180元，再使用92折券优惠到165（165.6向下取整），不同使用顺序的优惠价格不同，以最优惠价格为准。在一次购物种，同一类型优惠券使用多张时必须一次性使用，不能分多次拆开使用（不允许先使用1张满减券，再用打折券，再使用一张满减券）。
 * 问题
 *
 * 请设计实现一种解决方法，帮助购物者以最少的优惠券获得最优的优惠价格。优惠后价格越低越好，同等优惠价格，使用的优惠券越少越好，可以允许某次购物不使用优惠券。
 * 约定
 *
 * 优惠活动每人只能参加一次，每个人的优惠券种类和数量是一样的。
 * 输入描述
 * 第一行：每个人拥有的优惠券数量（数量取值范围为[0,10]），按满减、打折、无门槛的顺序输入
 * 第二行：表示购物的人数n（1 ≤ n ≤ 10000）
 * 最后n行：每一行表示某个人优惠前的购物总价格（价格取值范围(0, 1000] ，都为整数）。
 * 约定：输入都是符合题目设定的要求的。
 * 输出描述
 * 每行输出每个人每次购物优惠后的最低价格以及使用的优惠券总数量
 * 每行的输出顺序和输入的顺序保持一致
 * 备注
 * 优惠券数量都为整数，取值范围为[0, 10]
 * 购物人数为整数，取值范围为[1, 10000]
 * 优惠券的购物总价为整数，取值范围为 (0, 1000]
 * 优惠后价格如果是小数，则向下取整，输出都为整数。
 *
 * 解法：同OD14 商场打折
 * 注意有一部分条件更改 满100，最多使用1张减10元的券，满200最多使用2张减10元的券，满300可以使用3张减10元的券
 *
 */
public class OD67 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();

        int[] arr = new int[x];
        for (int i = 0; i < x; i++) {
            arr[i] = sc.nextInt();
        }

        getResult(arr, m, n, k);
    }

    public static void getResult(int[] arr, int m, int n, int k) {
        for (int i = 0; i < arr.length; i++) {
            Integer[][] ans = new Integer[4][2]; // 4的含义对应4种使用券的方式：MN,NM,MK,NK,  2的含义对应每种方式下：剩余总价，剩余券数量
            int price = arr[i];

            int[] resM = M(price, m); // 先满减
            int[] resN = N(price, n); // 先打折

            // MN
            int[] resMN_N = N(resM[0], n); // 满减后打折
            ans[0] =
                    new Integer[] {
                            resMN_N[0], m + n - resM[1] - resMN_N[1]
                    }; // resMN_N[0]是 “满减后打折” 的剩余总价， m + n - resM[1] - resMN_N[1] 是 该种用券方式的: 总券数 m+n， 剩余券数
            // resM[1] + resMN_N[1], 因此使用掉的券数： m+n - (resM[1] + resMN_N[1])

            // NM
            int[] resNM_M = M(resN[0], m); // 打折后满减
            ans[1] = new Integer[] {resNM_M[0], n + m - resN[1] - resNM_M[1]};

            // MK
            int[] resMK_K = K(resM[0], k); // 满减后无门槛
            ans[2] = new Integer[] {resMK_K[0], m + k - resM[1] - resMK_K[1]};

            // NK
            int[] resNK_K = K(resN[0], k); // 打折后无门槛
            ans[3] = new Integer[] {resNK_K[0], n + k - resN[1] - resNK_K[1]};

            Arrays.sort(
                    ans,
                    (a, b) ->
                            a[0].equals(b[0])
                                    ? a[1] - b[1]
                                    : a[0] - b[0]); // 对ans进行排序，排序规则是：优先按剩余总价升序，如果剩余总价相同，则再按“使用掉的券数量”升序
            System.out.println(ans[0][0] + " " + ans[0][1]);
        }
    }

    /**
     * @param price 总价
     * @param m 满减券数量
     * @return 总价满减后结果，对应数组含义是 [用券后剩余总价， 剩余满减券数量]
     */
    public static int[] M(int price, int m) {
        int maxCount = price / 100; // 满100最多用1张满减券，满200最多用2张满减券....，price总价最多使用price/100张券
        int count = Math.min(maxCount, m); // 实际可使用的满减券数量

        price -= count * 10; // 每张满减券只能减10元
        m -= count;

        return new int[] {price, m};
    }

    /**
     * @param price 总价
     * @param n 打折券数量
     * @return 总价打折后结果，对应数组含义是 [用券后剩余总价， 剩余打折券数量]
     */
    public static int[] N(int price, int n) {
        if (n >= 1) {
            price = (int) Math.floor((price * 0.92));
        }
        return new int[] {price, n - 1};
    }

    /**
     * @param price 总价
     * @param k 无门槛券数量
     * @return 无门槛券用后结果，对应数组含义是 [用券后剩余总价， 剩余无门槛券数量]
     */
    public static int[] K(int price, int k) {
        while (price > 0 && k > 0) {
            price -= 5;
            price = Math.max(price, 0); // 感谢m0_71826536提供的思路，当无门槛券过多时，是有可能导致优惠后总价低于0的情况的，此时我们应该避免总价小于0的情况
            k--;
        }
        return new int[] {price, k};
    }
}
