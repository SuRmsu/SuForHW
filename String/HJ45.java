package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HJ45 {
    /**
     * 难得写了一次最优解法，使用一个数组存储出现次数即可
     * @throws Exception
     */
    public void mySolution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int times = Integer.parseInt(br.readLine());
        String str = null;
        int[] record = new int[26];
        int output = 0;
        Arrays.fill(record, 0);
        for (int i = 0; i < times; i++) {
            str = br.readLine();
            str.toLowerCase();
            for (int j = 0; j < str.length(); j++) {
                record[(int) (str.charAt(j) - 'a')] += 1;
            }
            Arrays.sort(record);
            int t = 26;
            for (int j = 25; j >= 0; j--) {
                output += t * record[j];
                t--;
            }
            System.out.println(output);
            Arrays.fill(record, 0);
            output = 0;
        }


    }
}
