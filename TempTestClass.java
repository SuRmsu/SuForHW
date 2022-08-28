

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Math.pow;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
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

        input.put("this", 1);
        input.put("thi",2);
        input.put("thisss", 15);
        for(String temp : input.keySet() ){
            System.out.println(temp);
        }

    }

}



