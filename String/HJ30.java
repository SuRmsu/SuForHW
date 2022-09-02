package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HJ30 {
    public void mySolution() throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().replace(" ", "").toCharArray();
        int isOdd = input.length % 2 == 1 ? 1 : 0;
        char[] tempOdd = new char[input.length / 2 + isOdd];
        char[] tempEven  = new char[input.length / 2];
        //分割
        for ( int i = 0 ,j = 0, k =0; i < input.length; i++){
            if (i % 2 == 0) {
                tempOdd[j] = input[i];
                j++;
            } else {
                tempEven[k] = input[i];
                k++;
            }

        }
        //排序
        Arrays.sort(tempOdd);
        Arrays.sort(tempEven);
        //合并
        for ( int i = 0,j = 0, k =0 ; i < input.length; i++ ){
            if (i % 2 == 0) {
                input[i] = tempOdd[j];
                j++;
            } else {
                input[i] = tempEven[k];
                k++;
            }
        }
        //二进制处理转换
        StringBuilder output = new StringBuilder(new String(input));
        for (int i = 0 ; i < output.length(); i++){
                String s = output.substring(i,i+1);
                if(s.matches("[0-9a-fA-F]")){
                    //把16进制转成10进制，再转成二进制
                    StringBuilder binary = new StringBuilder(Integer.toBinaryString(Integer.parseInt(s,16)));
                    int len = binary.length();
                    for (int j = 0; j <4-len ; j++) { //补零
                        binary.insert(0, "0");
                    }
                    binary = binary.reverse();//翻转
                    int n = Integer.parseInt(binary.toString(), 2); //把二进制转成10进制
                    String hexString = Integer.toHexString(n).toUpperCase();//转成16进制字符串大写
                    output.replace(i,i+1,hexString);//替换
                }

        }

    }
}
