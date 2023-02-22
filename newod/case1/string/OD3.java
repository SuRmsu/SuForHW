package newod.case1.string;

import java.util.*;

/**
 * 给定一个字符串s，s包括以空格分隔的若干个单词，请对s进行如下处理后输出：
 * 1、单词内部调整：对每个单词字母重新按字典序排序
 * 2、单词间顺序调整：
 * 1）统计每个单词出现的次数，并按次数降序排列
 * 2）次数相同，按单词长度升序排列
 * 3）次数和单词长度均相同，按字典升序排列
 *
 * 请输出处理后的字符串，每个单词以一个空格分隔。
 *
 * 输入描述
 * 一行字符串，每个字符取值范围：[a-zA-z0-9]以及空格，字符串长度范围：[1，1000]
 * 输出描述
 * 输出处理后的字符串，每个单词以一个空格分隔。
 */
public class OD3 {
    public static void main(String[] args) {
        String[] input = {"This","is","is","a","a","ia","ia","apple"};
        System.out.println(process(input));
    }

    public static String process(String[] input) {
        // 用于将内部的数据进行按字典排序
        input = Arrays.stream(input).map( s ->{
            char[] temp =s.toCharArray();
            Arrays.sort(temp);
            return new String(temp);
        }).toArray(String[]::new);
        Map<String,Integer> count = new HashMap<String,Integer>();
        for (String s : input) {
            count.put(s,count.getOrDefault(s,0) + 1);
        }
        Arrays.sort(input, new Comparator<String>() {
            // 先比较次数是否相同，不相同就直接按出现的次数返回
            // 返回负数，顺序不变，返回正数，顺序相反
            @Override
            public int compare(String o1, String o2) {
                return !count.get(o1).equals(count.get(o2)) ? count.get(o2) - count.get(o1) : o1.length() != o2.length() ?
                        o1.length() - o2.length():o1.compareTo(o2);
            }
        });
        StringJoiner sj = new StringJoiner(",","","");
        for (String s : input) {
            sj.add(s);
            System.out.println(s);
        }
        return null;
    }
}
