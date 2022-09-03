package Others;

import java.util.Scanner;

/**
 * 高中的我们看到这个题的一瞬间，一定会觉得这是个弱智题。然而现在的我，想了好久。。
 * 题目的意思是已知等差数列和 Sn为 m^3，项数n为m，公差d为2，求首项a1
 */
public class HJ76 {
    public void bestSolution() throws Exception {
        Scanner sc = new Scanner(System.in);
        while ( sc.hasNextInt()) {
            int n = sc.nextInt();
            long sum = (long)Math.pow(n,3);
            int a1 = (int)sum / n - (n - 1);
            StringBuilder storage = new StringBuilder(Integer.toString(a1));
            for ( int i = 1; i < n; i++) {
                a1 = a1 + 2;
                storage.append("+");
                storage.append(a1);
            }
            System.out.println(storage);
        }
    }
}
