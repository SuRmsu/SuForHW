package newod.case1.logic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 采样过滤
 * 题目描述
 * 在做物理实验时，为了计算物体移动的速率，通过相机等工具周期性的采样物体移动距离。
 *
 * 由于工具故障，采样数据存在误差甚至相误的情况。
 *
 * 需要通过一个算法过滤掉不正确的采样值，不同工具的故意模式存在差异，算法的各关门限会根据工具类型做相应的调整。
 *
 * 请实现一个算法，计算出给定一组采样值中正常值的最长连续周期。
 *
 * 判断第1个周期的采样数据S0是否正确的规则如下(假定物体移动速率不超过10个单元前一个采样周期S[i-1]):
 *
 * S[i]<=0，即为错误值
 * S[i]<S[i-1]，即为错误值
 * S[i]-S[i-1]>=10，即为错误值。
 *
 * 其它情况为正常值
 *
 * 判断工具是否故障的规则如下：
 *
 * 在M个周期内，采样数据为错误值的次数为T(次数可以不连续)，则工具故障。
 *
 * 判断故障恢复的条件如下：
 *
 * 产生故障后的P个周期内，采样数据一直为正常值，则故障恢复
 *
 * 错误采样数据的处理方式：
 *
 * 检测到故障后，丢弃从故障开始到故障恢复的采样数据
 * 在检测到工具故障之前，错误的采样数据，则由最近一个正常值代替;如果前面没有正常的采样值，则丢弃此采样数据
 *
 * 给定一段周期的采样数据列表S，计算正常值的最长连续周期。
 *
 * 输入描述
 * 故障确认周期数和故障次数门限分别为M和T，故障恢复周期数为P。
 *
 * 第i个周期，检测点的状态为S[i]。
 *
 * 输入为两行，格式如下:
 *
 * M T F
 * s1 s2 s3 ...
 * M、t 和 e的取值范围为[1，100000]
 * s1取值范围为[0，100000]，从0开始编号
 *
 * 输出描述
 * 一行，输出正常值的最长连续周期。
 *
 * 解法：注意故障开始时间此解法是认为发现6个错误的时候才开始故障区间的
 */
public class OD2_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer[] mtp =
                Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] s =
                Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(mtp[0], mtp[1], mtp[2], s));
    }

    /**
     * @param m 故障确认周期数
     * @param t 故障次数门限值
     * @param p 故障恢复周期数
     * @param s 数组，元素是每个周期的采样数据
     * @return 最长连续周期的长度
     */
    public static int getResult(Integer m, Integer t, Integer p, Integer[] s) {
        int i = 0; // 连续正常周期的起始位置
        int fault = 0; // m个周期内错误数据的个数
        int cycle = 0; // 处于m个周期内第几个周期
        int ans = 0; // 最终结果，即最长连续正常周期长度

        // 如果采样数据一开始就是错误的，则直接丢弃
        while (s[i] <= 0) {
            // i指针后移，表示前面区间不属于连续正常周期范围
            i++;
            // 虽然错误数据被丢弃，但是仍然属于m周期内的出现的错误数据，因此需要计数
            fault++;
            cycle++;

            // 如果在m周期范围内，错误数据数量达到门限t，则工具故障，并进入故障恢复检测阶段
            if (cycle <= m) {
                if (cycle == m) {
                    cycle = 0;
                    fault = 0;
                }

                if (fault == t) {
                    cycle = 0;
                    fault = 0;
                    int p1 = p;
                    while (i < s.length && p1 > 0) {
                        // 故障恢复条件是，p个周期内一直都是正常数据，并要求连续，如果不连续，则重新计数
                        if (isFault(s, i)) {
                            p1 = p;
                        } else {
                            p1--;
                        }
                        i++;
                    }
                }
            }
        }

        // 这个cycle计数对应第i周期的
        cycle++;
        int j = i + 1;

        while (j < s.length) {
            cycle++; // 这个cycle计数对应第j周期的
            if (isFault(s, j)) {
                s[j] = s[j - 1];
                fault++;
            }

            j++;

            if (cycle <= m) {
                if (cycle == m) {
                    cycle = 0;
                    fault = 0;
                }

                if (fault == t) {
                    cycle = 0;
                    fault = 0;
                    ans = Math.max(ans, j - i); // 注意此时的j必然是故障开始的j，因此不计入正常连续周期中，因此求长度时不需要+1
                    // 发现故障，故障开始，进行故障恢复判断
                    int p1 = p;
                    while (j < s.length && p1 > 0) {
                        // 故障恢复条件是，p个周期内一直都是正常数据，要求连续
                        if (isFault(s, j)) {
                            p1 = p;
                        } else {
                            p1--;
                        }
                        i = j;
                    }
                }
            }
        }

        ans = Math.max(ans, j - i); // 注意这里的j必然是越界的j，因此求长度时不需要+1
        return ans;
    }

    // 该方法用于判断数据是否错误
    public static boolean isFault(Integer[] s, int j) {
        return s[j] <= 0 || (j >= 1 && (s[j] < s[j - 1] || s[j] - s[j - 1] >= 10));
    }
}
