package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class HJ92 {
    /**
     * 暴力算法，存储长度和字符串，单指针方法
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] storage = {"0",null} ;
        while (null != input && input != "") {
            int count = 0;
            input += "a";
            for (int i = 0; i < input.length();i++){
                if ( input.charAt(i) >= '0' && input.charAt(i) <= '9'){
                    count++ ;

                } else if ( count != 0 && count == Integer.parseInt(storage[0])  ){
                    storage[1] += input.substring(i - count,i);
                    count = 0;
                } else if ( count != 0 && count > Integer.parseInt(storage[0]) ) {
                    storage[0] = String.valueOf(count);
                    storage[1] = input.substring(i - count,i);
                    count = 0;
                } else {
                    count = 0;
                }
            }
            input = br.readLine();
            System.out.println(storage[1] + "," + storage[0]);
            storage[0] = "0";
            storage[1] = null;
        }

    }

    /**
     * 使用正则表达式，直接使用split函数分割代码即可
     * @throws Exception
     */
    public void secondSolution() throws Exception {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] ss = line.split("[^0-9]+");
            int max  = 0;
            ArrayList<String> list = new ArrayList<>();
            for(String s : ss){
                if(s.length() > max){
                    max = s.length();
                    list.clear();
                    list.add(s);
                }else if(s.length() == max){
                    max = s.length();
                    list.add(s);
                }
            }
            StringBuilder sb = new StringBuilder();
            for(String item : list){
                sb.append(item);
            }
            sb.append(",").append(max);
            System.out.println(sb);
        }
    }

    /* 一种想法，第一次先获取数字字串最长的长度，第二次截取最长长度的字串并判断是不是纯数字，时间复杂度为O(n)
            int n = 0,maxOne = 0;//计数变量
        // 遍历一遍获取长度最大值，时间复杂度O(n)
        for(int i=0; i < string.length(); i++){
            if(Character.isDigit(string.charAt(i))){
                n++;
                maxOne = Integer.max(maxOne,n);
            }else{
                n = 0;
            }
        }
        // 再遍历一遍获取长度为最大值的子串，并判断子串中是否全是数字，时间复杂度O(n)
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= string.length() - maxOne; i ++){
            String str = string.substring(i, i + maxOne);
            if(0 == str.replaceAll("[0-9]", "").length())){
                sb.append(str);
            }
        }
        System.out.println(sb+","+ maxOne);
     */

    /* 这和上一种解法本质上一样的
                Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                int len = str.length();
                int[] dp = new int[len+1];
                int res = 0;
                for (int i =1; i <= len; i++) {
                    char tmp = str.charAt(i-1);
                    if (tmp >= '0' && tmp <= '9') {
                        dp[i] = dp[i-1] +1;
                        res = Math.max(res, dp[i]);
                    }
                }

                for (int i =1; i<= len; i++) {
                    if (dp[i] == res) {
                        System.out.print(str.substring(i - res, i));
                    }
                }
                System.out.println("," + res);
            }
     */

}
