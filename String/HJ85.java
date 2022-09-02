package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ85 {
    /**
     * 参考HJ32
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int length = input.length();
        int count = 1;
        int maxCount = 0;
        //aba的形式
        for ( int i = 1,j = 0; i < length - 1; i++ ){
            while (  (i - 1 - j) >= 0 && (i + 1 + j) < length &&
                    input.charAt(i - 1 - j) == input.charAt( i + j + 1)){
                count += 2;
                j++;
            }
            maxCount = Math.max(count,maxCount);
            count = 1;
            j = 0;
        }
        count = 0;
        //aa的形式
        for ( int i = 1,j = 0; i < length; i++ ){
            while (  (i - 1 - j) >= 0 && (i + j) < length &&
                    input.charAt(i - 1 - j) == input.charAt( i + j )){
                count += 2;
                j++;
            }
            maxCount = Math.max(count,maxCount);
            count = 0;
            j = 0;
        }
        System.out.print(maxCount);
    }
}
