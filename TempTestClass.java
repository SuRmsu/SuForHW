import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        StringBuffer sb = new StringBuffer();

        while ((input = bf.readLine()) != null && !input.equals("")) {
            System.out.println(input.length());
            if (input.length() < 8 ) {
                sb.append("NG");
                continue;
            }

            int count = 0;
            if (input.matches(".?\\d.?")) {
                count++;
            }
            if (input.matches(".?[a-z].?")) {
                count++;
            }
            if (input.matches(".?[A-Z].?")) {
                count++;
            }
            if (input.matches(".?\\W.?")) {
                count++;
            }
            if (count > 2) {
                sb.append("OK");
                continue;
            }
            sb.append("NG");
        }
        System.out.println(sb.toString());
    }

}

