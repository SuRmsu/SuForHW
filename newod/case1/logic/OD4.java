package newod.case1.logic;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 完美走位
 * 题目描述
 * 在第一人称射击游戏中，玩家通过键盘的A、S、D、W四个按键控制游戏人物分别向左、向后、向右、向前进行移动，从而完成走位。
 *
 * 假设玩家每按动一次键盘，游戏任务会向某个方向移动一步，如果玩家在操作一定次数的键盘并且各个方向的步数相同时，此时游戏任务必定会回到原点，则称此次走位为完美走位。
 *
 * 现给定玩家的走位（例如：ASDA），请通过更换其中一段连续走位的方式使得原走位能够变成一个完美走位。其中待更换的连续走位可以是相同长度的任何走位。
 *
 * 请返回待更换的连续走位的最小可能长度。
 *
 * 如果原走位本身是一个完美走位，则返回0。
 *
 * 解法：求最小覆盖子串
 * 多的一定是大于少的，把多的都解决掉
 * 平衡状态时，W,A,S,D应该都是avg数量
 * total用于记录多余字母个数
 * 此段会导致出现次数小于0，小于等于0的都是满足的
 */
public class OD4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(getResult(sc.next()));
    }

    public static int getResult(String str) {
        // count用于记录W,A,S,D字母的数量
        HashMap<Character, Integer> count = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // 平衡状态时，W,A,S,D应该都是avg数量
        int avg = str.length() / 4;

        // total用于记录多余字母个数
        int total = 0;

        // flag表示当前是否为平衡状态，默认是
        boolean flag = true;

        for (Character c : count.keySet()) {
            if (count.get(c) > avg) {
                // 如果有一个字母数量超标，则平衡打破
                flag = false;
                // 此时count记录每个字母超过avg的数量
                count.put(c, count.get(c) - avg);
                total += count.get(c);
            } else {
                count.put(c, 0); // 此时count统计的其实是多余字母，如果没有超过avg,则表示没有多余字母
            }
        }

        // 如果平衡，则输出0
        if (flag) return 0;

        int i = 0;
        int j = 0;
        int minLen = str.length() + 1;

        // 这段求解最小字串的逻辑无法理解
        while (j < str.length()) {
            Character jc = str.charAt(j);

            if (count.get(jc) > 0) {
                total--;
            }
            // 此段会导致出现次数小于0，小于等于0的都是满足的
            count.put(jc, count.get(jc) - 1);

            while (total == 0) {
                minLen = Math.min(minLen, j - i + 1);

                Character ic = str.charAt(i);
                if (count.get(ic) >= 0) {
                    total++;
                }
                count.put(ic, count.get(ic) + 1);

                i++;
            }
            j++;
        }
        return minLen;
    }
}
