package newod.case1.dandiaozhan;

import java.util.*;

/**
 * 分奖金
 * 题目描述
 * 公司老板做了一笔大生意，想要给每位员工分配一些奖金，想通过游戏的方式来决定每个人分多少钱。
 * 按照员工的工号顺序，每个人随机抽取一个数字。
 * 按照工号的顺序往后排列，遇到第一个数字比自己数字大的，那么，前面的员工就可以获得“距离*数字差值”的奖金。
 * 如果遇不到比自己数字大的，就给自己分配随机数数量的奖金。
 * 例如，按照工号顺序的随机数字是：2,10,3。
 * 那么第2个员工的数字10比第1个员工的数字2大，
 * 所以，第1个员工可以获得1*（10-2）=8。第2个员工后面没有比他数字更大的员工，
 * 所以，他获得他分配的随机数数量的奖金，就是10。
 * 第3个员工是最后一个员工，后面也没有比他更大数字的员工，所以他得到的奖金是3。
 * <p>
 * 请帮老板计算一下每位员工最终分到的奖金都是多少钱。
 * <p>
 * 输入描述
 * 第一行n表示员工数量（包含最后一个老板）
 * 第二是每位员工分配的随机数字
 * <p>
 * 输出描述
 * 最终每位员工分到的奖金数量
 * <p>
 * 注：随机数字不重复，员工数量（包含老板）范围1~10000，随机数范围1~100000
 *
 * 待完善
 * 解法：1. 暴力双循环，可能会超时
 *      2. 用一个一维数组记录比当前数大的最大的值的索引
 */
public class OD20_2 {

    /*
    public static void main(String[] args) {
        int[] input = {2, 10, 3,12};
        int[] output = new int[input.length];


        for (int i = 0; i < input.length; i++) {
            int max = input[i];
            int index = 0;
            for (int j = i + 1; j < input.length; j++) {
                if (max < input[j]) {
                    max = input[j];
                    index = j;
                }
            }
            if (max == input[i]) {
                    output[i] = input[i];
            } else {
                output[i] = (max - input[i]) * (index - i);
                System.out.println(output[i]);
            }
        }*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();

        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(getResult(arr, m));
    }

    public static String getResult(int[] arr, int m) {
        LinkedList<Integer[]> stack = new LinkedList<>();
        Integer[] nextBigger = new Integer[m];
        Arrays.fill(nextBigger, -1);

        for (int i = 0; i < arr.length; i++) {
            while (stack.size() > 0) {
                Integer[] top = stack.peek();
                int idx = top[0];
                int val = top[1];

                if (arr[i] > val) {
                    stack.pop();
                    nextBigger[idx] = i;
                } else {
                    break;
                }
            }

            Integer[] ele = {i, arr[i]};
            stack.push(ele);
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            Integer idx = nextBigger[i];

            if (idx == -1) {
                ans.add(arr[i]);
            } else {
                ans.add((idx - i) * (arr[idx] - arr[i])); // 距离 * 数字差值
            }
        }

        StringJoiner sj = new StringJoiner(" ", "", "");
        for (Integer an : ans) sj.add(an + "");
        return sj.toString();
    }


}
