package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ29 {
    /**
     * 其实直接用if代替switch也没问题
     * 简单的字符匹配同上一题
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char compareChar;
        StringBuilder tempOutput = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            compareChar = str.charAt(i);
            switch (compareChar) {
                case 'z' :
                    tempOutput.append('A');
                    break;
                case 'Z' :
                    tempOutput.append('a');
                    break;
                case '9' :
                    tempOutput.append('0');
                    break;
                default :
                    if (compareChar <= 'z' && compareChar >= 'a'){
                        tempOutput.append((char)(compareChar - 32 + 1));
                    } else if (compareChar <= 'Z' && compareChar >= 'A'){
                        tempOutput.append((char)(compareChar + 32 + 1));
                    } else {
                        tempOutput.append((char)(compareChar + 1));
                    }

            }
        }
        System.out.println(tempOutput);
        tempOutput.delete(0,tempOutput.length());
        str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            compareChar = str.charAt(i);
            switch (compareChar) {
                case 'A' :
                    tempOutput.append('z');
                    break;
                case 'a' :
                    tempOutput.append('Z');
                    break;
                case '0' :
                    tempOutput.append('9');
                    break;
                default :
                    if (compareChar <= 'z' && compareChar >= 'a'){
                        tempOutput.append((char)(compareChar - 32 - 1));
                    } else if (compareChar <= 'Z' && compareChar >= 'A'){
                        tempOutput.append((char)(compareChar + 32 - 1));
                    } else {
                        tempOutput.append((char)(compareChar - 1));
                    }
            }
        }
        System.out.println(tempOutput);
    }
    /*
    这样很酷，但是并没有太多区别
            if 48 <= code <= 57:        #字符为数字时
            result += chr(48 + (code - 48 - mode) % 10)
        elif 65 <= code <= 90:      #字符为大写字母时
            result += chr(97 + (code - 65 - mode) % 26)
        elif 97 <= code <= 122:     #字符为小写字母时
            result += chr(65 + (code - 97 - mode) % 26)
        else:                       #其他字符
            result += i
     */
}
