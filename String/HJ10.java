package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Scanner;

public class HJ10 {
    /**
     * 使用可变数组记录范围内已经出现了的字符，可以直接优化，使用一个128元素的数组，每有一个就直接count，
     * 可以再优化，使用BitSet，类似一维度的0100101，用一位就可代表有无，最后统计有的个数就行
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        int i = 0;
        int count = 0;
        while (i<input.length()) {
            int tempChar = (int) input.charAt(i++);
            if (tempChar >= 0 && tempChar <= 127 && !tempList.contains(tempChar) ) {
                count++;
                tempList.add(tempChar);
            }
        }
        System.out.print(count);
    }
    /**
     * 使用哈希集合遍历每个字符，再通过哈希集合的特性，直接输出count即可
     */
    public void secondSolution() throws Exception {
        Scanner in=new Scanner(System.in);
        String str=in.next();
        HashSet<Character> hs=new HashSet<Character>();
        for(int i=0;i<str.length();i++)
            hs.add(str.charAt(i));
        System.out.println(hs.size());
    }
    /**
     * 使用BitSet的方法进行统计
     * @throws Exception
     */
    public void bestSolution() throws Exception{
        Scanner scanner = new Scanner(System.in);
        String line = scanner.next();
        //总共有128个字符。字需要用128位
        BitSet bitSet = new BitSet(128);
        for (char c : line.toCharArray()) {
            //判断字符c是否已出现
            if (!bitSet.get(c)) {
                //未出现就设置为已出现
                bitSet.set(c);
            }
        }
        //统计有多少字符已出现过
        System.out.println(bitSet.cardinality());
    }
}
