package Array;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

public class HJ9 {
    /**
     * 使用LinkedHashSet类快速排序
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] storage = br.readLine().toCharArray();
        LinkedHashSet<Integer> output = new LinkedHashSet<Integer>();
        for (int i = storage.length - 1; i >= 0 ; i-- ){
            output.add((int) (storage[i] - '0'));
        }
        for ( int i : output ){
            System.out.print(i);
        }
    }
}
