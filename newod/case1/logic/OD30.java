package newod.case1.logic;

import java.util.HashMap;
import java.util.Map;

/**
 * 密室逃生游戏
 * 题目描述
 * 小强在参加《密室逃生》游戏，当前关卡要求找到符合给定 密码K（升序的不重复小写字母组成） 的箱子，并给出箱子编号，箱子编号为 1~N 。
 * 每个箱子中都有一个 字符串s ，字符串由大写字母、小写字母、数字、标点符号、空格组成，需要在这些字符串中找到所有的字母，忽略大小写后排列出对应的密码串，并返回匹配密码的箱子序号。
 *
 * 提示：满足条件的箱子不超过1个。
 *
 * 输入描述
 * 第一行为 key 的字符串，
 * 第二行为箱子 boxes，为数组样式，以逗号分隔
 *
 * 箱子 N 数量满足 1 ≤ N ≤ 10000,
 * s 长度满足 0 ≤ s.length ≤ 50，
 * 密码为仅包含小写字母的升序字符串，且不存在重复字母，
 * 密码 K 长度1 ≤ K.l e n g t h ≤ 26
 * 输出描述
 * 返回对应箱子编号
 * 如不存在符合要求的密码箱，则返回 -1。
 *
 * 解法： 暴力解也可以，但关键是在优化
 * 复杂在，每一个字符都要遍历整个字符串
 * 优化：用map则只需要遍历一次字符串
 *
 *
 */
public class OD30 {
    public static void main(String[] args) {
        String include = "abc";
        String[] input ={
                "s,sdf134",
                "A2c4B"
        };
        Map<Character,Integer> map = new HashMap<>();
        Map<Character,Integer> includeMap = new HashMap<>();
        process(map,include);
        for (int i = 0; i < input.length; i++) {
            process(includeMap,input[i]);
            boolean flag = true;
            for (Character character : map.keySet()) {
                if (!includeMap.containsKey(character)){
                    flag = false;
                    break;
                } else if (!(map.get(character) == includeMap.get(character))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(i + 1);
                return;
            }
        }

        System.out.println(-1);


    }
    public static void process(Map< Character,Integer> map, String input){
    map.clear();
        for (int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            if (temp >= 'A' && temp <= 'Z'){
                map.put((char)(temp + 32),map.getOrDefault((char)(temp + 32),0) + 1);
            } else {
                map.put(temp,map.getOrDefault(temp,0) + 1);
            }
        }
    }





}
