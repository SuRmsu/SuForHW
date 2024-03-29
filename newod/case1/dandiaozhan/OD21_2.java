package newod.case1.dandiaozhan;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 最大数字
 * 题目描述
 * 给定一个由纯数字组成以字符串表示的数值，现要求字符串中的每个数字最多只能出现2次，超过的需要进行删除；
 * <p>
 * 删除某个重复的数字后，其它数字相对位置保持不变。
 * <p>
 * 如”34533″，数字3重复超过2次，需要删除其中一个3，删除第一个3后获得最大数值”4533″
 * <p>
 * 请返回经过删除操作后的最大的数值，以字符串表示。
 * <p>
 * 输入描述
 * 第一行为一个纯数字组成的字符串，长度范围：[1,100000]
 * <p>
 * 输出描述
 * 输出经过删除操作后的最大的数值
 * <p>
 * 解法： 暴力解不了
 * 首先，我们需要定义两个map，分别是unused和reserve，其中：
 *
 * unused含义是：每个数字剩余可用个数
 * reserve含义是：每个数字已保留的个数
 * 然后对他们进行初始化，初始时unused就是统计输入字符串中各数字字符的出现次数，而reserve每个数字字符的个数都初始化为0。
 *
 * 接下来，遍历出输入字符串的每个字符c：
 *
 * 如果此时stack栈顶没有元素，则将遍历的c直接压入栈，然后unused[c]--，reserve[c]++
 *
 * 如果此时stack栈顶有元素，假设为top，则：
 *
 * c <= top 的话，则直接压入栈，然后unused[c]--，reserve[c]++
 * c > top的话，则需要考虑将栈顶top弹出，而弹出是有条件的，因为题目说，每个数字最多出现两次，那么表示每个数字出现次数>=2的话，我们就保留该数字2个，此时整体数值才会最长，最长才有可能最大。如果此时我们将top弹出，那么需要看 unused[top] + reserve[top] - 1是不是 >= 2，只有成立时，我们才能弹出top，否则对应数字字符的数量将不足2个，最终影响整体数值的长度，进而影响大小。
 */
public class OD21_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        System.out.println(getResult(str));
    }

    public static String getResult(String str) {
        // 每个数字字符的可用个数
        HashMap<Character, Integer> unused = new HashMap<>();
        // 每个数字字符的保留个数
        HashMap<Character, Integer> reserve = new HashMap<>();

        // 初始时，每个数字有多少个，就可用多少个，由于还未使用，因此保留个数为0
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            unused.put(c, unused.getOrDefault(c, 0) + 1);
            reserve.putIfAbsent(c, 0);
        }

        // 定义一个栈
        LinkedList<Character> stack = new LinkedList<>();

        // 遍历输入字符串的每个数字字符c
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // 如果该字符已经保留了2个了，则后续再出现该数字字符可以不保留
            if (reserve.get(c) == 2) {
                // 则可用c数字个数--
                unused.put(c, unused.get(c) - 1);
                continue;
            }

            // 比较当前数字c和栈顶数字top，如果c>top，那么需要考虑将栈顶数字弹出
            while (stack.size() > 0) {
                char top = stack.getLast();

                // 如果栈顶数字被弹出后，已保留的top字符数量和未使用的top字符数量之和大于等于2，则可以弹出，否则不可以
                if (top < c && unused.get(top) + reserve.get(top) - 1 >= 2) {
                    stack.removeLast();
                    reserve.put(top, reserve.get(top) - 1);
                } else {
                    break;
                }
            }

            // 选择保留当前遍历的数字c
            stack.add(c);
            // 则可用c数字个数--
            unused.put(c, unused.get(c) - 1);
            // 已保留c数字个数++
            reserve.put(c, reserve.get(c) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
