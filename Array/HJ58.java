package Array;

import java.util.Arrays;
import java.util.Scanner;

public class HJ58 {
    /**
     * 暴力排序
     * @throws Exception
     */
    public void mySolution() throws Exception {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        int k = sc.nextInt();
        int[] storage = new int[times];
        for ( int i = 0; i < times; i++){
            storage[i] = sc.nextInt();
        }
        Arrays.sort(storage);
        int count = 0;
        for ( int i : storage ){
            if ( count != k) {
                System.out.print(i + " ");
                count++;
            }else {
                break;
            }

        }
    }
}
