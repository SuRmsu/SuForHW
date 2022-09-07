package String;

import java.util.*;

public class HJ27 {
    /**
     * 使用List<String>队列来保存输入的字符串数组
     * 使用Arrays.sort来进行比较是否为字符串数组
     *
     * @throws Exception
     */
    public void notMySolution() throws Exception {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String[] storage = sc.nextLine().split(" ");
            Integer inputNums = Integer.parseInt(storage[0]);
            String broWord = storage[storage.length - 2];
            Integer k = Integer.parseInt(storage[storage.length - 1]);
            List<String> list = new ArrayList<>();

            for (int i = 0; i <= inputNums; i++) {
                if (isBrother(broWord, storage[i])) {
                    list.add(storage[i]);
                }
            }
            int size = list.size();
            System.out.println(size);
            if (size >= k) {
                Collections.sort(list);
                System.out.println(list.get(k - 1));
            }

        }

    }

    public static boolean isBrother(String str1, String str2) {
        if (str1.length() != str2.length() || str2.equals(str1)) {
            return false;
        }
        char[] tempStr1 = str1.toCharArray();
        char[] tempStr2 = str2.toCharArray();
        Arrays.sort(tempStr1);
        Arrays.sort(tempStr2);
        return new String(tempStr1).equals(new String(tempStr2));


    }
}
