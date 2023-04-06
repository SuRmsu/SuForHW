package newod.case1.logic;

import java.util.*;

/**
 * 异常打卡记录
 * 题目描述
 * 考勤记录是分析和考核职工工作时间利用情况的原始依据，也是计算职工工资的原始依据，为了正确地计算职工工资和监督工资基金使用情况，公司决定对员工的手机打卡记录进行异常排查。
 * <p>
 * <p>
 * 如果出现以下两种情况，则认为打卡异常：
 * <p>
 * 实际设备号与注册设备号不一样
 * 或者，同一个员工的两个打卡记录的时间小于60分钟并且打卡距离超过5km。
 * 给定打卡记录的字符串数组clockRecords（每个打卡记录组成为：工号;时间（分钟）;打卡距离（km）;实际设备号;注册设备号），返回其中异常的打卡记录（按输入顺序输出）。
 * <p>
 * 输入描述
 * 第一行输入为N，表示打卡记录数；
 * <p>
 * 之后的N行为打卡记录，每一行为一条打卡记录。
 * <p>
 * 输出描述
 * 输出异常的打卡记录。
 * <p>
 * 备注
 * clockRecords长度 ≤ 1000
 * clockRecords[i] 格式：{id},{time},{distance},{actualDeviceNumber},{registeredDeviceNumber}
 * id由6位数字组成
 * time由整数组成，范围为0~1000
 * distance由整数组成，范围为0~100
 * actualDeviceNumber与registeredDeviceNumber由思维大写字母组成
 * <p>
 * 解法：
 * 首先，打卡记录异常，有两种类型：
 * 1、单条打卡记录自身比较，即单条打卡记录的实际设备号和注册设备号不同，则视为异常。
 * 2、同一员工的两条打卡记录对比，如果打卡时间间隔小于60min，但是打卡距离却超过了5km，则视为异常
 * <p>
 * 但是这里有一个疑点，那就是一旦有两条打卡记录对比异常了，那么其他打卡记录是否还需要和这两条异常记录对比吗？
 * <p>
 * 这点，题目描述没说，给的用例也无法验证。我理解是需要的，
 * 另外，本题还有一个关键点就是：异常的打卡记录需要（按输入顺序输出）。
 * <p>
 * 因此，我们在保存异常打卡记录时，还需要附加其输入时的索引，已方便按照输入索引输出。
 */
public class OD52 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        String[][] clockRecords = new String[n][];
        for (int i = 0; i < n; i++) {
            clockRecords[i] = sc.next().split(",");
        }

        System.out.println(getResult(clockRecords));
    }

    /**
     * @param clockRecords 打卡记录的字符串数组, [工号, 时间, 打卡距离, 实际设备号, 注册设备号]
     * @return 异常打卡记录列表（按输入顺序排序）
     */
    public static String getResult(String[][] clockRecords) {
        HashMap<String, ArrayList<String[]>> employees = new HashMap<>();
        TreeSet<Integer> ans = new TreeSet<>((a, b) -> a - b);

        for (int i = 0; i < clockRecords.length; i++) {
            // 由于异常打卡记录需要按输入顺序输出，因此这里追加一个输入索引到打卡记录中
            String[] clockRecord = Arrays.copyOf(clockRecords[i], clockRecords[i].length + 1);
            clockRecord[clockRecord.length - 1] = i + "";

            String id = clockRecord[0];
            String act_device = clockRecord[3];
            String reg_device = clockRecord[4];

            // 实际设备号与注册设备号不一样,则认为打卡异常
            if (!act_device.equals(reg_device)) {
                ans.add(i);
            }

            // 统计该员工的打卡
            employees.putIfAbsent(id, new ArrayList<>());
            employees.get(id).add(clockRecord);
        }

        for (String id : employees.keySet()) {
            // 某id员工的所有打卡记录records
            ArrayList<String[]> records = employees.get(id);

            // 将该员工打卡记录按照打卡时间升序
            records.sort((a, b) -> Integer.parseInt(a[1]) - Integer.parseInt(b[1]));

            for (int i = 0; i < records.size(); i++) {
                int time1 = Integer.parseInt(records.get(i)[1]);
                int dist1 = Integer.parseInt(records.get(i)[2]);

                for (int j = i + 1; j < records.size(); j++) {
                    int time2 = Integer.parseInt(records.get(j)[1]);
                    int dist2 = Integer.parseInt(records.get(j)[2]);

                    // 如果两次打卡时间超过60分治，则不计入异常，由于已按打卡时间升序，因此后面的都不用检查了
                    if (time2 - time1 >= 60) break;
                    else {
                        // 如果两次打开时间小于60MIN，且打卡距离超过5KM，则这两次打卡记录算作异常
                        if (Math.abs(dist2 - dist1) > 5) {
                            // 如果打卡记录已经加入异常列表ans，则无需再次加入，否则需要加入
                            ans.add(Integer.parseInt(records.get(i)[5]));
                            ans.add(Integer.parseInt(records.get(j)[5]));
                        }
                    }
                }
            }
        }

        // 如果没有异常打卡记录，则返回null
        if (ans.size() == 0) return "null";

        StringJoiner sj = new StringJoiner(";");
        ans.stream()
                .map(i -> clockRecords[i])
                .forEach(
                        sArr -> {
                            StringJoiner sj1 = new StringJoiner(",");
                            for (String s : sArr) {
                                sj1.add(s);
                            }
                            sj.add(sj1.toString());
                        });

        return sj.toString();
    }
}
