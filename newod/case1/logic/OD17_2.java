package newod.case1.logic;

/**
 * 数字加减游戏
 * 题目描述
 * 小明在玩一个数字加减游戏，只使用加法或者减法，将一个数字s变成数字t。
 * 每个回合，小明可以用当前的数字加上或减去一个数字。
 * 现在有两种数字可以用来加减，分别为a,b(a!=b)，其中b没有使用次数限制。
 * 请问小明最少可以用多少次a，才能将数字s变成数字t。
 * 题目保证数字s一定能变成数字t。
 * <p>
 * 输入描述
 * 输入的唯一一行包含四个正整数s,t,a,b(1<=s,t,a,b<=105)，并且a!=b。
 * <p>
 * 输出描述
 * 输出的唯一一行包含一个整数，表示最少需要使用多少次a才能将数字s变成数字t。
 *
 * 解法：数学问题，求解5 * x + 2 * y = 9， x的绝对值的最小值
 * 让x从0开始递增，同时判断x和-x是否满足要求
 * (t-s-ax)%b == 0，则x满足要求
 */
public class OD17_2 {
    public static void main(String[] args) {
//        int[] input = {1, 10, 5, 2};
        int[] input = {11, 33, 4, 10};
        int x = 0;

        while (true) {
            if ((input[1] - input[0] - x * input[2]) % input[3] == 0 || (input[1] - input[0] - x * -1 * input[2]) % input[3] == 0) {
                System.out.println(x);
                break;
            }
            x++;
        }
    }
}
