package newod.case1.logic;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 箱子之字形摆放
 * 题目描述
 * 有一批箱子（形式为字符串，设为str），
 * 要求将这批箱子按从上到下以之字形的顺序摆放在宽度为 n 的空地，请输出箱子的摆放位置。
 * 例如：箱子ABCDEFG，空地宽度为3，摆放结果如图：
 *
 *
 *
 * 则输出结果为：
 * AFG
 * BE
 * CD
 *
 * 输入描述
 * 输入一行字符串，通过空格分隔，前面部分为字母或数字组成的字符串str，表示箱子；
 * 后面部分为数字n，表示空地的宽度。例如：
 * ABCDEFG 3
 *
 * 输出描述
 * 箱子摆放结果，如题目示例所示
 *
 * AFG
 * BE
 * CD
 *
 * 备注
 * 请不要在最后一行输出额外的空行
 * str只包含字母和数字，1 <= len(str) <= 1000
 * 1 <= n <= 1000
 *
 * 解法：
 * 此时，我们可以总结出，遍历的字符插入到二维矩阵的行数和其索引有关，索引和行数的转换方程有两个：
 *
 * 行数 = n - 1 - (i%n)
 * 行数 = i % n
 * 因此我们可以用一个reverse来标记，
 *
 * 当reverse=true，用公式：行数 = i % n
 *
 * 当reverse=false，用公式：行数 = n - 1 - (i%n)
 *
 * 而reverse变化的条件就是i % n ==0，每遇到i % n ==0，reverse = !reverse
 */
public class OD22 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        int n = sc.nextInt();

        getResult(str, n);
    }

    public static void getResult(String str, int n) {
        ArrayList<ArrayList<Character>> matrix = new ArrayList<>();
        for (int i = 0; i < n; i++) matrix.add(new ArrayList<>());

        boolean reverse = true;
        for (int i = 0; i < str.length(); i++) {
            int k = i % n;
            if (k == 0) reverse = !reverse;
            if (reverse) k = n - 1 - k;
            matrix.get(k).add(str.charAt(i));
        }

        for (ArrayList<Character> list : matrix) {
            StringBuilder sb = new StringBuilder();
            for (Character character : list) {
                sb.append(character);
            }
            System.out.println(sb);
        }
    }

}
