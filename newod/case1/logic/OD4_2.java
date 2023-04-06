package newod.case1.logic;

import java.util.Scanner;

/**
 * 探索地块建立
 * 题目描述
 * 给一块n*m的地块，相当于n*m的二维数组，每个元素的值表示这个小地块的发电量；
 * <p>
 * 求在这块地上建立正方形的边长为c的发电站，发电量满足目标电量k的地块数量。
 * <p>
 * 输入描述
 * 第一行为四个按空格分隔的正整数，分别表示n, m , c k
 * <p>
 * 后面n行整数，表示每个地块的发电量
 * <p>
 * 输出描述
 * 输出满足条件的地块数量
 * <p>
 * 解法：
 * 暴力：依次计算
 * 优化：矩阵压缩
 */
public class OD4_2 {
    /*
    public static void main(String[] args) {
        int[][] input = {
                {1, 3, 4, 5, 8},
                {2, 3, 6, 7, 1},
                {1, 1, 1, 1, 1},
        };
        int n = input.length;
        int m = input[0].length;
        int c = 2;
        int k = 6;

        int sum = 0;

        // 先暴力解 可以做一些剪枝，有值大于k，则直接跳出；这个效果可以用递归来实现

        for (int i = 0; i <= n - c; i++) {
            for (int j = 0; j <= m - c; j++) {
                int temp = 0;
                for (int ii = 0; ii < c; ii++) {
                    for (int jj = 0; jj < c; jj++) {
                        temp += input[i + ii][j + jj];
                    }
                }
                if (temp >= k) {
                    sum++;
                }
            }
        }

        //优化算法 矩阵的压缩 可能还挺麻烦，之后再说吧

        System.out.println(sum);

    }
    */

    /*
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int c = sc.nextInt();
        int k = sc.nextInt();

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(matrix, n, m, c, k));
    }

    public static int getResult(int[][] matrix, int n, int m, int c, int k) {
        // 行压缩
        int[][] dps = new int[n][];
        // 遍历每一行进行水平方向区块压缩
        for (int i = 0; i < n; i++) {
            int[] arr = matrix[i];
            // 每c个相邻区块压缩的话，则一行最多压缩为m - c + 1个压缩区块
            int[] dp = new int[m - c + 1];

            // 计算每行第一个压缩区块的发电量作为基准值
            for (int j = 0; j < c; j++) {
                dp[0] += arr[j];
            }

            // 第二个开始的压缩区块的发电量，基于和前面一个压缩区块的差异比较计算出，而不是重新全量计算
            for (int j = 1; j < dp.length; j++) {
                dp[j] = dp[j - 1] - arr[j - 1] + arr[j + c - 1];
            }

            dps[i] = dp;
        }

        // 列压缩
        int[][] newDps = new int[m - c + 1][];
        // 遍历经过行压缩后的地块的每一列，在垂直方向上进行压缩
        for (int i = 0; i < m - c + 1; i++) {
            // 如果c个相邻区块压缩的话，则一列最多压缩为n - c + 1个
            int[] newDp = new int[n - c + 1];

            // 计算每列第一个压缩区块的发电量作为基准值
            for (int j = 0; j < c; j++) {
                newDp[0] += dps[j][i];
            }

            // 第二个开始的压缩区块的发电量，基于和前面一个压缩区块的差异比较计算出，而不是重新全量计算
            for (int j = 1; j < newDp.length; j++) {
                newDp[j] = newDp[j - 1] - dps[j - 1][i] + dps[j + c - 1][i];
            }

            newDps[i] = newDp;
        }

        // 统计压缩区域发电量大于等于k的数量
        int ans = 0;
        for (int i = 0; i < newDps.length; i++) {
            for (int j = 0; j < newDps[i].length; j++) {
                if (newDps[i][j] >= k) {
                    ans++;
                }
            }
        }

        return ans;
    }*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int c = sc.nextInt();
        int k = sc.nextInt();

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(matrix, n, m, c, k));
    }

    /**
     * @param matrix n*m的地块
     * @param n 地块行数
     * @param m 地块列数
     * @param c 正方形的发电站边长为c
     * @param k 目标电量k
     * @return 可以建设几个发电站
     */
    public static int getResult(int[][] matrix, int n, int m, int c, int k) {
        int[][] preSum = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                preSum[i][j] =
                        preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        int ans = 0;

        for (int i = c; i <= n; i++) {
            for (int j = c; j <= m; j++) {
                int square = preSum[i][j] - (preSum[i - c][j] + preSum[i][j - c]) + preSum[i - c][j - c];
                if (square >= k) ans++;
            }
        }

        return ans;
    }}
