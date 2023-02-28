package newod.case1.logic;

/**
 * 日志限流 二分法
 * 题目描述
 * 某软件系统会在运行过程中持续产生日志，系统每天运行N单位时间，运行期间每单位时间产生的日志条数保行在数组records中。records[i]表示第i单位时间内产生日志条数。
 * 由于系统磁盘空间限制，每天可记录保存的日志总数上限为total条。
 * 如果一天产生的日志总条数大于total，则需要对当天内每单位时间产生的日志条数进行限流后保存，请计算每单位时间最大可保存日志条数limit，以确保当天保存的总日志条数不超过total。
 * 对于单位时间内产生日志条数不超过limit的日志全部记录保存;
 * 对于单位时间内产生日志条数超过limit的日志，则只记录保存limit条日志；
 * 如果一天产生的日志条数总和小于等于total，则不需要启动限流机制．result为-1。
 * 请返回result的最大值或者-1。
 * <p>
 * 输入描述
 * 第一行为系统某一天运行的单位时间数N,1<=N<=10^5
 * 第二行为表示这一天每单位时间产生的日志数量的数组records[]，0 <= records[i] <= 10^5
 * 第三行为系统一天可以保存的总日志条数total。1 <= total <= 10^9
 * <p>
 * 输出描述
 * 每单位时间内最大可保存的日志条数limit，如果不需要启动限流机制，返回-1。
 */
public class OD70 {
    static int totalMax = 0;
    public static void main(String[] args) {


        int[] input = {3, 3, 8, 7, 10, 15};
        int n = 6;
        int total = 41;
        int max = 0;
        int tempSum = 0;
        for (int i : input) {
            tempSum += i;
            max = Math.max(max,i);
        }
        if (tempSum <= total) {
            System.out.println(-1);
            return;
        }
        int min = total / n;
        process(input,total,min,max);
        System.out.println(totalMax);

    }

    public static void process(int[] input, int target, int min, int max) {
        // 出口条件 max==min 或者 middle == min 或者 middle == max
        if (max == min){
            if (isValid(input,target,min)) {
                totalMax = max;
                return;
            }
        }
        // min满足，max不满足，取中间值测试，如中间值满足，则中间值，max;如中间值不满足，则min，中间值
        int middle = (int)Math.ceil((double)(min + max) / 2) ;
        boolean flag = isValid(input,target,middle);
        if (flag) {
            totalMax = Math.max(totalMax,middle);
        }
        if (middle == min || middle == max) {
            return;
        }

        if (flag){
            process(input, target, middle,max);
        } else{
            process(input,target,min,middle);
        }


    }

    // 判断current是否可用
    public static boolean isValid(int[] input, int target, int current) {
        int temp = 0;
        for (int i : input) {
            if (i <= current) {
                temp += i;
            } else {
                temp += current;
            }
        }
        if (temp <= target) {
            return true;
        }
        return false;
    }
}
