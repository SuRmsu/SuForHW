import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.Math.pow;

public class TempTestClass {
    /**
     * 简单的转化为StringBuffer，然后调用转换函数reverse()
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
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

