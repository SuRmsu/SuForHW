package newod.case1.logic;

/**
 * 荒地、光伏场地建设规划
 * 题目描述
 * 祖国西北部有一片大片荒地，其中零星的分布着一些湖泊，保护区，矿区;
 * 整体上常年光照良好，但是也有一些地区光照不太好。
 * 某电力公司希望在这里建设多个光伏电站，生产清洁能源对每平方公里的土地进行了发电评估，
 * 其中不能建设的区域发电量为0kw，可以发电的区域根据光照，地形等给出了每平方公里年发电量x千瓦。
 * 我们希望能够找到其中集中的矩形区域建设电站，能够获得良好的收益。
 *
 * 输入描述
 * 第一行输入为调研的地区长，宽，以及准备建设的电站【长宽相等，为正方形】的边长最低要求的发电量
 * 之后每行为调研区域每平方公里的发电量
 *
 * 输出描述
 * 输出为这样的区域有多少个
 *
 * 解法： 同OD4_2 待优化
 */
public class OD27_2 {
    public static void main(String[] args) {
        int n = 2;
        int m = 5;
        int l = 2;
        int k = 6;
        int[][] input = {
                {1, 0, 4, 5, 8},
                {2, 3, 6, 7, 1}
        };
        int sum = 0;
        for (int i = 0; i < n - l + 1; i++) {
            for (int i1 = 0; i1 < m - l + 1; i1++) {
                int temp = 0;
                int flag = 0;
                for (int i2 = 0; i2 < l; i2++) {
                    for (int i3 = 0; i3 < l; i3++) {
                        if (input[i2 + i][i1 + i3] == 0) {
                            flag = 1;
                            break;
                        }
                        temp += input[i2 + i][i1 + i3];

                    }
                }
                if (flag == 1) {
                    flag = 0;
                    continue;
                }
                flag = 0;
                if (temp >= k) {
                    sum++;
                }
            }
        }
        System.out.println(sum);

    }
}
