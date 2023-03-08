package newod.case1.logic;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 任务总执行时长
 * 题目描述
 * 任务编排服务负责对任务进行组合调度。
 *
 * 参与编排的任务有两种类型，其中一种执行时长为taskA，另一种执行时长为taskB。
 *
 * 任务一旦开始执行不能被打断，且任务可连续执行。
 *
 * 服务每次可以编排num个任务。
 *
 * 请编写一个方法，生成每次编排后的任务所有可能的总执行时长。
 *
 * 输入描述
 * 第1行输入分别为第1种任务执行时长taskA，
 *
 * 第2种任务执行时长taskB，
 *
 * 这次要编排的任务个数num，以逗号分隔。
 *
 * 注：每种任务的数量都大于本次可以编排的任务数量
 *
 * 0 < taskA
 * 0 < taskB
 * 0 <= num <= 100000
 * 输出描述
 * 数组形式返回所有总执行时时长，需要按从小到大排列。
 *
 * 解法：本题看上去是求解全排列，但是实际上无论如何排列，其实就只是两种类型任务的排列，而且本题要求任务编排的执行总时长，这其实就是就每种排列的和，因此其实我们不需要求解排列，只需要求解该排列对应的组合即可
 *
 * 不需要全排列，直接一层for循环就行了
 *
 */
public class OD48 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        Integer[] arr = Arrays.stream(str.split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(arr[0], arr[1], arr[2]));
    }

    public static String getResult(int taskA, int taskB, int num) {
        if (taskA == taskB) {
            return Arrays.toString(new int[]{taskA * num});
        }

        TreeSet<Integer> ans = new TreeSet<>();
        for (int i = 0; i <= num; i++) {
            ans.add(taskA * i + taskB * (num - i));
        }

        return ans.toString();

    }
}