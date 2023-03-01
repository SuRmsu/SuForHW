package newod.case1.logic;

import java.util.*;

/**
 * 通信误码
 * 题目描述
 * 信号传播过程中会出现一些误码，不同的数字表示不同的误码ID，取值范围为1~65535，用一个数组记录误码出现的情况，
 * 每个误码出现的次数代表误码频度，请找出记录中包含频度最高误码的最小子数组长度。
 *
 * 输入描述
 * 误码总数目：取值范围为0~255，取值为0表示没有误码的情况。
 * 误码出现频率数组：误码ID范围为1~65535，数组长度为1~1000。
 *
 * 输出描述
 * 包含频率最高的误码最小子数组长度
 * 解法：
 * Map<Integer, Integer> tempTimes 用来记录
 * Map<Integer, LinkedList<Integer>> index 用来记录每个数字的索引的链表
 * List<Map.Entry<Integer, Integer>> times 用来堆tempTimes的数据按value大小排序
 * Map类的putIfAbsent，能够判断是否需要新建列表
 */
public class OD38 {
    public static void main(String[] args) {
        int n = 7;
        int[] input = {1, 2, 2, 4, 2, 1, 1};

        Map<Integer, Integer> tempTimes = new HashMap<Integer, Integer>();
        Map<Integer, LinkedList<Integer>> index = new HashMap<Integer, LinkedList<Integer>>();


        for (int i = 0; i < input.length; i++) {
            // 记录出现次数
            tempTimes.put(input[i], tempTimes.getOrDefault(input[i], 0) + 1);
            // 用链表记录索引
            // 这两行代码可以代替下面的判断
//            idxs.putIfAbsent(code, new ArrayList<>());
//            idxs.get(code).add(i);
            if (index.containsKey(input[i])) {
//                List<Integer> temp = index.get(input[i]);
//                temp.add(i);
//                index.put(input[i], temp);
                // 等价
                index.get(input[i]).add(i);
            } else {
                LinkedList<Integer> temp = new LinkedList<Integer>();
                temp.add(i);
                index.put(input[i], temp);
            }
        }
        List<Map.Entry<Integer, Integer>> times = new ArrayList<Map.Entry<Integer, Integer>>(tempTimes.entrySet());
        times.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        int maxCount = times.get(0).getValue();
        if (maxCount == 1) {
            System.out.println(1);
            return;
        }
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> time : times) {
            if (time.getValue() == maxCount) {
                min = Math.min(min, index.get(time.getKey()).getLast() - index.get(time.getKey()).getFirst() + 1);
            } else {
                break;
            }
        }

        System.out.println(min);


    }
}
