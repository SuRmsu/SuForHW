package newod.case1.logic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 字符串解密
 * 题目描述
 * 给定两个字符串string1和string2。
 * string1是一个被加扰的字符串。
 * <p>
 * string1由小写英文字母（’a’~’z’）和数字字符（’0’~’9’）组成，而加扰字符串由’0’~’9’、’a’~’f’组成。
 * <p>
 * string1里面可能包含0个或多个加扰子串，剩下可能有0个或多个有效子串，这些有效子串被加扰子串隔开。
 * <p>
 * string2是一个参考字符串，仅由小写英文字母（’a’~’z’）组成。
 * <p>
 * 你需要在string1字符串里找到一个有效子串，这个有效子串要同时满足下面两个条件：
 * <p>
 * （1）这个有效子串里不同字母的数量不超过且最接近于string2里不同字母的数量，即小于或等于string2里不同字母的数量的同时且最大。
 * <p>
 * （2）这个有效子串是满足条件（1）里的所有子串（如果有多个的话）里字典序最大的一个。
 * <p>
 * 如果没有找到合适条件的子串的话，请输出”Not Found”
 * <p>
 * 输入描述
 * input_string1
 * input_string2
 * <p>
 * 输出描述
 * output_string
 * <p>
 * 解法：用Set获得最大不同字母的数量，用TreeSet排序
 * 0-9 a-f 是加扰的字符串
 * 用正则分隔
 * 重写TreeSet的比较器
 * 注意String的split方法的返回值
 */
public class OD16_2 {
    /*
    public static void main(String[] args) {
//        String input1 = "123admyffc79pt";
//        String input2 = "ssyy";
//        String input1 = "123admyffc79ptaagghi2222smeersst88mnrt";
//        String input2 = "ssyyfgh";
        String input1 = "abcmnq";
        String input2 = "rt";
        // 可以就用默认的排序
        TreeSet<String> treeSet = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Set<Character> tempSet = new HashSet<>();
                for (int i = 0; i < o1.length(); i++) {
                    tempSet.add(o1.charAt(i));
                }
                int length1 = tempSet.size();
                tempSet.clear();
                for (int i = 0; i < o2.length(); i++) {
                    tempSet.add(o2.charAt(i));
                }
                int length2 = tempSet.size();
                int i = length2 - length1;
                if (i == 0) {
                    i = o2.compareTo(o1);
                }
                return i;
            }
        });

        for (String s : input1.split("[0-9a-f]")) {
            if (!s.equals("")) {
                treeSet.add(s);
            }

        }
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < input2.length(); i++) {
            set.add(input2.charAt(i));
        }
        int length = set.size();

        for (String s : treeSet) {
            if (s.length() <= length) {
                System.out.println(s);
                break;
            }
        }
        System.out.println("Not Found");


    }

     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str1 = sc.next();
        String str2 = sc.next();

        System.out.println(getResult(str1, str2));
    }

    public static String getResult(String str1, String str2) {
        String reg = "[0-9a-f]+";

        String[] valids = str1.split(reg);

        int count = getDistinctCount(str2);

        String[] ans = Arrays.stream(valids).filter(valid -> !"".equals(valid) && getDistinctCount(valid) <= count).toArray(String[]::new);

        if(ans.length == 0) return "Not Found";

        Arrays.sort(ans, (a,b)->{
            int c1 = getDistinctCount(a);
            int c2 = getDistinctCount(b);
            return c1 != c2 ? c2 - c1 : b.compareTo(a);
        });

        return ans[0];
    }

    public static int getDistinctCount(String str) {
        HashSet<Character> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            set.add(c);
        }
        return set.size();
    }

}
