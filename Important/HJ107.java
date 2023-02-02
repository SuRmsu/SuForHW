package Important;

import java.util.Scanner;


public class HJ107 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            double input = sc.nextDouble();
            System.out.printf("%.1f", process(input));
        }
    }

    public static double process(double input) {
        double left = Math.min(-1.0, input);
        double right = Math.max(1.0, input);
        double mid = 0.0;
        while (right - left > 0.001) {
            mid = (left + right) / 2;
            // 若乘积大于num，则立方根在mid的左侧
            if (mid * mid * mid > input) {
                right = mid;
            }
            // 若成绩小于num，则立方根在mid的右侧
            else if ( mid * mid * mid < input) {
                left = mid;
            } else {
                return mid;
            }
        }
        return right;
    }
}

