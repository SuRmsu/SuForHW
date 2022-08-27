package Others;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HJ15 {
    /**
     * Integer类有两个方法，toBinaryString方法转化为二进制数字符串，bitCount方法直接计算转化成二进制后1的个数
     * @throws Exception
     */
    public void mySolution() throws Exception {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        String output = Integer.toBinaryString(input);
        System.out.println(output.replace("0","").length());
        //System.out.println(Integer.bitCount(input));
    }

    /**
     * 位运算
     * @throws Exception
     */
    public void secondSolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        // 对于进制转换问题，使用位运算效率有很大提升
        int count = 0;
        while(num > 0){
            int temp = num & 1;
            if(temp == 1){
                count++;
            }
            num = num >>> 1;
        }
        System.out.println(count);
    }
}
