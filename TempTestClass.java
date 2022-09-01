

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
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
            for ( int i = storage.length; i >= 0; i--){
                System.out.print(storage[i] + " ");
            }
        }
    }
}



