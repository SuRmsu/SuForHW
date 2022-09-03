package Others;

import java.util.Scanner;

public class HJ6 {
    /**
     * 求质数因子，直接从2开始累除，没问题，能被 4 整除就一定能被 2 整除，6，8，9也一样
     * @throws Exception
     */
    public void notMySolution() throws Exception {
        Scanner scanner = new Scanner(System.in);

        long num = scanner.nextLong();
        long k = (long) Math.sqrt(num);

        for (long i = 2; i <= k; i++) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
            }
        }
        System.out.println(num == 1 ? "" : num + " ");

    }
}
