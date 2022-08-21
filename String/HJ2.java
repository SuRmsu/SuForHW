package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ2 {
    /**
     * 简单的解决办法，用一个字符串存储第一行，另一个字符存储小写后的要求的字符。大写的utf-8编码比小写的少32
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();
        char needChar = Character.toLowerCase(br.readLine().charAt(0));
        int count = 0;
        int i = 0;
        while (i < inputString.length()) {
            if (inputString.charAt(i) == needChar || inputString.charAt(i) == (char) (needChar - 32)) {
                count++;
            }
            i++;
        }

        System.out.println(count);
    }
    /**
     * 优化方法，将第一个字符串数组直接小写，第二个不需要进行这么多次转化，直接转为char数组后char[0]就ok了。
     */
}
