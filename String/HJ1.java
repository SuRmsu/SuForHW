package String;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class HJ1 {
    /**
     * 常规方法，用空格分割，找最后一个字符串
     */
    public void mySolution() {
        Scanner a = new Scanner(System.in);
        if (a.hasNextLine()) {
            String s = a.nextLine();
            String[] temp = s.split(" ");
            int length = temp.length;
            length = temp[length - 1].length();
            System.out.println(length);
        }
    }
    /**
     * 第二种方法，从数组的最后往前计数，直到字符为‘ ’，则break，输出count
     */
    public void secondSolution() {};

    /**
     * 第三种方法类似于第二种方法，但是从前往后，使用的是InputStream
     */
    public void thirdSolution() throws IOException{
        InputStream is = System.in;
        int length = 0;
        char c;
        while ('\n' != (c = (char) is.read())){
            length++;
            if ( c == ' ') {
                length = 0;
            }
        }
        System.out.print(length);
    }
}
