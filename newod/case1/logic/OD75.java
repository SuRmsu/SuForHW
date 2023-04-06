package newod.case1.logic;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 服务中心选址
 * 题目描述
 * 一个快递公司希望在一条街道建立新的服务中心。公司统计了该街道中所有区域在地图上的位置，并希望能够以此为依据为新的服务中心选址：使服务中心到所有区域的距离的总和最小。
 *
 * 给你一个数组positions，其中positions[i] = [left, right] 表示第 i 个区域在街道上的位置，其中left代表区域的左侧的起点，right代表区域的右侧终点，假设服务中心的位置为location：
 *
 * 如果第 i 个区域的右侧终点right满足 right < location，则第 i 个区域到服务中心的距离为 location - right；
 * 如果第 i 个区域的左侧起点left 满足 left > location，则第 i 个区域到服务中心的距离为left - location；
 * 如果第 i 个区域的两侧left，right满足left <= location <= right，则第 i 个区域到服务中心的距离为0
 * 选择最佳的服务中心位置为location，请返回最佳的服务中心位置到所有区域的距离总和的最小值。
 *
 * 输入描述
 * 第一行，一个整数N表示区域个数。
 * 后面N行，每行两个整数，表示区域的左右起点终点。
 * 输出描述
 * 运行结果输出一个整数，表示服务中心位置到所有区域的距离总和的最小值。
 *
 * 解法：暴力
 *
 * 如何优化？
 * https://blog.csdn.net/qfc_128220/article/details/128808359
 *
 * 随着 服务中心位置 i 的变化，服务中心到各区域的距离之和 dis 呈现上图U型曲线。
 *
 * 即，一定存在一个 i ，其左边点 i-0.5 的，和其右边点 i+0.5 到各区域的距离和大于它。
 * 因此，我想是否可以用二分法求解，即取min点和max点的中间点mid作为服务中心位置，：
 *
 * 如果 dis(mid - 0.5)  >= dis(mid)  && dis(mid+0.5) >= dis(mid)，则 mid 就是所求的点
 * 如果 dis(mid - 0.5 ) > dis(mid) > dis(mid +0.5)，则说明当前 mid 点处于上图的下降区间，此时我们应该将mid作为新的min值，然后重新取min，max的中间点作为新mid
 * 如果 dis(mid - 0.5 ) < dis(mid) < dis(mid +0.5)，则说明当前 mid 点处于上图的上升区间，此时我们应该将mid作为新的max值，然后重新取min，max的中间点作为新mid
 *
 * 找U型谷的最低点
 */
public class OD75 {
    /*
    public static void main(String[] args) {
        int n = 11;
        int[][] input = {
                {-10,10},
                {1,2},
                {3,4},
                {5,10},
                {6,8},
                {7,12},
                {9,13},
                {15,20},
                {31,41},
                {22,35},
                {34,50}
        };
        int maxIndex=0;
        for (int[] ints : input) {
            for (int anInt : ints) {
                maxIndex = Math.max(maxIndex,anInt);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= maxIndex; i++){
            min = Math.min(process(input,i),min);
        }
        System.out.println(min);
    }

    public static int process(int[][] input, int position){
        int temp = 0;
        for (int[] ints : input) {
            if (position <= ints[1] && position >= ints[0]){
                temp += 0;
            } else if ( position > ints[1]){
                temp += position - ints[1];
            } else {
                temp += ints[0] - position;
            }
        }
        return temp;
    }
     */


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        double[][] positions = new double[n][2];
        for (int i = 0; i < n; i++) {
            positions[i][0] = sc.nextInt();
            positions[i][1] = sc.nextInt();
        }

        System.out.println(getResult(n, positions));
    }

    public static double getResult(int n, double[][] positions) {
        ArrayList<Double> tmp = new ArrayList<>();
        for (double[] pos : positions) {
            tmp.add(pos[0]);
            tmp.add(pos[1]);
        }
        tmp.sort(Double::compareTo);

        double min = tmp.get(0);
        double max = tmp.get(tmp.size() - 1);

        while (min < max) {
            double mid = Math.ceil((min + max) / 2);

            double midDis = getDistance(mid, positions);
            double midLDis = getDistance(mid - 0.5, positions);
            double midRDis = getDistance(mid + 0.5, positions);

            if (midDis <= midLDis && midDis <= midRDis) {
                return midDis;
            }

            if (midDis < midLDis) {
                min = mid + 0.5;
                continue;
            }

            if (midDis < midRDis) {
                max = mid - 0.5;
            }
        }

        return 0;
    }

    public static double getDistance(double t, double[][] positions) {
        double dis = 0;
        for (double[] pos : positions) {
            double l = pos[0];
            double r = pos[1];
            if (r < t) dis += t - r;
            else if (t < l) dis += l - t;
        }
        return dis;
    }
}
