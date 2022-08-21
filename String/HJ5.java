package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Math.pow;

public class HJ5 {
    /**
     * 用switch来判断字符对应的数字，但是其实可以用UTF-8码来代替的
     * @throws Exception
     */
    public void mySolution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int length = str.length();
        int output = 0;
        for (int i = 0; i < length - 2; i++) {
            output += (int)pow(16,i) * dataprocess(str.charAt(length - i - 1)) ;
        }
        System.out.println(output);
    }
    public static int dataprocess (char temp) {
        switch (temp) {
            case '0' :
                return 0;
            case '1' :
                return 1;
            case '2' :
                return 2;
            case '3' :
                return 3;
            case '4' :
                return 4;
            case '5' :
                return 5;
            case '6' :
                return 6;
            case '7' :
                return 7;
            case '8' :
                return 8;
            case '9' :
                return 9;
            case 'A' :
                return 10;
            case 'B' :
                return 11;
            case 'C' :
                return 12;
            case 'D' :
                return 13;
            case 'E' :
                return 14;
            case 'F' :
                return 15;
        }
        return 0;
    }
/**
 * 用ASCII码代替switch,char和int可以直接转化，准确的说是UTF-8编码，不过英文部分刚好一样
 */
    /*
    while((input = bf.readLine()) != null){
        String temp = input.substring(2,input.length());
        int sum = 0;
        int length = temp.length();
        for (int i = length - 1; i >= 0; i--) {
            char c = temp.charAt(i);
            int tempNum = (int)c;
            if (tempNum >= 65) {
                tempNum = tempNum - 65 + 10;
            } else {
              tempNum = tempNum -48;
            }
            sum = sum + (int) Math.pow(16,length - i - 1) * tempNum;

        }
        System.out.println(sum);
    }
    */
}
