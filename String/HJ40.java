package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HJ40 {
    public void mySolution() throws Exception{
        /**
         * 使用正则替换
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String s1 = line.replaceAll("[A-Z]+|[a-z]+","");
        String s2 = line.replaceAll(" ","");
        String s3 = line.replaceAll("[0-9]","");
        System.out.println(line.length() - s1.length());
        System.out.println(line.length() - s2.length());
        System.out.println(line.length() - s3.length());
        System.out.println(s2.length() + s3.length() + s1.length() - 2 * line.length());
    }

    /**
     * 调用api，Character类判断是否为字母数字空格等等
     * @throws Exception
     */
    public void secondSolution() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            //统计其中英文字符，空格字符，数字字符，其他字符的个数
            int letters = 0;
            int spaces= 0;
            int digits = 0;
            int others = 0;
            int len = s.length();
            for(int i = 0; i < len; i++){
                char c = s.charAt(i);
                if(Character.isLetter(c)){
                    letters++;
                }else if(Character.isDigit(c)){
                    digits++;
                }else if(Character.isSpaceChar(c)){
                    spaces++;
                }else{
                    others++;
                }
            }
//            统计其中英文字符，空格字符，数字字符，其他字符的个数
            System.out.println(letters);
            System.out.println(spaces);
            System.out.println(digits);
            System.out.println(others);
        }
    }
}
