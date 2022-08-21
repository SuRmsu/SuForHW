package String;


import MyUtils.AllSolution;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ11 {
    /**
     * 简单的转化为StringBuffer，然后调用转换函数reverse()
     * @throws Exception
     */
    public void mySolution() throws Exception{
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
/*
    int a = 0;
    int num = Integer.parseInt(str);
        do{
        a = num % 10; 此处会记录多的0
        num = num / 10; 此处会记录每个位
        System.out.print(a);
    }while(num > 0);
*/



}
