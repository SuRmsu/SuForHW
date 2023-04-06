package newod.case1.logic;

/**
 * 人数最多的站点
 * 题目描述
 * 公园园区提供小火车单向通行，从园区站点编号最小到最大通行如1~2~3~4~1，然后供员工在各个办公园区穿梭，通过对公司N个员工调研统计到每个员工的坐车区间，包含前后站点，请设计一个程序计算出小火车在哪个园区站点时人数最多。
 * <p>
 * 输入描述
 * 第1个行，为调研员工人数
 * <p>
 * 第2行开始，为每个员工的上车站点和下车站点。
 * 使用数字代替每个园区用空格分割，如3 5表示从第3个园区上车，在第5个园区下车
 * <p>
 * 输出描述
 * 人数最多时的园区站点编号，最多人数相同时返回编号最小的园区站点
 * <p>
 * 注：此题描述有问题，不确定是否是环，个人猜测是
 * 解法： 对于循环，考虑扩充数组为两倍大小，或者拆分
 * 优化解法：优先队列，记录每一次重叠区间的长度
 * 首先，将所有区间按开始位置升序
 * 然后，遍历排序后区间，并将小顶堆中小于遍历区间起始位置的区间弹出（小顶堆实际存储区间结束位置），此操作后，小顶堆中剩余的区间个数，就是和当前遍历区间重叠数。
 */
public class OD56 {
    public static int[] storage;

    public static void main(String[] args) {
        int n = 0;
        int[][] input = {
                {1, 3},
                {2, 4},
                {1, 4},
                {3, 4},
                {3, 4}
        };
        for (int[] ints : input) {
            n = Math.max(n, ints[1]);
        }

        storage = new int[n];
        for (int[] ints : input) {
            if (ints[0] > ints[1]) {
                process(ints[0], n);
                process(1, ints[1] - 1);
            } else {
                process(ints[0], ints[1] - 1);
            }
        }
        int max = 0;
        int maxIndex = 0;

        for (int i = 1; i < storage.length; i++) {
            max = Math.max(max, storage[i]);
            if (max == storage[i] && max != storage[i - 1]) {
                maxIndex = i;
            }
        }
        System.out.println(maxIndex + 1);
    }

    public static void process(int left, int right) {
        for (int i = left - 1; i < right; i++) {
            storage[i]++;
        }

    }


}
