package Others;

import java.util.Arrays;
import java.util.Scanner;

public class HJ101 {
    /**
     * 倒序排列为顺序排列的倒序输出
     * @throws Exception
     */
    public void mySolution() throws Exception {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        int[] storage = new int[times];
        for(int i = 0; i < times; i++){
            storage[i] = sc.nextInt();
        }
        int flag = sc.nextInt();
        Arrays.sort(storage);
        if (flag == 0) {
            for ( int i : storage){
                System.out.print(i + " ");
            }
        } else {
            for ( int i = storage.length - 1; i >= 0; i--){
                System.out.print(storage[i] + " ");
            }
        }
    }
}
