package newod.case1.logic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 区间重叠
 * 题目描述
 * 给定坐标轴上的一组线段，线段的起点和终点均为整数并且长度不小于1，请你从中找到最少数量的线段，这些线段可以覆盖柱所有线段。
 * <p>
 * 输入描述
 * 第一行输入为所有线段的数量，不超过10000，后面每行表示一条线段，格式为"x,y"，x和y分别表示起点和终点，取值范围是[-10^5，10^5]。
 * <p>
 * 输出描述
 * 最少线段数量，为正整数
 * <p>
 * 解法：暴力 栈， 需要继续思考
 * 优化；
 * 首先，将所有区间ranges按照开始位置升序。
 *
 * 然后，创建一个辅助的栈stack，初始时将排序后的第一个区间压入栈中。
 *
 * 然后，遍历出1~ranges.length范围内的每一个区间ranges[i]，将遍历到ranges[i]和stack栈顶区间对比：
 *
 * 如果stack栈顶区间可以包含ranges[i]区间，则range[i]不压入栈顶
 * 如果stack栈顶区间被ranges[i]区间包含，则弹出stack栈顶元素，继续比较ranges[i]和stack新的栈顶元素
 * 如果stack栈顶区间和ranges[i]无法互相包含，只有部分交集，则将ranges[i]区间去除交集部分后，剩余部分区间压入stack
 * 如果stack栈顶区间和ranges[i]区间没有交集，那么直接将ranges[i]压入栈顶
 */
public class OD7_2 {
    /*
    public static void main(String[] args) {
        Integer[][] input = {
                {1, 4},
                {1, 3},
                {2, 5},
                {2, 7},
                {3, 7},
                {6, 8},
                {9, 10}
        };
        int n = 3;
        //从小到大排序
        Arrays.sort(input, (s1, s2) -> s1[0] - s2[0]);
        Stack<Integer[]> storage = new Stack<Integer[]>();
        storage.push(input[0]);
        int count = 0;
        int sum = 0;
        int flag = 0;
        for (int i = 1; i < input.length; i++) {
            Integer[] temp = storage.peek();

            // 情况就是这么个情况
            // 当前的起始值，小于等于前一个的终止值
            if (input[i][0] <= temp[1]) {
                // 当前的终止值，小于等于前一个的终止值
                if (input[i][1] <= temp[1]) {
                    sum += count;
                    count = 0;
                    flag = 0;
                    continue;
                    // 当前的终止值，大于前一个的终止值
                } else {
                    // 起始值也相等，你出来，我进去
                    if (input[i][0] == temp[0]) {
                        sum += count;
                        flag = 0;
                        count = 0;
                        storage.pop();
                        storage.push(input[i]);
                        // 起始值不相等，我也进去
                    } else {
                        // 不对，flag用来将连续的替换变为1个  1,4 2,5 3,6
                        if (flag == 0) {
                            count = 1;
                        }
                        temp[1] = input[i][1];
                        storage.push(temp);
                    }
                }
            }
            // 当前的起始值，大于前一个的终止值（当前终止值不需要考虑了）：直接把当前的添加进去
            if (input[i][0] > temp[1]) {
                sum += count;
                storage.push(input[i]);
                flag = 0;
                count = 0;
            }
        }
        System.out.println(storage.size() - sum);

    }
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Integer[][] ranges = new Integer[n][];
        for (int i = 0; i < n; i++) {
            ranges[i] =
                    Arrays.stream(sc.nextLine().split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        }

        System.out.println(getResult(ranges));
    }

    public static int getResult(Integer[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);

        LinkedList<Integer[]> stack = new LinkedList<>();
        stack.add(ranges[0]);

        for (int i = 1; i < ranges.length; i++) {
            Integer[] range = ranges[i];

            while (true) {
                if (stack.size() == 0) {
                    stack.add(range);
                    break;
                }

                Integer[] top = stack.getLast();
                int s0 = top[0];
                int e0 = top[1];

                int s1 = range[0];
                int e1 = range[1];

                if (s1 <= s0) {
                    if (e1 <= s0) {
                        break;
                    } else if (e1 < e0) {
                        break;
                    } else {
                        stack.removeLast();
                    }
                } else if (s1 < e0) {
                    if (e1 <= e0) {
                        break;
                    } else {
                        stack.add(new Integer[] {e0, e1});
                        break;
                    }
                } else {
                    stack.add(range);
                    break;
                }
            }
        }

        return stack.size();
    }
}
