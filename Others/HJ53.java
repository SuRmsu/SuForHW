package Others;

import java.util.Scanner;

public class HJ53 {
    /**
     * 暴力算法，算出完整的杨辉三角
     */
    public void mySolution() throws Exception {

    }
    /**
     * 找规律，从第三位开始 2 3 2 4 位置是偶数
     */
    public static void bestSolution() throws Exception {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int num = in.nextInt();
            if(num == 1 || num == 2){
                System.out.println(-1);
                continue;
            }
            else if(num % 4 == 1 || num % 4 == 3){
                System.out.println(2);
                continue;
            }
            else if(num % 4 == 0){
                System.out.println(3);
                continue;
            }
            else if(num % 4 == 2){
                System.out.println(4);
                continue;
            }
        }

    }


}
