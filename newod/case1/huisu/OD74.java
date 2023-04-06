package newod.case1.huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 最多几个直角三角形
 * 题目描述
 * 有N条线段，长度分别为a[1]-a[n]。
 * <p>
 * 现要求你计算这N条线段最多可以组合成几个直角三角形。
 * <p>
 * 每条线段只能使用一次，每个三角形包含三条线段。
 * <p>
 * 输入描述
 * 第一行输入一个正整数T（1<＝T<＝100），表示有T组测试数据.
 * <p>
 * 对于每组测试数据，接下来有T行，
 * <p>
 * 每行第一个正整数N，表示线段个数（3<＝N<＝20），接着是N个正整数，表示每条线段长度，（0<a[i]<100）。
 * <p>
 * 输出描述
 * 对于每组测试数据输出一行，每行包括一个整数，表示最多能组合的直角三角形个数
 *
 * 解法：首先用全组合，求出所有直角三角形的组合可能
 * 然后统计出给的各种长度线段对应的数量
 * 通过回溯算法，比如遍历出（前面全组合求解出来的）第一个可能的直角三角形组合3 4 5，然后上面统计数量变为
 * 发现统计的3的数量已经为0了，因此这个三角形无法组合，继续遍历下一个可能直角三角形组合 5 12 13，然后统计数
 * 历下一个可能组合5 12 13，发现对应长度线段的数量都变为了0，因此无法组合。
 * 继续遍历，发现没有下一个可能的组合了，因此，计算出该种情况可以得到2个直角三角形组合：3 4 5以及5 12 13
 * 下面通过回溯算法，开始从第二个可能的组合开始向后遍历。
 * 最终，最多的组合数就是题解
 * 回溯里套回溯
 */
public class OD74 {
    // 先用全排列找出符合的三个整数的组合
    // 然后去掉不可行的
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int[][] cases = new int[t][];

        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = sc.nextInt();
            }
            cases[i] = arr;
        }

        getResult(cases);
    }

    public static void getResult(int[][] cases) {
        for (int[] arr : cases) {
            // 对每组测试线段升序排序
            Arrays.sort(arr);

            ArrayList<Integer[]> res = new ArrayList<>();
            dfs(arr, 0, new LinkedList<>(), res);

            int[] count = new int[100];
            for (int i : arr) {
                count[i]++;
            }

            ArrayList<Integer> ans = new ArrayList<>();
            canCombine(res, 0, count, 0, ans);
            System.out.println(ans.stream().max((a, b) -> a - b).orElse(0));
        }
    }

    // 全组合求解，即n个数中选3个
    public static void dfs(int[] arr, int index, LinkedList<Integer> path, ArrayList<Integer[]> res) {
        if (path.size() == 3) {
            if (isRightTriangle(path)) {
                res.add(path.toArray(new Integer[3]));
            }
            return;
        }

        for (int i = index; i < arr.length; i++) {
            path.add(arr[i]);
            dfs(arr, i + 1, path, res);
            path.removeLast();
        }
    }

    // 判断三条边是否可以组成直角三角形
    public static boolean isRightTriangle(LinkedList<Integer> path) {
        // 注意，path中元素是升序的，因为path是取自arr的组合，而arr是升序的
        int x = path.get(0);
        int y = path.get(1);
        int z = path.get(2);

        return x * x + y * y == z * z;
    }

    // 求解当前直角三角形中不超用线段的最多组合数
    public static void canCombine(
            ArrayList<Integer[]> ts, int index, int[] count, int num, ArrayList<Integer> ans) {
        if (index >= ts.size()) {
            ans.add(num);
            return;
        }

        for (int i = index; i < ts.size(); i++) {
            Integer[] tri = ts.get(i);
            int a = tri[0];
            int b = tri[1];
            int c = tri[2];

            if (count[a] > 0 && count[b] > 0 && count[c] > 0) {
                count[a]--;
                count[b]--;
                count[c]--;
                num++;
                canCombine(ts, i + 1, count, num, ans);
                num--;
                count[a]++;
                count[b]++;
                count[c]++;
            }
        }

        ans.add(num);
    }
}
