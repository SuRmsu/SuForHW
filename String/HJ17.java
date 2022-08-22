package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Math.pow;

public class HJ17 {
    /**
     * 用split分割字符串，用长度先筛选掉一些数据，然后对字符串进行匹配，用switch来判断。
     * 难以相信这坨代码超过了90%的人，简直离谱。有非常多的重复代码段可以优化，不忍直视说实话
     * 已经优化过了
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(";");
        int[] output = {0, 0};
        for (int i = 0; i < input.length; i++) {
            int length = input[i].length();
            if (length < 4 && length > 1) {
                char temp = input[i].charAt(0);
                int count = 0;
                int flag = 0;
                for (int j = 1; j < length; j++) {
                    int tempNum = input[i].charAt(j);
                    if (tempNum > 47 && tempNum < 58) {
                        count += (tempNum - 48) * pow(10, length - 1 - j);
                    } else {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    switch (temp) {
                        case 'W':
                            output[1] += count;
                            break;
                        case 'S':
                            output[1] -= count;
                            break;
                        case 'A':
                            output[0] -= count;
                            break;
                        case 'D':
                            output[0] += count;
                            break;
                    }
                }
            }
        }
        System.out.println(String.format("%s,%s", output[0], output[1]));

    }



    /**
     * 使用正则表达式匹配
     *
     * @throws Exception
     */
    public void bestSolution() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] in = bf.readLine().split(";");
        int x = 0;
        int y = 0;
        for (String s : in) {
            // 不满足题目给定坐标规则
            if (!s.matches("[WASD][0-9]{1,2}")) {
                continue;
            }
            int val = Integer.valueOf(s.substring(1));
            switch (s.charAt(0)) {
                case 'W':
                    y += val;
                    break;
                case 'S':
                    y -= val;
                    break;
                case 'A':
                    x -= val;
                    break;
                case 'D':
                    x += val;
                    break;
            }
        }
        System.out.println(x + "," + y);

    }
}
