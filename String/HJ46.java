package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ46 {
    public void mySolution() throws Exception {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int length = Integer.parseInt(br.readLine());
        System.out.println(str.substring(0,length));
    }
}
