package newod.case1.logic;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 最大化控制资源成本
 * 题目描述
 * 公司创新实验室正在研究如何最小化资源成本，最大化资源利用率，请你设计算法帮他们解决一个任务混部问题：
 *
 * 有taskNum项任务，每个任务有开始时间（startTime），结束时间（endTime），并行度（parallelism）三个属性，
 *
 * 并行度是指这个任务运行时将会占用的服务器数量，一个服务器在每个时刻可以被任意任务使用但最多被一个任务占用，任务运行完成立即释放（结束时刻不占用）。
 *
 * 任务混部问题是指给定一批任务，让这批任务由同一批服务器承载运行，
 *
 * 请你计算完成这批任务混部最少需要多少服务器，从而最大化控制资源成本。
 *
 * 输入描述
 * 第一行输入为taskNum，表示有taskNum项任务
 * 接下来taskNum行，每行三个整数，表示每个任务的
 *
 * 开始时间（startTime ），结束时间（endTime ），并行度（parallelism）
 *
 * 输出描述
 * 一个整数，表示最少需要的服务器数量
 *
 * 备注
 * 1 <= taskNum <= 100000
 * 0 <= startTime < endTime <= 50000
 * 1 <= parallelism <= 100
 *
 * 解法：暴力
 * 优化：
 * 本题并不是要求解最大区间重叠个数，而是要求解重叠区间的权重和。
 * 因此，我们需要定义一个变量sum来记录重叠区间的权重和
 * 当小顶堆弹出不重叠区间时，sum需要减去被弹出区间的权重，当我们向小顶堆压入重叠区间时，则sum需要加上被压入区间的权重。
 */
public class OD3_2 {
    /*
    public static void main(String[] args) {
        int[] storage = new int[50000];
        int[][] input = {
                {2,3,1},
                {6,9,2},
                {0,5,1},
                {2,7,2}
        };
        int max = 0;
        for (int[] ints : input) {
            for (int i = ints[0]; i < ints[1];i++){
                storage[i] += ints[2];
                max = Math.max(max,storage[i]);
            }
        }
        System.out.println(max);

    }
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] ranges = new int[n][3];

        for (int i = 0; i < n; i++) {
            ranges[i][0] = sc.nextInt();
            ranges[i][1] = sc.nextInt();
            ranges[i][2] = sc.nextInt();
        }

        System.out.println(getResult(ranges));
    }

    public static int getResult(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer[]> end = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int max = 0;
        // sum用来记录当前重叠区间的最大值
        int sum = 0;
        for (int[] range : ranges) {
            int s = range[0];
            int e = range[1];
            int p = range[2];

            while (end.size() > 0) {
                Integer[] top = end.peek();

                if (top[0] <= s) {
                    Integer[] poll = end.poll();
                    sum -= poll[1];
                } else {
                    break;
                }
            }

            end.offer(new Integer[] {e, p});
            sum += p;

            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

}
