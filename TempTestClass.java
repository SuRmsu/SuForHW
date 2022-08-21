import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        int i = 0;
        int count = 0;
        while (i<input.length()) {
            int tempChar = (int) input.charAt(i++);
            if (tempChar >= 0 && tempChar <= 127 && !tempList.contains(tempChar) ) {
                count++;
                tempList.add(tempChar);
            }
        }
        System.out.print(count);
    }

}

