package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ63 {
    /**
     * 简单的循环和截取，可以使用正则来做，但是没有必要
     * @throws Exception
     */
    public void mySoluiton() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String output = null;
        int lengthN = Integer.parseInt(br.readLine());
        int maxCount = 0;
        for ( int i = 0; i < input.length() - lengthN + 1; i++ ){
            int tempCount = 0;
            for (int j = 0; j < lengthN; j++){
                if (input.charAt(i + j) == 'G' || input.charAt(i + j) == 'C'){
                    tempCount++;
                }
            }
            if ( maxCount < tempCount) {
                maxCount = tempCount;
                output = input.substring(i , i + lengthN);
            }
            tempCount = 0;
        }
        System.out.print(output);
    }
}
