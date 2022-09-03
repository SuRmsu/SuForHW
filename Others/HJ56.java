package Others;

import java.util.Scanner;

public class HJ56 {
    /**
     * 取余运算为0则为因数，同时只需要遍历到n/2就可以了
     * @throws Exception
     */
    public void mySolution() throws Exception{
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if ( n < 6) {
                System.out.println(0);
                continue;
            }
            int count = 0;
            for (int t = 6; t <= n; t++){
                int sum = 0;
                for ( int i = 1; i <= t / 2; i++){
                    if (t % i == 0){
                        sum += i;
                    }
                }
                if (sum == t){
                    count++;
                }
            }
            System.out.println(count);
        }

    }
}
