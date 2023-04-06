package newod.case1.logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 最多颜色的车辆
 * 题目描述
 * 在一个狭小的路口，每秒只能通过一辆车，假设车辆的颜色只有 3 种，找出 N 秒内经过的最多颜色的车辆数量。
 *
 * 三种颜色编号为0 ，1 ，2
 *
 * 输入描述
 * 第一行输入的是通过的车辆颜色信息
 *
 * [0,1,1,2] 代表4 秒钟通过的车辆颜色分别是 0 , 1 , 1 , 2
 *
 * 第二行输入的是统计时间窗，整型，单位为秒
 *
 * 输出描述
 * 输出指定时间窗内经过的最多颜色的车辆数量。
 *
 * 解法：
 * 简单的滑动窗口应用。我们可以利用相邻两个滑窗的差异比较，来避免重复的计算。
 *  第二个滑窗相较于第一个滑窗而言，失去了0，新增了1，因此我们不需要重新统计第二个滑窗内部各种颜色的数量
 *  只需要在第一个滑窗的统计结果基础上，减少0颜色数量1个，增加1颜色数量1个即可。
 */
public class OD5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer[] arr =
                Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        int n = sc.nextInt();

        System.out.println(getResult(arr, n));
    }

    public static int getResult(Integer[] arr, int n) {
        // count用于统计滑动窗口内各种颜色的数目
        HashMap<Integer, Integer> count = new HashMap<>();
        count.put(0, 0);
        count.put(1, 0);
        count.put(2, 0);

        // 初始滑动窗口的左右边界，注意这里的右边界r是不包含的
        int l = 0;
        int r = l + n;

        // 记录滑窗内部最多颜色数量
        int max = 0;

        // 统计初始滑动窗口中各种颜色的数量
        for (int i = l; i < r; i++) {
            Integer c = arr[i];
            count.put(c, count.get(c) + 1);
            max = Math.max(max, count.get(c));
        }

        // 如果滑动窗口右边界未达到数组尾巴，就继续右移
        // 注意，初始滑窗的右边界r是不包含的，因此r可以直接当成下一个滑窗的右边界使用
        while (r < arr.length) {
            // 当滑动窗口右移后，新的滑动窗口相比移动前来看，新增了arr[r]，失去了arr[l]，注意此时左边界l还是指向上一个滑窗的左边界，而不是新滑窗的左边界，因此可以直接通过arr[l]取得失去的
            Integer add = arr[r++];
            Integer remove = arr[l++];

            count.put(add, count.get(add) + 1);
            count.put(remove, count.get(remove) - 1);

            // 只有新增数量的颜色可能突破最大值
            max = Math.max(max, count.get(add));
        }

        return max;
    }
}
