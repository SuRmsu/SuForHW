package newod.case1.logic;

/**
 * 服务中心选址
 * 题目描述
 * 一个快递公司希望在一条街道建立新的服务中心。公司统计了该街道中所有区域在地图上的位置，并希望能够以此为依据为新的服务中心选址：使服务中心到所有区域的距离的总和最小。
 *
 * 给你一个数组positions，其中positions[i] = [left, right] 表示第 i 个区域在街道上的位置，其中left代表区域的左侧的起点，right代表区域的右侧终点，假设服务中心的位置为location：
 *
 * 如果第 i 个区域的右侧终点right满足 right < location，则第 i 个区域到服务中心的距离为 location - right；
 * 如果第 i 个区域的左侧起点left 满足 left > location，则第 i 个区域到服务中心的距离为left - location；
 * 如果第 i 个区域的两侧left，right满足left <= location <= right，则第 i 个区域到服务中心的距离为0
 * 选择最佳的服务中心位置为location，请返回最佳的服务中心位置到所有区域的距离总和的最小值。
 *
 * 输入描述
 * 第一行，一个整数N表示区域个数。
 * 后面N行，每行两个整数，表示区域的左右起点终点。
 * 输出描述
 * 运行结果输出一个整数，表示服务中心位置到所有区域的距离总和的最小值。
 *
 * 解法：暴力
 *
 * 如何优化？
 * https://blog.csdn.net/qfc_128220/article/details/128808359
 */
public class OD75 {
    public static void main(String[] args) {
        int n = 11;
        int[][] input = {
                {-10,10},
                {1,2},
                {3,4},
                {5,10},
                {6,8},
                {7,12},
                {9,13},
                {15,20},
                {31,41},
                {22,35},
                {34,50}
        };
        int maxIndex=0;
        for (int[] ints : input) {
            for (int anInt : ints) {
                maxIndex = Math.max(maxIndex,anInt);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= maxIndex; i++){
            min = Math.min(process(input,i),min);
        }
        System.out.println(min);
    }

    public static int process(int[][] input, int position){
        int temp = 0;
        for (int[] ints : input) {
            if (position <= ints[1] && position >= ints[0]){
                temp += 0;
            } else if ( position > ints[1]){
                temp += position - ints[1];
            } else {
                temp += ints[0] - position;
            }
        }
        return temp;
    }
}
