package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class HJ41 {
    /**
     * 基本思想是用HashSet存储结果和去重
     * 至于输入的方式使用Scanner类调用nextInt方法，能直接接受数值
     * @throws Exception
     */
    public void mySolution() throws Exception {
        //Scanner in = new Scanner(System.in);
        //int n = in.nextInt();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int count = Integer.parseInt(line);
        line = reader.readLine();
        String[] weight = line.split(" ");
        line = reader.readLine();
        String[] num = line.split(" ");
        HashSet<Integer> storage = new HashSet<Integer>();
        HashSet<Integer> tempForTemp = new HashSet<>();
        storage.add(0);
        for (int i = 0; i < count; i++) {
            for (int j =  1; j <= Integer.parseInt(num[i]); j++){
                for (int temp : storage) {
                    tempForTemp.add(temp + Integer.parseInt(weight[i]) * j);
                }
            }
            storage.addAll(tempForTemp);
            tempForTemp.clear();
        }
        System.out.print(storage.size());
    }
}
