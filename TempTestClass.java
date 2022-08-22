import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.Math.pow;

public class TempTestClass {
    /**
     * 简单的转化为StringBuffer，然后调用转换函数reverse()
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String re2 = "a\\&c"; // 对应的正则是a\&c
        System.out.println("a&c".matches(re2));
        System.out.println("a-c".matches(re2));
        System.out.println("a&c".matches(re2));
    }

}

