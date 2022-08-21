package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ4 {
    /**
     * 暴力算法了属于是，我的员工要是写出这样的代码，我立刻就给他开除了
     * @throws Exception
     */
    public void mySolution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();
        char[] tempStringArray = inputString.toCharArray();
        int length = tempStringArray.length;
        int circle = length / 8;
        for (int i = 0; i < circle; i++) {
            String output = "";
            for (int j = 0; j < 8; j++) {
                output = output + tempStringArray[i*8 + j];
            }
            System.out.println(output);
        }
        if (length % 8 != 0){
            String output = "";
            for (int i = 0; i < length % 8; i++) {
                output = output + tempStringArray[circle * 8 + i];
            }
            for (int j = 0; j < 8 - length % 8; j++) {
                output = output +"0";
            }
            System.out.println(output);
        }

    }
    /**
     * 使用StringBuilder，建立字符串缓存区，使用append添加0，对string对象使用substring进行切割
     */
}
