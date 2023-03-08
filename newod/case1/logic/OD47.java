package newod.case1.logic;

import java.util.*;

/**
 * 整理扑克牌
 * 题目描述
 * 给定一组数字，表示扑克牌的牌面数字，忽略扑克牌的花色，请按如下规则对这一组扑克牌进行整理：
 *
 * 步骤1. 对扑克牌进行分组，形成组合牌，规则如下：
 *
 * 当牌面数字相同张数大于等于4时，组合牌为“炸弹”；
 * 3张相同牌面数字 + 2张相同牌面数字，且3张牌与2张牌不相同时，组合牌为“葫芦”；
 * 3张相同牌面数字，组合牌为“三张”；
 * 2张相同牌面数字，组合牌为“对子”；
 * 剩余没有相同的牌，则为“单张”；
 * 步骤2. 对上述组合牌进行由大到小排列，规则如下：
 *
 * 不同类型组合牌之间由大到小排列规则：“炸弹” > “葫芦” > “三张” > “对子” > “单张”；
 * 相同类型组合牌之间，除“葫芦”外，按组合牌全部牌面数字加总由大到小排列；
 * “葫芦”则先按3张相同牌面数字加总由大到小排列，3张相同牌面数字加总相同时，再按另外2张牌面数字加总由大到小排列；
 * 由于“葫芦”>“三张”，因此如果能形成更大的组合牌，也可以将“三张”拆分为2张和1张，其中的2张可以和其它“三张”重新组合成“葫芦”，剩下的1张为“单张”
 * 步骤3. 当存在多个可能组合方案时，按如下规则排序取最大的一个组合方案：
 *
 * 依次对组合方案中的组合牌进行大小比较，规则同上；
 * 当组合方案A中的第n个组合牌大于组合方案B中的第n个组合牌时，组合方案A大于组合方案B；
 * 输入描述
 * 第一行为空格分隔的N个正整数，每个整数取值范围[1,13]，N的取值范围[1,1000]
 *
 * 输出描述
 * 经重新排列后的扑克牌数字列表，每个数字以空格分隔
 *
 * 解法：此题逻辑不一定对
 *
 * 首先，将给定牌中，炸弹，三张，对子，单子先统计出来，即先不处理葫芦。
 *
 * 统计逻辑很简单，就是看某个牌面的数量：
 *
 * >=4，那么该牌面可以组成炸弹
 * ===3，那么该牌面可以组成三张
 * ===2，那么该牌面可以组成对子
 * ===1，那么该牌面可以组成单张
 * 统计完后，我们就可以先对炸弹进行排序，排序规则是：全部牌面数字加总由大到小排列
 *
 * 接着可以组合葫芦了，组合逻辑如下：
 *
 * 首先，需要先对三张、对子按照加总降序
 *
 * 然后，选取一个最大的三张，并比较第二大的三张的牌面和第一大的对子的牌面
 *
 * 如果对子牌面大，则直接组合最大的三张和最大对子为忽略
 * 如果第二大三张牌面大，则拆分该三张为一个对子，一个单张，其中对子和最大的三张组合成葫芦，单张加入前面统计的单张数组
 * 按照上面规则组合葫芦，直到三张用完。
 */
