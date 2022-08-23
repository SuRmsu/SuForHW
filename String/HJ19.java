package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class HJ19 {
    /**
     * 利用LinkedHashMap来进行记录顺序，将一整个数据保存为键，将出现次数保存为值
     * @throws Exception
     */
    public void mySolution() throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        LinkedHashMap<String, Integer> storges = new LinkedHashMap<>();
        String tempInput;
        while ((tempInput = bf.readLine()) != null && !tempInput.equals("")) {
            String[] tempRecord = new String[2];
            tempRecord = tempInput.split(" ");
            tempRecord[0] = tempRecord[0].substring(tempRecord[0].lastIndexOf("\\") + 1);
            if (tempRecord[0].length() > 16) {
                tempRecord[0] = tempRecord[0].substring(tempRecord[0].length() - 16);
            }
            String tempKey = tempRecord[0] + " " + Integer.parseInt(tempRecord[1]);
            int tempValue = 1;
            if (storges.containsKey(tempKey)) {
                tempValue += storges.get(tempKey);
            }
            storges.put(tempKey, tempValue);
        }
        int count = 0;
        for (String i : storges.keySet()) {
            count++;
            if (count > storges.keySet().size() - 8) {
                System.out.println(i + " " + storges.get(i));
            }
        }


    }
}
