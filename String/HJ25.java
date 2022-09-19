package String;

import java.util.Scanner;
import java.util.TreeSet;

public class HJ25 {
    /**
     * TreeSet排序加去重，直接foreach遍历，注意String排序是依据第一个字符串
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            if (a.equals("")) {
                break;
            }
            String[] storage = a.split(" ");
            String[] input = in.nextLine().split(" ");
            TreeSet<Integer> myInput = new TreeSet<Integer>();
            for (int i = 1; i < input.length; i++) {
                myInput.add(Integer.parseInt(input[i]));
            }
            int countMax = 0;
            StringBuilder output = new StringBuilder();

            for (int i : myInput) {
                int flag = 0;
                int count = 0;
                StringBuilder temp = new StringBuilder();
                for (int j = 1; j < storage.length; j++) {

                    if (storage[j].contains(String.valueOf(i))) {
                        temp.append(j - 1 + " " + storage[j] + " ");
                        count++;
                    }
                }
                if (count != 0) {
                    output.append(i + " " + count + " " + temp);
                    countMax += (count * 2 + 2);
                }
            }
            System.out.println(countMax + " " + output.substring(0, output.length() - 1));
        }
    }
}
