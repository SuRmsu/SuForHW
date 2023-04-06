package newod.case1.youxianduilie;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 工单调度策略
 * 假设华为和运营商A签订了运维合同，部署了一套调度系统，只有1个外线工程师（FME），每个工单根据问题严重程度会给一个评分，在SLA时间内完成修复的工单，华为获得工单评分对应的积分，超过SLA完成的工单不获得积分，但必须完成该工单。运营商最终会根据积分进行付款。
 * <p>
 * 请设计一种调度策略，根据现状得到调度结果完成所有工单，让这个外线工程师处理的工单获得的总积分最多。
 * <p>
 * 假设从某个调度时刻开始，当前工单数量为N，不会产生新的工单，每个工单处理修复耗时为1小时，请设计你的调度策略，完成业务目标。
 * <p>
 * 不考虑外线工程师在小区之间行驶的耗时。
 * <p>
 * 输入描述
 * 第一行为一个整数N，表示工单的数量。
 * <p>
 * 接下来N行，每行包括两个整数。第一个整数表示工单的SLA时间（小时），第二个数表示该工单的积分。
 * <p>
 * 输出描述
 * 输出一个整数表示可以获得的最大积分。
 * <p>
 * 备注
 * 工单数量N ≤ 10^6
 * SLA时间 ≤ 7 * 10^5
 * 答案的最大积分不会超过2147483647
 * 解法： 优先队列
 * 如果 任务时间 <= currentTime + 1 ，即在接下来的一个时间内，该任务是可以做的，直接添加就完事
 * 如果 任务时间 > currentTime + 1， 则判断值不值得用分数最小的来换当前的
 *
 * 解法：经典的有限队列问题，用小根堆，判断值不值得用当前的替换掉之前的任务
 *
 */
public class OD35_2 {
    public static void main(String[] args) {
        int n = 7;
        int[][] input = {
                {1, 6},
                {1, 7},
                {3, 2},
                {3, 1},
                {2, 4},
                {2, 5},
                {6, 1}
        };
        // 按紧急程度升序排序
        Arrays.sort(input, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 记录累加的分数
        int sum = 0;
        int currentTime = 0;
        PriorityQueue<Integer> query = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });


        for (int i = 0; i < input.length ;i++ ) {
            int taskTime = input[i][0];
            int taskValue = input[i][1];

            if (query.size() == 0) {
                query.add(taskValue);
                sum += taskValue;
                currentTime++;
                continue;
            }

            // 如果 任务时间 <= currentTime + 1 ，即在接下来的一个时间内，该任务是可以做的，直接添加就完事
            // 如果 任务时间 > currentTime + 1， 则判断值不值得用分数最小的来换当前的
            // 真 TM 蠢
            if ( currentTime + 1 > taskTime) {
                if (taskValue <= query.peek()){
                    continue;
                } else {
                    sum -= query.poll();
                    sum += taskValue;
                    query.add(taskValue);
                }
            } else {
                query.add(taskValue);
                sum += taskValue;
                currentTime++;
            }

        }

        System.out.println(sum);
    }
}
