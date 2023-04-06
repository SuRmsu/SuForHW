package newod.case1.erfen;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 题目描述
 * 火车站附近的货物中转站负责将到站货物运往仓库，小明在中转站负责调度2K辆中转车（K辆干货中转车，K辆湿货中转车）。
 *
 * 货物由不同供货商从各地发来，各地的货物是依次进站，然后小明按照卸货顺序依次装货到中转车，一个供货商的货只能装到一辆车上，不能拆装，但是一辆车可以装多家供货商的货；
 *
 * 中转车的限载货物量由小明统一指定，在完成货物中转的前提下，请问中转车的统一限载货物数最小值为多少。
 *
 * 输入描述
 * 第一行 length 表示供货商数量 1 <= length <= 10^4
 * 第二行 goods 表示供货数数组 1 <= goods[i] <= 10^4
 * 第三行 types表示对应货物类型，types[i]等于0或者1，其中0代表干货，1代表湿货
 * 第四行 k表示单类中转车数量 1 <= k <= goods.length
 * 输出描述
 * 运行结果输出一个整数，表示中转车统一限载货物数
 *
 * 备注
 * 中转车最多跑一趟仓库
 *
 * 解法： 二分 + 回溯 桶
 * 其中二分法用于求解最低高度值，回溯算法用于验证二分求得的最低高度值是否符合要求。
 *
 * 二分法用于求解可能的最低累积高度值，这里必然有一个上下界高度值问题。
 *
 * 当所有圆饼全部放入一个桶中时，那么此时该桶内圆饼的累积高度值就是最大可能的累积高度值。
 *
 * 当一个桶不放入一个圆饼时，此时该桶内圆饼的累积高度值0就是最低可能的累积高度值。、
 *
 * 另外本题回溯有一个重要的剪枝，那就是，如果回溯放圆饼到桶的过程中，出现了空桶，那么此时即使最终可以放置成功，也肯定不是最优解，
 * 因为我们只要把其他桶的圆饼放到空桶，则必然能产生更优解。因此回溯过程中，出现空桶，可以直接认定为不符合要求。
 *
 * 最小值应该是平分，最高值是总和
 */
public class OD73 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] goods = new int[n];
        for (int i = 0; i < n; i++) {
            goods[i] = sc.nextInt();
        }

        int[] types = new int[n];
        for (int i = 0; i < n; i++) {
            types[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        System.out.println(getResult(n, goods, types, k));
    }

    /**
     * @param n 供货商数量
     * @param goods 供货数数组
     * @param types 表示对应货物类型，types[i]等于0或者1，其中0代表干货，1代表湿货
     * @param k 表示单类中转车数量
     * @return 中转车的统一限载货物数最小值为多少
     */
    public static int getResult(int n, int[] goods, int[] types, int k) {
        ArrayList<Integer> dry = new ArrayList<>();
        ArrayList<Integer> wet = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (types[i] == 0) {
                dry.add(goods[i]);
            } else {
                wet.add(goods[i]);
            }
        }

        return Math.max(getMinMaxWeight(dry, k), getMinMaxWeight(wet, k));
    }

    public static int getMinMaxWeight(ArrayList<Integer> weights, int k) {
        int n = weights.size();

        if (n <= k) {
            return weights.stream().max((a, b) -> a - b).orElse(0);
        }

        weights.sort((a, b) -> b - a);

        int min = weights.get(0);
        int max = weights.stream().reduce(Integer::sum).get();

        while (min < max) {
            int mid = (min + max) >> 1;

            int[] buckets = new int[k];
            if (check(0, weights, buckets, mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        return min;
    }

    public static boolean check(int index, ArrayList<Integer> weights, int[] buckets, int limit) {
        if (index == weights.size()) {
            return true;
        }

        int select = weights.get(index);
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] + select <= limit) {
                buckets[i] += select;
                if (check(index + 1, weights, buckets, limit)) return true;
                buckets[i] -= select;
                if (buckets[i] == 0) return false;
            }
        }

        return false;
    }
}
