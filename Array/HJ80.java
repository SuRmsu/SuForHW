package Array;

import java.util.Scanner;
import java.util.TreeSet;

public class HJ80 {
    /**
     * TreeSet自动排序
     * @throws Exception
     */
    public void mySolution() throws Exception{
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        TreeSet<Integer> storage = new TreeSet<>();
        int input;
        for (int i = 0; i < times; i++ ){
            input = sc.nextInt();
            storage.add(input);
        }
        times = sc.nextInt();
        for (int i = 0; i < times ; i++){
            input = sc.nextInt();
            storage.add(input);
        }
        for (int temp : storage){
            System.out.print(temp);
        }
    }
}
