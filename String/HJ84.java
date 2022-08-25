package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ84 {
    /**
     * easy，也可以用正则表达式将其他字符替换为空
     * @throws Exception
     */
    public void mySolution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int count = 0;
        char[] tempInput = input.toCharArray();
        for (char i : tempInput) {
            if ( i >= 'A' && i <= 'Z' ) {
                count++;
            }
        }
        System.out.print(count);
    }
}
