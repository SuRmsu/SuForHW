package Others;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ38 {
    /**
     * 模拟下落过程
     * @throws Exception
     */
    public void bestSolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input = br.readLine()) != null) {
            double h = Double.parseDouble(input);
            double temp = h / 2;
            for (int i = 1; i < 5; i++){
                h += temp * 2;
                temp = temp / 2;
            }
            System.out.println(h);
            System.out.println(temp);
        }
    }
}
