package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HJ75 {
    /**
     * 暴力算法，双向循环
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input1 = br.readLine();
        String input2 = br.readLine();
        int count = 0;
        int maxCount = 0;
        for ( int i = 0; i < input1.length(); i++) {
            for ( int j = 0 ; j < input2.length(); j++){
                while (i + count != input1.length() && j + count != input2.length() &&input1.charAt(i + count ) == input2.charAt(j + count)) {
                    count++;
                }
                maxCount =Math.max(count, maxCount);
                count = 0;
            }
        }
        System.out.print(maxCount);
    }
    /**
     * dp算法，待理解
     */
    public static void bestSolution() throws Exception {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            // TODO  1. 输入2个字符串
            String str1 = sc.next();
            String str2 = sc.next();

            // TODO  2. 转换为字符数组
            char[] c1 = str1.toCharArray();
            char[] c2 = str2.toCharArray();

            // TODO 3. 构建dp数组
            int[][] dp = new int[c1.length + 1][c2.length + 1];

            // TODO 4. 处理边界问题和初始值
            for (int i = 0; i <= c1.length; i++) {
                dp[i][0] = 0;
            }
            for (int j = 0; j <= c2.length; j++) {
                dp[0][j] = 0;
            }
            int res = 0;  // 定义一个结果来保存最长子串
            // TODO 5. 填充数组其余值
            for (int i = 1; i <= c1.length; i++) {
                for (int j = 1; j <= c2.length; j++) {
                    // TODO 6. 逐一对比每个字符
                    if (c1[i-1] == c2[j-1]){ //因为 c1.c2 是从0开始存字符的
                        // TODO 7. 相等则用不包含该字符的上一个最优解去 + 1
                        dp[i][j] = dp[i-1][j-1] +1;

                    }else {
                        dp[i][j] = 0;
                    }
                    res = Math.max(res,dp[i][j]);
                }
            }
            System.out.println(res);

        }

    }

    /**
     * 双指针算法
     * @throws Exception
     */
    public static void secondSolution() throws Exception{
        Scanner in = new Scanner(System.in);
        String ss1 = in.nextLine();
        String ss2 = in.nextLine();
        String s1 = ss1.length()<ss2.length() ? ss1:ss2;  // 短的字符串
        String s2 = ss1.length()<ss2.length() ? ss2:ss1;  // 长的字符串
        int n = 0;
        for(int i=0;i<s1.length();i++){              // 头指针从第一位开始递增
            for(int j=s1.length();j>i;j--){          // 尾指针从最后一位开始缩减
                if(s2.contains(s1.substring(i,j))){  // 第一次发现合集的长度一定是最大的
                    n = j-i>n ? j-i:n;               // 取每一次比较的最大值
                    break;                           // 已经是最大的，无需再进行后续的操作
                }
            }
        }
        System.out.println(n);
    }
}
