package newod.case1.logic;

import java.util.*;

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
 * 异常情况须考虑，待完善
 */
public class OD9_2 {
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
}
