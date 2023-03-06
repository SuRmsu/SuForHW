package newod.case1.logic;

import java.util.*;

/**
 * 机房布局
 * 题目描述
 * 小明正在规划一个大型数据中心机房，为了使得机柜上的机器都能正常满负荷工作，需要确保在每个机柜边上至少要有一个电箱。
 * 为了简化题目，假设这个机房是一整排，M表示机柜，I表示间隔，请你返回这整排机柜，至少需要多少个电箱。 如果无解请返回 -1 。
 * <p>
 * 输入描述
 * 无
 * <p>
 * 输出描述
 * 无
 * <p>
 * 解法：
 * 用HashMap记录等和的子区间，然后对每一个值进行如下操作
 * 而求解给定的多个区间的最大不相交区间数量，有固定策略：
 * <p>
 * 假设不相交区间数量为count，则至少为1
 * <p>
 * 1、先将多个区间，按照右边界升序
 * <p>
 * 2、然后取排序后第一个区间的右边界作为 t，并遍历之后的下一个区间[l, r]：
 * <p>
 * 如果 l <= t，则说明当前两个区间有交集，则舍弃遍历的区间，继续遍历下一个区间
 * 如果 l > t，则说明当前两个区间无交集，即不相交，此时 t = r，并且不相交区间数量count++，然后继续下一次遍历
 * 这样最后得到的不相交区间数量count，就是最大不相交区间数量
 */
public class OD25_2 {
    public static void main(String[] args) {
        int[] input = {8, 8, 9, 1, 9, 6, 3, 9, 1, 0};
//        int[] input = {-1, 0, 4, -3, 6, 5, -6, 5, -7, -3};

        // 存储等和的子队列
        HashMap<Integer, ArrayList<Integer[]>> storage = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            int temp = input[i];
            storage.putIfAbsent(temp, new ArrayList<>());
            Integer[] tempI = {i, i};
            storage.get(temp).add(tempI);

            for (int j = i + 1; j < input.length; j++) {
                temp += input[j];
                storage.putIfAbsent(temp, new ArrayList<>());
                Integer[] tempJ = {i, j};
                storage.get(temp).add(tempJ);
            }
        }
        // 可以对storage按照Arraylist的size排个序，等于1的就直接跳过
        List<Map.Entry<Integer, ArrayList<Integer[]>>> entry = new ArrayList<>(storage.entrySet());
        entry.sort((o1, o2) -> o2.getValue().size() - o1.getValue().size());

        // 开始对重叠的区间进行一个取舍
        /*
         * 而求解给定的多个区间的最大不相交区间数量，有固定策略：
         * <p>
         * 假设不相交区间数量为count，则至少为1
         * <p>
         * 1、先将多个区间，按照右边界升序
         * <p>
         * 2、然后取排序后第一个区间的右边界作为 t，并遍历之后的下一个区间[l, r]：
         * <p>
         * 如果 l <= t，则说明当前两个区间有交集，则舍弃遍历的区间，继续遍历下一个区间
         * 如果 l > t，则说明当前两个区间无交集，即不相交，此时 t = r，并且不相交区间数量count++，然后继续下一次遍历
         * 这样最后得到的不相交区间数量count，就是最大不相交区间数量
         */
        int maxCount = 1;
        for (Map.Entry<Integer, ArrayList<Integer[]>> listEntry : entry) {
            // 剪个枝，如果maxCount已经等于最大值了，那就算了
            if (listEntry.getValue().size() <= 1 || listEntry.getValue().size() <= maxCount) {
                break;
            }

            // 按右边界递增
            listEntry.getValue().sort((o1, o2) -> o1[1] - o2[1]);
            // 处理
            int count = 1;
            int t = listEntry.getValue().get(0)[1];
            for (int i = 1; i < listEntry.getValue().size(); i++) {
                if (listEntry.getValue().get(i)[0] <= t) {
                    continue;
                } else {
                    count++;
                    t = listEntry.getValue().get(i)[1];
                }
            }
            maxCount = Math.max(count, maxCount);

        }


        System.out.println(maxCount);
    }
}
