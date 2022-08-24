package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class HJ31 {
    /**
     * 还能改善，字符分割用了个判断语句，有没有更好的办法？
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        ArrayList<String> output = new ArrayList<String>();
        StringBuilder tempOutput = new StringBuilder();
        for (int i = 0; i < line.length(); i++ ){
            if (judge(line.charAt(i)) && i != line.length() - 1) {
                tempOutput.append(line.charAt(i));
            }  else if (i == line.length()-1 ){
                tempOutput.append(line.charAt(i));
                output.add(tempOutput.toString());
                tempOutput.delete(0,tempOutput.length());
            }  else {
                output.add(tempOutput.toString());
                tempOutput.delete(0,tempOutput.length());
            }
        }
        Collections.reverse(output);
        for (String i : output) {
            System.out.print(i + ' ');
        }
    }
    public static boolean judge (char data) {
        if (data >= 'a' && data <= 'z') {
            return true;
        }
        if (data >= 'A' && data <= 'Z') {
            return true;
        }
        return false;
    }

    /*
        String[] words = str.split("[^A-Za-z]");
        StringBuilder result = new StringBuilder();

        // 逆序添加分割完的单词
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]).append(" ");
        }
        return result.toString().trim();
     */
}
