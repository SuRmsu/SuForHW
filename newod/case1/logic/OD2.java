package newod.case1.logic;

import java.util.HashMap;

/**
 * 单向链表中间节点
 * 题目描述
 * 求单向链表中间的节点值，如果奇数个节点取中间，偶数个取偏右边的那个值。
 *
 * 输入描述
 * 第一行 链表头节点地址 后续输入的节点数n
 *
 * 后续输入每行表示一个节点，格式 节点地址 节点值 下一个节点地址(-1表示空指针)
 *
 * 输入保证链表不会出现环，并且可能存在一些节点不属于链表。
 *
 * 输出描述
 * 单向链表中间的节点值
 *
 * 解法：用HashMap<String,String[]>来存储节点，再用String[]来按顺序存储值即可
 *
 */
public class OD2 {
    public static void main(String[] args) {
//        String[][] input = {
//                {"00000","3","-1"},
//                {"00010","5","12309"},
//                {"11451","6","00000"},
//                {"12309","7","11451"}
//        };
//        String startAddress = "00010";
//        int counts = 4;
        String[][] input = {
                {"76892","7","12309"},
                {"12309","5","-1"},
                {"10000","1","76892"}
        };
        String startAddress = "10000";
        int counts = 3;

        String[] values = new String[counts];
        int usefulCount = 0;

        HashMap<String,String[]> nodes = new HashMap< String, String[]>();
        for (String[] strings : input) {
            String[] tempString = { strings[2],strings[1]};
            nodes.put(strings[0],tempString);
        }
        while (!startAddress.equals("-1")){
            values[usefulCount] = nodes.get(startAddress)[1];
            usefulCount++;
            startAddress = nodes.get(startAddress)[0];
        }
        System.out.println(values[usefulCount / 2]);
    }
}
