package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ90 {
    /**
     * \d匹配数字，\D匹配非数字
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("\\.");
        int flag = 0;
        if ( input.length != 4) {
            System.out.print("NO");
            return;
        }
        for (int i = 0 ; i < 4; i++){
            if (input[i].matches("\\D") || input[i].length() == 0){
                System.out.print("NO");
                return;
            }
            if ( input[i].length() >= 2 && input[i].charAt(0) == '0'|| input[i].contains("-")  || input[i].contains("+") ){
                System.out.print("NO");
                return;
            }

            int temp = Integer.parseInt(input[i]);
            if ( temp < 0 || temp > 255) {
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }
}
