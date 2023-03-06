package newod.case1.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 猜字谜
 * 题目描述
 * 小王设计了一个简单的猜字谜游戏，游戏的谜面是一个错误的单词，比如nesw，玩家需要猜出谜底库中正确的单词。猜中的要求如下：
 * 对于某个谜面和谜底单词，满足下面任一条件都表示猜中：
 * <p>
 * 变换顺序以后一样的，比如通过变换w和e的顺序，“nwes”跟“news”是可以完全对应的；
 * 字母去重以后是一样的，比如“woood”和“wood”是一样的，它们去重后都是“wod”
 * 请你写一个程序帮忙在谜底库中找到正确的谜底。谜面是多个单词，都需要找到对应的谜底，如果找不到的话，返回”not found”
 * <p>
 * 输入描述
 * 谜面单词列表，以“,”分隔
 * 谜底库单词列表，以","分隔
 * 输出描述
 * 匹配到的正确单词列表，以","分隔
 * 如果找不到，返回"not found"
 * <p>
 * 解法：用Set记录并比较字典序就行了
 */
public class OD51 {
    public static void main(String[] args) {
        String[] input = {"conection","false"};
        String[] answer = {"connection", "today"};

        ArrayList<String> storage = new ArrayList<String>();
        for (String i : input) {
            i = toSet(i);
            boolean find = false;
            for (String j : answer) {
                String temp = j;
                j = toSet(j);
                if (i.equals(j)){
                    storage.add(temp);
                    find = true;
                }
            }
            if (!find){
                storage.add("not found");
            }
        }

        System.out.println(storage);
    }

    public static String toSet(String str) {

        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }
        return set.toString();
    }
}
