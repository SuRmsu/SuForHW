package Others;

import java.util.Scanner;

public class HJ7 {
    /**
     * 强制转型，double转int会直接丢掉小数点
     * @throws Exception
     */
    public void mySolution() throws Exception {
        Scanner in = new Scanner(System.in);
        double input =in.nextDouble();
        System.out.print((int)(input + 0.5));
    }
}
