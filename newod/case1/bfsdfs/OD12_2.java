package newod.case1.bfsdfs;

import java.util.Arrays;

/**
 * 查找树中元素
 * 题目描述
 * 已知树形结构的所有节点信息，现要求根据输入坐标（x,y）找到该节点保存的内容值，其中x表示节点所在的层数，根节点位于第0层，根节点的子节点位于第1层，依次类推；y表示节点在该层内的相对偏移，从左至右，第一个节点偏移0，第二个节点偏移1，依次类推；
 * <p>
 * <p>
 * <p>
 * 举例：上图中，假定圆圈内的数字表示节点保存的内容值，则根据坐标(1,1)查到的内容值是23。
 * <p>
 * 输入描述
 * 每个节点以一维数组（int[]）表示，所有节点信息构成二维数组（int[][]），二维数组的0位置存放根节点；
 * 表示单节点的一维数组中，0位置保存内容值，后续位置保存子节点在二维数组中的索引位置。
 * 对于上图中：
 * <p>
 * 根节点的可以表示为{10，1，2}，
 * 树的整体表示为{{10,1,2},{-21,3,4},{23,5},{14},{35},{66}}
 * 查询条件以长度为2的一维数组表示，上图查询坐标为(1,1)时表示为{1,1}
 * <p>
 * 使用Java标准IO键盘输入进行录入时，
 * <p>
 * 先录入节点数量
 * 然后逐行录入节点
 * 最后录入查询的位置
 * 对于上述示例为：
 * <p>
 * 6
 * 10 1 2
 * -21 3 4
 * 23 5
 * 14
 * 35
 * 66
 * 1 1
 * <p>
 * 输出描述
 * 查询到内容时，输出{内容值}，查询不到时输出{}
 * 上图中根据坐标(1,1)查询输出{23}，根据坐标(1,2)查询输出{}
 * <p>
 * 解法：两个一维数组分别存链表元素 和 每一层元素个数
 */
public class OD12_2 {
    static Integer[] storage = new Integer[100];
    static Integer[][] tempStorage = new Integer[100][];
    static Integer[] lengths = new Integer[100];
    static int currentIndex = 0;

    public static void main(String[] args) {
        String[] input = {
                "0 1 2 3 4",
                "-11 5 6 7 8",
                "113 9 10 11",
                "24 12",
                "35",
                "66 13",
                "77",
                "88",
                "99",
                "101",
                "102",
                "103",
                "25",
                "104"
        };
        int k = 14;
        for (int i = 0; i < k; i++) {
            //String数组转Integer数组
            Integer[] temp = Arrays.stream(input[i].split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);
            tempStorage[i] = temp;
        }
        storage[0] = tempStorage[0][0];
        int firstCount = 0;
        int sumCount = 1;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < sumCount; j++) {
                if (currentIndex >= k) {
                    break;
                }
                if (tempStorage[currentIndex] == null  ){
                    break;
                }
                storage[currentIndex] = tempStorage[currentIndex][0];
                firstCount += tempStorage[currentIndex].length - 1;
                currentIndex++;
            }
            lengths[i] = sumCount;
            sumCount = firstCount;
            firstCount = 0;
        }

        int h = 2;
        int i = 5;
        int tempSum = 0;
        for (int j = 0; j < h; j++) {
            tempSum += lengths[j];
        }
        System.out.println(storage[tempSum + i]);




    }
}
