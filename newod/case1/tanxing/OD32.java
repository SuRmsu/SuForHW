package newod.case1.tanxing;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 最短木板长度
 * 题目描述
 * 小明有 n 块木板，第 i ( 1 ≤ i ≤ n ) 块木板长度为 ai。
 * 小明买了一块长度为 m 的木料，这块木料可以切割成任意块，拼接到已有的木板上，用来加长木板。
 * 小明想让最短的模板尽量长。请问小明加长木板后，最短木板的长度可以为多少？
 * <p>
 * 输入描述
 * 输入的第一行包含两个正整数， n ( 1 ≤ n ≤ 10^3 ), m ( 1 ≤ m ≤ 10^6 )，n 表示木板数， m 表示木板长度。
 * 输入的第二行包含 n 个正整数， a1, a2,…an ( 1 ≤ ai ≤ 10^6 )。
 * <p>
 * 输出描述
 * 输出的唯一一行包含一个正整数，表示加长木板后，最短木板的长度最大可以为多少？
 * <p>
 * 解法：sum 加上木料的长度 再除以n的个数，即每块木板平均的长度
 * 先直接让最小的补齐，然后看看下一个能补到什么程度, 取当前的和上一个的最小值即可
 * 此解法错误，需重做
 *
 * 解法：即优先分配m的长度给最短板，和第二小的差值，一个一个加
 * 将所有最短板补足到第二短板的长度，不够则最短的就是结果；够了就继续往后
 */
public class OD32 {
    /*
    public static void main(String[] args) {
        Integer[] storage = {1,1,1,5};
        int k = 4;
        Arrays.sort(storage);
        int sum = k;
        for (Integer integer : storage) {
            sum += integer;
        }
        int average = sum / storage.length;
        for (int i = 0; i < storage.length; i++) {
            // 不会出现要补超过平均值的那块木板的情况
            if (k <= 0){
                // 这里逻辑不太对，应改成max(storage[i-1],storage[i])
                System.out.println(Math.min(storage[i],storage[i - 1]));
                return;
            }
            int temp = k;
            k -= average - storage[i];
            storage[i] += k < 0 ? temp : average - storage[i];
        }
        System.out.println(average);
    }

     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.println(getResult(m, a));
    }

    public static int getResult(int m, int[] a) {
        // 统计每种长度板的数量，记录到woods中，key是板长度，val是板数量
        HashMap<Integer, Integer> woods = new HashMap<>();
        for (Integer ai : a) {
            if (woods.containsKey(ai)) {
                Integer val = woods.get(ai);
                woods.put(ai, ++val);
            } else {
                woods.put(ai, 1);
            }
        }

        // 将统计到的板，按板长度排优先级，长度越短优先级越高，这里使用优先队列来实现优先级
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((b, c) -> b[0] - c[0]);
        for (Integer wood : woods.keySet()) {
            pq.offer(new Integer[] {wood, woods.get(wood)});
        }

        // 只要还有剩余的m长度，就将他补到最短板上
        while (m > 0) {
            // 如果只有一种板长度，那么就尝试将m平均分配到各个板上
            if (pq.size() == 1) {
                Integer[] info = pq.poll();
                int len = info[0];
                int count = info[1];
                return len + m / count;
            }

            // 如果有多种板长度
            // min1是最短板
            Integer[] min1 = pq.poll();
            // min2是第二最短板
            Integer[] min2 = pq.peek();

            // diff是最短板和第二最短板的差距
            int diff = min2[0] - min1[0];
            // 将所有最短板补足到第二短板的长度，所需要总长度total
            int total = diff * min1[1];

            // 如果m的长度不够补足所有最短板，那么说明此时最短板的长度就是题解
            if (total > m) {
                return min1[0] + m / min1[1];
            }
            // 如果m的长度刚好可以补足所有最短板，那么说明最短板可以全部升级到第二短板，且刚好用完m，因此第二短板的长度就是题解
            else if (total == m) {
                return min2[0];
            }
            // 如果m的长度足够长，能补足所有最短板到第二短板，还能有剩余，则将最短的数量加到第二短板的数量上，继续下轮循环
            else {
                m -= total;
                min2[1] += min1[1];
            }
        }

        return pq.peek()[0];
    }
}
