package newod.case1.huisu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 羊、狼、农夫 过河
 * 题目描述
 * 羊、狼、农夫都在岸边，当羊的数量小于狼的数量时，狼会攻击羊，农夫则会损失羊。农夫有一艘容量固定的船，能够承载固定数量的动物。
 *
 * 要求求出不损失羊情况下将全部羊和狼运到对岸需要的最小次数。
 *
 * 只计算农夫去对岸的次数，回程时农夫不会运送羊和狼。
 *
 * 备注：农夫在或农夫离开后羊的数量大于狼的数量时狼不会攻击羊。
 *
 * 输入描述
 * 第一行输入为M，N，X， 分别代表羊的数量，狼的数量，小船的容量。
 *
 * 输出描述
 * 输出不损失羊情况下将全部羊和狼运到对岸需要的最小次数（若无法满足条件则输出0）。
 *
 * 解法：
 * 农夫如果在本岸带走的动物后，如果本岸羊 <= 狼了，在农夫离开后，羊就会被攻击
 * 农夫如果在对岸离开后，对岸的羊 <= 狼，羊就会被攻击
 * 因此，”农夫在时，狼不会攻击羊“，这句话只会影响：船上，羊和狼的关系，即农夫在船上时，如果羊数量 <= 狼数量，此时因为农夫在，因此狼不会攻击羊。
 *
 * 暴力枚举：
 * 农夫离开后，本岸羊 > 本岸狼
 * 农夫离开后，对岸羊 > 对岸狼
 * 船上由于农夫始终在，因此羊、狼什么数量都可以，只要不超过船载量
 *
 */
public class OD12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        int x = sc.nextInt();

        System.out.println(getResult(m, n, x));
    }

    /**
     * @param sheep 本岸羊数量
     * @param wolf 本岸狼数量
     * @param boat 船负载
     * @return 最少运送次数
     */
    public static int getResult(int sheep, int wolf, int boat) {
        ArrayList<Integer> ans = new ArrayList<>();
        dfs(sheep, wolf, boat, 0, 0, 0, ans);

        if (ans.size() > 0) {
            return Collections.min(ans);
        } else {
            return 0;
        }
    }

    public static void dfs(
            int sheep,
            int wolf,
            int boat,
            int oppo_sheep,
            int oppo_wolf,
            int count,
            ArrayList<Integer> ans) {
        if (sheep == 0 && wolf == 0) {
            ans.add(count);
            return;
        }

        if (sheep + wolf <= boat) {
            ans.add(count + 1);
            return;
        }

        // i 代表船上羊数量，最多Math.min(boat, sheep)
        for (int i = 0; i <= Math.min(boat, sheep); i++) {
            // j 代表船上狼数量，最多Math.min(boat, wolf)
            for (int j = 0; j <= Math.min(boat, wolf); j++) {
                // 空运
                if (i + j == 0) continue;
                // 超载
                if (i + j > boat) break;

                // 本岸羊 <= 本岸狼，说明狼运少了
                if (sheep - i <= wolf - j && sheep - i != 0) continue;

                // 对岸羊 <= 对岸狼，说明狼运多了
                if (oppo_sheep + i <= oppo_wolf + j && oppo_sheep + i != 0) break;

                // 对岸没羊，但是对岸狼已经超过船载量，即下次即使整船都运羊，也无法保证对岸羊 > 对岸狼
                if (oppo_sheep + i == 0 && oppo_wolf + j >= boat) break;

                dfs(sheep - i, wolf - j, boat, oppo_sheep + i, oppo_wolf + j, count + 1, ans);
            }
        }
    }
}
