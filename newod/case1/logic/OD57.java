package newod.case1.logic;

import java.util.*;

/**
 * 快递投放问题
 * 题目描述
 * 有N个快递站点用字符串标识，某些站点之间有道路连接。
 * 每个站点有一些包裹要运输，每个站点间的包裹不重复，路上有检查站会导致部分货物无法通行，计算哪些货物无法正常投递？
 *
 * 输入描述
 * 第一行输入M N，M个包裹N个道路信息.
 * 0<=M,N<=100，
 * 检查站禁止通行的包裹如果有多个以空格分开
 * 输出描述
 * 输出不能送达的包裹，如：package2 package4，
 * 如果所有包裹都可以送达则输出：none，
 * 输出结果按照升序排列。
 *
 * 解法：常规逻辑
 */
public class OD57 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer[] tmp =
                Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        int m = tmp[0];
        int n = tmp[1];

        String[][] want = new String[m][];
        for (int i = 0; i < m; i++) {
            want[i] = sc.nextLine().split(" ");
        }

        String[][] cant = new String[n][];
        for (int i = 0; i < n; i++) {
            cant[i] = sc.nextLine().split(" ");
        }

        System.out.println(getResult(want, cant));
    }

    /**
     * @param want 二维数组，元素是 [包裹， 起始站点， 目的站点]
     * @param cant 二维数组，元素是 [起始站点， 目的站点，...无法通行的包裹列表]
     * @return 无法通行的包裹
     */
    public static String getResult(String[][] want, String[][] cant) {
        HashMap<String, HashSet<String>> wantMap = new HashMap<>();
        HashMap<String, HashSet<String>> cantMap = new HashMap<>();

        for (String[] arr : want) {
            String pkg = arr[0];
            String path = arr[1] + "-" + arr[2];
            wantMap.putIfAbsent(path, new HashSet<>());
            wantMap.get(path).add(pkg);
        }

        for (String[] arr : cant) {
            String path = arr[0] + "-" + arr[1];
            String[] pkgs = Arrays.copyOfRange(arr, 2, arr.length);
            cantMap.putIfAbsent(path, new HashSet<>());
            cantMap.get(path).addAll(Arrays.asList(pkgs));
        }

        ArrayList<String> ans = new ArrayList<>();

        for (String path : wantMap.keySet()) {
            HashSet<String> wantPKG = wantMap.get(path);
            HashSet<String> cantPKG = cantMap.get(path);

            if (cantPKG == null) continue;

            for (String pkg : wantPKG) {
                if (cantPKG.contains(pkg)) {
                    ans.add(pkg);
                }
            }
        }

        if (ans.size() == 0) return "none";

        ans.sort((a, b) -> Integer.parseInt(a.substring(7)) - Integer.parseInt(b.substring(7)));

        StringJoiner sj = new StringJoiner(" ");
        for (String an : ans) {
            sj.add(an);
        }
        return sj.toString();
    }
}
