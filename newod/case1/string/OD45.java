package newod.case1.string;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 小明负责维护项目下的代码，需要查找出重复代码，用以支撑后续的代码优化，请你帮助小明找出重复的代码。
 * 重复代码查找方法：以字符串形式给定两行代码（字符串长度 1 < length <= 100，由英文字母、数字和空格组成），找出两行代码中的最长公共子串。
 * 注：如果不存在公共子串，返回空字符串
 * <p>
 * 输入描述
 * 输入的参数text1, text2分别表示两行代码
 * <p>
 * 输出描述
 * 输出任一最长公共子串
 *
 * 解法：
 * 1. 双指针，但要注意边界条件，即两个字符串分别遍历到最后一个位置
 * 2. dp
 */
public class OD45 {
    public static void main(String[] args) {
//        String b = "public_void_method";
//        String a = "private_void_method";

//        String b = "hello123abc4";
//        String a = "hello123world";

        String b = "hiweborlda";
        String a = "hiwaaaorld";


        int max = 0;
        HashSet<String> hashSet = new HashSet<String>();
        for (int i = 0; i < a.length(); i++) {
            int temp = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    temp++;
                    max = Math.max(max, temp);
                    sb.append(b.charAt(j));
                    i++;
                } else if (!sb.isEmpty()) {
                    hashSet.add(sb.toString());
                    temp = 0;
                    sb = new StringBuilder();
                    i = i - temp;
                }
                if (j == b.length() - 1) {
                    hashSet.add(sb.toString());
                    i = i - temp;
                }
                if ( i == a.length()){
                    hashSet.add(sb.toString());
                    break;
                }


            }
        }
        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            String ab = iterator.next();
            if (ab.length() == max) {
                System.out.println(ab);
            }
        }

    }
}
