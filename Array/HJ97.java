package Array;

import java.util.Scanner;

public class HJ97 {
    /**
     * 暴力算法，注意int/int是没有小数的
     * @throws Exception
     */
    public void mySolution() throws Exception {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        int count = 0;
        int avg = 0;
        int countInt = 0;
        for (int i = 0; i < times; i++) {
            int input = sc.nextInt();
            if (input > 0) {
                countInt++;
                avg += input;
            } else if (input < 0) {
                count++;
            }
            if (input == 0) {
                countInt += 0;

            }

        }
        double output;
        if (countInt != 0) {
            output = avg / (double) countInt;
        } else {
            output = 0.0;
        }
        System.out.print(count + " " + String.format("%.1f", output));
    }
    /*
    System.out.println(negative+" "+Math.round(sum*10.0/positive)/10.0);
     */
}
