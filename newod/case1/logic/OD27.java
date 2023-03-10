package newod.case1.logic;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 总最快检测效率
 * 题目描述
 * 在系统、网络均正常的情况下组织核酸采样员和志愿者对人群进行核酸检测筛查。
 *
 * 每名采样员的效率不同，采样效率为N人/小时。
 *
 * 由于外界变化，采样员的效率会以M人/小时为粒度发生变化，M为采样效率浮动粒度，M=N*10%，输入保证N*10%的结果为整数。
 *
 * 采样员效率浮动规则：采样员需要一名志愿者协助组织才能发挥正常效率，在此基础上，每增加一名志愿者，效率提升1M，最多提升3M；如果没有志愿者协助组织，效率下降2M。
 *
 * 怎么安排速度最快？求总最快检测效率（总检查效率为各采样人员效率值相加）。
 *
 * 输入描述
 * 第一行：第一个值，采样员人数，取值范围[1, 100]；第二个值，志愿者人数，取值范围[1, 500]；
 * 第二行：各采样员基准效率值（单位人/小时），取值范围[60, 600]，保证序列中每项值计算10%为整数。
 *
 * 输出描述
 * 第一行：总最快检测效率（单位人/小时）
 *
 * 解法一：逻辑解法，非最优
 * 首先分两种情况：
 *
 * 1、志愿者数量少于采样员
 *
 * 2、志愿者数量不少于采样员
 *
 * 对于情况1，我们应该将不多的志愿者优先分配给高效率的采样员，默认一比一分配。
 *
 * 接下来，我们应该考虑，剥夺低效率的采样员的志愿者  给 高效率的采样员，只要 高效率采样员增加的10%的效率   可以大于  低效率采样员减少的20%的效率。
 *
 * 其中还要考虑，高效率的采样员最多可以追加3个志愿者，即最多增加30%的效率。如果最高效率的采样员已经提升30%效率，则第二高效率的采样员称为最高优先级，继续上面剥夺逻辑。
 *
 * 对于情况2，我们应该先按一比一的方式，给每个采样员分配一个志愿者。
 *
 * 然后，如果还多出志愿者的话，则优先分配给高效率的采样员，同样需要注意每个采样员最追加3个志愿者。
 *
 * 当多出的志愿者分配完后，我们需要考虑剥夺低效率的采样员的志愿者  给 高效率的采样员，只要 高效率采样员增加的10%的效率   可以大于  低效率采样员减少的20%的效率。逻辑同情况1。
 *
 * 根据ant_shi网友的指正，上面解析对于志愿者超出采样员数量四倍时的情况考虑不够全面：
 *
 * 另外，对于情况2而言，如果采样员：志愿者 的比例，超过了1：4，那么超出4倍采样员范围的志愿者将没有效率提升作用，因此有效志愿者数量最多是四倍的采样员数量。
 *
 * 解法二：优先队列解法 最优
 * 即将所有采样员加入（大顶堆）优先队列中，优先级是：该采样员新增一名志愿者，所能提升的效率。
 *
 * 初始时，加入优先队列的采样员都不搭配采样员，此时新增一名志愿者，每个采样员都能提升20%，但是由于基准效率base不同，因此实际每个采样员提升的效率值为base * 0.2。
 *
 * 之后，我们取出堆顶最大提升效率的采样员，为其新增一名志愿者：
 *
 * 如果其已有志愿者数量为0，则新增一名志愿者后，恢复为base效率
 * 如果其已有志愿者数量<=3，则新增一名志愿者后，其总效率 += base * 0.1
 * 如果其已有志愿者数量 > 3，即至少为4，则再新增一名志愿者，不会带来效率提升，即新增效率为0
 * 当我们更新完取出的采样员的志愿者数量和总效率后，将其重新压入优先队列，进行排序。
 *
 * 最后，当志愿者用完了，或者所有采样员都安排了4名志愿者了，则结束上面操作。
 *
 * 接下来就是取出优先队列的所有采样员，并累加他们的总效率作为题解。
 *
 */
public class OD27 {

