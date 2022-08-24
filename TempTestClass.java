

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char compareChar = str.charAt(0);
        StringBuilder tempOutput = new StringBuilder();
        if (compareChar > 'a' && compareChar < 'z') {
            str.toUpperCase();
            for (int i =0; i < str.length(); i++) {
                if (str.charAt(i) == 'Z') {
                    tempOutput.append('A');
                } else {
                    tempOutput.append((char) (compareChar + 1));
                }
            }
        } else if (compareChar > 'A' && compareChar < 'Z') {
            str.toLowerCase();

        } else if (compareChar > '0' && compareChar < '9') {

        }




    }

}

