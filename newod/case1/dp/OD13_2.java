package newod.case1.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 士兵过河
 * 题目描述
 * 一支N个士兵的军队正在趁夜色逃亡，途中遇到一条湍急的大河。
 * 敌军在T的时长后到达河面，没到过对岸的士兵都会被消灭。
 * 现在军队只找到了1只小船，这船最多能同时坐上2个士兵。
 *
 * 当1个士兵划船过河，用时为 a[i]；0 <= i < N
 * 当2个士兵坐船同时划船过河时，用时为max(a[j],a[i])两士兵中用时最长的。
 * 当2个士兵坐船1个士兵划船时，用时为 a[i]*10；a[i]为划船士兵用时。
 * 如果士兵下河游泳，则会被湍急水流直接带走，算作死亡。
 * 请帮忙给出一种解决方案，保证存活的士兵最多，且过河用时最短。
 *
 * 输入描述
 * 第一行：N 表示士兵数(0<N<1,000,000)
 * 第二行：T 表示敌军到达时长(0 < T < 100,000,000)
 * 第三行：a[0] a[1] … a[i]… a[N- 1]
 * a[i]表示每个士兵的过河时长。
 * (10 < a[i]< 100; 0<= i< N）
 *
 * 输出描述
 * 第一行：”最多存活士兵数” “最短用时”
 *
 * 解法：
 * 让最慢的两个人所浪费的时间最小化，这可以通过让他们一起过桥来实现，
 * 因为需要有人提着灯（开船）返回，所以得让速度最快得两个人来完成这个任务。
 * 首先让用时最短的两个士兵A，B划船过河到对岸，用时A <= B
 * 然后让B留在对岸，让最快的士兵A划船回来本岸
 * 然后让本岸用时最长的两个士兵划船过河到对岸
 * 然后再让对岸用时最短的士兵B划船回来本岸
 * 按以上逻辑循环，直到用时达到上限T，或者士兵全部运完。
 *
 * 如果只有1个士兵的话，则上面步骤只能走到第1步。
 * 如果只有2个士兵的话，则上面步骤也只能走到第1步。
 * 如果有3个士兵的话，则上面步骤可以走到第3步，且此时是A和用时最长的士兵过河。
 * 如果有4个士兵的话，则上面步骤可以走到第4步，且此时是两个用时最短的士兵A和B过河。
 * 其中只有1个士兵和只有2个士兵的只能走到第1步的原因很容易想到，这里就不赘述了。
 *
 * 而造成3个士兵，和4个士兵走到不同步骤结束的原因是：
 *
 * 抛开用时最短的A，B士兵，剩余用时较慢的士兵是奇数个，还是偶数个。
 *
 * 如果是偶数个，则可以走到上面第4步，如果是奇数个，则只能走到上面第3步。
 * 假设每个士兵过河所需时间记录在times数组中，且times数组已经按用时升序。
 *
 * 那么可得：
 *
 * dp[0] = times[0]
 *
 * dp[1] = times[1]
 *
 * dp[i] 的取值有两种选择，如下：
 *
 * dp[i] = dp[i-1] + times[0] + times[i]
 *
 * dp[i] = dp[i-2] + times[0] + times[i] + times[1] + times[1]
 *
 * dp[i] = dp[i-1] + times[0] + times[i] 的含义是：
 *
 * 抛开最快的A，B士兵，剩余较慢的士兵是奇数个，因此最后较慢士兵中会遗留1个在本岸没有人组队，其余 0 ~ i-1 士兵都已经过河（dp[i-1]代表0 ~ i-1士兵全部过河），因此我们需要让对岸最快的士兵A，即 0号士兵送船回来，此时用时times[0]，然后 0号士兵和 i 号士兵一起过河，此时用时times[i]。
 *
 * dp[i] = dp[i-2] + times[0] + times[i] + times[1] + times[1] 的含义是：
 *
 * 抛开最快的A，B士兵，剩余较慢的士兵是偶数个，因此最后较慢士兵会遗留0个士兵在本岸，但是此时无法找到dp状态转移关系，因此我们后退一步，即当较慢士兵会遗留2个士兵在本岸，即 0 ~ i - 2 的士兵已经全部过河（dp[i-2]代表0~i-2的士兵已经全部过河），因此我们需要让对岸最快的士兵A，即0号士兵送船回来，此时用时times[0]，然后 i - 1 号士兵和 i 号士兵划船过河，用时times[i]，然后对岸最快的B士兵，即1号士兵送船回来，用时times[1]，然后0号士兵和1号士兵再开船到对岸，用时times[1]。
 *
 *            dp[i] = Math.min(dp[i - 1] + times[0] + getMax(times[0], times[i]),
 *                     dp[i - 2] + times[0] + getMax(times[i - 1], times[i]) + times[1] + getMax(times[0], times[1]));
 */
public class OD13_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int t = sc.nextInt();

        int[] times = new int[n];

        for (int i = 0; i < n; i++) {
            times[i] = sc.nextInt();
        }

        System.out.println(getResult(n, t, times));
    }

    public static String getResult(int n, int t, int[] times) {
        Arrays.sort(times);

        // 动态规划，四两拨千斤
        int[] dp = new int[n];

        dp[0] = times[0];
        if (dp[0] > t) return "0 0";

        dp[1] = getMax(times[0], times[1]);
        if (dp[1] > t) return 1 + " " + dp[0];

        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 1] + times[0] + getMax(times[0], times[i]),
                    dp[i - 2] + times[0] + getMax(times[i - 1], times[i]) + times[1] + getMax(times[0], times[1]));

            if (dp[i] > t) return i + " " + dp[i - 1];
        }

        return n + " " + dp[n - 1];
    }

    public static int getMax(int t1, int t2) {
        if (t1 * 10 < t2) {
            return t1 * 10;
        }
        return t2;
    }
}
