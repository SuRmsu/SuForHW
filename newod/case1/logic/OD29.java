package newod.case1.logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 匿名信
 * 题目描述
 * 电视剧《分界线》里面有一个片段，男主为了向警察透露案件细节，且不暴露自己，于是将报刊上的字减下来，剪拼成匿名信。
 * 现在又一名举报人，希望借鉴这种手段，使用英文报刊完成举报操作。
 * 但为了增加文章的混淆度，只需满足每个单词中字母数量一致即可，不关注每个字母的顺序。
 * 解释：单词on允许通过单词no进行替代。
 * 报纸代表newspaper，匿名信代表anonymousLetter，求报纸内容是否可以拼成匿名信。
 *
 * 输入描述
 * 第一行输入newspaper内容，包括1-N个字符串，用空格分开
 * 第二行输入anonymousLetter内容，包括1-N个字符串，用空格分开。
 *
 * newspaper和anonymousLetter的字符串由小写英文字母组成，且每个字母只能使用一次；
 * newspaper内容中的每个字符串字母顺序可以任意调整，但必须保证字符串的完整性（每个字符串不能有多余字母）
 * 1 < N < 100,
 * 1 <= newspaper.length,anonymousLetter.length <= 10^4
 *
 * 输出描述
 * 如果报纸可以拼成匿名信返回true，否则返回false
 *
 * 解法：
 * 统计出newspaper中每个单词出现的次数到count对象中，统计前，对每个单词进行字典序排序
 * 再遍历anonymousLetter每个单词，并对单词进行字典序排序，忽略字母顺序，然后用排序后单词去count中找，如果count[letter] > 0，则可以找到，然后count[letter]--
 *
 */
public class OD29 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] newspaper = sc.nextLine().split(" ");
        String[] anonymousLetter = sc.nextLine().split(" ");

        System.out.println(getResult(newspaper, anonymousLetter));
    }

    public static boolean getResult(String[] newspaper, String[] anonymousLetter) {
        HashMap<String, Integer> count = new HashMap<>();
        for (String str : newspaper) {
            String newStr = strSort(str);
            count.put(newStr, count.getOrDefault(newStr, 0) + 1);
        }

        boolean flag = true;
        for (String str : anonymousLetter) {
            String newStr = strSort(str);

            if (count.containsKey(newStr) && count.get(newStr) > 0) {
                count.put(newStr, count.get(newStr) - 1);
            } else {
                flag = false;
                break;
            }
        }

        return flag;
    }

    public static String strSort(String str) {
        char[] cArr = str.toCharArray();
        Arrays.sort(cArr);
        return new String(cArr);
    }
}
