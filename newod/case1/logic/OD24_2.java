package newod.case1.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 去除多余空格
 * 题目描述
 * 去除文本多余空格，但不去除配对单引号之间的多余空格。给出关键词的起始和结束下标，去除多余空格后刷新关键词的起始和结束下标。
 *
 * 条件约束：
 * 1，不考虑关键词起始和结束位置为空格的场景；
 * 2，单词的的开始和结束下标保证涵盖一个完整的单词，即一个坐标对开始和结束下标之间不会有多余的空格；
 * 3，如果有单引号，则用例保证单引号成对出现；
 * 4，关键词可能会重复；
 * 5，文本字符长度length取值范围：[0, 100000];
 *
 * 输入描述
 * 输入为两行字符串：
 *
 * 第一行：待去除多余空格的文本，用例保证如果有单引号，则单引号成对出现，且单引号可能有多对。
 *
 * 第二行：关键词的开始和结束坐标，关键词间以逗号区分，关键词内的开始和结束位置以单空格区分。
 *
 * 输出描述
 * 输出为两行字符串：
 *
 * 第一行：去除多余空格后的文本
 * 第二行：去除多余空格后的关键词的坐标开始和结束位置，为数组方式输出。
 *
 * 解法：记录每一个需要删除的空格的下标
 * 还需要用一个变量用来将单引号里面的空格删除
 * 每删除一个坐标，不仅关键字的坐标要减，空格下标也要减少//也可以用数组存储，然后用空串替换空格
 *
 *  可以用深拷贝的方法，重新创建一个存储关键字的索引
 *  然后比较要不要减少索引有原生的，减少用新建的
 *
 */
public class OD24_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        Integer[][] ranges =
                Arrays.stream(sc.nextLine().split(","))
                        .map(s -> Arrays.stream(s.split(" "))
                                .map(Integer::parseInt)
                                .toArray(Integer[]::new))
                        .toArray(Integer[][]::new);

        getResult(str, ranges);
    }

    public static void getResult(String str, Integer[][] ranges) {
        boolean quotaStart = false;
        ArrayList<Integer> needDel = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            if (i > 0 && ' ' == str.charAt(i) && ' ' == str.charAt(i - 1) && !quotaStart) {
                needDel.add(i);
            }

            if ('\'' == str.charAt(i)) {
                quotaStart = !quotaStart;
            }
        }

        char[] cArr = str.toCharArray();
        Integer[][] ans = Arrays.stream(ranges).map(Integer[]::clone).toArray(Integer[][]::new);

        for (Integer del : needDel) {
            cArr[del] = '\u0000';
            for (int i = 0; i < ranges.length; i++) {
                int start = ranges[i][0];
                if (del < start) {
                    ans[i][0]--;
                    ans[i][1]--;
                }
            }
        }

        System.out.println(new String(cArr).replace("\u0000", ""));

        StringBuilder sb = new StringBuilder();
        for (Integer[] an : ans) {
            sb.append(Arrays.toString(an));
        }
        System.out.println(sb);


    }
}
