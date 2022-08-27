package Others;

import java.util.Scanner;
import java.util.TreeMap;

public class HJ8 {
    /**
     * TreeMap结构能根据key升序存储
     * @throws Exception
     */
    public void mySolution() throws Exception {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        int temp;
        TreeMap<Integer,Integer> storage = new TreeMap<>();
        for (int i = 0; i < times; i++){
            temp = in.nextInt();
            storage.put(temp,storage.getOrDefault(temp,0) + in.nextInt());
        }
        for(Integer i : storage.keySet() ) {
            System.out.println( i + " " + storage.get(i));
        }

    }
}
