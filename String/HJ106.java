package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HJ106 {
    /**
     * StringBuilder可以直接使用String构建，也可以直接当作String输出
     * @throws Exception
     */
    public void mySolution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(br.readLine());
        sb.reverse();
        System.out.println(sb);
    }
}
