package newod.case1.erfen;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 题目描述
 * 放暑假了，小明决定到某旅游景点游玩，他在网上搜索到了各种价位的酒店（长度为n的数组A），他的心理价位是x元，请帮他筛选出k个最接近x元的酒店（n>=k>0）,并由低到高打印酒店的价格。
 * <p>
 * 输入描述
 * 第一行：n, k, x
 * 第二行：A[0] A[1] A[2]…A[n-1]
 * <p>
 * 输出描述
 * 从低到高打印筛选出的酒店价格
 * <p>
 * 解法：
 * 然后，找到最接近心理价位x的值price[i]，将i作为中心位置。然后，分别遍历中心位置左边位值left=i-1，右边位置right=i+1，
 * 比较price[left]和price[right]谁更接近x（即与x的差的绝对值更小），
 * 如果price[left]接近，则left--，如果price[right]接近，则right++，
 * 如果price[left]和price[right]一样近，则优先选取price[left]
 *
 * 如果left--或者right++之后，发生了越界，则对应的price值置为Infinity，
 * 表示和中心值距离无穷大，这样就可以只会产生一边延申的效果了。
 */
public class OD44 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();

        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        System.out.println(getResult(n, k, x, prices));
    }

    public static String getResult(int n, int k, int x, int[] prices) {
        // 数组升序
        Arrays.sort(prices);

        // 二分查找数组中最接近心理价位x的元素的位置idx
        int idx = Arrays.binarySearch(prices, x);

        // 如果idx<0，说明在数组中没有找到对应值，因此此时idx是有序插入位置
        if (idx < 0) {
            idx = -idx - 1; // 将idx转换为有序插入位置

            // 如果idx的插入位置越界，则只会是右边界越界,或者插入的位置为0
            if (idx == prices.length || idx == 0) {
                int[] ans;
                if (idx == prices.length) ans = Arrays.copyOfRange(prices, idx - k, idx);
                else ans = Arrays.copyOfRange(prices, 0, k);

                StringJoiner sj = new StringJoiner(" ");
                for (int an : ans) {
                    sj.add(an + "");
                }
                return sj.toString();
            }

            int diff1 = Math.abs(x - prices[idx]);
            int diff2 = Math.abs(x - prices[idx - 1]);
            if (diff1 > diff2) {
                // 选择最接近的值的位置作为中心位置
                idx -= 1;
            }
        }

        // 中心位置的值算1个接近心里价位的值，因此已经找到1个，所以k--
        k--;

        int left = idx;
        int right = idx;
        while (k > 0) {
            int leftVal = left == 0 ? Integer.MAX_VALUE : prices[left - 1];
            int rightVal = right == n - 1 ? Integer.MAX_VALUE : prices[right + 1];

            // 从中心位置向两边发散~
            int diff1 = Math.abs(x - leftVal);
            int diff2 = Math.abs(x - rightVal);

            // 那边值更接近，则范围向哪边扩大
            if (diff1 <= diff2) {
                left--;
            } else {
                right++;
            }

            // 每次都能找到一个最接近的心里价位
            k--;
        }

        // left,right范围就是题解
        int[] ans = Arrays.copyOfRange(prices, left, right + 1);
        StringJoiner sj = new StringJoiner(" ");
        for (int an : ans) {
            sj.add(an + "");
        }
        return sj.toString();
    }
}
