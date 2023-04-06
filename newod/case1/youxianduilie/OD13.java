package newod.case1.youxianduilie;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 题目描述
 * 有5台打印机打印文件，每台打印机有自己的待打印队列。
 *
 * 因为打印的文件内容有轻重缓急之分，所以队列中的文件有1~10不同的代先级，其中数字越大优先级越高。
 *
 * 打印机会从自己的待打印队列中选择优先级最高的文件来打印。
 *
 * 如果存在两个优先级一样的文件，则选择最早进入队列的那个文件。
 *
 * 现在请你来模拟这5台打印机的打印过程。
 *
 * 输入描述
 * 每个输入包含1个测试用例，
 *
 * 每个测试用例第一行给出发生事件的数量N（0 < N < 1000）。
 *
 * 接下来有 N 行，分别表示发生的事件。共有如下两种事件：
 *
 * “IN P NUM”，表示有一个拥有优先级 NUM 的文件放到了打印机 P 的待打印队列中。（0< P <= 5, 0 < NUM <= 10)；
 * “OUT P”，表示打印机 P 进行了一次文件打印，同时该文件从待打印队列中取出。（0 < P <= 5）。
 * 输出描述
 * 对于每个测试用例，每次”OUT P”事件，请在一行中输出文件的编号。
 * 如果此时没有文件可以打印，请输出”NULL“。
 * 文件的编号定义为”IN P NUM”事件发生第 x 次，此处待打印文件的编号为x。编号从1开始。
 *
 * 解法：标准的优先队列
 */
public class OD13 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String[][] matrix = new String[n][];

        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            matrix[i] = s;
        }

        getResult(matrix);
    }

    public static void getResult(String[][] matrix) {
        // print中存放每台打印机的等待队列
        HashMap<String, PriorityQueue<Integer[]>> print = new HashMap<>();

        // 文件的编号定义为”IN P NUM”事件发生第 x 次，此处待打印文件的编号为x。编号从1开始。
        int x = 1;
        for (String[] task : matrix) {
            // IN,OUT都有type和printId
            String type = task[0];
            String printId = task[1];

            if ("IN".equals(type)) {
                // IN还有priority
                String priority = task[2];
                // arr是打印任务
                Integer[] arr = {x, Integer.parseInt(priority)};
                // 为打印机printId设置打印优先级，打印任务的priority越大，优先级越高
                print.putIfAbsent(printId, new PriorityQueue<>((a, b) -> b[1] - a[1]));
                // 将打印任务加入对应打印机
                print.get(printId).offer(arr);
                x++;
            } else {
                // 打印机等待队列中取出优先级最高的打印任务arr
                if (!print.containsKey(printId) || print.get(printId).isEmpty()) {
                    // 如果此时没有文件可以打印，请输出”NULL“。
                    System.out.println("NULL");
                } else {
                    Integer[] arr = print.get(printId).poll();
                    if (arr != null) System.out.println(arr[0]); // arr[0]是x
                    else System.out.println("NULL");
                }
            }
        }
    }
}
