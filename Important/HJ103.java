package Important;

import java.util.Arrays;
import java.util.Scanner;

public class HJ103 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int nums = sc.nextInt();
            int[] storage = new int[nums];
            for (int i = 0; i < nums; i++) {
                storage[i] = sc.nextInt();
            }
            int[] dp = new int[nums + 1];
            Arrays.fill(dp, 1);
            int max = 1;
            for (int i = 1; i < nums; i++) {
                for (int i1 = 0; i1 < i; i1++) {
                    if (storage[i1] < storage[i]) {
                        dp[i] = Math.max(dp[i], dp[i1] + 1);
                    }
                    max = Math.max(dp[i], max);
                }
            }
            System.out.println(max);


        }
    }
}
