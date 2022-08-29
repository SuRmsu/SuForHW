package Others;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HJ62 {
    /**
     * 使用api计算，调用Integer.bieCount()方法
     * @throws Exception
     */
    public void mySolution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String temp = br.readLine();
        while ( null != temp ){
            int input = Integer.parseInt(temp);
            int output = Integer.bitCount(input);
            System.out.println(output);
            temp = br.readLine();
        }
    }
    /**
     * 使用位运算
     * @throws Exception
     */
    public void secondSolution() throws Exception{
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            int in = input.nextInt();
            int count=0;
            while(in!=0){
                if(in%2==1) count++;
                in=in>>1;
            }
            System.out.println(count);
        }
    }
}
