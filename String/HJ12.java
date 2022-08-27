package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ12 {
    //同HJ11
    public void mySolution() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        for (int i = input.length() - 1; i >= 0; i--) {
            System.out.print(input.charAt(i));
        }
    }

}
