package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HJ23 {
    /**
     * 两种思想，一种使用HashMap存储字符和出现的次数，另一种使用数组保存当前字符出现的次数。
     * 代码有很多需要简化的部分
     * @throws Exception
     */
    public void mySolution() throws Exception{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String str = read.readLine();
        char[] input = str.toCharArray();
        HashMap<Character,Integer> storage = new HashMap<Character,Integer>();
        for (int i = 0; i < input.length; i++) {
            if (storage.get(input[i]) != null) {
                storage.put(input[i],storage.get(input[i]) + 1);
            } else {
                storage.put(input[i],1);
            }
        }
        int minNum = storage.get(input[0]);
        ArrayList<Character> minChar = new ArrayList<Character>();
        for (char i : storage.keySet()) {
            if (storage.get(i) == minNum) {
                minChar.add(i);
            } else if (storage.get(i) < minNum) {
                minChar.clear();
                minChar.add(i);
                minNum = storage.get(i);
            }
        }
        for (int i = 0; i < input.length; i++){
            if (!minChar.contains(input[i])) {
                System.out.print(input[i]);
            }
        }
    }
    /*上述代码的简化
        // Map记录每个字母的次数
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        // 快速找出最少次数
        int min = Integer.MAX_VALUE;
        for (int times : map.values()) {
            min = Math.min(min, times);
        }
        StringBuilder res = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (map.get(ch) != min) {
                res.append(ch);
            }
        }
     */

    /**
     * 通过数组存储出现次数
     * @throws Exception
     */
    public static void secondSolution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while((input=br.readLine())!= null) {
            char ch[] = input.toCharArray();
            int[] count = new int[ch.length];
            int[] newcount = new int[ch.length];
            int min = 0;
            for(int i=0;i<ch.length-1;i++) {
                for(int j=i+1;j<ch.length;j++) {
                    if(ch[i]==ch[j]) {
                        count[i]++;
                        count[j]++;
                    }
                }
            }
            for(int i=0;i<count.length;i++) {
                newcount[i] = count[i];
            }
            Arrays.sort(count);
            for(int i=0;i<ch.length;i++) {
                if(newcount[i]>count[0]) {
                    System.out.print(ch[i]);
                }else {
                    continue;
                }
            }
            System.out.println("");
        }
    }
}
