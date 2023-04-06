package newod.case1.tanxing;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态扫描
 * 题目描述
 * 静态扫描可以快速识别源代码的缺陷，静态扫描的结果以扫描报告作为输出：
 * <p>
 * 1、文件扫描的成本和文件大小相关，如果文件大小为N，则扫描成本为N个金币
 * <p>
 * 2、扫描报告的缓存成本和文件大小无关，每缓存一个报告需要M个金币
 * <p>
 * 3、扫描报告缓存后，后继再碰到该文件则不需要扫描成本，直接获取缓存结果
 * <p>
 * 给出源代码文件标识序列和文件大小序列，求解采用合理的缓存策略，最少需要的金币数。
 * <p>
 * 输入描述
 * 第一行为缓存一个报告金币数M，L<= M <= 100
 * <p>
 * 第二行为文件标识序列：F1,F2,F3,....,Fn。
 * <p>
 * 第三行为文件大小序列：S1,S2,S3,....,Sn。
 * <p>
 * 备注：
 * <p>
 * 1 <= N <= 10000
 * 1 <= Fi <= 1000
 * 1 <= Si <= 10
 * 输出描述
 * 采用合理的缓存策略，需要的最少金币数
 * <p>
 * 解法：贪心
 * 用两个HashMap，一个用来记录出现次数，一个用来记录单价
 */
public class OD54 {
    public static void main(String[] args) {
        int n = 5;
//        int[] input = {1, 2, 2, 1, 2, 3, 4};
//        int[] money = {1, 1, 1, 1, 1, 1, 1};
        int[] input = {2, 2, 2, 2, 2, 5, 2, 2, 2};
        int[] money = {3, 3, 3, 3, 3, 1, 3, 3, 3};

        // 一个用来记录出现次数，一个用来记录单价
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        Map<Integer, Integer> prices = new HashMap<Integer, Integer>();
        for (int i = 0; i < input.length; i++) {
            counts.put(input[i], counts.getOrDefault(input[i], 0) + 1);
            prices.put(input[i], money[i]);
        }

        int count = 0;
        for (Integer integer : counts.keySet()) {
            if (counts.get(integer) == 1) {
                count += prices.get(integer);
            } else if (counts.get(integer) * prices.get(integer) <= n + prices.get(integer)) {
                count += counts.get(integer) * prices.get(integer);
            } else {
                count += prices.get(integer) + n;
            }
        }
        System.out.println(count);
    }
}
