package newod.case1.logic;

import java.util.*;

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
 *
 * 解法：
 * 我们首先基于arr1，将arr1和它后面的arr2合并，如果可以合并，则先将arr1合并入arr2，再将arr1.length = 0 ，然后结束本轮，如果不可以合并，则接着分别和arr3、arr4、arr5合并。
 *
 * 然后基于arr2，将arr2和它后面的arr3合并，同上逻辑。
 *
 * 但是我们需要注意的是，如果第一轮时 arr1无法和其他端口组合并，那么arr1将保留，然后第二轮arr2和尝试和其他端口组合并，比如arr2和arr3可以合并，且合并后的arr3和arr1是可以合并的。
 *
 * 那么双重for的逻辑可以支持arr3再回头和arr1合并吗？答案是不可以的，因为外层for循环是不可逆的，因此我们必须再在双重for外面套一层循环。
 *
 * 我觉得使用while比较好，我们可以定义一个flag变量，初始为true，作为while的循环条件，当进入while循环后，立即将flag=false，然后进行上面双重for逻辑，只要有端口组发生合并，则将flag=true，如果双重for结束了，也没有端口组发生合并，那么就说明真的没有端口组可以合并了，因此flag保持为false，while结束。
 * 这里，我们可以先将两个尝试合并的端口组，先进行升序排序，然后一层循环即可统计出相同端口的对数
 *
 */
public class OD20 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = Integer.parseInt(sc.nextLine());

        if (m > 10 || m < 1) {
            System.out.println("[[]]");
            return;
        }

        Integer[][] matrix = new Integer[m][];
        for (int i = 0; i < m; i++) {
            matrix[i] =
                    Arrays.stream(sc.nextLine().split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        }

        System.out.println(Arrays.deepToString(getResult(matrix)));
    }

    public static Integer[][] getResult(Integer[][] matrix) {
        boolean flag = true;

        while (flag) {
            flag = false;
            for (int i = matrix.length - 1; i >= 1; i--) {
                if (matrix[i] == null || matrix[i].length <= 1) continue;
                for (int j = i - 1; j >= 0; j--) {
                    if (matrix[j] == null || matrix[j].length <= 1) continue;
                    if (canMerge(matrix[i], matrix[j])) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        Collections.addAll(tmp, matrix[i]);
                        Collections.addAll(tmp, matrix[j]);
                        matrix[j] = new HashSet<Integer>(tmp).stream().sorted().toArray(Integer[]::new);
                        matrix[i] = null;
                        flag = true;
                        break;
                    }
                }
            }
        }

        return Arrays.stream(matrix).filter(Objects::nonNull).toArray(Integer[][]::new);
    }

    public static boolean canMerge(Integer[] m, Integer[] n) {
        // 从用例1输出的2,3,2来看，未合并的端口组是不能去重和排序的，因此这里需要浅克隆下，避免改变原数组顺序
        m = m.clone();
        n = n.clone();

        Arrays.sort(m);
        Arrays.sort(n);

        int i = 0;
        int j = 0;
        int count = 0;

        while (i < m.length && j < n.length && count < 2) {
            if (m[i].equals(n[j])) {
                i++;
                j++;
                count++;
            } else if (m[i] > n[j]) {
                j++;
            } else {
                i++;
            }
        }

        return count >= 2;
    }
}
