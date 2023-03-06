package newod.case1.logic;

/**
 * 最快到达医院的方法
 * 题目描述
 * 新型冠状病毒疫情的肆虐，使得家在武汉的大壮不得不思考自己家和附近定点医院的具体情况。
 *
 * 经过一番调查，大壮明白了距离自己家最近的定点医院有两家。其中：
 *
 * 医院A和自己的距离是X公里
 * 医院B和自己的距离是Y公里
 * 由于武汉封城，公交停运，私家车不能上路，交通十分不便。现在：
 *
 * 到达医院A只能搭乘志愿者计程车，已知计程车的平均速度是M米/分钟，上车平均等待时间为L分钟。
 * 到达医院B只能步行，平均速度是N米/分钟；
 * 给出X，Y，M，L，N的数据，请问大壮到达哪家医院最快？
 *
 * 输入描述
 * 一行，5个数。
 *
 * 分别是到达A医院的距离，到达B医院的距离，计程车平均速度，上车等待时间，步行速度。
 *
 * 输出描述
 * 一行，计程车（Taxi）、步行（Walk）、相等（Same）
 *
 * 解法：注意单位
 */
public class OD76 {
    public static void main(String[] args) {
        double x = 50;
        double y = 5;
        double m = 500;
        double l = 30;
        double n = 90;

        double timeX = x * 1000.0 / m + 30;
        double timeY = y * 1000 / n;

        if (timeX < timeY){
            System.out.println("Taxi");
        } else if (timeX == timeY){
            System.out.println("Same");
        } else {
            System.out.println("Walk");
        }

    }
}
