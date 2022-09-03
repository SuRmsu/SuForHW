package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class HJ81 {
    /**
     * 用HashSet存储对比的字符串，大大降低时间复杂度
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Character> storage = new HashSet<>();
        String input3 = br.readLine();
        String input2 = br.readLine();
        String input1;
        if ( input3.length() > input2.length()){
            input1 = input3;
        } else {
            input1 = input2;
            input2 = input3;
        }
        for ( int i = 0 ; i < input1.length(); i++) {
            storage.add(input1.charAt(i));
        }
        int flag = 0;
        for ( int i = 0 ; i < input2.length(); i++){
            if ( !storage.contains(input2.charAt(i)) ){
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
