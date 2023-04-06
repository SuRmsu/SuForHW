package newod.case1.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 题目描述
 * 公司某部门软件教导团正在组织新员工每日打卡学习活动，他们开展这项学习活动已经一个月了，所以想统计下这个月优秀的打卡员工。每个员工会对应一个id，每天的打卡记录记录当天打卡员工的id集合，一共30天。
 *
 * 请你实现代码帮助统计出打卡次数top5的员工。加入打卡次数相同，将较早参与打卡的员工排在前面，如果开始参与打卡的时间还是一样，将id较小的员工排在前面。
 *
 * 注：不考虑并列的情况，按规则返回前5名员工的id即可，如果当月打卡的员工少于5个，按规则排序返回所有有打卡记录的员工id。
 *
 * 输入描述
 * 第一行输入为新员工数量N，表示新员工编号id为0到N-1，N的范围为[1,100]
 *
 * 第二行输入为30个整数，表示每天打卡的员工数量，每天至少有1名员工打卡。
 *
 * 之后30行为每天打卡的员工id集合，id不会重复。
 *
 * 输出描述
 * 按顺序输出打卡top5员工的id，用空格隔开。
 *
 * 解法：简单排序题。需要注意的是，排序要素需要记录每个员工第一次打卡日期，作为第二优先级排序。
 */
public class OD53 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] dayCount = new int[30];
        for (int i = 0; i < 30; i++) {
            dayCount[i] = sc.nextInt();
        }

        int[][] dayIds = new int[30][];
        for (int i = 0; i < 30; i++) {
            int m = dayCount[i];
            dayIds[i] = new int[m];
            for (int j = 0; j < m; j++) {
                dayIds[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(dayIds));
    }

    public static String getResult(int[][] dayIds) {
        HashMap<Integer, Integer[]> employees = new HashMap<>();

        for (int i = 0; i < dayIds.length; i++) {
            int[] ids = dayIds[i];

            for (int id : ids) {
                if (employees.containsKey(id)) {
                    employees.get(id)[0]++;
                } else {
                    // 加入数组含义是：该id员工的 [打卡次数，第一天打卡日期]
                    employees.put(id, new Integer[] {1, i});
                }
            }
        }

        ArrayList<Integer[]> list = new ArrayList<>();
        for (Integer id : employees.keySet()) {
            Integer[] employee = employees.get(id);
            int count = employee[0];
            int firstDay = employee[1];
            list.add(new Integer[] {id, count, firstDay});
        }

        list.sort(
                (a, b) ->
                        a[1].equals(b[1]) ? (a[2].equals(b[2]) ? a[0] - b[0] : a[2] - b[2]) : b[1] - a[1]);

        StringJoiner sj = new StringJoiner(" ");
        // 不考虑并列的情况，按规则返回前5名员工的id即可，如果当月打卡的员工少于5个，按规则排序返回所有有打卡记录的员工id
        for (int i = 0; i < Math.min(5, list.size()); i++) {
            sj.add(list.get(i)[0] + "");
        }
        return sj.toString();
    }
}
