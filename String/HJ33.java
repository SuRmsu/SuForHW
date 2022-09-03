package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ33 {
    /**
     * 普通的暴力算法，切割，循环
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] input = str.split("\\.");
        StringBuilder temp = new StringBuilder();
        for ( int i = 0 ; i < input.length; i++){
            input[i] = Long.toBinaryString(Long.parseLong(input[i]));
        }
        for( int i = 0; i < 4; i++){
            for ( int j = 0; j < 8 - input[i].length(); j++ ){
                temp.append('0');
            }
            temp.append(input[i]);
        }
        long count = 0;
        for( int i = temp.length() - 1; i >= 0; i--){
            count += (temp.charAt(i) - '0') * Math.pow(2,temp.length() - 1 - i);
        }
        System.out.println(count);
        count = 0;
        str = br.readLine();
        str = Long.toBinaryString(Long.parseLong(str));
        temp.delete(0,temp.length());
        for(int i = 0 ; i < 32 - str.length(); i++){
            temp.append('0');
        }
        temp.append(str);
        for(int i = 0; i < 32; i += 8 ){
            for (int j = 7; j >= 0; j-- ){
                count += (temp.charAt(i + j) - '0') * Math.pow(2,7 - j);
            }
            System.out.print(count);
            if (i == 24) {
                continue;
            }
            System.out.print(".");
            count = 0;
        }
    }

    /**
     * 很酷的想法，增加8位二进制等于整体*256
     * 反过来减少8位二进制等于整体%256
     * @param str
     * @return
     */
    public String convert(String str) {
        // ipv4 -> int
        if (str.contains(".")) {
            String[] fields = str.split("\\.");
            long result = 0;
            for (int i = 0; i < 4; i++) {
                result = result * 256 + Integer.parseInt(fields[i]);
            }
            return "" + result;
        }
        // int -> ipv4
        else {
            long ipv4 = Long.parseLong(str);
            String result = "";
            for (int i = 0; i < 4; i++) {
                result = ipv4 % 256 + "." + result;
                ipv4 /= 256;
            }
            return result.substring(0, result.length() - 1);
        }
    }
}
