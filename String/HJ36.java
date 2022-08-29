package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

public class HJ36 {
    /**
     * 计算密钥，替换，还能优化
     * @throws Exception
     */
    public void mySolution() throws Exception{
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
