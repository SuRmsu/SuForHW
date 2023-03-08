package newod.case1.logic;

import java.util.Scanner;

/**
 * 最小调整顺序次数
 * 题目描述
 * 有一个特异性的双端队列，该队列可以从头部或尾部添加数据，但是只能从头部移出数据。
 *
 * 小A依次执行2n个指令往队列中添加数据和移出数据。其中n个指令是添加数据（可能从头部添加、也可能从尾部添加），依次添加1到n；n个指令是移出数据。
 *
 * 现在要求移除数据的顺序为1到n。
 *
 * 为了满足最后输出的要求，小A可以在任何时候调整队列中数据的顺序。
 *
 * 请问 小A 最少需要调整几次才能够满足移除数据的顺序正好是1到n；
 *
 * 输入描述
 * 第一行一个数据n，表示数据的范围。
 *
 * 接下来的2n行，其中有n行为添加数据，指令为：
 *
 * "head add x" 表示从头部添加数据 x，
 * "tail add x" 表示从尾部添加数据x，
 * 另外 n 行为移出数据指令，指令为："remove" 的形式，表示移出1个数据；
 *
 * 1 ≤ n ≤ 3 * 10^5。
 *
 * 所有的数据均合法。
 *
 * 输出描述
 * 一个整数，表示 小A 要调整的最小次数。
 * 解法：
 * 本题不需要模拟出一个队列，因为那样需要频繁的验证队列元素顺序，以及调整顺序，非常不划算。
 *
 * 我们可以总结规律：
 *
 * 如果队列为空，即size===0，此时无论head add，还是tail add，都不会破坏队列顺序性。
 *
 * 如果队列不为空，即size!==0，此时tail add不会破坏顺序性，head add会破坏顺序性。
 *
 * 我们定义一个变量isSorted表示队列是否有序，初始时isSorted = true，表示初始时队列有序。当有序性被破坏，即isSorted = false。
 */
public class OD11 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int m = n * 2;

        String[] cmds = new String[m];
        for (int i = 0; i < m; i++) {
            cmds[i] = sc.nextLine();
        }

        System.out.println(getResult(cmds));
    }

    public static int getResult(String[] cmds) {
        int size = 0;
        boolean isSorted = true;
        int count = 0;

        for (int i = 0; i < cmds.length; i++) {
            String cmd = cmds[i];
            if (cmd.startsWith("head add")) {
                if (size > 0 && isSorted) isSorted = false;
                size++;
            } else if (cmd.startsWith("tail add")) {
                size++;
            } else {
                if (size == 0) continue;
                if (!isSorted) {
                    count++;
                    isSorted = true;
                }
                size--;
            }
        }

        return count;

    }
}
