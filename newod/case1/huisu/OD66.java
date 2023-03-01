package newod.case1.huisu;

/**
 * 天然蓄水库
 * 题目描述
 * 公元2919年，人类终于发现了一颗宜居星球——X星。
 * 现想在X星一片连绵起伏的山脉间建一个天热蓄水库，如何选取水库边界，使蓄水量最大？
 * <p>
 * 要求：
 * <p>
 * 山脉用正整数数组s表示，每个元素代表山脉的高度。
 * 选取山脉上两个点作为蓄水库的边界，则边界内的区域可以蓄水，蓄水量需排除山脉占用的空间
 * 蓄水量的高度为两边界的最小值。
 * 如果出现多个满足条件的边界，应选取距离最近的一组边界。
 * 输出边界下标（从0开始）和最大蓄水量；如果无法蓄水，则返回0，此时不返回边界。
 * 例如，当山脉为s=[3,1,2]时，则选取s[0]和s[2]作为水库边界，则蓄水量为1，此时输出：0 2:1
 * 当山脉s=[3,2,1]时，不存在合理的边界，此时输出：0。
 * <p>
 * 输入描述
 * 一行正整数，用空格隔开，例如输入
 * <p>
 * 1 2 3
 * <p>
 * 表示s=[1,2,3]
 * <p>
 * 输出描述
 * 当存在合理的水库边界时，输出左边界、空格、右边界、英文冒号、蓄水量；例如
 * <p>
 * 0 2:1
 * <p>
 * 当不存在合理的书库边界时，输出0；例如
 *
 * 解法：
 * 双指针 + 回溯
 * 不断划分区间并判断是否合理
 */
public class OD66 {

    public static int max = 0;
    public static int leftIndex = 0;
    public static int rightIndex = 0;

    public static void main(String[] args) {
//        int[] nums = {1, 9, 6, 2, 5, 4, 9, 3, 7};
        int[] nums = {1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int[] nums = {4, 3, 2, 1};
        // 双指针
//        for (int i = 0 ; i < nums.length; i++){
//            process(nums,i,nums.length - 1);
//        }

        if ( nums.length <= 2) {
            System.out.println(0);
            return;
        }
        process(nums, 0, nums.length - 1);

        if ( max == 0){
            System.out.println(0);
            return;
        }
        System.out.println(leftIndex + " " + rightIndex + ":" + max);

    }

    public static void process(int[] input, int left, int right) {
        if (right - left <= 1) {
            return;
        }
        //    int tempSum = 0;
        int sum = 0;
        for (int i = left + 1; i < right; i++) {
            // 取 当前和右边
            if (input[i] >= input[left] && input[i] <= input[right]) {
                process(input, i, right);
                return;
            // 取 左边和当前
            } else if (input[i] <= input[left] && input[i] >= input[right]) {
                process(input, left, i);
                return;
            // 取左边和当前；当前和右边
            } else if (input[i] >= input[left] && input[i] >= input[right]) {
                process(input, left, i);
                process(input, i, right);
                return;
            // 这种情况可以不用记录，因为不满足的都return了
            } else if (input[i] <= input[left] && input[i] <= input[right]) {
                //            tempSum++;
            }
        }
        //     if (tempSum + 1 == right - left) {
        int min = Math.min(input[left], input[right]);
        for (int i = left + 1; i < right; i++) {
            sum += min - input[i];
        }
        max = Math.max(max, sum);
        if (max == sum) {
            leftIndex = left;
            rightIndex = right;
        }
        //    }

    }
}
