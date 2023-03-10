package newod.case1.huisu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 现在有n个容器服务，服务的启动可能有一定的依赖性（有些服务启动没有依赖），其次服务自身启动加载会消耗一些时间。
 * <p>
 * 给你一个 n x n 的二维矩阵useTime，其中
 * <p>
 * useTime[i][i]=10 表示服务i自身启动加载需要消耗10s
 * useTime[i][j] = 1 表示服务i启动依赖服务j启动完成
 * useTime[i][k]=0  表示服务i启动不依赖服务k
 * 其实 0<= i，j，k < n。
 * <p>
 * 服务之间启动没有循环依赖（不会出现环），若想对任意一个服务i进行集成测试（服务i自身也需要加载），求最少需要等待多少时间。
 * <p>
 * 输入描述
 * 第一行输入服务总量 n，
 * 之后的 n 行表示服务启动的依赖关系以及自身启动加载耗时
 * 最后输入 k 表示计算需要等待多少时间后可以对服务 k 进行集成测试
 * <p>
 * 其中 1 <= k <=n，1<=n<=100
 * <p>
 * 输出描述
 * 最少需要等待多少时间(s)后可以对服务 k 进行集成测试
 *
 * 解法：
 * 现在要求解服务4的启动时间，其实就是：
 *
 * 服务4的启动时间 = max(服务1启动时间， 服务2启动时间， 服务3启动时间)  + 本身启动时间
 *
 * 其中，服务1，服务2没有前置服务，因此他们的启动时间就是本身启动时间。
 *
 * 而，服务3有前置服务，因此服务3的启动时间 = max(服务1启动时间， 服务2启动时间）+ 本身启动时间
 *
 * 因此，服务3的启动时间 = max(2, 3) + 4 = 7
 *
 * 进而，服务4的启动时间 = max(2, 3, 7) + 5 = 12
 */
public class OD61 {
    static int[] cache;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        cache = new int[n];
        Arrays.fill(cache, -1);

        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        int k = sc.nextInt();

        System.out.println(getResult(matrix, n, k));
    }

    public static int getResult(int[][] matrix, int n, int k) {
        return dfs(k - 1, matrix);
    }

    public static int dfs(int k, int[][] matrix) {
        // cache用于记录每个服务所需要时间，避免多个子服务依赖同一个父服务时，对父服务启动时间的重复递归求解
        if (cache[k] != -1) return cache[k];

        int[] preK = matrix[k];

        int maxPreTime = 0;
        for (int i = 0; i < preK.length; i++) {
            if (i != k && preK[i] != 0) {
                maxPreTime = Math.max(maxPreTime, dfs(i, matrix));
            }
        }

        cache[k] = matrix[k][k] + maxPreTime;

        return cache[k];
    }
}
