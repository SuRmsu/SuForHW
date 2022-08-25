package String;

import java.util.Calendar;
import java.util.Scanner;

public class HJ73 {
    /**
     * 常规计算方法，满足每4年为闰年，每百年需要满足400年才为闰年
     * @throws Exception
     */
    public void mySolutions() throws Exception{
        Scanner in = new Scanner(System.in);
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            year = 29;
        } else {
            year = 28;
        }

        int[] dayscount = { 31,year,31,30,31,30,31,31,30,31,30,31 };

        int count = 0;

        for (int i = 0 ; i < month - 1; i++) {
            count += dayscount[i];
        }

        count += day;
        System.out.print(count);


    }
    public void secondSolutions() throws Exception{
        Scanner in=new Scanner(System.in);
        int y=in.nextInt();
        int m=in.nextInt();
        int d=in.nextInt();
        Calendar c1=Calendar.getInstance();//实例化
        c1.set(y, m-1, d);//注意月份从0开始
        System.out.println(c1.get(Calendar.DAY_OF_YEAR));
    }
}
