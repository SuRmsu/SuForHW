package newod.case1.bfsdfs;

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
    public static int maxSignal = 0;

    public static void main(String[] args) {
        int n = 6;
        int m = 5;
        int[][] input = {
                {0, 0, 0, -1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, -1, 4, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, -1},
                {0, 0, 0, 0, 0}
        };
        int signalX = 0;
        int signalY = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                maxSignal = Math.max(maxSignal, input[i][j]);
                if (maxSignal == input[i][j]) {
                    signalX = i;
                    signalY = j;
                }
            }
        }
        int targetX = 1;
        int targetY = 4;

        process(input, maxSignal, signalX, signalY);

        System.out.println(input[targetX][targetY]);
    }

    public static void process(int[][] input, int signal, int x, int y) {
        // 如果当前值为-1，则直接返回;xy超过边界也是
        if (x < 0 || y < 0 || x >= input.length || y >= input[0].length || input[x][y] == -1  ) {
            return;
        }
        // 如果当前值为signal，上下左右都得去看看，可以省略，
/*        else if (input[x][y] == maxSignal) {
            process(input, signal - 1, x, y + 1);
            process(input, signal - 1, x, y - 1);
            process(input, signal - 1, x + 1, y);
            process(input, signal - 1, x - 1, y);
        }*/
        else {
            // 如果当前值不为0，那肯定被别人处理过了，如果自己的signal比较大，则需要处理；否则直接return;上面注释掉，下面加上=号，可能会超时
            if (signal >= input[x][y]) {
                input[x][y] = signal;
                process(input, signal - 1, x, y + 1);
                process(input, signal - 1, x, y - 1);
                process(input, signal - 1, x + 1, y);
                process(input, signal - 1, x - 1, y);
            } else {
                return;
            }
        }
    }
}


