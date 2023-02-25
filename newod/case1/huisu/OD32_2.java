package newod.case1.huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 题目描述
 * 某公司目前推出了AI开发者套件，AI加速卡，AI加速模块，AI服务器，智能边缘多种硬件产品，每种产品包含若干个型号。
 * 现某合作厂商要采购金额为amount元的硬件产品搭建自己的AI基座。
 * 例如当前库存有N种产品，每种产品的库存量充足，给定每种产品的价格，记为price（不存在价格相同的产品型号）。
 * 请为合作厂商列出所有可能的产品组合。
 * <p>
 * 输入描述
 * 输入包含采购金额amount和产品价格列表price。第一行为amount，第二行为price，例如：
 * <p>
 * 500
 * [100, 200, 300, 500]
 * <p>
 * 输出描述
 * 输出为组合列表。例如：
 * <p>
 * [[100, 100, 100, 100, 100], [100, 100, 100, 200], [100, 100, 300], [100, 200, 200], [200, 300], [500]]
 * 解法：
 * 回溯，通过排序，则不需要去重
 * 可以剪枝，找到第一个不满足的，后面就都不用搜索了，但暂未实现
 */

public class OD32_2 {
    static List<List<Integer>> storage = new ArrayList<>();
    static Stack<Integer> tempStorage = new Stack<>();

    public static void main(String[] args) {
        int money = 600;
        Integer[] price = {100, 200, 300, 500};
        Arrays.sort(price);
        process(price,0,0,money);
        System.out.println(storage);
    }

    public static void process(Integer[] price, int startIndex, int sum, int target) {
        if (sum == target) {
            storage.add(new ArrayList<>(tempStorage));
            return;
        } else if (sum > target) {
            return;
        } else if (startIndex == price.length) {
            return;
        }
        for (int i = startIndex; i < price.length; i++) {
            sum += price[i];
            if (sum <= target) {
                tempStorage.push(price[i]);
                process(price,i,sum,target);
                tempStorage.pop();

            } else {
                return;
            }
            sum -= price[i];
        }
    }

}
