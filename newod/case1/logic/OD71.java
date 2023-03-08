package newod.case1.logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 最优资源分配
 * 题目描述
 * 某块业务芯片最小容量单位为1.25G，总容量为M*1.25G，对该芯片资源编号为1，2，...，M。该芯片支持3种不同的配置，分别为A、B、C。
 *
 * 配置A：占用容量为 1.25 * 1 = 1.25G
 * 配置B：占用容量为 1.25 * 2 = 2.5G
 * 配置C：占用容量为 1.25 * 8 = 10G
 * 某块板卡上集成了N块上述芯片，对芯片编号为1，2，...，N，各个芯片之间彼此独立，不能跨芯片占用资源。
 *
 * 给定板卡上芯片数量N、每块芯片容量M、用户按次序配置后，请输出芯片资源占用情况，保证消耗的芯片数量最少。
 *
 * 资源分配规则：按照芯片编号从小到大分配所需资源，芯片上资源如果被占用标记为1，没有被占用标记为0.
 *
 * 用户配置序列：用户配置是按次序依次配置到芯片中，如果用户配置序列种某个配置超过了芯片总容量，丢弃该配置，继续遍历用户后续配置。
 *
 * 输入描述
 * M：每块芯片容量为 M * 1.25G，取值范围为：1~256
 *
 * N：每块板卡包含芯片数量，取值范围为1~32
 *
 * 用户配置序列：例如ACABA，长度不超过1000
 *
 * 输出描述
 * 板卡上每块芯片的占用情况
 *
 * 备注
 * 用户配置是按次序依次配置到芯片中，如果用户配置序列种某个配置超过了芯片总容量，丢弃该配置，继续遍历用户后续配置。
 *
 * 解法：
 * 纯逻辑题，注意1.25的个数代表输出的1的位置
 *
 */
public class OD71 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        String sequence = sc.next();

        getResult(m, n, sequence);
    }

    public static void getResult(int m, int n, String sequence) {
        double[] boardCard = new double[n];
        Arrays.fill(boardCard, m * 1.25);

        HashMap<Character, Integer> dict = new HashMap<>();
        dict.put('A', 1);
        dict.put('B', 2);
        dict.put('C', 8);

        for (int i = 0; i < sequence.length(); i++) {
            double need = 1.25 * dict.get(sequence.charAt(i));
            for (int j = 0; j < n; j++) {
                if (boardCard[j] >= need) {
                    boardCard[j] -= need;
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int unUsed = (int) (boardCard[i] / 1.25);
            int used = m - unUsed;

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < used; j++) {
                sb.append(1);
            }
            for (int k = 0; k < unUsed; k++) {
                sb.append(0);
            }
            System.out.println(sb);
        }
    }
}
