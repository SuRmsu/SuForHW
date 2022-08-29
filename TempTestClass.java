

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String key = br.readLine();
        String input = br.readLine();
        LinkedHashSet<Character> storage = new LinkedHashSet<>();
        for ( char i : key.toCharArray()){
            storage.add(i);
        }
        for ( char i = 'a' ; i <= 'z'; i++ ){
            storage.add(i);
        }
        Character[] password = storage.toArray(new Character[0]);//太酷了
        char[] output = input.toCharArray();
        for (int i = 0; i < output.length; i++) {
            if ( output[i] >= 'a' && output[i] <='z') {
                output[i] = password[output[i] - 'a'];
            }
        }
        System.out.println(new String(output));

    }
}



