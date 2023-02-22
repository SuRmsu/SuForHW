package newod.case1.dp;

/**
 * 题目描述
 * 小明在学习二进制时，发现了一类不含 101的数，也就是：
 *
 * 将数字用二进制表示，不能出现 101 。
 * 现在给定一个整数区间 [l,r] ，请问这个区间包含了多少个不含 101 的数？
 *
 * 输入描述
 * 输入的唯一一行包含两个正整数 l， r（ 1 ≤ l ≤ r ≤ 10^9）。
 *
 * 输出描述
 * 输出的唯一一行包含一个整数，表示在 [l,r] 区间内一共有几个不含 101 的数。
 */
public class OD6 {
    public static void main(String[] args) {
        int a = 1,b = 10;
        System.out.println(process(a, b));
    }
    /* 暴力算法
    public static int process(int a, int b) {
        int count = 0;
        for (int i = a; i <= b ; i++) {
            if (Integer.toBinaryString(i).contains("101")){
                count++;
            }
        }
        return b - a - count + 1;
    }*/
    /**
     * 按位dp
     */
    public static int process(int a, int b) {
        int count = 0;
        for (int i = a; i <= b ; i++) {
            if (Integer.toBinaryString(i).contains("101")){
                count++;
            }
        }
        return b - a - count + 1;
    }
}
