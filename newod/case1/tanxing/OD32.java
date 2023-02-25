package newod.case1.tanxing;

import java.util.Arrays;

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
 */
public class OD32 {
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
}
