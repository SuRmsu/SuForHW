package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class HJ20 {
    /**
     * 使用正则表达式匹配大小写数字和符号的需求，可以用char数组直接代替大于小于进行比较，
     * 匹配字串直接进行双循环对比即可
     * 写的有点复杂，bestSolution简化了代码，但是逻辑是一样的。
     *
     * @throws Exception
     */
    public static void mySolution() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        StringBuffer sb = new StringBuffer();

        while ((input = bf.readLine()) != null && !input.equals("")) {
            if (input.length() < 8) {
                sb.append("NG");
                sb.append("\n");
                continue;
            }

            int count = 0;
            Pattern p1 = Pattern.compile("\\d");
            if (p1.matcher(input).find()) {
                count++;
            }
            Pattern p2 = Pattern.compile("[a-z]");
            if (p2.matcher(input).find()) {
                count++;
            }
            Pattern p3 = Pattern.compile("[A-Z]");
            if (p3.matcher(input).find()) {
                count++;
            }
            Pattern p4 = Pattern.compile("\\W");
            if (p4.matcher(input).find()) {
                count++;
            }
            int flag = 0;
            for (int i = 0; i < input.length(); i++) {
                for (int j = i + 1; j < input.length() - 2; j++) {
                    if (input.charAt(j) == input.charAt(i)) {
                        if (input.charAt(j + 1) == input.charAt(i + 1)) {
                            if (input.charAt(j + 2) == input.charAt(i + 2)) {
                                flag++;
                            }

                        }
                    }
                    if (flag == 1)
                        break;
                }
                if (flag == 1)
                    break;
            }
            if (flag == 1) {
                sb.append("NG");
                sb.append("\n");
                continue;
            }
            if (count > 2) {
                sb.append("OK");
                sb.append("\n");
                continue;
            }
            sb.append("NG");
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    public void bestSolution() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        StringBuffer sb = new StringBuffer();

        while (null != (input = reader.readLine())) {

            //设置四种类型数据初始为空即false，有数据了就更改为true
            boolean[] flag = new boolean[4];
            char[] chars = input.toCharArray();

            // 第一个条件
            if (chars.length < 9) {
                sb.append("NG").append("\n");
                continue;
            }

            // 第二个条件
            for (int i = 0; i < chars.length; i++) {
                if ('A' <= chars[i] && chars[i] <= 'Z') {
                    flag[0] = true;
                } else if ('a' <= chars[i] && chars[i] <= 'z') {
                    flag[1] = true;
                } else if ('0' <= chars[i] && chars[i] <= '9') {
                    flag[2] = true;
                } else {
                    flag[3] = true;
                }
            }
            int count = 0;
            for (int i = 0; i < 4; i++) {
                if (flag[i]) {
                    count++;
                }
            }

            // 第三个条件
            boolean sign = true;        //不存在两个大于2的子串相同，即！（i=i+3,i+1=i+4,i+2=i+5）
            for (int i = 0; i < chars.length - 5; i++) {
                for (int j = i + 3; j < chars.length - 2; j++) {
                    if (chars[i] == chars[j] && chars[i + 1] == chars[j + 1] && chars[i + 2] == chars[j + 2]) {
                        sign = false;
                    }
                }
            }

            if (count >= 3 && sign) {
                sb.append("OK").append("\n");
            } else {
                sb.append("NG").append("\n");
            }
        }
        System.out.println(sb.toString());
    }

}
