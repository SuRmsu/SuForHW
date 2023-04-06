package newod.case1.huisu;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 无向图染色
 * 题目描述
 * 给一个无向图染色，可以填红黑两种颜色，必须保证相邻两个节点不能同时为红色，输出有多少种不同的染色方案？
 *
 * 输入描述
 * 第一行输入M(图中节点数) N(边数)
 *
 * 后续N行格式为：V1 V2表示一个V1到V2的边。
 *
 * 数据范围：1 <= M <= 15,0 <= N <= M * 3，不能保证所有节点都是连通的。
 *
 * 输出描述
 * 输出一个数字表示染色方案的个数。
 *
 * 解法：过回溯算法，求解出染红节点的全组合
 * 我们需要尝试对组合中的节点进行染红色，但是相邻节点不能同时染成红色。因此，在求解全组合时，还可以进行剪枝优化，即判断新加入的节点  是否和 已存在的节点相邻，如果相邻，则剪枝，如果不相邻则继续递归。
 * 处于两个连通分量中的节点必然不相连，则必然可以同时染红，因此直接用前面求染红节点组合就可以，不需要用并查集。
 */
public class OD1_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] edges = new int[n][2];
        for (int i = 0; i < n; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }

        System.out.println(getResult(edges, m));
    }

    /**
     * @param edges 边，即[v1, v2]
     * @param m 点数量
     * @return
     */
    public static int getResult(int[][] edges, int m) {
        // connect用于存放每个节点的相邻节点
        HashMap<Integer, HashSet<Integer>> connect = new HashMap<>();

        for (int[] edge : edges) {
            connect.putIfAbsent(edge[0], new HashSet<>());
            connect.get(edge[0]).add(edge[1]);

            connect.putIfAbsent(edge[1], new HashSet<>());
            connect.get(edge[1]).add(edge[0]);
        }

        // 节点从index=1开始，必有count=1个的全黑染色方案
        return dfs(connect, m, 1, 1, new LinkedList<>());
    }

    // 该方法用于求解给定多个节点染红的全组合数
    public static int dfs(
            HashMap<Integer, HashSet<Integer>> connect,
            int m,
            int index,
            int count,
            // path似乎是用来统计重复的,为什么要用HashSet,存了全部相邻节点
            LinkedList<HashSet<Integer>> path) {
        // 非正常返回的异常情况
        if (path.size() == m) return count;

        outer:
        for (int i = index; i <= m; i++) {
            // 如果新加入节点i和已有节点j相邻，则说明新加入节点不能染成红色，需要进行剪枝
            for (HashSet<Integer> p : path) {
                if (p.contains(i)) continue outer;
            }
            // 当前节点能加入，可能性+1
            count++;

            if (connect.containsKey(i)) {
                //存了该点的全部的相邻节点
                path.addLast(connect.get(i));
                //这个count写在此处不是很好，count记录的是所有情况的总和，此处正好当作参数记作之前的和加上之后的现在的
                count = dfs(connect, m, i + 1, count, path);
                //移除了该点的全部相邻节点
                path.removeLast();
            } else {
                // 该点一定没有任何相邻的边，不用加到队列
                count = dfs(connect, m, i + 1, count, path);
            }
        }

        return count;
    }
}
