package Others;

import java.util.Scanner;

public class HJ108 {
    /**
     * 暴力算法，最大为相乘，最小为自增
     * @throws Exception
     */
    public void mySoluiton() throws Exception {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int minNum = num1 * num2;

        for ( int i = Math.max(num1,num2); i < minNum ; i++) {
            if ( i % num1 == 0 && i % num2 == 0) {
                minNum = i;
                break;
            }
        }
        System.out.print(minNum);
    }
    /**
     * 递归的思想
     */
    public int gcb(int a,int b,int c){
        if (a%b== 0){                    //a累加过程中永远可以整除自身，所以可以整除b时就是最小公倍数！
            return a;
        }
        return gcb(a+c,b,c);            //a累加自身原始值，例如a=4。  a=4,8,12,16....
    }
}
