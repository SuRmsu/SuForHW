package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeMap;

public class HJ65 {
    /**
     * 使用倒序排列的TreeMap来存储长度和字串
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<Integer,String> storage = new TreeMap<>(Comparator.reverseOrder());
        String str3 = br.readLine();
        String str2 = br.readLine();
        String str1;
        if ( str3.length() < str2.length()) {
            str1 = str3;
        } else {
            str1 = str2;
            str2 = str3;
        }
        for ( int i = 0; i < str1.length(); i++){
            for ( int j = 0; j < str2.length(); j++) {
                int temp = 0;
                while ((i + temp < str1.length()) && (j + temp < str2.length()) && str1.charAt(i + temp) == str2.charAt(j + temp)){
                    temp++;
                }
                if (temp != 0 && storage.get(temp) == null) {
                    storage.put(temp,str1.substring(i,i+temp));
                }
            }
        }
        for(int i : storage.keySet()){
            System.out.println(storage.get(i));
            return;
        }


    }
    /*以下代码使TreeMap按照字符串长度排序
    TreeMap<String,Integer> input = new TreeMap<String, Integer>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int num= Integer.compare(o1.length(), o2.length());
                if(num==0)
                {
                    return o1.compareTo(o2);
                }
                return num;
            }
        });
     */

}
