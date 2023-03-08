package newod.case1.logic;

/**
 * 题目描述
 * 快递业务范围有N个站点，A站点与B站点可以中转快递，则认为A-B站可达，如果A-B可达，B-C可达，则A-C可达。
 * 现在给N个站点编号0、1、…n-1，用s[i][j]表示i-j是否可达，s[i][j]=1表示i-j可达，s[i][j]=0表示i-j不可达。
 * <p>
 * 现用二维数组给定N个站点的可达关系，请计算至少选择从几个主站点出发，才能可达所有站点（覆盖所有站点业务）。
 * <p>
 * 说明：s[i][j]与s[j][i]取值相同。
 * <p>
 * 输入描述
 * 第一行输入为N（1 < N < 10000），N表示站点个数。
 * 之后N行表示站点之间的可达关系，第i行第j个数值表示编号为i和j之间是否可达。
 * <p>
 * 输出描述
 * 输出站点个数，表示至少需要多少个主站点。
 */

public class OD22_2 {
    public static void main(String[] args) {
        int [][] input = {
                {1,1,0,0},
                {1,1,0,0},
                {0,0,1,0},
                {0,0,0,1}
        };
        int n = 4 ;
        UnisonFindSet unison = new UnisonFindSet(n);
        // 可以剪枝
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
