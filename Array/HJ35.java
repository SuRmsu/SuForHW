package Array;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HJ35 {
    /**
     * 暴力算法，但是总觉得哪里很违和
     * 按顺序给数组赋值，再按照位置输出
     *
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());
        int[][] storage = new int[input][input];
        int initialNum = input * (input + 1) / 2;

        for (int i = input; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                storage[j][i - 1 - j] = initialNum - j;
            }
            initialNum -= i;
        }
        for (int i = 0; i < input; i++) {
            for (int j = 0; j < input - i; j++) {
                System.out.print(storage[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 优雅的使用二维数组，太优雅了
     * 对二维数组的第一维度单独赋值，依次对对角线赋值
     * 对二维数组的输出，遍历第一维度，输出每个第一维度的第二个维度
     */
    public void bestSolution() throws Exception {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();    //读入正整数n

            int[][] result = new int[n][];    //建立数组（n行）
            int t = 1;    //记录依次赋予的数组值
            for (int i = 0; i < n; i++) {
                result[i] = new int[n - i];    //数组第i行有n-i个元素
                for (int j = 0; j < i + 1; j++) {    //对第i个对角线赋值
                    result[i - j][j] = t;
                    t++;
                }
            }
            //输出数组值
            for (int[] a : result) {
                for (int a1 : a)
                    System.out.print(a1 + " ");
                System.out.println();
            }
        }
    }
}
