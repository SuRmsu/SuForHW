package newod.case1.bingchaji;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 机器人
 * 题目描述
 * 现有一个机器人，可放置于 M × N 的网格中任意位置，每个网格包含一个非负整数编号，当相邻网格的数字编号差值的绝对值小于等于 1 时，机器人可以在网格间移动。
 *
 *
 * 问题： 求机器人可活动的最大范围对应的网格点数目。
 * 说明：网格左上角坐标为 (0,0) ,右下角坐标为(m−1,n−1)，机器人只能在相邻网格间上下左右移动
 *
 * 输入描述
 * 第 1 行输入为 M 和 N ， M 表示网格的行数 N 表示网格的列数
 * 之后 M 行表示网格数值，每行 N 个数值（数值大小用 k 表示），
 * 数值间用单个空格分隔，行首行尾无多余空格。
 * M、 N、 k 均为整数，且 1 ≤ M,N ≤ 150, 0 ≤ k ≤ 50
 *
 * 输出描述
 * 输出 1 行，包含 1 个数字，表示最大活动区域的网格点数目，
 * 行首行尾无多余空格。
 *
 * 解法：并查集
 *  // 注意下面这层for是常量级的，就四次循环，因此，这里的时间复杂度还是O(n*m)，
 *  // 当相邻网格的数字编号差值的绝对值小于等于 1 时，机器人可以在网格间移动，即表示网格连通，可以合并
 *  这个for循环是有必要的，确保每个点都指向祖先
 *  统计指向同一个祖先下点的个数，即每个连通分量下的点个数
 *  取最大个数，这里必然有值，因此不需要在get前判空
 */
public class OD25 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(matrix, m, n));
    }

    public static int getResult(int[][] matrix, int m, int n) {
        UnionFindSet ufs = new UnionFindSet(m * n);

        // 上下左右的偏移量
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 注意下面这层for是常量级的，就四次循环，因此，这里的时间复杂度还是O(n*m)，即
                for (int[] offset : offsets) {
                    int newI = i + offset[0];
                    int newJ = j + offset[1];

                    if (newI < 0 || newI >= m || newJ < 0 || newJ >= n) continue;
                    // 当相邻网格的数字编号差值的绝对值小于等于 1 时，机器人可以在网格间移动，即表示网格连通，可以合并
                    if (Math.abs(matrix[i][j] - matrix[newI][newJ]) <= 1) {
                        ufs.union(i * n + j, newI * n + newJ);
                    }
                }
            }
        }

        int total = m * n;

        // ufs.count是连通分量的个数，如果只有一个连通分量，那么代表所有的点都可达
        if (ufs.count == 1) return total;

        // 这个for循环是有必要的，确保每个点都指向祖先 很重要
        for (int i = 0; i < total; i++) {
            ufs.find(i);
        }

        // 统计指向同一个祖先下点的个数，即每个连通分量下的点个数
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i : ufs.fa) {
            count.put(i, count.getOrDefault(i, 0) + 1);
        }

        // 取最大个数，这里必然有值，因此不需要在get前判空
        return count.values().stream().max((a, b) -> a - b).get();
    }
}

// 并查集
class UnionFindSet {
    int[] fa;
    int count;

    public UnionFindSet(int n) {
        this.fa = new int[n];
        this.count = n;
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