public class OD47 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        Integer[] arr = Arrays.stream(str.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(arr));
    }

    public static String getResult(Integer[] arr) {
        HashMap<Integer, Integer> card = new HashMap<>();

        // 统计各种牌面的数量
        for (Integer num : arr) {
            if (card.containsKey(num)) {
                int val = card.get(num);
                card.put(num, ++val);
            } else {
                card.put(num, 1);
            }
        }

        // 统计组合，4代表炸弹，3+2代表葫芦，3代表三张，2代表对子，1代表单张
        HashMap<String, LinkedList<Integer[]>> combine = new HashMap<>();
        combine.put("4", new LinkedList<Integer[]>());
        combine.put("3+2", new LinkedList<Integer[]>());
        combine.put("3", new LinkedList<Integer[]>());
        combine.put("2", new LinkedList<Integer[]>());
        combine.put("1", new LinkedList<Integer[]>());

        // 首先将初始组合统计出来
        Set<Integer> cardKeys = card.keySet();
        for (Integer num : cardKeys) {
            switch (card.get(num)) {
                case 3:
                    combine.get("3").add(new Integer[] {num});
                    break;
                case 2:
                    combine.get("2").add(new Integer[] {num});
                    break;
                case 1:
                    combine.get("1").add(new Integer[] {num});
                    break;
                default:
                    combine
                            .get("4")
                            .add(
                                    new Integer[] {
                                            num, card.get(num)
                                    }); // 由于炸弹可能有4张以上相同牌面组成，因此既需要统计牌面num，也需要统计牌数card[num]
            }
        }

        // 炸弹排序，按照牌面值总和大小排序，总和越大，越高前，牌面总和 = 牌面值 * 牌数
        combine.get("4").sort((a, b) -> b[0] * b[1] - a[0] * a[1]);

        // 三张排序，牌面值越大，三张越大
        combine.get("3").sort((a, b) -> b[0] - a[0]);

        // 对子排序，牌面值越大，对子越大
        combine.get("2").sort((a, b) -> b[0] - a[0]);

        // 尝试组合出葫芦
        while (combine.get("3").size() > 0) {
            // 如果对子用完，三张还有一个，那么可以直接结束循环
            if (combine.get("2").size() == 0 && combine.get("3").size() == 1) break;

            // 否则，选取一个最大的三张
            Integer san_top = combine.get("3").removeFirst()[0];

            Integer tmp;
            // 如果此时没有对子了，胡总和第二大的三张的牌面，比最大的对子牌面大，则可以拆分三张，组合出葫芦
            if (combine.get("2").size() == 0
                    || (combine.get("3").size() > 0
                    && combine.get("3").get(0)[0] > combine.get("2").get(0)[0])) {
                tmp = combine.get("3").removeFirst()[0];
                // 拆分三张为对子的话，会多出一个单张
                combine.get("1").add(new Integer[] {tmp});
            } else {
                // 如果对子牌面比三张大，则不需要拆分三张，直接使用对子组合出葫芦
                tmp = combine.get("2").removeFirst()[0];
            }
            combine.get("3+2").add(new Integer[] {san_top, tmp}); // 葫芦元素含义：[三张牌面，对子牌面]
        }

        // 处理完葫芦后，就可以对单张进行降序了（因为组合葫芦的过程中，可能产生新的单张，因此单张排序要在葫芦组合得到后进行）
        combine.get("1").sort((a, b) -> b[0] - a[0]);

        // ans存放题解
        ArrayList<Integer> ans = new ArrayList<>();

        // 首先将炸弹放到ans中
        for (Integer[] vals : combine.get("4")) {
            int score = vals[0];
            int count = vals[1];
            for (int i = 0; i < count; i++) {
                ans.add(score);
            }
        }

        // 然后将葫芦放大ans中
        for (Integer[] vals : combine.get("3+2")) {
            int san = vals[0];
            int er = vals[1];
            for (int i = 0; i < 3; i++) ans.add(san);
            for (int i = 0; i < 2; i++) ans.add(er);
        }

        // 之后将三张放到ans中
        for (Integer[] vals : combine.get("3")) {
            for (int i = 0; i < 3; i++) ans.add(vals[0]);
        }

        // 接着是对子放到ans中
        for (Integer[] vals : combine.get("2")) {
            for (int i = 0; i < 2; i++) ans.add(vals[0]);
        }

        // 最后是单张放到ans中
        for (Integer[] vals : combine.get("1")) {
            ans.add(vals[0]);
        }

        StringJoiner sj = new StringJoiner(" ", "", "");
        for (Integer an : ans) {
            sj.add(an + "");
        }

        return sj.toString();
    }
}
