package newod.case1.logic;

import java.util.*;

/**
 * 机房布局
 * 题目描述
 * 小明正在规划一个大型数据中心机房，为了使得机柜上的机器都能正常满负荷工作，需要确保在每个机柜边上至少要有一个电箱。
 * 为了简化题目，假设这个机房是一整排，M表示机柜，I表示间隔，请你返回这整排机柜，至少需要多少个电箱。 如果无解请返回 -1 。
 * <p>
 * 输入描述
 * 无
 * <p>
 * 输出描述
 * 无
 * <p>
 * 解法：
 * 我这里利用了区间交集的思想：
 *
 * 即，如果机柜处于 i 位置，则该机柜的区间为 [i-1, i+1]，需要注意的是i-1和i+1不能超过边界，如果超过边界，则取边界值。
 *
 * 如果后面一个机柜的区间和前面一个机柜有交集，则代表两个机柜可以共享一个电箱。
 *
 * 这里，我使用栈结构来实现电箱数的统计，即将机柜区间尝试压入栈中，如果要要入的机柜区间range和栈顶区间top有交集，则弹出栈顶区间top，并压入range。这样最终栈中区间数，就是电箱数了。
 * 额外定义了一个stick标识，初始stick = false，表示栈顶区间未形成共享电箱
 *
 * 如果压入区间A和栈顶区间B存在交集，则将栈顶区间B弹出，并将stick=true，表示A区间已形成共享电箱
 * 如果栈顶区间已形成共享电箱，即stick=true，则此时，我们可以直接压入新区间，并将stick重置为false。
 *
 */
public class OD55 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        System.out.println(getResult(str));
    }

    public static int getResult(String str) {
        int n = str.length();
        LinkedList<Integer[]> stack = new LinkedList<>();
        boolean stick = false;

        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == 'M') {
                // // 如果机柜A两边都是机柜，或者没有间隔，则无法给机柜A放电箱，返回-1
                boolean left = i - 1 < 0 || str.charAt(i - 1) == 'M';
                boolean right = i + 1 >= n || str.charAt(i + 1) == 'M';
                if (left && right) return -1;

                // 将求解最少电箱问题，转化为区间交集问题
                Integer[] range = {Math.max(0, i - 1), Math.min(n - 1, i + 1)};

                if (stack.size() > 0 && !stick) {
                    int e1 = stack.getLast()[1];
                    int s2 = range[0];

                    if (e1 == s2) {
                        stack.removeLast();
                        stick = true;
                    }
                } else {
                    stick = false;
                }
                stack.addLast(range);
            }
        }

        return stack.size();
    }
}
