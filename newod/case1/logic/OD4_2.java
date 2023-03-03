package newod.case1.logic;

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
        /*  O(n*m)
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
        }*/

        //优化算法 矩阵的压缩 可能还挺麻烦，之后再说吧

        System.out.println(sum);

    }
}
