package newod.case1.logic;

import java.util.Arrays;

/**
 * 预定酒店
 * 题目描述
 * 放暑假了，小明决定到某旅游景点游玩，他在网上搜索到了各种价位的酒店（长度为n的数组A），他的心理价位是x元，请帮他筛选出k个最接近x元的酒店（n>=k>0）,并由低到高打印酒店的价格。
 * <p>
 * 输入描述
 * 第一行：n, k, x
 * 第二行：A[0] A[1] A[2]…A[n-1]
 * <p>
 * 输出描述
 * 从低到高打印筛选出的酒店价格
 * <p>
 * 解法：先通过二分查找的api找到最接近的位置
 * Arrays.binarySearch 如何查找不到，则返回-idx-1 ，idx为应插入位置
 * 然后从左右依次进行查找
 */
public class OD44 {
    public static void main(String[] args) {
        int n = 10;
        int k = 5;
        int x = 11;
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // 先排序
        Arrays.sort(input);
        // 在通过二分查找找到指定位置
        int index = Arrays.binarySearch(input, x);
        // index < 0 则没找到呗
        if (index < 0) {
            index = -index - 1;

            // 有可能插入的是最后一个元素
            if (index == input.length) {
                int[] newInput = Arrays.copyOfRange(input, index - k, index);
                for (int i : newInput) {
                    System.out.print(i + " ");
                }
                return;
            }
            // 比较左右的值，选择一个当中心位置
            int tempLeft = Math.abs(x - input[index - 1]);
            int tempRight = Math.abs(x - input[index]);
            if (tempLeft > tempRight) {
                index -= 1;
            }

        }
        k--;

        int left = index;
        int right = index;

        while (k > 0) {
            // 超过边界就认为是无穷大，不要了
            int leftVal = left == 0 ? Integer.MAX_VALUE : input[left - 1];
            int rightVal = right == n - 1 ? Integer.MAX_VALUE : input[right + 1];

            // 从中心向两边发散
            int tempLeft = Math.abs(x - leftVal);
            int tempRight = Math.abs(x - rightVal);

            // 选更接近的
            if (tempLeft <= tempRight) {
                left--;
            } else {
                right++;
            }
            k--;

        }

        int[] ans = Arrays.copyOfRange(input, left, right + 1);
        for (int i : ans) {
            System.out.print(i + " ");
        }

        //System.out.println(index);
    }
}
