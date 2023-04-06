package newod.case1.huisu;

/**
 * 过滤组合字符串
 * 题目描述
 * 数字0、1、2、3、4、5、6、7、8、9分别关联 a~z 26个英文字母。
 * <p>
 * 0 关联 “a”,”b”,”c”
 * 1 关联 “d”,”e”,”f”
 * 2 关联 “g”,”h”,”i”
 * 3 关联 “j”,”k”,”l”
 * 4 关联 “m”,”n”,”o”
 * 5 关联 “p”,”q”,”r”
 * 6 关联 “s”,”t”
 * 7 关联 “u”,”v”
 * 8 关联 “w”,”x”
 * 9 关联 “y”,”z”
 * 例如7关联”u”,”v”，8关联”x”,”w”，输入一个字符串例如“78”，和一个屏蔽字符串“ux”，那么“78”可以组成多个字符串例如：“ux”，“uw”，“vx”，“vw”，过滤这些完全包含屏蔽字符串的每一个字符的字符串，然后输出剩下的字符串。
 * <p>
 * 输入描述
 * 无
 * <p>
 * 输出描述
 * 无
 * <p>
 * 解法：回溯 DFS
 * 简单的回溯法
 */
public class OD9 {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {

        String[][] nums = {
                {"a", "b", "c"},
                {"d", "e", "f"},
                {"g", "h", "i"},
                {"j","k","l"},
                {"m","n","o"},
                {"p","q","r"},
                {"s","t"},
                {"u","v"},
                {"w","x"},
                {"y","z"}
        };

        String input = "6789";
        String notInclude = "ux";
        process(nums,input,0,notInclude);
    }

    public static void process(String[][] nums, String input, int startIndex, String notInclude){
        if (startIndex == input.length()){
            isValid(notInclude);
            return;
        }
        String[] temp = nums[input.charAt(startIndex) - '0'];
        for (String s : temp) {
            sb.append(s);
            process(nums,input,startIndex + 1,notInclude);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void isValid(String notInclude){
        int temp = 0;
        String tempSb = sb.toString();
        for (int i = 0; i < notInclude.length(); i++) {
            if (tempSb.contains(String.valueOf(notInclude.charAt(i)))) {
                temp++;
            }
        }
        if (temp != notInclude.length()){
            System.out.println(sb);
        }
    }

}
