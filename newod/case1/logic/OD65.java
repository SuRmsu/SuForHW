package newod.case1.logic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 组合出合法最小数
 * 题目描述
 * 给一个数组，数组里面哦都是代表非负整数的字符串，将数组里所有的数值排列组合拼接起来组成一个数字，输出拼接成的最小的数字。
 *
 * 输入描述
 * 一个数组，数组不为空，数组里面都是代表非负整数的字符串，可以是0开头，例如：["13", "045", "09", "56"]。
 *
 * 数组的大小范围：[1, 50]
 *
 * 数组中每个元素的长度范围：[1, 30]
 *
 * 输出描述
 * 以字符串的格式输出一个数字，
 *
 * 如果最终结果是多位数字，要优先选择输出不是“0”开头的最小数字
 * 如果拼接出来的数字都是“0”开头，则选取值最小的，并且把开头部分的“0”都去掉再输出
 * 如果是单位数“0”，可以直接输出“0”
 *
 * 解法：
 * 然后，比较 (a + b) 和 （b+a）的数值谁小，如果a+b的数值更小，则保持当前a,b顺序不变，如果b+a的数值更小，则交换a,b位置
 * 首先，按照前面的规则将数组元素排序，排序后，检查数组头元素是否以“0”开头，如果是的话，则开始遍历数组后面的元素，直到找到一个不以“0”开头的元素x，然后将元素x取出，并插入到数组头部。如果一直找不到这样的x，则说明数组元素全部是以0开头的，此时直接拼接出组合数，然后去除前导0。
 *
 * 关于去除前导0，这里不建议使用parseInt，或者python的int，因为对应组合数非常有可能超出int范围，这里我们应该保持组合数为字符串，实现去除前导0。
 * "^0+" 正则用于去除前导0
 */
public class OD65 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] strs = sc.nextLine().split(" ");
        System.out.println(getResult(strs));
    }

    public static String getResult(String[] strs) {
        Arrays.sort(strs, (a, b) -> (a + b).compareTo(b + a));

        if (strs[0].charAt(0) == '0') {
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(0) != '0') {
                    strs[0] = strs[i] + strs[0];
                    strs[i] = "";
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }

        return sb.toString().replaceAll("^0+", "");
    }
}
