package newod.case1.bingchaji;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 最优高铁城市修建方案
 * 题目描述
 * 高铁城市圈对人们的出行、经济的拉动效果明显。每年都会规划新的高铁城市圈建设。
 *
 * 在给定：城市数量，可建设高铁的两城市间的修建成本列表、以及结合城市商业价值会固定建设的两城市建高铁。
 *
 *
 * 请你设计算法，达到修建城市高铁的最低成本。
 *
 * 注意，需要满足城市圈内城市间两两互联可达(通过其他城市中转可达也属于满足条件）。
 *
 * 输入描述
 * 第一行，包含此城市圈中城市的数量、可建设高铁的两城市间修建成本列表数量、必建高铁的城市列表。三个数字用空格间隔。
 * 可建设高铁的两城市间的修建成本列表，为多行输入数据，格式为3个数字，用空格分隔，长度不超过1000。
 * 固定要修建的高铁城市列表，是上面参数2的子集，可能为多行，每行输入为2个数字，以空格分隔。
 * 城市id从1开始编号，建设成本的取值为正整数，取值范围均不会超过1000
 *
 * 输出描述
 * 修建城市圈高铁的最低成本，正整数。如果城市圈内存在两城市之间无法互联，则返回-1。
 *
 * 解法：
 * 我们定义一个minFee来代表最低成本，首先我们需要将必建高铁的成本计算进去。并且在计算的过程中，将必建高铁的两个城市（两个顶点）纳入一个连通分量中（基于并查集）。
 *
 * 之后，我们在将  “可以建高铁” 的列表 按照成本费用升序排序（Kruskal算法），然后遍历排序后列表，依次将“可以建高铁” 的两个城市（两个顶点）尝试纳入连通分量中，如果有环，则不纳入，无环，则纳入，纳入的话，则将成本费用计入minFee中。
 *
 * 那么上面逻辑何时结束呢？1、所有顶点已经纳入到一个连通分量中；2、循环结束。
 *
 * 循环结束后，
 *
 * 如果并查集中的连通分量数量为1，则说明所有城市（顶点）已经通车，且是最低成本。此时返回minFee就是题解。
 *
 * 如果并查集中的连通分量数量不为1，则说明所有城市（顶点）无法连通，返回-1。
 *
 */
public class OD11_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int can = sc.nextInt();
        int must = sc.nextInt();

        int[][] cans = new int[can][3];
        for (int i = 0; i < can; i++) {
            cans[i][0] = sc.nextInt();
            cans[i][1] = sc.nextInt();
            cans[i][2] = sc.nextInt();
        }

        int[][] musts = new int[must][2];
        for (int i = 0; i < must; i++) {
            musts[i][0] = sc.nextInt();
            musts[i][1] = sc.nextInt();
        }

        System.out.println(getResult(n, cans, musts));
    }

    /**
     * @param n 一共几个城市
     * @param cans 哪些城市之间可以修建高铁，以及修建费用
     * @param musts 哪些城市之间必须修建高铁
     * @return 最少费用
     */
    public static int getResult(int n, int[][] cans, int[][] musts) {
        UnionFindSet ufs = new UnionFindSet(n);

        // 为了方便统计“必建高铁”的费用，我们需要将cans数组改造为cansMap，key为'1-2' 两个城市，val为 这两个城市建高铁的费用
        HashMap<String, Integer> cansMap = new HashMap<>();
        for (int[] can : cans) {
            int city1 = can[0], city2 = can[1], fee = can[2];
            String key = city1 < city2 ? city1 + "-" + city2 : city2 + "-" + city1;
            cansMap.put(key, fee);
        }

        int minFee = 0;
        for (int[] must : musts) {
            // 计入必建高铁的费用到minFee中
            String key = must[0] < must[1] ? must[0] + "-" + must[1] : must[1] + "-" + must[0];
            minFee += cansMap.get(key);
            // 并将必建高铁的两个城市纳入同一个连通分量重
            ufs.union(must[0], must[1]);
        }

        //  如果必建高铁本身已经满足所有城市通车了，那么直接返回minFee
        if (ufs.count == 1) return minFee;

        // 否则，按照求解最小生成树的Kruskal算法，将高铁线（即图的边）按照成本费用（即边的权重）升序
        Arrays.sort(cans, (a, b) -> a[2] - b[2]);

        // 遍历排序后的cans，每次得到的都是当前的最小权重边
        for (int[] can : cans) {
            int city1 = can[0], city2 = can[1], fee = can[2];
            // 如果对应城市已经接入高铁线（即处于连通分量中）则再次合入就会产生环，因此不能合入，否则就可以合入
            //      if (ufs.fa[city1] != ufs.fa[city2]) {
            if (ufs.find(city1) != ufs.find(city2)) {
                ufs.union(city1, city2);
                // 若可以合入，则将对应的建造成本计入minFee
                minFee += fee;
            }

            // 如果此时，所有城市都通车了（即并查集中只有一个连通分量），则提前结束循环
            if (ufs.count == 1) break;
        }

        // 如果循环完，发现并查集中还有多个连通分量，那么代表有的城市无法通车，因此返回-1
        if (ufs.count > 1) return -1;

        return minFee;
    }

    static class UnionFindSet {
        int[] fa;
        int count;

        public UnionFindSet(int n) {
            this.fa = new int[n + 1];
            this.count = n;
            for (int i = 0; i < n; i++) this.fa[i] = i;
        }

        public int find(int x) {
            if (x != this.fa[x]) {
                return (this.fa[x] = this.find(this.fa[x]));
            }
            return x;
        }

        public void union(int x, int y) {
            int x_fa = this.find(x);
            int y_fa = this.find(y);

            if (x_fa != y_fa) {
                this.fa[y_fa] = x_fa;
                this.count--;
            }
        }
    }

}
