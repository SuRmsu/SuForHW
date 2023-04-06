package newod.case1.string;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 *
 * 优化的正则写法： String reg = "^(\\d+)(\\.(\\d+))(\\.(\\d+))?(\\-.+)?$";
 */
public class OD43 {
    /*
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

     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str1 = sc.next();
        String str2 = sc.next();

        System.out.println(getResult(new String[]{str1, str2}));
    }

    public static String getResult(String[] versions) {
        String reg = "^(\\d+)(\\.(\\d+))(\\.(\\d+))?(\\-.+)?$";
        Pattern p = Pattern.compile(reg);

        Arrays.sort(versions, (v1, v2) -> {
            Matcher m1 = p.matcher(v1);
            Matcher m2 = p.matcher(v2);

            if (m1.find() && m2.find()) {
                Integer major1 = Integer.parseInt(m1.group(1));
                Integer major2 = Integer.parseInt(m2.group(1));

                if(major1 != major2) return major2 - major1;

                Integer minor1 = Integer.parseInt(m1.group(3));
                Integer minor2 = Integer.parseInt(m2.group(3));

                if(minor1 != minor2) return minor2 - minor1;

                String patch1 = m1.group(5);
                String patch2 = m2.group(5);

                if(patch1 != null && patch2 != null) {
                    int patch1_intVal = Integer.parseInt(patch1);
                    int patch2_intVal = Integer.parseInt(patch2);
                    if(patch1_intVal != patch2_intVal) {
                        return Integer.parseInt(patch2) - Integer.parseInt(patch1);
                    }
                } else if(patch1 != null) {
                    return -1;
                } else if(patch2 != null) {
                    return 1;
                }


                String mile1 = m1.group(6);
                String mile2 = m2.group(6);

                if(mile1 != null && mile2 != null) {
                    return mile2.compareTo(mile1);
                } else if(mile1 != null) {
                    return -1;
                } else if(mile2 != null) {
                    return 1;
                } else {
                    return 0;
                }
            }

            return 0;
        });

        return versions[0];
    }

}
