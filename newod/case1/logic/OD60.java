package newod.case1.logic;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 新员工座位
 * 题目描述
 * 工位由序列F1,F2…Fn组成，Fi值为0、1或2。其中0代表空置，1代表有人，2代表障碍物。
 * <p>
 * 1、某一空位的友好度为左右连续老员工数之和，
 * 2、为方便新员工学习求助，优先安排友好度高的空位，
 * <p>
 * 给出工位序列，求所有空位中友好度的最大值。
 * <p>
 * 输入描述
 * 第一行为工位序列：F1，F2…Fn组成，
 * 1<=n<=10000，Fi值为0、1或2。其中0代表空置，1代表有人，2代表障碍物。
 * <p>
 * 输出描述
 * 所有空位中友好度的最大值。如果没有空位，返回0。
 * <p>
 * 解法：
 * 定义一个变量friendShip = 0，
 * <p>
 * 首先对输入数组进行正向遍历（从左到右）：
 * <p>
 * 遇到“1”，则friendShip++
 * 遇到“0”，则说明该空位左边的友好度为friendShip，记录下来，然后将friendShip重置为0
 * 遇到“2”，则直接将friendShip重置为0
 * 这样每个空位的左边的友好度就统计出来了。
 * <p>
 * 接着，将friendShip重置为0，
 * <p>
 * 然后对输入数组进行反向遍历（从右到左）：
 * <p>
 * 遇到“1”，则friendShip++
 * 遇到“0”，则说明该空位右边的友好度为friendShip，累加下来，然后将friendShip重置为0
 * 遇到“2”，则直接将friendShip重置为0
 * 这样每个空位的整体友好度就统计出来了，取最大值返回。
 */
public class OD60 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] arr = sc.nextLine().split(" ");

        System.out.println(getResult(arr));
    }

    public static int getResult(String[] arr) {
        // 记录空位的友好度
        HashMap<Integer, Integer> ep = new HashMap<>();

        int friendShip = 0;
        // 从左向右遍历，记录每个空位左边的友好度
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case "0":
                    ep.put(i, friendShip);
                    friendShip = 0;
                    break;
                case "1":
                    friendShip++;
                    break;
                case "2":
                    friendShip = 0;
                    break;
            }
        }

        friendShip = 0;
        int ans = 0;
        // 从右向左遍历，累加每个空位右边的友好度，这样就得到了每个空位的友好度，取最大值即可
        for (int i = arr.length - 1; i >= 0; i--) {
            switch (arr[i]) {
                case "0":
                    ans = Math.max(ans, ep.get(i) + friendShip);
                    friendShip = 0;
                    break;
                case "1":
                    friendShip++;
                    break;
                case "2":
                    friendShip = 0;
                    break;
            }
        }

        return ans;
    }

}
