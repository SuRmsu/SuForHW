package Others;

import java.util.Scanner;

public class HJ100 {
    public void mySolution() throws Exception{
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        int output = ( 2 + 2 + 3 * (input - 1) ) * input / 2;
        System.out.print(output);
    }
}
