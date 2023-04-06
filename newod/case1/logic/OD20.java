package newod.case1.logic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 端口合并
 * 题目描述
 * 有M(1<=M<=10)个端口组，
 * 每个端口组是长度为N(1<=N<=100)的整数数组，
 * 如果端口组间存在2个及以上不同端口相同，则认为这2个端口组互相关联，可以合并。
 * 第一行输入端口组个数M，再输入M行，每行逗号分隔，代表端口组。
 * 输出合并后的端口组，用二维数组表示。
 *
 * 输入描述
 * 第一行输入一个数字M
 * 第二行开始输入M行，每行是长度为N的整数数组，用逗号分割
 *
 * 输出描述
 * 合并后的二维数组
 * 解法：
 * 主要难度在于：组外顺序保持输入顺序
 * 这里我利用反序遍历的方式，
 * 将后面的端口组并入前面的端口组，这样就可以避免组外顺序发生改变了。
 */
public class OD20 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = Integer.parseInt(sc.nextLine());

        // M,N不在限定范围内，统一输出一组空数组[[]]
        if (m > 10 || m < 1) {
            System.out.println("[[]]");
            return;
        }

        ArrayList<TreeSet<Integer>> ports = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> tmp =
                    Arrays.stream(sc.nextLine().split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());

            // M,N不在限定范围内，统一输出一组空数组[[]]
            int n = tmp.size();
            if (n < 1 || n > 100) {
                System.out.println("[[]]");
                return;
            }

            ports.add(new TreeSet<>(tmp));
        }

        System.out.println(getResult(ports, m));
    }

    public static String getResult(ArrayList<TreeSet<Integer>> ports, int m) {

        outer:
        while (true) {
            for (int i = m - 1; i >= 0; i--) {
                TreeSet<Integer> port1 = ports.get(i);
                if (port1.size() == 0) continue;

                for (int j = i - 1; j >= 0; j--) {
                    TreeSet<Integer> port2 = ports.get(j);
                    if (port2.size() == 0) continue;

                    if (hasTwoSamePort(port1, port2)) {
                        port2.addAll(port1);
                        port1.clear();
                        continue outer;
                    }
                }
            }
            break;
        }

        return ports.stream().filter(port -> port.size() > 0).collect(Collectors.toList()).toString();
    }

    // 判断两个区间是否可以合并
    public static boolean hasTwoSamePort(TreeSet<Integer> port1, TreeSet<Integer> port2) {
        int count = 0;
        for (Integer val : port1) {
            if (port2.contains(val)) {
                if (++count >= 2) return true;
            }
        }

        return false;
    }
}
