import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.pow;

public class TempTestClass {
    /**
     * 简单的转化为StringBuffer，然后调用转换函数reverse()
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        /*
        StringBuilder tempOutput = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            tempOutput.append(str.charAt(i));
        }
        */
        StringBuffer strb = new StringBuffer(str);
        strb.reverse();
        System.out.println(strb.toString());
    }

}

