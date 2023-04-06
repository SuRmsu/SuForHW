package newod.case1.bingchaji;

/**
 * 并查集
 * 题目描述
 * 快递业务范围有 N 个站点，A 站点与 B 站点可以中转快递，则认为 A-B 站可达，
 * 如果 A-B 可达，B-C 可达，则 A-C 可达。
 * 现在给 N 个站点编号 0、1、…n-1，用 s[i][j]表示 i-j 是否可达，
 * s[i][j] = 1表示 i-j可达，s[i][j] = 0表示 i-j 不可达。
 * 现用二维数组给定N个站点的可达关系，请计算至少选择从几个主站点出发，才能可达所有站点（覆盖所有站点业务）。
 * 说明：s[i][j]与s[j][i]取值相同。
 * <p>
 * 输入描述
 * 第一行输入为 N，N表示站点个数。 1 < N < 10000
 * 之后 N 行表示站点之间的可达关系，第i行第j个数值表示编号为i和j之间是否可达。
 * <p>
 * 输出描述
 * 输出站点个数，表示至少需要多少个主站点。
 *
 * 解法：并查集
 */
public class OD21 {
    public static void main(String[] args) {
        int [][] input = {
                {1,1,0,0},
                {1,1,0,0},
                {0,0,1,0},
                {0,0,0,1}
        };
        int n = 4 ;
        UnisonFindSet unison = new UnisonFindSet(n);
        // 可以剪枝，其实此处已经剪过了
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (input[i][j] == 1){
                  unison.union(i,j);
                }
            }
        }
        System.out.println(unison.count);

    }


}

class UnisonFindSet {
    int[] fa;
    int count;

    public UnisonFindSet(int n) {
        this.count = n;
        this.fa = new int[n];
        for (int i = 0; i < n; i++) {
         this.fa[i] = i;
        }
    }

    public int find(int x){
        if(x == this.fa[x]){
            return x;
        } else {
            this.fa[x] = this.find(this.fa[x]);
            return this.fa[x];
            // 优化成这句
            // return (this.fa[x] = this.find(this.fa[x]));
        }
    }

    public void union(int x, int y) {
        int x_fa = this.find(x);
        int y_fa = this.find(y);
        if (x_fa != y_fa){
            this.fa[y_fa] = x_fa;
            this.count--;
        }

    }

}
