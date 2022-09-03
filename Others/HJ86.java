package Others;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ86 {
    /**
     * 简单的比较
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = Integer.toBinaryString(Integer.parseInt(br.readLine()));
        int count = 0;
        int maxCount = 0;
        for(int i = 0 ; i < input.length(); i++){
            if (input.charAt(i) == '1'){
                count++;
                maxCount = Math.max(maxCount,count);
            } else {
                count = 0;
            }
        }
        System.out.print(maxCount);
    }
    /**
     * 位运算 >>是有符号位移 >>>是无符号位移
     */
    public void secondSolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input =Integer.parseInt(br.readLine());
        int count = 0;
        int maxCount = 0;
        while(input != 0){
            if ((input & 1) == 1){
                count++;
                maxCount = Math.max(maxCount,count);
            } else {
                count = 0;
            }
            input >>>= 1;
        }
        System.out.print(maxCount);
    }
    /**
     * 也可以直接用0作为分割符，找到最长的数组
     */
}
