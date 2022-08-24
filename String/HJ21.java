package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ21 {
    /**
     * switch判断数据，简单有效，可以不用记住ascii码其实
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] tempInput = str.toCharArray();
        for (int i = 0; i < tempInput.length; i++) {
            if (tempInput[i] > 64 && tempInput[i] < 91 ){
                if (tempInput[i] < 90) {
                    tempInput[i] = (char) (tempInput[i] + 33);
                } else {
                    tempInput[i] = (char) (tempInput[i] + 7);
                }
                continue;
            }

            switch (tempInput[i]) {
                case 'a':
                case 'b':
                case 'c':
                    tempInput[i] = '2';
                    break;
                case 'd':
                case 'e':
                case 'f':
                    tempInput[i] = '3';
                    break;
                case 'g':
                case 'h':
                case 'i':
                    tempInput[i] = '4';
                    break;
                case 'j':
                case 'k':
                case 'l':
                    tempInput[i] = '5';
                    break;
                case 'm':
                case 'n':
                case 'o':
                    tempInput[i] = '6';
                    break;
                case 'p':
                case 'q':
                case 'r':
                case 's':
                    tempInput[i] = '7';
                    break;
                case 't':
                case 'u':
                case 'v':
                    tempInput[i] = '8';
                    break;
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    tempInput[i] = '9';
                    break;

            }

        }
        System.out.print(new String(tempInput));

    }

    /**
     * 直接用减法就能找到编码，也可以直接用字符比较
     * @throws Exception
     */
    public void bestSolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            //获取第i个字符
            char c = str.charAt(i);
            //判断，处理
            if (c >= 'A' && c < 'Z') {
                c = (char) (c + 'b' - 'A');
            } else if (c == 'Z') {
                c = 'a';
            } else if (c>='a' && c<='c') {
                c = '2';
            } else if (c>='d' && c<='f') {
                c = '3';
            } else if (c>='g' && c<='i') {
                c = '4';
            } else if (c>='j' && c<='l') {
                c = '5';
            } else if (c>='m' && c<='o') {
                c = '6';
            } else if (c>='p' && c<='s') {
                c = '7';
            } else if (c>='t' && c<='v') {
                c = '8';
            } else if (c>='w' && c<='z') {
                c = '9';
            }
            sb.append(c);
        }
        System.out.println(sb.toString().trim());
    }
}
