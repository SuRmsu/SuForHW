package newod.case1.logic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 优选核酸点
 * 题目描述
 * 张三要去外地出差，需要做核酸，需要在指定时间点前做完核酸，请帮他找到满足条件的核酸检测点。
 *
 * 给出一组核酸检测点的距离和每个核酸检测点当前的人数
 * 给出张三要去做核酸的出发时间 出发时间是10分钟的倍数，同时给出张三做核酸的最晚结束时间
 * 题目中给出的距离是整数，单位是公里，时间1分钟为一基本单位
 * 去找核酸点时，有如下的限制：
 *
 * 去往核酸点的路上，每公里距离花费时间10分钟，费用是10元
 * 核酸点每检测一个人的时间花费是1分钟
 * 每个核酸点工作时间都是8点到20点中间不休息，核酸点准时工作，早到晚到都不检测
 * 核酸检测结果可立刻知道
 * 在张三去某个核酸点的路上花费的时间内，此核酸检测点的人数是动态变化的，变化规则是
 * 在非核酸检测时间内，没有人排队
 * 8点-10点每分钟增加3人
 * 12点-14点每分钟增加10人
 * 要求将所有满足条件的核酸检测点按照优选规则排序列出 ：
 * 优选规则：
 *
 * 花费时间最少的核酸检测点排在前面。
 * 花费时间一样,花费费用最少的核酸检测点排在前面。
 * 时间和费用一样，则ID值最小的排在前面
 *
 * 解法：
 * 我们可以通过求区间交集的方式，来获取张三【出发时间，到达时间】  和  【8:00，10:00】以及【10:00, 12:00】，以及【12:00, 14:00】以及【14:00，20:00】的交集。
 *
 * 其中，
 *
 * 和  【8:00，10:00】的交集，每分钟净增2人
 * 和  【10:00, 12:00】的交集，每分钟净减1人
 * 和  【12:00, 14:00】的交集，每分钟净增9人
 * 和  【14:00，20:00】的交集，每分钟净减1人
 * 但是8:00前是不给排队的，因此张三还要等待到8:00，因此张三花费的时间其实是：路上时间 + 等待时间 + 排队时间
 */
public class OD36 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int h1 = sc.nextInt();
        int m1 = sc.nextInt();

        int h2 = sc.nextInt();
        int m2 = sc.nextInt();

        int n = sc.nextInt();

        int[][] idcs = new int[n][3];
        for (int i = 0; i < n; i++) {
            idcs[i][0] = sc.nextInt();
            idcs[i][1] = sc.nextInt();
            idcs[i][2] = sc.nextInt();
        }

        getResult(h1, m1, h2, m2, idcs);
    }

    /**
     * @param h1 当前时间的小时数
     * @param m1 当前时间的分钟数
     * @param h2 指定完成核算时间的小时数
     * @param m2 指定完成核算时间的分钟数
     * @param idcs [[核酸点的ID值, 核酸检测点距离张三的距离,核酸检测点当前检测的人数]]
     */
    public static void getResult(int h1, int m1, int h2, int m2, int[][] idcs) {
        int start = h1 * 60 + m1;
        int end = h2 * 60 + m2;

        int[][] ans =
                Arrays.stream(idcs)
                        .map(
                                idc -> {
                                    int id = idc[0];
                                    int distance = idc[1];
                                    int count = idc[2];

                                    int money = distance * 10;
                                    int road = distance * 10; // 花在路上的时间
                                    int arrived = start + road; // 到达核酸检测点的时间

                                    // 如果在8：00之前就赶到了，那么其实要等待到8:00才能排队，这里其实花费的时间应该包括等待的时间
                                    if (arrived < 8 * 60) {
                                        arrived = 8 * 60;
                                        road = arrived - start;
                                    }

                                    // 出发时间，结束时间
                                    int[] ran1 = {start, arrived};

                                    // 和[8:00, 10:00]的交集，每分钟净增2人
                                    int[] ran2 = {8 * 60, 10 * 60};
                                    int add1 = getIntersection(ran1, ran2);
                                    if (add1 != -1) {
                                        count += 2 * add1;
                                    }

                                    // 和[10:00, 12:00]的交集，每分钟净减1人
                                    int[] ran3 = {10 * 60, 12 * 60};
                                    int min1 = getIntersection(ran1, ran3);
                                    if (min1 != -1) {
                                        count -= min1;
                                        count = Math.max(0, count); // 注意至多减到0
                                    }

                                    // 和[12:00, 14:00]的交集，每分钟净增9人
                                    int[] ran4 = {12 * 60, 14 * 60};
                                    int add2 = getIntersection(ran1, ran4);
                                    if (add2 != -1) {
                                        count += 9 * add2;
                                    }

                                    // 和[14:00, 20:00]的交集，每分钟净减1人
                                    int[] ran5 = {14 * 60, 20 * 60};
                                    int min2 = getIntersection(ran1, ran5);
                                    if (min2 != -1) {
                                        count -= min2;
                                        count = Math.max(0, count); // 注意至多减到0
                                    }

                                    return new int[] {id, count + road, money};
                                })
                        // arr[1] = count + road，因此实际到达时间为start + arr[1]，此处需要过滤出规定结束时间end之前可达的核酸点
                        .filter(arr -> start + arr[1] <= end)
                        .sorted((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[2] != b[2] ? a[2] - b[2] : a[0] - b[0])
                        .toArray(int[][]::new);

        System.out.println(ans.length);
        for (int[] arr : ans) {
            System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
        }
    }

    // 获取交集长度，如果没有交集返回-1
    public static int getIntersection(int[] ran1, int[] ran2) {
        int s1 = ran1[0], e1 = ran1[1];
        int s2 = ran2[0], e2 = ran2[1];

        if (s1 < s2) {
            if (s2 >= e1) return -1;
            else return Math.min(e1, e2) - s2;
        } else if (s1 > s2) {
            if (s1 >= e2) return -1;
            else return Math.min(e1, e2) - s1;
        } else {
            return Math.min(e1, e2) - s1;
        }
    }
}
