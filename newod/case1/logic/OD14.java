package newod.case1.logic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 模拟商场优惠打折
 * 题目描述
 * 模拟商场优惠打折，有三种优惠券可以用，满减券、打折券和无门槛券。
 * <p>
 * 满减券：满100减10，满200减20，满300减30，满400减40，以此类推不限制使用；
 * <p>
 * 打折券：固定折扣92折，且打折之后向下取整，每次购物只能用1次；
 * <p>
 * 无门槛券：一张券减5元，没有使用限制。
 * <p>
 * 每个人结账使用优惠券时有以下限制：
 * <p>
 * 每人每次只能用两种优惠券，并且同一种优惠券必须一次用完，不能跟别的穿插使用（比如用一张满减，再用一张打折，再用一张满减，这种顺序不行）。
 * <p>
 * 求不同使用顺序下每个人用完券之后得到的最低价格和对应使用优惠券的总数；如果两种顺序得到的价格一样低，就取使用优惠券数量较少的那个。
 * <p>
 * 输入描述
 * 第一行三个数字m,n,k，分别表示每个人可以使用的满减券、打折券和无门槛券的数量;
 * <p>
 * 第二行一个数字x, 表示有几个人购物;
 * <p>
 * 后面x行数字，依次表示是这几个人打折之前的商品总价。
 * <p>
 * 输出描述
 * 输出每个人使用券之后的最低价格和对应使用优惠券的数量
 *
 * 解法：
 * 无门槛券一定是最后用才划算，就只有四种情况，满折，折满，满无，折无
 * 三种情况每种都各自用一个函数来计算，再两两组合成最终结果
 *
 */
public class OD14 {
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
        while (price >= 100 && m > 0) {
            price -= price / 100 * 10; // 假设price=340，那么可以优惠 340/100 * 10 = 30元
            m--;
        }
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
            n--;
        }
        return new int[] {price, n};
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
