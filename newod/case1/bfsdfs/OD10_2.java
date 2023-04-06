package newod.case1.bfsdfs;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 计算网络信号、信号强度
 * 题目描述
 * 网络信号经过传递会逐层衰减，且遇到阻隔物无法直接穿透，在此情况下需要计算某个位置的网络信号值。
 * 注意:网络信号可以绕过阻隔物。
 * <p>
 * array[m][n] 的二维数组代表网格地图，
 * array[i][j] = 0代表i行j列是空旷位置;
 * array[i][j] = x(x为正整数)代表i行j列是信号源，信号强度是x;
 * array[i][j] = -1代表i行j列是阻隔物。
 * 信号源只有1个，阻隔物可能有0个或多个
 * 网络信号衰减是上下左右相邻的网格衰减1
 * 现要求输出对应位置的网络信号值。
 * <p>
 * 输入描述
 * 输入为三行，第一行为 m 、n ，代表输入是一个 m × n 的数组。
 * 第二行是一串 m × n 个用空格分隔的整数.
 * 每连续 n 个数代表一行，再往后 n 个代表下一行，以此类推。
 * 对应的值代表对应的网格是空旷位置，还是信号源，还是阻隔物。
 * 第三行是 i 、 j，代表需要计算array[i][j]的网络信号值。
 * 注意：此处 i 和 j 均从 0 开始，即第一行 i 为 0。
 *
 * 解法： 简单的BFS
 */
public class OD10_2 {
    // 剪枝，当前目标位置的最大值
    static int targetMax = 0;
    static int signalX = 0;
    static int signalY = 0;
    static int targetX = 0;
    static int targetY = 0;

    public static void main(String[] args) {



        // 先获取输入
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        // 信号源
        int signal = 0;


        int[][] storage = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int temp = sc.nextInt();
                storage[i][j] = temp;
                if (temp != 0 && temp != -1) {
                    signal = temp;
                    signalX = i;
                    signalY = j;
                }
            }
        }
        targetX = sc.nextInt();
        targetY = sc.nextInt();
        targetMax = storage[targetX][targetY];
        process(storage, signal, signalX, signalY);
        System.out.println(storage[targetX][targetY]);
    }

    public static void process(int[][] input, int Signal, int i, int j) {

        // 设置一个队列用于存储下一个需要处理的节点
        LinkedList<Integer[]> queue = new LinkedList<Integer[]>();
        queue.add(new Integer[]{i, j});

        while (queue.size() > 0) {
            Integer[] tempPoint = queue.poll();
            int tempSignal = input[tempPoint[0]][tempPoint[1]] - 1;

            // 剪枝，信号小于0没必要往后了
            if (tempSignal == 0) {
                continue;
            }
            int[][] ways = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            for (int[] way : ways) {
                int x = tempPoint[0] + way[0];
                int y = tempPoint[1] + way[1];
                if (x >= 0 && x < input.length && y >= 0 && y < input[0].length && input[x][y] == 0) {
                    input[x][y] = tempSignal;
                    queue.addLast(new Integer[]{x, y});
                }
                // 剪枝，找到目标位置值就直接跳出
                if (x == targetX && y == targetY) {
                    return;
                }
            }


        }

    }
}


