package newod.case1.string;

import java.util.Arrays;

/**
 * 获取最大软件版本号
 * 题目描述
 * Maven 版本号定义，<主版本>.<次版本>.<增量版本>-<里程碑版本>，举例3.1.4-beta
 * <p>
 * 其中，主版本和次版本都是必须的，主版本，次版本，增量版本由多位数字组成，可能包含前导零，里程碑版本由字符串组成。
 * <主版本>.<次版本>.<增量版本>：基于数字比较；例如
 * <p>
 * 1.5 > 1.4 > 1.3.11 > 1.3.9
 * <p>
 * 里程碑版本：基于字符串比较，采用字典序；例如
 * <p>
 * 1.2-beta-3 > 1.2-beta-11
 * <p>
 * 比较版本号时，按从左到右的顺序依次比较。
 * <p>
 * 基于数字比较， 只需比较忽略任何前导零后的整数值 。
 * 输入2个版本号，输出最大版本号。
 * <p>
 * 输入描述
 * 输入2个版本号，换行分割，每个版本的最大长度小于50
 * <p>
 * 输出描述
 * 版本号相同时输出第一个输入版本号
 *
 * 解法： 屎山的化解
 * 需要重写
 */
public class OD43 {
    public static void main(String[] args) {
//        String a = "1.5.1-C";
//        String b = "1.4.2-D";
//        String a = "1.5.1-A";
//        String b = "1.5.1-a";
        String a = "1.5";
        String b = "1.5.0";
//        String a = "1.05.1";
//        String b = "1.5.01";
        String[] subA = new String[4];
        String[] subB = new String[4];
        Arrays.fill(subA, " ");
        Arrays.fill(subB, " ");


        if (Integer.parseInt(a.split("\\.")[0]) > Integer.parseInt(b.split("\\.")[0])) {
            System.out.println(a);
            return;
        }

        process(a, subA);
        process(b, subB);

        for (int i = 0; i < 3; i++) {
            if (!subA[i].equals(" ")) {
                subA[i] = toInteger(subA[i]);
            }
            if (!subB[i].equals(" ")) {
                subB[i] = toInteger(subB[i]);
            }
        }
        if (subA[1].equals(subB[1])) {
            if (subA[2].equals(subB[2])) {
                if (subA[3].equals(subB[3]) || (subA[3].compareTo(subB[3]) > 0)) {
                    System.out.println(a);
                    return;
                } else {
                    System.out.println(b);
                    return;
                }

            } else if (Integer.parseInt(subA[2]) > Integer.parseInt(subB[2])) {
                System.out.println(a);
                return;
            } else {
                System.out.println(b);
                return;
            }
        } else if (Integer.parseInt(subA[1]) > Integer.parseInt(subB[1])) {
            System.out.println(a);
            return;
        } else {
            System.out.println(b);
            return;
        }


    }

    public static String toInteger(String a) {
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(0) == '0') {
                if (a.equals("0")) {
                    break;
                } else {
                    a = a.substring(1);
                }
            } else {
                break;
            }
        }
        return a;
    }

    public static void process(String input, String[] subInput) {
        String[] temp = input.split("\\.");
        subInput[0] = temp[0];
        // 如果为3，则一定包含增量版本号
        if (temp.length == 3) {
            subInput[1] = temp[1];
            // 包含里程碑版本
            if (temp[2].contains("-")) {
                String[] tempOfTemp = temp[2].split("-");
                subInput[2] = tempOfTemp[0];
                subInput[3] = tempOfTemp[1];
            } else {
                subInput[2] = temp[2];
            }
            // 不包含增量号
        } else {
            if (temp[1].contains("-")) {
                String[] tempOfTemp = temp[1].split("-");
                subInput[1] = tempOfTemp[0];
                subInput[3] = tempOfTemp[1];
            } else {
                subInput[2] = temp[1];
            }
        }
    }


}
