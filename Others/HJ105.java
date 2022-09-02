package Others;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ105 {
    /**
     * 暴力统计
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        double avg = 0.0;
        int countNegative = 0;
        int countNonNegative = 0;
        double temp;
        while ( null != str && !str.equals("") ) {
            temp = Double.parseDouble(str);
            if ( temp < 0) {
                countNegative++;
            } else  {
                avg += temp;
                countNonNegative++;
            }
            str = reader.readLine();
        }
        System.out.println(countNegative);
        if (countNonNegative == 0) {
            System.out.println("0.0");
        } else {
            System.out.println( String.format("%.1f",avg / countNonNegative));
        }

    }
}
