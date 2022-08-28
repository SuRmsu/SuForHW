package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HJ34 {
    /**
     * 调用api
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine() ;
        char[] output = str.toCharArray();
        Arrays.sort(output);
        for (char temp : output) {
            System.out.print(temp);
        }
    }
}
