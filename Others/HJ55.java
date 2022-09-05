package Others;

import java.util.Scanner;

public class HJ55 {
    /**
     * 两种可能，能被7整除或者包含7
     * @throws Exception
     */
    public void mySolution() throws Exception {
        Scanner sc = new Scanner(System.in);
        while ( sc.hasNext()){
            int temp = sc.nextInt();
            int sum = 0;
            for (int i = 1; i <= temp; i++){
                if (i % 7 == 0) {
                    sum++;
                } else {
                    if (String.valueOf(i).contains("7")){
                        sum++;
                    }
                }
            }
            System.out.println(sum);
        }
    }
}
