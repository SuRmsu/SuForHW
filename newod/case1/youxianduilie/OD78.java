package newod.case1.youxianduilie;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 插队
 * 题目描述
 * 某银行将客户分为了若干个优先级， 1 级最高， 5 级最低，当你需要在银行办理业务时，优先级高的人随时可以插队到优先级低的人的前面。
 *
 * 现在给出一个人员到来和银行办理业务的时间序列，请你在每次银行办理业务时输出客户的编号。
 *
 * 如果同时有多位优先级相同且最高的客户，则按照先来后到的顺序办理。
 *
 * 输入描述
 * 输入第一行是一个正整数 n ,表示输入的序列中的事件数量。(1 ≤ n ≤ 500)
 *
 * 接下来有 n 行，每行第一个字符为 a 或 p 。
 *
 * 当字符为 a 时，后面会有两个的正整数 num 和 x ,表示到来的客户编号为 num ,优先级为 x ;
 *
 * 当字符为 p 时，表示当前优先级最高的客户去办理业务。
 *
 * 输出描述
 * 输出包含若干行，对于每个 p ， 输出一行，仅包含一个正整数 num , 表示办理业务的客户编号。
 *
 * 解法：
 * 简单的优先队列应用。
 * 但是本题需要注意几个问题：
 *
 * 1、客户编号应该不是先来后到的顺序，输入顺序才是
 * 2、会不会存在p数量超过a数量的情况？我这边考虑了这种情况，
 * 如果p时，优先队列已经没有客户了，那么就输出空
 */
public class OD78 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        String[][] arr = new String[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLine().split(" ");
        }

        getResult(n, arr);
    }

    public static void getResult(int n, String[][] arr) {
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        for (int i = 0; i < n; i++) {
            String[] tmp = arr[i];
            switch (tmp[0]) {
                case "a":
                    int num = Integer.parseInt(tmp[1]);
                    int x = Integer.parseInt(tmp[2]);
                    pq.offer(new int[] {x, i, num}); // i 是先来后到的顺序
                    break;
                case "p":
                    int[] poll = pq.poll();
                    if (poll != null) System.out.println(poll[2]);
                    else System.out.println("");
            }
        }
    }
}
