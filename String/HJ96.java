package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HJ96 {
    /**
     * 暴力算法，非常不提倡，以后不能这样写，列举出每一种情况，并进行对应处理
     *
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            if (i == 0) {
                if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                    System.out.print("*");
                }
                System.out.print(input.charAt(i));

            } else if (i == input.length() - 1) {
                if ((input.charAt(i - 1) < '0' || input.charAt(i - 1) > '9')
                        && input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                    System.out.print("*");
                }
                if ((input.charAt(i) < '0' || input.charAt(i) > '9')
                        && input.charAt(i - 1) >= '0' && input.charAt(i - 1) <= '9') {
                    System.out.print("*");
                }
                System.out.print(input.charAt(i));
                if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                    System.out.print("*");
                }

            } else if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                if (input.charAt(i - 1) >= '0' && input.charAt(i - 1) <= '9') {
                    System.out.print(input.charAt(i));
                } else {
                    System.out.print("*" + input.charAt(i));
                }

            } else {

                if (input.charAt(i - 1) >= '0' && input.charAt(i - 1) <= '9') {
                    System.out.print("*");
                    System.out.print(input.charAt(i));
                } else {
                    System.out.print(input.charAt(i));
                }
            }
        }
    }

    /**
     * 最好的方法，正则匹配
     * @throws Exception
     */
    public void bestSolution() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            System.out.println(input.replaceAll("([0-9]+)", "*$1*")); //把所有的数字段提取出来，前后加上星号再放回去
        }
    }
}
