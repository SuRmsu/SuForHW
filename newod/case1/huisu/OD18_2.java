package newod.case1.huisu;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 基站维护工程师
 *
 * 题目描述
 * 小王是一名基站维护工程师，负责某区域的基站维护。
 * 某地方有  n  个基站(1 < n < 10)，已知各基站之间的距离 s(0 < s < 500)，并且基站 x 到基站 y 的距离，与基站 y 到基站 x 的距离并不一定会相同。
 * 小王从基站 1 出发，途经每个基站 1 次，然后返回基站 1 ，需要请你为他选择一条距离最短的路。
 *
 * 输入描述
 * 站点数n和各站点之间的距离(均为整数)
 *
 * 输出描述
 * 最短路程的数值
 *
 * 解法：
 * 并且按照题目输入来看，每个站点都与剩下的其他站点相连，因此本题其实就是求解n-1个站点（即2~n站点，起始站点1）的全排列。
 * 比如用例一共三个站点，从1站点出发，即求2，3站点的全排列：23，32
 * 因此一共有两种途径选择：1 → 2 → 3 → 1 和  1 → 3 → 2 → 1
 * 我们只要比较各排列路径中距离最小的即为题解。
 * 两个站点i，j之间距离，即为matrix[i-1][j-1]，比如求解1 → 2距离，起始就是matrix[0][1]。
 *
 * 题目中说 1 < n < 10 ，也就是说最多有9个站点，而我们求解n-1个站点的全排列，即8个站点的全排列，一共有8！= 40320 个，
 * 每个排列求解距离要进行一个O(n)的遍历，即9次遍历。
 * 因此一共是差不多40w次循环，好在没什么计算量。
 */
public class OD18_2 {

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
        boolean[] used = new boolean[n];
        LinkedList<Integer> path = new LinkedList<>();
        int[] ans = {Integer.MAX_VALUE};

        dfs(n, used, path, ans, matrix);

        return ans[0];
    }

    public static void dfs(
            int n, boolean[] used, LinkedList<Integer> path, int[] ans, int[][] matrix) {
        if (path.size() == n - 1) {
            int dis = matrix[0][path.get(0)];
            for (int i = 0; i < path.size() - 1; i++) {
                int p = path.get(i);
                int c = path.get(i + 1);
                dis += matrix[p][c];
            }
            dis += matrix[path.getLast()][0];
            ans[0] = Math.min(ans[0], dis);
            return;
        }

        for (int i = 1; i < n; i++) {
            if (!used[i]) {
                path.push(i);
                used[i] = true;
                dfs(n, used, path, ans, matrix);
                used[i] = false;
                path.pop();
            }
        }
    }
}
