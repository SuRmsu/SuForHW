package newod.case1.huisu;

import java.util.Scanner;

/**
 * ## OD19 对称美学
 * 题目描述
 * 对称就是最大的美学，现有一道关于对称字符串的美学。已知：
 *
 * 第1个字符串：R
 * 第2个字符串：BR
 * 第3个字符串：RBBR
 * 第4个字符串：BRRBRBBR

 * 相信你已经发现规律了，没错！就是第 i 个字符串 = 第 i - 1 号字符串取反 + 第 i - 1 号字符串；
 *
 * 取反（R->B, B->R）;
 *
 * 现在告诉你n和k，让你求得第n个字符串的第k个字符是多少。（k的编号从0开始）
 *
 * 输入描述
 * 第一行输入一个T，表示有T组用例；
 *
 * 解析来输入T行，每行输入两个数字，表示n，k
 *
 * 1 ≤ T ≤ 100；
 * 1 ≤ n ≤ 64；
 * 0 ≤ k ＜ 2^(n-1)；
 * 输出描述
 * 输出T行表示答案；
 *
 * 输出 "blue" 表示字符是B；
 *
 * 输出 "red" 表示字符是R。
 * 解法：找规律
 * 第n个字串的后半部分等于第n-1个字串的相同位置  get(n-1, k - 2^(n-2))
 * 第n个字串的前半部分等于第n-1个字串的相同位置取反 k <= 2^(n-2) ，则相当于 get(n-1, k) 的颜色取反
 */
public class OD19 {
    /*
    public static void main(String[] args) {
//        int n = 1;
//        double k = 0;
//        int n = 2;
//        double k = 1;
//        int n = 3;
//        double k = 2;
//        int n = 4;
//        double k = 6;
//        int n = 5;
//        double k = 8;
        int n = 64;
        Long k = 73709551616l;
        System.out.println(process(n,k+1));
    }

    public static boolean process(int n, double k) {
        if ( n == 0 ){
            return false;
        }
        if ( n == 1 ){
            if ( k == 1) {
                return false;
            }
            return true;
        }

        if ( k > Math.pow(2,n-2) - 1) {
            return process(n-1,  k - Math.pow(2,n - 2));
        }else{
            return !process(n-1,  k);
        }

    }

     */


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        long[][] arr = new long[t][2];
        for (int i = 0; i < t; i++) {
            arr[i][0] = sc.nextLong();
            arr[i][1] = sc.nextLong();
        }

        getResult(arr);
    }

    public static void getResult(long[][] arr) {
        for (long[] nk : arr) {
            System.out.println(getNK(nk[0], nk[1]));
        }
    }

    public static String getNK(long n, long k) {
        if (n == 1) {
            return "red";
        }

        if (n == 2) {
            if (k == 0) return "blue";
            else return "red";
        }

        long half = 1L << (n - 2);

        if (k >= half) {
            return getNK(n - 1, k - half);
        } else {
            return "red".equals(getNK(n - 1, k)) ? "blue" : "red";
        }
    }
}
