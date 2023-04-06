package newod.case1.huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 处理器问题
 * 题目描述
 * 某公司研发了一款高性能AI处理器。每台物理设备具备8颗AI处理器，编号分别为0、1、2、3、4、5、6、7。
 * 编号0-3的处理器处于同一个链路中，编号4-7的处理器处于另外一个链路中，不通链路中的处理器不能通信。
 * 如下图所示。现给定服务器可用的处理器编号数组array，以及任务申请的处理器数量num，找出符合下列亲和性调度原则的芯片组合。
 * 如果不存在符合要求的组合，则返回空列表。
 *
 * 亲和性调度原则：
 * -如果申请处理器个数为1，则选择同一链路，剩余可用的处理器数量为1个的最佳，其次是剩余3个的为次佳，然后是剩余2个，最后是剩余4个。
 * -如果申请处理器个数为2，则选择同一链路剩余可用的处理器数量2个的为最佳，其次是剩余4个，最后是剩余3个。
 * -如果申请处理器个数为4，则必须选择同一链路剩余可用的处理器数量为4个。
 * -如果申请处理器个数为8，则申请节点所有8个处理器。
 *
 * 提示：
 *
 * 任务申请的处理器数量只能是1、2、4、8。
 * 编号0-3的处理器处于一个链路，编号4-7的处理器处于另外一个链路。
 * 处理器编号唯一，且不存在相同编号处理器。
 * 输入描述
 * 输入包含可用的处理器编号数组array，以及任务申请的处理器数量num两个部分。
 *
 * 第一行为array，第二行为num。例如：
 *
 * [0, 1, 4, 5, 6, 7]
 * 1
 * 表示当前编号为0、1、4、5、6、7的处理器可用。任务申请1个处理器。
 *
 * 0 <= array.length <= 8
 * 0 <= array[i] <= 7
 * num in [1, 2, 4, 8]
 * 输出描述
 * 输出为组合列表，当array=[0，1，4，5，6，7]，num=1 时，输出为[[0], [1]]。
 *
 * 解法：前面是逻辑，后面就是全组合，4选1，4选2等等
 */
public class OD1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer[] arr =
                Arrays.stream(sc.nextLine().split("[\\[\\]\\,\\s]"))
                        .filter(str -> !"".equals(str))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);

        String num = sc.next();

        System.out.println(getResult(arr, num));
    }

    public static String getResult(Integer[] arr, String num) {
        ArrayList<Integer> link1 = new ArrayList<>();
        ArrayList<Integer> link2 = new ArrayList<>();

        Arrays.sort(arr, (a, b) -> a - b);
        for (Integer e : arr) {
            if (e < 4) {
                link1.add(e);
            } else {
                link2.add(e);
            }
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int len1 = link1.size();
        int len2 = link2.size();

        switch (num) {
            case "1":
                if (len1 == 1 || len2 == 1) {
                    if (len1 == 1) dfs(link1, 0, 1, new ArrayList<>(), ans);
                    if (len2 == 1) dfs(link2, 0, 1, new ArrayList<>(), ans);
                } else if (len1 == 3 || len2 == 3) {
                    if (len1 == 3) dfs(link1, 0, 1, new ArrayList<>(), ans);
                    if (len2 == 3) dfs(link2, 0, 1, new ArrayList<>(), ans);
                } else if (len1 == 2 || len2 == 2) {
                    if (len1 == 2) dfs(link1, 0, 1, new ArrayList<>(), ans);
                    if (len2 == 2) dfs(link2, 0, 1, new ArrayList<>(), ans);
                } else if (len1 == 4 || len2 == 4) {
                    if (len1 == 4) dfs(link1, 0, 1, new ArrayList<>(), ans);
                    if (len2 == 4) dfs(link2, 0, 1, new ArrayList<>(), ans);
                }
                break;
            case "2":
                if (len1 == 2 || len2 == 2) {
                    if (len1 == 2) dfs(link1, 0, 2, new ArrayList<>(), ans);
                    if (len2 == 2) dfs(link2, 0, 2, new ArrayList<>(), ans);
                } else if (len1 == 4 || len2 == 4) {
                    if (len1 == 4) dfs(link1, 0, 2, new ArrayList<>(), ans);
                    if (len2 == 4) dfs(link2, 0, 2, new ArrayList<>(), ans);
                } else if (len1 == 3 || len2 == 3) {
                    if (len1 == 3) dfs(link1, 0, 2, new ArrayList<>(), ans);
                    if (len2 == 3) dfs(link2, 0, 2, new ArrayList<>(), ans);
                }
                break;
            case "4":
                if (len1 == 4 || len2 == 4) {
                    if (len1 == 4) ans.add(link1);
                    if (len2 == 4) ans.add(link2);
                }
                break;
            case "8":
                if (len1 == 4 && len2 == 4) {
                    ans.add(
                            Stream.concat(link1.stream(), link2.stream())
                                    .collect(Collectors.toCollection(ArrayList<Integer>::new)));
                }
                break;
        }

        return ans.toString();
    }

    public static void dfs(
            ArrayList<Integer> arr,
            int index,
            int level,
            ArrayList<Integer> path,
            ArrayList<ArrayList<Integer>> res) {
        if (path.size() == level) {
            res.add((ArrayList<Integer>) path.clone());
        }

        for (int i = index; i < arr.size(); i++) {
            path.add(arr.get(i));
            dfs(arr, i + 1, level, path, res);
            path.remove(path.size() - 1);
        }
    }
}
