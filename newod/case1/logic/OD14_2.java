package newod.case1.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二进制差异数
 * 题目描述
 * 对于任意两个正整数A和B，定义它们之间的差异值和相似值：
 * 差异值：A、B转换成二进制后，对于二进制的每一位，对应位置的bit值不相同则为1，否则为0；
 * 相似值：A、B转换成二进制后，对于二进制的每一位，对应位置的bit值都为1则为1，否则为0；
 * 现在有n个正整数A0到A（n-1），问有多少(i, j) (0<=i<j<n），Ai和Aj的差异值大于相似值。
 * 假设A=5，B=3；则A的二进制表示101；B的二进制表示011；
 * 则A与B的差异值二进制为110；相似值二进制为001；
 * A与B的差异值十进制等于6，相似值十进制等于1，满足条件。
 * <p>
 * 输入描述
 * 一个n接下来n个正整数
 * <p>
 * 数据范围：1<=n<=10^5，1<=A[i]<2^30
 * <p>
 * 输出描述
 * 满足差异值大于相似值的对数
 * <p>
 * 解法： 暴力 ，差异值等于异或，相似值等于并操作
 * <p>
 * 优化： 如果第一位1的位置相同，则必定不满足；如果第一位1的位置不相同，则必定满足
 * 最后用双层for来计算总的个数
 */
public class OD14_2 {
    static List<List<Integer>> storage = new ArrayList<List<Integer>>();

    public static void main(String[] args) {
        // 1 10 11 110
        int[] input = {1,2,3,4};


    /* 暴力解法
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                int tempA = input[i] ^ input[j];
                int tempB = input[i] & input[j];
                if (tempA > tempB){
                    List<Integer> tempStorage = new ArrayList<Integer>();
                    tempStorage.add(input[i]);
                    tempStorage.add(input[j]);
                    storage.add(tempStorage);
                }
            }
        }
        System.out.println(storage);*/
        // 优化
        int[] count = new int[31];
        Arrays.fill(count,0);

        for (int i = 0; i < input.length; i++) {
            count[Integer.toBinaryString(input[i]).length() - 1]++;
        }
        int sum = 0;
        for (int i = 0; i < count.length; i++){
            for (int j = i + 1; j < count.length; j++) {
                sum += count[i] * count[j];
            }
        }
        System.out.println(sum);
    }
}
