package newod.case1.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 连接器问题
 * 题目描述
 * 有一组区间[a0，b0]，[a1，b1]，…（a，b表示起点，终点），区间有可能重叠、相邻，重叠或相邻则可以合并为更大的区间；
 * <p>
 * 给定一组连接器[x1，x2，x3，…]（x表示连接器的最大可连接长度，即x>=gap），可用于将分离的区间连接起来，但两个分离区间之间只能使用1个连接器；
 * <p>
 * 请编程实现使用连接器后，最少的区间数结果。
 * <p>
 * 区间数量<10000，a,b均 <=10000
 * 连接器梳理<10000；x <= 10000
 * <p>
 * 输入描述
 * 区间组：[1,10],[15,20],[18,30],[33，40]
 * 连接器组：[5,4,3,2]
 * <p>
 * 输出描述
 * 1
 * <p>
 * 说明：
 * <p>
 * 合并后：[1,10],[15,30],[33,40]，使用5, 3两个连接器连接后只剩下 [1, 40]。
 *
 * 解法：先组合重合的，以及相邻的，再计算出间隔有多少，以及分别是什么
 * 间隔从大到小排序，连接器从小到大排序
 * 最好全用list，数组需要考虑索引，过于麻烦了
 * 需要完善
 */
public class OD6_2 {
    public static void main(String[] args) {
//        int[] keys ={5,4,3,2};
        int[] keys ={2};
        Integer[][] input = {
                {18, 30},
                {15, 20},
                {1, 10},
                {33, 40}
        };
        Arrays.sort(input, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<Integer> list = new ArrayList<Integer>();
        Integer[][] newInput = new Integer[10][];
        for (int i = 1; i < input.length; i++) {
            if (input[i][0] <= input[i - 1][1]) {
                Integer[] tempInput = {input[i - 1][0], input[i][1]};
                newInput[i - 1] = tempInput;
            } else {
                newInput[i] = input[i];
            }
        }

        for (int i = 1; i < newInput.length; i++) {
            if(newInput[i] == null) {
                continue;
            }
            list.add(input[i][0] - input[i - 1][1]);
        }

        List<Integer> newKeys = new ArrayList<Integer>();
        for (int key : keys) {
            newKeys.add(key);
        }
        newKeys.sort((o1,o2) -> o1 - o2);
        list.sort((o1,o2) -> o2 - o1);



        int sum = list.size();
        for (Integer integer : list) {
            if(newKeys.size() == 0){
                break;
            }
            for (Integer newKey : newKeys) {
                if (newKey >= integer){
                    sum--;
                    newKeys.remove(newKey);
                    break;
                }
            }
        }
        System.out.println(sum + 1);
    }
}
