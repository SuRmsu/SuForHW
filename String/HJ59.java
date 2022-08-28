package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class HJ59 {
    /**
     * 利用LinkedHashMap
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        LinkedHashMap<Character, Integer> storage = new LinkedHashMap<>();
        for (int i = 0; i < input.length(); i++) {
            storage.put(input.charAt(i), storage.getOrDefault(input.charAt(i), 0) + 1);
        }
        for (char i : storage.keySet()) {
            if (storage.get(i) == 1) {
                System.out.print(i);
                return;
            }

        }

        System.out.print(-1);
    }
}
