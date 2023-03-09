package newod.case1.logic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 垃圾短信识别
 * 题目描述
 * 大众对垃圾短信深恶痛绝，希望能对垃圾短信发送者进行识别，为此，很多软件增加了垃圾短信的识别机制。
 * 经分析，发现正常用户的短信通常具备交互性，而垃圾短信往往都是大量单向的短信，按照如下规则进行垃圾短信识别：
 * <p>
 * 本题中，发送者A符合以下条件之一的，则认为A是垃圾短信发送者：
 * <p>
 * A发送短信的接收者中，没有发过短信给A的人数L > 5；
 * A发送的短信数 – A接收的短信数M > 10；
 * 如果存在X，A发送给X的短信数 – A接收到X的短信数N > 5；
 * 输入描述
 * 第一行是条目数，接下来几行是具体的条目，每个条目，是一对ID，第一个数字是发送者ID，后面的数字是接收者ID，中间空格隔开，所有的ID都为无符号整型，ID最大值为100；
 * <p>
 * 同一个条目中，两个ID不会相同（即不会自己给自己发消息）
 * <p>
 * 最后一行为指定的ID
 * <p>
 * 输出描述
 * 输出该ID是否为垃圾短信发送者，并且按序列输出 L M 的值（由于 N 值不唯一，不需要输出）；
 * 输出均为字符串。
 * 解法：
 * A发送给那些人短信和接受到那些人的短信，Map<Integer,List<Integer>>
 *         // send记录 tid发送短信给“哪些人”
 *         LinkedList<Integer> send = new LinkedList<>();
 *         // receive记录  “哪些人”发送短信给tid
 *         LinkedList<Integer> receive = new LinkedList<>();
 *
 *         // sendCount记录 tid发送了“几次”（对象属性值）短信给某个人（对象属性）
 *         HashMap<Integer, Integer> sendCount = new HashMap<>();
 *         // receiveCount记录 某人（对象属性）发送了“几次”（对象属性值）短信给tid
 *         HashMap<Integer, Integer> receiveCount = new HashMap<>();
 *
 */
public class OD9_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        int id = sc.nextInt();

        System.out.println(getResult(id, arr));
    }

    public static String getResult(int tid, int[][] arr) {
        // send记录 tid发送短信给“哪些人”
        LinkedList<Integer> send = new LinkedList<>();
        // receive记录  “哪些人”发送短信给tid
        LinkedList<Integer> receive = new LinkedList<>();

        // sendCount记录 tid发送了“几次”（对象属性值）短信给某个人（对象属性）
        HashMap<Integer, Integer> sendCount = new HashMap<>();
        // receiveCount记录 某人（对象属性）发送了“几次”（对象属性值）短信给tid
        HashMap<Integer, Integer> receiveCount = new HashMap<>();

        for (int[] ele : arr) {
            int sid = ele[0];
            int rid = ele[1];

            if (sid == tid) {
                send.addLast(rid);
                sendCount.put(rid, sendCount.getOrDefault(rid, 0) + 1);
            }

            if (rid == tid) {
                receive.addLast(sid);
                receiveCount.put(sid, receiveCount.getOrDefault(sid, 0) + 1);
            }
        }

        HashSet<Integer> sendSet = new HashSet<>(send);
        HashSet<Integer> receiveSet = new HashSet<>(receive);

        // connect记录和tid有交互的id
        List<Integer> connect = send.stream().filter(receiveSet::contains).collect(Collectors.toList());

        int l = sendSet.size() - connect.size();
        int m = send.size() - receive.size();

        boolean isSpammers = l > 5 || m > 10;

        // 如果已经通过l和m确定了tid是垃圾短信发送者，那就不需要再确认n了，否则还是需要确认n
        if (!isSpammers) {
            for (Integer id : connect) {
                if (sendCount.getOrDefault(id, 0) - receiveCount.getOrDefault(id, 0) > 5) {
                    // 一旦发现x,则可以判断，则确定tid是垃圾短信发送者，可提前结束循环
                    isSpammers = true;
                    break;
                }
            }
        }

        return isSpammers + " " + l + " " + m;
    }



    /*
    public static void main(String[] args) {
        int target = 2;
        int[][] inputs = {
                {1, 2},
                {1, 3},
                {1, 4},
                {1, 5},
                {1, 6},
                {1, 7},
                {1, 8},
                {1, 9},
                {1, 10},
                {1, 11},
                {1, 12},
                {1, 13},
                {1, 14},
                {14, 1},
                {1, 15},
        };
        // A发送给那些人短信和接受到那些人的短信，Map<Integer,List<Integer>>
        // 此处记录的是全局的，但没必要，针对A记录就可以了
        Map<Integer, ArrayList<Integer>> input = new HashMap<Integer, ArrayList<Integer>>();
        Map<Integer, ArrayList<Integer>> output = new HashMap<Integer, ArrayList<Integer>>();

        for (int i = 0; i < inputs.length; i++) {
            // A给哪些人发了短信
            input.putIfAbsent(inputs[i][0], new ArrayList<Integer>());
            input.get(inputs[i][0]).add(inputs[i][1]);
            // A收到了哪些人的短信
            output.putIfAbsent(inputs[i][1], new ArrayList<Integer>());
            output.get(inputs[i][1]).add(inputs[i][0]);
        }
        // A发送的短信 - A接受的短信 > 10
        //
        int m = input.get(target).size() - output.get(target).size();

        // A发出去的人 - 没有给A发过短信的人 > 5
        Set<Integer> tempInput = new HashSet<Integer>(input.get(target));
        int countInput = tempInput.size();
        int countOutput = 0;
        for (Integer integer : tempInput) {
            if (input.get(integer) != null) {
                if (input.get(integer).contains(target)) {
                    countOutput++;
                }
            }
        }
        int l = countInput - countOutput;

        int flag = 0;
        // A给某个特定的人发的短信 - 其发给A的短信 > 5;
        for (Integer integer : tempInput) {
            int tempCountInput = 0;
            int tempCountOutput = 0;
            for (Integer i : input.get(target)) {
                if (i == integer) {
                    tempCountInput++;
                }
            }
            for (Integer i : output.get(integer)) {
                if ( i == integer){
                    tempCountOutput++;
                }
            }
             if (tempCountOutput - tempCountInput > 5){
                 flag = 1;
                 break;
             }
        }
        if (l > 5 || m > 10 || flag == 1){
            System.out.println(true + " " + l + " " + m);
        }else {
            System.out.println(false +" " + l + " " + m);
        }


    }
     */
}
