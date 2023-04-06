package newod.case1.dandiaozhan;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 信号发射和接收
 * 题目描述
 * 有一个二维的天线矩阵，每根天线可以向其他天线发射信号，也能接收其他天线的信号，为了简化起见，我们约定每根天线只能向东和向南发射信号，换言之，每根天线只能接收东向或南向的信号。
 *
 * 每根天线有自己的高度anth，每根天线的高度存储在一个二维数组中，各个天线的位置用[r, c]表示，r代表天线的行位置（从0开始编号），c代表天线的列位置（从0开始编号）。
 *
 * 在某一方向（东向或南向），某根天线可以收到多根其他天线的信号（也可能收不到任何其他天线的信号），对任一天线X和天线Y，天线X能接收到天线Y的条件是：
 *
 * 天线X在天线Y的东边或南边
 * 天线X和天线Y之间的其他天线的高度都低于天线X和天线Y，或天线X和天线Y之间无其他天线，即无遮挡。
 *
 * 在天线矩阵的第0行上：
 *
 * 天线[0, 0]接收不到任何其他天线的信号，
 * 天线[0, 1]可以接收到天线[0, 0]的信号，
 * 天线[0, 2]可以接收到天线[0, 1]的信号，
 * 天线[0, 3]可以接收到天线[0, 1]和天线[0, 2]的信号，
 * 天线[0, 4]可以接收到天线[0, 3]的信号，
 * 天线[0, 5]可以接收到天线[0, 4]的信号；
 * 在天线的第0列上：
 *
 * 天线[0, 0]接收不到任何其他天线的信号，
 * 天线[1, 0]可以接收到天线[0, 0]的信号，
 * 天线[2, 0]可以接收到天线[1, 0]的信号，
 * 天线[3, 0]可以接收到天线[1, 0]和天线[2, 0]的信号，
 * 天线[4, 0]可以接收到天线[3, 0]的信号，
 * 天线[5, 0]可以接收到天线[3, 0]和天线[4, 0]的信号；
 * 给一个m行n列的矩阵（二维数组），矩阵存储各根天线的高度，求出每根天线可以接收到多少根其他天线的信号，结果输出到m行n列的矩阵（二维矩阵）中。
 *
 * 输入描述
 * 输入为1个m行n列的矩阵（二维矩阵）anth[m][n]，矩阵存储各根天线的高度，高度值anth[r]][c]为大于0的整数。
 *
 * 第一行为输入矩阵的行数和列数，如：
 *
 * m n
 *
 * 第二行为输入矩阵的元素值，按行输入，如：
 *
 * anth[0][0] anth[0][1] ... anth[0][n-1] anth[1][0] anth[1][1] ... anth[1][n-1] ... anth[m-1][0] ... anth[m-1][n-1]
 *
 * 输出描述
 * 输出一个m行n列的矩阵（二维数组）ret[m][n]，矩阵存储每根天线能收到多少根其他天线的信号，根数为ret[r][c]。
 *
 * 第一行为输出矩阵的行数和列数，如:
 *
 * m n
 *
 * 第二行为输出矩阵的元素值，按行输出，如：
 *
 * ret[0][0] ret[0][1] ... ret[0][n-1] ret[1][0] ret[1][1] ... ret[1][n-1] ... ret[m-1][0] ... ret[m-1][n-1]
 *
 * 解法：
 * 首先定义一个单调递减栈stack，然后开始遍历天线anth[i][j]（比如先处理东向，即按行从左到右遍历）：
 *
 * 1、如果stack为空，则直接将天线anth[i][j]加入stack
 *
 * 2、如果stack不为空，则获取栈顶天线top
 *
 * 2.1、如果anth[i][j] > top，则将stack栈顶的top弹出，然后anth[i][j]对应的ret[i][j]++，表示anth[i][j]天线新增接收一个信号，而由于stack栈是递减栈，因此anth[i][j]还可以继续接收新栈顶天线的信号
 *
 * 2.2、如果anth[i][j] == top，则将stack栈顶的top弹出，然后anth[i][j]新增接收一个信号，ret[i][j]++。（注意，由于stack是严格递减栈，因此如果栈顶元素和anth[i][j]等高，则必然只有一个，且stack弹栈后的新栈顶必然大于anth[i][j]，此时其实可以直接结束）
 *
 * 2.3、如果anth[i][j] < top，则表示anth[i][j]已经无法接收到top之前的信号了，因为已经被top完全阻挡了。因此anth[i][j]只能栈顶天线的信号，ret[i][j]++，而无法继续接收前面天线的信号。
 */
