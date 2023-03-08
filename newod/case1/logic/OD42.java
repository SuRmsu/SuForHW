package newod.case1.logic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 简单的自动曝光、平均像素值
 * 题目描述
 * 一个图像有n个像素点，存储在一个长度为n的数组img里，每个像素点的取值范围[0,255]的正整数。
 * 请你给图像每个像素点值加上一个整数k（可以是负数），得到新图newImg，使得新图newImg的所有像素平均值最接近中位值128。
 * 请输出这个整数k。
 *
 * 输入描述
 * n个整数，中间用空格分开
 *
 * 输出描述
 * 一个整数k
 *
 * 备注
 * • 1 <= n <= 100
 * • 如有多个整数k都满足，输出小的那个k；
 * • 新图的像素值会自动截取到[0,255]范围。当新像素值<0，其值会更改为0；当新像素值>255，其值会更改为255；
 *
 * 例如newImg=”-1 -2 256″,会自动更改为”0 0 255″
 *
 * 解法：
 * 首先输入的老图片的像素值，应该是符合要求的，即像素点应都在[0,255]范围内，因此像素点最小值为0，最大值为255。
 *
 * 那么如果我们想让0接近中位值128，则需要加上128。
 *
 * 如果我们想让255接近中位值128，则需要减去127。
 *
 * 因此，k的取值范围应该在-127到128之间，这样的话，就可以保证每一个点都能接近到中位值。
 *
 * 那么我们就从-127遍历到128，然后将遍历值加到老图片的每一个像素值上，然后求平均值。
 *
 * 需要注意的是，如果新图片的像素点值低于0，则取0，高于255，则取255
 *
 */
public class OD42 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        Integer[] arr = Arrays.stream(str.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(arr));
    }

    public static int getResult(Integer[] arr) {
        int len = arr.length;
        double minDiff = Integer.MAX_VALUE;
        Integer ans = null;

        for (int k = -127; k <= 128; k++) {
            double sum = 0;
            for (Integer val : arr) {
                int newVal = val + k;
                // 新图的像素值会自动截取到[0,255]范围。当新像素值<0，其值会更改为0；当新像素值>255，其值会更改为255；
                newVal = Math.max(0, Math.min(newVal, 255));
                sum += newVal;
            }

            double diff = Math.abs(sum / len - 128);

            if (diff < minDiff) {
                minDiff = diff;
                ans = k;
            } else if (diff == minDiff && ans != null) {
                // 如有多个整数k都满足，输出小的那个k
                ans = Math.min(ans, k);
            }
        }

        return ans;
    }
}
