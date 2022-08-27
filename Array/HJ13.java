package Array;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ13 {
    public void mySolutio() throws Exception{
        /**
         * 简单写法
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] output = str.split(" ");
        for(int i = output.length - 1; i > 0 ; i-- ){
            System.out.print(output[i] + " ");
        }
        System.out.print(output[0]);
    }
}