    /* 此解法仍有问题，需要记录每个人已经配备了多少人
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int y = sc.nextInt();

        Integer[] arrX = new Integer[x];
        for (int i = 0; i < x; i++) {
            arrX[i] = sc.nextInt();
        }

        System.out.println(getResult(arrX, x, y));
    }

    public static int getResult(Integer[] arr, int x, int y) {
        // 按照正常效率降序
        Arrays.sort(arr, (a, b) -> b - a);

        int max = 0;
        int count = 0;
        int i;
        int j;

        // 如果志愿者少于采样员，则优先将志愿者分配给正常效率高的采样员
        if (y < x) {
            // 0~y-1范围内高效率的采样员优先获得一个志愿者，因此保持正常效率，而y~x-1范围内的低效率采样员则没有志愿者，效率下降20%
            for (int k = 0; k < x; k++) {
                max += k < y ? arr[k] : arr[k] * 0.8;
            }

            i = 0;
            j = y - 1;
        }
        // 如果志愿者 不少于 采样员，那么默认情况下每个采样员都分配一个志愿者
        else {
            // 如果志愿者人数超过采样员四倍，则多出来的志愿者就没有作用了
            if (y >= 4 * x) {
                y = 4 * x;
            }

            // 每个采样员都默认发挥正常效率
            for (Integer val : arr) {
                max += val;
            }

            // surplus记录每个采样员分配一个志愿者后，还多出来的志愿者
            int surplus = y - x;

            i = 0;
            j = x - 1;

            // 优先将多出来的志愿者分配给高效率的采样员
            while (surplus > 0) {
                max += arr[i] * 0.1;
                surplus--;
                if (++count == 3) {
                    count = 0;
                    i++;
                }
            }
        }

        // 志愿者分配完后，则继续考虑剥夺低效率采样员的志愿者给高效率的采样员
        while (i < j) {
            // 如果最高效率的采样员上升10%的效率  是否大于  最低效率的采样员下降20%的效率，那么就值得剥夺
            if (arr[i] * 0.1 > arr[j] * 0.2) {
                max += arr[i] * 0.1 - arr[j] * 0.2;

                // 由于一个采样员最多只能提升30%，即除了一个基础志愿者外，最多再配3个志愿者，多配了也没用
                if (++count == 3) {
                    count = 0;
                    i++;
                }
                j--;
            } else {
                break;
            }
        }

        return max;
    }

     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int y = sc.nextInt();

        Integer[] arrX = new Integer[x];
        for (int i = 0; i < x; i++) {
            arrX[i] = sc.nextInt();
        }

        System.out.println(getResult(arrX, x, y));
    }

    /**
     * @param arr 各采样员基准效率值（单位人/小时） [60, 600]
     * @param x 采样员人数 [1, 100]
     * @param y 志愿者人数 [1, 500]
     * @return 总最快检测效率（单位人/小时）
     */
    public static int getResult(Integer[] arr, int x, int y) {
        // 大顶堆优先队列，堆顶元素总是：新增一个志愿者后，提升效率最多的采样员
        PriorityQueue<Sampler> pq = new PriorityQueue<>((a, b) -> (int) (getAdd(b) - getAdd(a)));
        // 将所有采样员员加入优先队列，初始时采样员都不搭配志愿者
        for (int base : arr) {
            pq.offer(new Sampler(0, base));
        }

        // 只要还有志愿者，就将其分配给采样员
        while (y > 0) {
            // 如果堆顶采样员已有四个志愿者，那么该采样员能提升的效率为0，说明此时提升0的效率就是所有采样员中能提升的最大效率，即说明所有采样员都安排到了四个采样员，因此再增加志愿者也不会带来效率提升
            if (pq.isEmpty() || pq.peek().volunteer == 4) break;

            // 如果上一步不成立，则取出堆顶采样员
            Sampler s = pq.poll();

            // 为其新增一个志愿者，并提升相应效率
            s.total += getAdd(s);
            s.volunteer += 1;

            // 重新压入队列
            pq.offer(s);
            y--;
        }

        double ans = 0;
        while (!pq.isEmpty()) ans += pq.poll().total;
        return (int) ans;
    }

    // 采样员s增加一名志愿者能提升的效率
    public static double getAdd(Sampler s) {
        // 如果当前采样员没有志愿者，则新增一名志愿者可以提升base * 20%的效率
        if (s.volunteer == 0) return s.base * 0.2;
            // 如果当前采样员搭配的志愿者数量小于等于3个，则说明再新增一个志愿者，可以提升base * 10%的效率
        else if (s.volunteer <= 3) return s.base * 0.1;
            // 如果当前采样员已有4个志愿者，则再新增一个志愿者，不能提升效率，即提升效率为0
        else return 0;
    }
}



// 采样员类
class Sampler {
    int volunteer = 0; // 该采样员搭配的志愿者人数
    double base = 0; // 该采样员的基准效率
    double total = 0; // 该采样员的搭配志愿者后的总效率

    public Sampler(int volunteer, double base) {
        this.volunteer = volunteer;
        this.base = base;
        this.total = base * 0.8; // 初始时采样员没有搭配志愿者，则效率只有base*0.8
    }
}