public class OD69 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] anth = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                anth[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(anth, m, n));
    }

    public static String getResult(int[][] anth, int m, int n) {
        int[][] ret = new int[m][n];

        // 先处理南向发射信号
        for (int j = 0; j < n; j++) {
            LinkedList<Integer> stack = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                // 如果栈顶天线比anth[i][j]，则anth[i][j]必然能接收到栈顶天线的信号，并且还能继续接收栈顶前面一个天线的信号（递减栈，栈顶前面天线高度必然大于栈顶天线高度）
                while (stack.size() > 0 && anth[i][j] > stack.getLast()) {
                    ret[i][j] += 1;
                    stack.removeLast();
                }

                // 走到此步，如果stack还有值，那么由于是递减栈，因此此时栈顶天线高度必然
                if (stack.size() > 0) {
                    // 如果栈顶天线高度 == anth[i][j]，那么此时anth[i][j]可以接收栈顶天线的信号，
                    // 比如5 3 2 3，最后一个3可以接收到前面等高3的信号，但是无法继续接收前面5的信号，因此这里anth[i][j]结束处理
                    if (anth[i][j] == stack.getLast()) {
                        ret[i][j] += 1;
                        stack.removeLast(); // 维护严格递减栈
                    }
                    // 此情况必然是：anth[i][j] < stack.at(-1)，那么此时anth[i][j]可以接收栈顶天线的信号，
                    // 比如6 5 2 3，最后一个3可以接收到前面5的信号，但是无法继续接收更前面6的信号，因此这里anth[i][j]结束处理
                    else {
                        ret[i][j] += 1;
                    }
                }

                stack.add(anth[i][j]);
            }
        }

        StringJoiner sj = new StringJoiner(" ");

        // 再处理东向发射信号,和上面同理
        for (int i = 0; i < m; i++) {
            LinkedList<Integer> stack = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                // 如果栈顶天线比anth[i][j]，则anth[i][j]必然能接收到栈顶天线的信号，并且还能继续接收栈顶前面一个天线的信号（递减栈，栈顶前面天线高度必然大于栈顶天线高度）
                while (stack.size() > 0 && anth[i][j] > stack.getLast()) {
                    ret[i][j] += 1;
                    stack.removeLast();
                }

                // 走到此步，如果stack还有值，那么由于是递减栈，因此此时栈顶天线高度必然
                if (stack.size() > 0) {
                    // 如果栈顶天线高度 == anth[i][j]，那么此时anth[i][j]可以接收栈顶天线的信号，
                    // 比如5 3 2 3，最后一个3可以接收到前面等高3的信号，但是无法继续接收前面5的信号，因此这里anth[i][j]结束处理
                    if (anth[i][j] == stack.getLast()) {
                        ret[i][j] += 1;
                        stack.removeLast(); // 维护严格递减栈
                    }
                    // 此情况必然是：anth[i][j] < stack.at(-1)，那么此时anth[i][j]可以接收栈顶天线的信号，
                    // 比如6 5 2 3，最后一个3可以接收到前面5的信号，但是无法继续接收更前面6的信号，因此这里anth[i][j]结束处理
                    else {
                        ret[i][j] += 1;
                    }
                }

                stack.add(anth[i][j]);
                sj.add(ret[i][j] + "");
            }
        }

        return m + " " + n + "\n" + sj.toString();
    }
}
