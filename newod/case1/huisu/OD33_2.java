package newod.case1.huisu;

import java.util.Arrays;
import java.util.Stack;

/**
 * 九宫格
 * 题目描述
 * 九宫格是一款广为流传的游戏，起源于河图洛书。
 * 游戏规则是：1到9九个数字放在3×3的格子中，要求每行、每列以及两个对角线上的三数之和都等于15.
 * 在金麻名著《射雕英雄传》中黃蓉曾给九宫格的一种解法，口诀：戴九恩一，左三右七，二四有肩，八六为足，五居中央。解法如图所示。
 * <p>
 * 现在有一种新的玩法，给九个不同的数字，将这九个数字放在3×3的格子中，要求每行、每列以及两个对角线上的三数之积相等（三阶积幻方）。其中一个三阶幻方如图：
 * 解释：每行、每列以及两个对角线上的三数之积相等，都为216。请设计一种算法，将给定的九个数宇重新排列后，使其满足三阶积幻方的要求。
 * 排列后的九个数宇中：第1-3个数字为方格的第一行，第4-6个数宇为方格的第二行，第7-9个数字为方格的第三行。
 * <p>
 * 输入描述
 * 九个不同的数宇，每个数字之间用空格分开。
 * 0＜数字<10^7。0<排列后满足要求的每行、每列以及两个对角线上的三数之积 ＜ 2^31-1。
 * <p>
 * 输出描述
 * 九个数字所有满足要求的排列，每个数字之间用空格分开。每行输出一个满足要求的排列。
 * 要求输出的排列升序排序，即：对于排列A (A1.A2.A3…A9)和排列B(B1,B2,B3…B9），从排列的第1个数字开始，遇到Ai<Bi，则排列A<排列B （1<=j<=9)。
 * <p>
 * 说明：用例保证至少有一种排列组合满足条件。
 * <p>
 * 解法：暴力排列所有组合，判断每个组合是否满足；直接最小排序
 * 得用used数组表示是否使用过 用 boolean[]
 * 需要剪枝
 */
public class OD33_2 {
    public static void main(String[] args) {
        Integer[] tempStorage = {75, 36, 10, 4, 30, 225, 90, 25, 12};
        Arrays.sort(tempStorage);
        //Integer[] a = {10,225,12,36,30,25,75,4,90};
        boolean[] used = new boolean[tempStorage.length];
        process(tempStorage, used);
    }

    static Stack<Integer> stack = new Stack<Integer>();

    public static void process(Integer[] input, boolean[] used) {
        if (stack.size() == input.length) {
            Integer[] temp = new Integer[input.length];
            if (isValid(stack.toArray(temp))) {
                System.out.println(Arrays.toString(temp));
            }
            return;
        }

        for (int i = 0; i < input.length; i++) {
            if (!used[i]) {
                used[i] = true;
                stack.push(input[i]);
                process(input, used);
                stack.pop();
                used[i] = false;
            }

        }
    }

    public static boolean isValid(Integer[] input) {
        int target = input[0] * input[1] * input[2];
        // 判断行是不是都相等
        for (int i = 1; i < 3; i++) {
            if (target != input[i * 3] * input[i * 3 + 1] * input[i * 3 + 2]) {
                return false;
            }
            ;
        }
        // 判断列是不是都相等
        for (int i = 0; i < 3; i++) {
            if (target != input[0 + i] * input[3 + i] * input[6 + i]) {
                return false;
            }
            ;
        }
        // 判断对角线是不是都相等
        if (target != input[0] * input[4] * input[8] || target != input[2] * input[4] * input[6]) {
            return false;
        }
        return true;
    }
}
