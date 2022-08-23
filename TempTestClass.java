import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        StringBuffer sb = new StringBuffer();

        while ((input = bf.readLine()) != null && !input.equals("")) {
            if (input.length() < 8) {
                sb.append("NG");
                sb.append("\n");
                continue;
            }

            int count = 0;
            Pattern p1 = Pattern.compile("\\d");
            if (p1.matcher(input).find()) {
                count++;
            }
            Pattern p2 = Pattern.compile("[a-z]");
            if (p2.matcher(input).find()) {
                count++;
            }
            Pattern p3 = Pattern.compile("[A-Z]");
            if (p3.matcher(input).find()) {
                count++;
            }
            Pattern p4 = Pattern.compile("\\W");
            if (p4.matcher(input).find()) {
                count++;
            }
            int flag = 0;
            for (int i = 0; i < input.length(); i++) {
                for (int j = i + 1; j < input.length() - 2; j++) {
                    if (input.charAt(j) == input.charAt(i)) {
                        if (input.charAt(j + 1) == input.charAt(i + 1)) {
                            if (input.charAt(j + 2) == input.charAt(i + 2)) {
                                flag++;
                            }

                        }
                    }
                    if (flag == 1)
                        break;
                }
                if (flag == 1)
                    break;
            }
            if (flag == 1) {
                sb.append("NG");
                sb.append("\n");
                continue;
            }
            if (count > 2) {
                sb.append("OK");
                sb.append("\n");
                continue;
            }
            sb.append("NG");
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

}

