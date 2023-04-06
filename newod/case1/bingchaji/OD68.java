package newod.case1.bingchaji;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 题目描述
 * Linux操作系统有多个发行版，distrowatch.com提供了各个发行版的资料。这些发行版互相存在关联，例如Ubuntu基于Debian开发，而Mint又基于Ubuntu开发，那么我们认为Mint同Debian也存在关联。
 * <p>
 * 发行版集是一个或多个相关存在关联的操作系统发行版，集合内不包含没有关联的发行版。
 * <p>
 * 给你一个 n * n 的矩阵 isConnected，其中 isConnected[i][j] = 1 表示第 i 个发行版和第 j 个发行版直接关联，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回最大的发行版集中发行版的数量。
 * <p>
 * 输入描述
 * 第一行输入发行版的总数量N，
 * <p>
 * 之后每行表示各发行版间是否直接相关
 * <p>
 * 输出描述
 * 输出最大的发行版集中发行版的数量
 * <p>
 * 解法：并查集
 * connected的key代表某个连通分量的顶级父节点，value代表该连通分量下的节点个数
 */
public class OD68 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(matrix, n));
    }

    public static int getResult(int[][] matrix, int n) {
        UnionFindSet ufs = new UnionFindSet(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { // j从i+1开始，是因为矩阵是对称的
                if (matrix[i][j] == 1) {
                    ufs.union(i, j);
                }
            }
        }

        // connected的key代表某个连通分量的顶级父节点，value代表该连通分量下的节点个数
        HashMap<Integer, Integer> connected = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Integer fa = ufs.find(ufs.fa[i]);
            connected.put(fa, connected.getOrDefault(fa, 0) + 1);
        }

        // 返回最大节点数
        return connected.values().stream().max((a, b) -> a - b).get();
    }


    // 并查集实现
    static class UnionFindSet {
        int[] fa;
        int count;

        public UnionFindSet(int n) {
            this.count = n;
            this.fa = new int[n];
            for (int i = 0; i < n; i++) this.fa[i] = i;
        }

        public int find(int x) {
            if (x != this.fa[x]) {
                return (this.fa[x] = this.find(this.fa[x]));
            }
            return x;
        }

        public void union(int x, int y) {
            int x_fa = this.find(x);
            int y_fa = this.find(y);

            if (x_fa != y_fa) {
                this.fa[y_fa] = x_fa;
                this.count--;
            }
        }
    }
}
