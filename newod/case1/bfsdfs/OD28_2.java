package newod.case1.bfsdfs;

/**
 * 上班之路
 * 题目描述
 * Jungle 生活在美丽的蓝鲸城，大马路都是方方正正，但是每天马路的封闭情况都不一样。
 * 地图由以下元素组成：
 * 1）”.” — 空地，可以达到;
 * 2）”*” — 路障，不可达到;
 * 3）”S” — Jungle的家;
 * 4）”T” — 公司.
 * 其中我们会限制Jungle拐弯的次数，同时Jungle可以清除给定个数的路障，现在你的任务是计算Jungle是否可以从家里出发到达公司。
 * <p>
 * 输入描述
 * 输入的第一行为两个整数t,c（0 ≤ t,c ≤ 100）,t代表可以拐弯的次数，c代表可以清除的路障个数。
 * <p>
 * 输入的第二行为两个整数n,m（1 ≤ n,m ≤ 100）,代表地图的大小。
 * <p>
 * 接下来是n行包含m个字符的地图。n和m可能不一样大。
 * <p>
 * 我们保证地图里有S和T。
 * <p>
 * 输出描述
 * 输出是否可以从家里出发到达公司，是则输出YES，不能则输出NO。
 * 解法： DFS 需要记录转弯的方法 待优化
 */
public class OD28_2 {
    static boolean flag = false;

    public static void main(String[] args) {
        char[][] storage = {
                {'*', '*', 'S', '.', '.'},
                {'*', '*', '*', '*', '.'},
                {'T', '*', '*', '*', '.'},
                {'*', '*', '*', '*', '.'},
                {'*', '*', '.', '.', '.'},
        };
        process(storage, 0, 0, 2, 2, 3);
        System.out.println(flag);
    }

    // 原数组，上一步前进的方向：1右2下3左4上，当前位置，转弯剩余次数，破屏障剩余次数
    public static void process(char[][] storage, int turnC, int i, int j, int turn, int breakBull) {
        // 满足以下条件任一，则都是无效的
        if (turn < 0 || i < 0 || i >= storage.length || j < 0 || j >= storage[0].length) {
            return;
        }
        if (storage[i][j] == '*') {
            if (breakBull < 1) {
                return;
            }
            breakBull--;
        } else if (storage[i][j] == 'T') {
            if (breakBull < 0) {
                return;
            }
            flag = true;
            System.out.println(i + " " + j);
            return;
        }
        // 用switch代替也得这么多行，考虑有无优化的方法？
        if (turnC == 0) {
            process(storage, 1, i, j + 1, turn, breakBull);
            process(storage, 2, i + 1, j, turn, breakBull);
            process(storage, 3, i, j - 1, turn, breakBull);
            process(storage, 4, i - 1, j, turn, breakBull);
        } else if (turnC == 1) {
            process(storage, 1, i, j + 1, turn, breakBull);
            process(storage, 2, i + 1, j, turn - 1, breakBull);
            process(storage, 4, i - 1, j, turn - 1, breakBull);
        } else if (turnC == 2) {
            process(storage, 1, i, j + 1, turn - 1, breakBull);
            process(storage, 2, i + 1, j, turn, breakBull);
            process(storage, 3, i, j - 1, turn - 1, breakBull);
        } else if (turnC == 3) {
            process(storage, 2, i + 1, j, turn - 1, breakBull);
            process(storage, 3, i, j - 1, turn, breakBull);
            process(storage, 4, i - 1, j, turn - 1, breakBull);
        } else if (turnC == 4) {
            process(storage, 1, i, j + 1, turn - 1, breakBull);
            process(storage, 3, i, j - 1, turn - 1, breakBull);
            process(storage, 4, i - 1, j, turn, breakBull);
        }
    }
}
