package newod.case1.logic;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 投篮大赛
 * 题目描述
 * 你现在是一场采用特殊赛制投篮大赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。
 * 比赛开始时，记录是空白的。
 * 你会得到一个记录操作的字符串列表 ops，其中ops[i]是你需要记录的第i项操作，ops遵循下述规则：
 *
 * 整数x-表示本回合新获得分数x
 * “+” – 表示本回合新获得的得分是前两次得分的总和。
 * “D” – 表示本回合新获得的得分是前一次得分的两倍。
 * “C” – 表示本回合没有分数，并且前一次得分无效，将其从记录中移除。
 * 请你返回记录中所有得分的总和。
 *
 * 输入描述
 * 输入为一个字符串数组
 *
 * 输出描述
 * 输出为一个整形数字
 *
 * 提示
 * 1 <= ops.length <= 1000
 * ops[i] 为 “C”、“D”、“+”，或者一个表示整数的字符串。整数范围是 [-3 * 10^4, 3 * 10^4]
 * 需要考虑异常的存在，如有异常情况，请返回-1
 * 对于“+”操作，题目数据不保证记录此操作时前面总是存在两个有效的分数
 * 对于“C”和“D”操作，题目数据不保证记录此操作时前面存在一个有效的分数
 * 题目输出范围不会超过整型的最大范围，不超过2^63 - 1
 *
 * 解法； String reg = "^\\-?\\d+$" 正则判断是不是一个数
 *
 *
 */
public class OD40 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        String[] ops = str.split(" ");

        System.out.println(getResult(ops));
    }

    public static int getResult(String[] ops) {
        // ans用于保存每轮的得分
        LinkedList<Integer> ans = new LinkedList<>();
        String reg = "^\\-?\\d+$";

        for (String op : ops) {
            // 如果op是整数，则表示本轮得分，直接加入ans
            if (op.matches(reg)) {
                ans.addLast(Integer.parseInt(op));
            } else {
                switch (op) {
                    // 如果op是+，则表示本轮得分是前两轮得分之和，注意越界处理
                    case "+":
                        if (ans.size() < 2) return -1;
                        ans.addLast(ans.getLast() + ans.get(ans.size() - 2));
                        break;
                    // 如果op是D，表示本轮得分是前一轮得分的双倍，注意越界处理
                    case "D":
                        if (ans.size() < 1) return -1;
                        ans.addLast(ans.getLast() * 2);
                        break;
                    // 如果op是C，则表示本轮无得分，且上一轮得分无效，需要去除
                    case "C":
                        // 感谢网友m0_71826536的提示，由于题目说：对于“C”和“D”操作，题目数据不保证记录此操作时前面存在一个有效的分数，因此这里C操作，不能直接removeLast，需要先判断ans是否有数据
                        if (ans.size() < 1) return -1;
                        ans.removeLast();
                        break;
                }
            }
        }

        int sum = 0;
        for (Integer an : ans) {
            sum += an;
        }
        return sum;
    }

}
