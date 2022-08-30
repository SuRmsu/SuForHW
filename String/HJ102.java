package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class HJ102 {
    public void mySolution() throws Exception {
        /**
         * 暴力算法，虽然是屎山，但你就说问题解决了没，还挺自豪的
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] storage = new int[36];
        Arrays.fill(storage, 0);
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if (temp >= 'a' && temp <= 'z') {
                storage[temp - 'a' + 10] += 1;
            } else {
                storage[temp - '0'] += 1;
            }
        }
        for (int i = 0; i < 36; i++) {
            int maxNum = Arrays.stream(storage).max().getAsInt();
            for (int j = 0; j < 36; j++) {
                if (storage[j] == maxNum && maxNum != 0) {
                    if (j >= 0 && j <= 9) {
                        System.out.print((char) (j + '0'));
                        storage[j] = 0;
                        break;
                    } else {
                        System.out.print((char) (j + 'a' - 10));
                        storage[j] = 0;
                        break;
                    }

                }
            }
        }
    }

    /**
     * 一种使用TreeMap解决的方法，是常规的方法
     */
    public void secondSolution() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        //将字符存入TreeMap
        TreeMap<Character,Integer> map = new TreeMap<>();
        for (int i = 0; i < str.length(); i++) {
            if(!map.containsKey(str.charAt(i))){
                map.put(str.charAt(i),1);
            }else{
                map.put(str.charAt(i),map.get(str.charAt(i))+1);
            }
        }

        //找到max值
        int max = 0;
        for (int val:map.values()) {
            if(val>max)
                max = val;
        }

        //输出
        while (max>0){
            for (char key:map.keySet()){
                if(map.get(key)==max)
                    System.out.print(key);
            }
            max--;
        }

    }
}
