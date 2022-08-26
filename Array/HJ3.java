package Array;

import java.util.*;

public class HJ3 {
    /**
     * 利用HashSet方法能解决去重问题
     * 利用TreeSet方法能同时解决去重和排序问题
     * @throws Exception
     */
    public void mySolution() throws Exception {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        HashSet<Integer> storage = new HashSet<>();
        int temp ;
        for (int i = 0 ; i < times ; i++ ){
            temp = in.nextInt();
            storage.add(temp);
        }
        Integer[] output = new Integer[storage.size()];
        storage.toArray(output);
        Arrays.sort(output);
        for (Integer i : output){
            System.out.println(i);
        }
    }

    public void bestSolution() throws Exception{
        Scanner sc = new Scanner(System.in);
        //获取个数
        int num = sc.nextInt();
        //创建TreeSet进行去重排序
        TreeSet set = new TreeSet();
        //输入
        for(int i =0 ; i < num ;i++){
            set.add(sc.nextInt());
        }

        //输出
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
