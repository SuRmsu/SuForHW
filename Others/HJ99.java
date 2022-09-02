package Others;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ99 {
    /**
     * also the best solution
     * 25 * 25 = 625
     * 625 - 25 = 600
     * 600 % 10的2次方 = 0
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());
        int count = 0;
        int temp;
        int nums;
        for ( int i = 0; i <= input; i++){
            temp = i * i;
            nums = (int) Math.log10(i) + 1;
            if ((temp - i) % Math.pow(10,nums) == 0 || i == 0){
                count++;
            }
        }
        System.out.print(count);
    }
}
