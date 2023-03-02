package newod.case1.logic;

/**
 * 模拟商场优惠打折
 * 题目描述
 * 模拟商场优惠打折，有三种优惠券可以用，满减券、打折券和无门槛券。
 * <p>
 * 满减券：满100减10，满200减20，满300减30，满400减40，以此类推不限制使用；
 * <p>
 * 打折券：固定折扣92折，且打折之后向下取整，每次购物只能用1次；
 * <p>
 * 无门槛券：一张券减5元，没有使用限制。
 * <p>
 * 每个人结账使用优惠券时有以下限制：
 * <p>
 * 每人每次只能用两种优惠券，并且同一种优惠券必须一次用完，不能跟别的穿插使用（比如用一张满减，再用一张打折，再用一张满减，这种顺序不行）。
 * <p>
 * 求不同使用顺序下每个人用完券之后得到的最低价格和对应使用优惠券的总数；如果两种顺序得到的价格一样低，就取使用优惠券数量较少的那个。
 * <p>
 * 输入描述
 * 第一行三个数字m,n,k，分别表示每个人可以使用的满减券、打折券和无门槛券的数量;
 * <p>
 * 第二行一个数字x, 表示有几个人购物;
 * <p>
 * 后面x行数字，依次表示是这几个人打折之前的商品总价。
 * <p>
 * 输出描述
 * 输出每个人使用券之后的最低价格和对应使用优惠券的数量
 *
 * 解法：
 * 无门槛券一定是最后用才划算，就只有四种情况，满折，折满，满无，折无
 * 还是屎山，待优化
 */
public class OD14 {
    static double tempMin = Integer.MAX_VALUE;
    static int usedMin = 0;

    public static void main(String[] args) {
        int[] tickets = {3, 2, 5};
        double[] men = {100, 200, 400};

        for (double man : men) {
            process(man, tickets[0], tickets[1], 1);
            process(man, tickets[1], tickets[0], 2);
            process(man, tickets[0], tickets[2], 3);
            process(man, tickets[1], tickets[2], 4);
            System.out.println(tempMin);
            System.out.println(usedMin);
            tempMin = Integer.MAX_VALUE;
            usedMin = 0;
        }


    }

    public static void process(double money, int x, int y, int type) {
        double a = 0;
        double b = 0;
        switch (type) {
            case 1 -> {
                a = 0.9;
                b = 0.92;
                if (money >= 100 && x > 0) {
                    int used = (int) Math.min(money / 100, x);
                    money -= used * 10;
                }
                if (y > 0) {
                    tempMin = Math.min(tempMin, money * 0.92);
                } else {
                    tempMin = Math.min(tempMin, money);
                }
            }
            case 2 -> {
                a = 0.92;
                b = 0.9;

                if (x > 0) {
                    money = money * 0.92;
                }
                if (money >= 100 && y > 0) {
                    int used = (int) Math.min(money / 100, y);
                    money -= used * 10;
                }
                tempMin = Math.min(tempMin, money);

            }
            case 3 -> {
                a = 0.9;
                b = -5;
                if (money >= 100 && x > 0) {
                    int used = (int) Math.min(money / 100, x);
                    money -= used * 10;
                }
                while (y > 0){
                    if (money >= 5) {
                        money -= 5;
                        y--;
                    }
                }
                tempMin = Math.min(tempMin,money);

            }
            case 4 -> {
                a = 0.92;
                b = -5;
                if (x > 0) {
                    money = money * 0.92;
                }
                while (y > 0){
                    if (money >= 5) {
                        money -= 5;
                        y--;
                    }
                }
                tempMin = Math.min(tempMin,money);
            }
        }


    }


}
