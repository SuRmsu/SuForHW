package newod.case1.logic;

import java.util.*;

/**
 * 创建二叉树
 * 题目描述
 * 请按下列描述构建一颗二叉树，并返回该树的根节点：
 *
 * 1、先创建值为-1的根结点，根节点在第0层;
 *
 * 2、然后根据operations依次添加节点： operations[i] = [height, index] 表示对第 height 层的第index 个节点node， 添加值为 i 的子节点：
 *
 * 若node 无「左子节点」，则添加左子节点;
 * 若node 有「左子节点」，但无「右子节点」，则添加右子节点；
 * 否则不作任何处理。
 * height、index 均从0开始计数；
 *
 * index 指所在层的创建顺序。
 *
 * 注意：
 *
 * 输入用例保证每次操作对应的节点已存在；
 * 控制台输出的内容是根据返回的树根节点，按照层序遍历二叉树打印的结果。
 * 输入描述
 * operations
 *
 * 输出描述
 * 根据返回的树根节点，按照层序遍历二叉树打印的结果
 *
 * 备注
 * 1 <= operations.length <= 100
 * operations[i].length == 2
 * 0 <= operations[i][0] < 100
 * 0 <= operations[i][1] < 100
 *
 * 解法：这题比较麻烦，须思考实现的思路
 * 创建一个二维数组（集合，列表）tree，初始时为： [[node(-1)]]
 *
 * 即tree[0][0] = node(-1)，这里tree[0][0]表示height = 0, index = 0
 *
 * 即tree的行索引就是height层数，列索引就是index创建顺序
 *
 * tree[0][0]就代表二叉树根节点。
 *
 *
 * 当我们遍历operations时，operations[i] = [height, index] ，其实就是找 tree[height][index]父节点，在该父节点下插入node(i)子节点，而在该父节点下插入node(i)子节点，其实就是向tree[height+1]数组中加入node(i)节点，比如
 *
 * 插入逻辑是：
 *
 * 若tree[height][index] 无「左子节点」，则添加左子节点;
 * 若tree[height][index] 有「左子节点」，但无「右子节点」，则添加右子节点；
 * 否则不作任何处理。
 * 如果tree[height+1]不存在，需要先初始化一个数组，然后加入node(i)节点，此时node(i)节点在tree[height+1]中的索引其实就是node(i)节点，在height+1层的index创建顺序。
 *
 * 这样的话，我们就能依赖于tree二维数组结构来完成任意节点的height，index的记录了。这样用例2插入值7时，就可以快速找到第3行第1个创建的节点了。
 *
 * 但是，此时各节点之间并没有建立连续，因此我们还要设计Node类，来完成各节点之间的父子关系构建。这个就很简单了，具体实现看代码。
 */
public class OD36_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        Integer[][] operations =
                Arrays.stream(str.substring(1, str.length() - 1).split("(?<=]), (?=\\[)"))
                        .map(
                                s ->
                                        Arrays.stream(s.substring(1, s.length() - 1).split(", "))
                                                .map(Integer::parseInt)
                                                .toArray(Integer[]::new))
                        .toArray(Integer[][]::new);

        System.out.println(getResult(operations));
    }

    public static String getResult(Integer[][] operations) {
        Node head = new Node(-1);

        ArrayList<Node> level0 = new ArrayList<>();
        level0.add(head);
        // 用于记录 每一层新建的顺序
        ArrayList<ArrayList<Node>> tree = new ArrayList<>();
        tree.add(level0);

        for (int i = 0; i < operations.length; i++) {
            int height = operations[i][0];
            int index = operations[i][1];

            if (tree.size() <= height + 1) {
                tree.add(new ArrayList<>());
            }
            // 将这个节点插入到 tree中，记录了其插入当前的次序
            Node ch = new Node(i);
            tree.get(height + 1).add(ch);
            // 获得这个节点的父节点，并将子节点指针写入父节点
            Node fa = tree.get(height).get(index);
            if (fa.lc == null) fa.lc = ch;
            else if (fa.rc == null) fa.rc = ch;
        }

        // ans用于存放层序遍历后的结果
        LinkedList<Integer> ans = new LinkedList<>();
        // 用于存放当前层的父节点和对应的子节点
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(tree.get(0).get(0));

        // 先遍历当前节点，然后将其加入到ans结果集中，然后将其子节点放入到队列中
        // 从父节点开始，那么父节点那一层遍历完之后就会到子节点
        //

        while (queue.size() > 0) {
            Node node = queue.removeFirst();

            if (node != null) {
                ans.add(node.val);
                queue.add(node.lc);
                queue.add(node.rc);
            } else {
                // 后面还有节点，添加一个null节点
                ans.add(null);
            }
        }

        // 如果最后一个是null，去除掉

        while (true) {
            if (ans.getLast() == null) ans.removeLast();
            else break;
        }
        // 输出转化
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (Integer an : ans) {
            sj.add(an + "");
        }

        return sj.toString();
    }

}

class Node {
    int val;
    Node lc;
    Node rc;

    public Node(int val) {
        this.val = val;
        this.lc = null;
        this.rc = null;
    }
}