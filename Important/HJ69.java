package Important;

import java.util.Scanner;

public class HJ69 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            int [][] num1 = new int[x][y];
            int [][] num2 = new int[y][z];
            int [][] num3 = new int[x][z];
            for (int i = 0; i < x; i++) {
                for (int i1 = 0; i1 < y; i1++) {
                    num1[i][i1] = sc.nextInt();
                }
            }
            for (int i = 0; i < y; i++) {
                for (int i1 = 0; i1 < z; i1++) {
                    num2[i][i1] = sc.nextInt();
                }
            }

            for (int i = 0; i < x; i++) {
                for (int i1 = 0; i1 < z; i1++) {
                    for (int i2 = 0; i2 < y; i2++) {
                        num3[i][i1] += num1[i][i2] * num2[i2][i1];
                    }
                    System.out.print(num3[i][i1] + " ") ;
                }
                System.out.println();
            }
        }

    }



}