package newod.case1.logic;

import java.util.HashMap;
import java.util.Scanner;

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
 * 最优：快慢指针
 * 此时，就要用到快慢指针。
 *
 * 所谓快慢指针，即通过两个指针遍历链表，慢指针每次步进1个节点，快指针每次步进2个节点，
 * 这样快指针必然先到达链表尾部，而当快指针到达链表尾部时，慢指针其实刚好就是在链表中间节点的位置（奇数个节点取中间，偶数个取偏右边的那个值）。
 *
 * 本题虽然给出了节点数，但是这些节点不一定属于同一个链表结构，因此本题的链表长度也是未知的，而本题要求的链表中间节点要求刚好和快慢指针找的中间节点吻合，因此本题最佳策略是使用快慢指针。
 *
 */
public class OD2 {
    /*
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
    */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String head = sc.next();
        int n = sc.nextInt();

        HashMap<String, String[]> nodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String addr = sc.next();
            String val = sc.next();
            String nextAddr = sc.next();
            nodes.put(addr, new String[]{val, nextAddr});
        }

        System.out.println(getResult(head, nodes));
    }

    public static String getResult(String head, HashMap<String, String[]> nodes) {
        String[] slow = nodes.get(head);
        String[] fast = nodes.get(slow[1]);

        while (fast != null) {
            slow = nodes.get(slow[1]);

            fast = nodes.get(fast[1]);
            if (fast != null) {
                fast = nodes.get(fast[1]);
            } else {
                break;
            }
        }

        return slow[0];

    }
}
