package String;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HJ14 {
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine().trim());
        String[] output = new String[num];
        for (int i = 0; i < num; i++) {
            output[i] = br.readLine();
        }
        Arrays.sort(output);//nnd，直接Arrays.sort就完事了
        for (int i = 0; i < num; i++) {
            System.out.println(output[i]);
        }

        //Arrays.stream(output).sorted().forEach(System.out::println);
    }
}
