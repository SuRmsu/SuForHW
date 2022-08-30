package Others;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class HJ94 {
    /**
     * 使用LinkedHashMap
     * @throws Exception
     */
    public void mySolution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedHashMap<String,Integer> storage = new LinkedHashMap<>();
        br.readLine();
        String[] candidate = br.readLine().split(" ");
        for ( String temp : candidate){
            storage.put(temp,0);
        }
        br.readLine();
        int count = 0;
        String[] vote = br.readLine().split(" ");
        for ( String temp : vote){
            if (storage.get(temp) != null){
                storage.put(temp, storage.get(temp) + 1);
            } else {
                count++;
            }
        }
        for (String temp : storage.keySet() ){
            System.out.println(temp+ " : " + storage.get(temp));
        }
        System.out.println("Invalid : " + count);
    }
}
