package newod.case1.logic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 积木最远距离
 * 题目描述
 * 小华和小薇一起通过玩积木游戏学习数学。
 * 他们有很多积木，每个积木块上都有一个数字，积木块上的数字可能相同。
 * 小华随机拿一些积木挨着排成一排，请小薇找到这排积木中数字相同且所处位置最远的2块积木块，计算他们的距离，小薇请你帮忙替她解决这个问题。
 *
 * 输入描述
 * 第一行输入为N，表示小华排成一排的积木总数。
 * 接下来N行每行一个数字，表示小华排成一排的积木上数字。
 *
 * 输出描述
 * 相同数字的积木的位置最远距离；如果所有积木数字都不相同，请返回-1。
 *
 * 备注
 * 0<=积木上的数字<10^9
 * 1<=积木长度<=10^5
 *
 * 解法：
 * 自己的思考：用Map<元素，第一次出现的索引>记录每个元素第一次出现的位置
 * 遍历时，维护一个maxCount用来记录最远的距离。
 *
 * 解法2：用数组代替Map
 *  各有优劣
 *
 */
public class OD16 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(getResult(arr));
    }

    public static int getResult(int[] arr) {
        HashMap<Integer, LinkedList<Integer>> idx = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            idx.putIfAbsent(num, new LinkedList<>());
            idx.get(num).add(i);
        }

        int ans = -1;

        for (Integer k : idx.keySet()) {
            LinkedList<Integer> link = idx.get(k);
            if (link.size() > 1) {
                ans = Math.max(ans, link.getLast() - link.getFirst());
            }
        }

        return ans;
    }
}
