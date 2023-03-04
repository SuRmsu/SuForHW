package newod.case1.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 快速开租建站
 * 题目描述
 * 当前IT部门支撑了子公司颗粒化业务，该部门需要实现为子公司快速开租建站的能力，建站是指在一个全新的环境部署一套IT服务。
 * <p>
 * 每个站点开站会由一系列部署任务项构成，每个任务项部署完成时间都是固定和相等的，设为1。
 * 部署任务项之间可能存在依赖，假如任务2依赖任务1，那么等任务1部署完，任务2才能部署。
 * 任务有多个依赖任务则需要等所有依赖任务都部署完该任务才能部署。
 * 没有依赖的任务可以并行部署，优秀的员工们会做到完全并行无等待的部署。
 * 给定一个站点部署任务项和它们之间的依赖关系，请给出一个站点的最短开站时间。
 * <p>
 * 输入描述
 * 第一行是任务数taskNum,第二行是任务的依赖关系数relationsNum
 * <p>
 * 接下来 relationsNum 行，每行包含两个id，描述一个依赖关系，格式为：IDi IDj，表示部署任务i部署完成了，部署任务j才能部署，IDi 和 IDj 值的范围为：[0, taskNum)
 * <p>
 * 注：输入保证部署任务之间的依赖不会存在环。
 * <p>
 * 输出描述
 * 一个整数，表示一个站点的最短开站时间。
 * <p>
 * 备注
 * 1 ＜ taskNum ≤ 100
 * 1 ≤ relationsNum ≤ 5000
 * 解法：入度为当前节点依赖的节点；因此只需要不停的删除度为0的节点即可
 * map用来记录节点及依赖关系
 * 两个set分别记录度不为0的节点和度为0的节点
 */
public class OD29_2 {
    public static void main(String[] args) {
//        int m = 5;
//        int k = 5;
//        int[][] input = {
//                {0, 4},
//                {1, 2},
//                {1, 3},
//                {2, 3},
//                {2, 4}
//        };
        int m = 5;
        int k = 3;
        int[][] input = {
                {0, 3},
                {0, 4},
                {1, 3}
        };
        // storage用来记录当前度不为0的值及其依赖
        Map<Integer, Set<Integer>> storage = new HashMap<>();
        // 记录当前度不为0的值
        Set<Integer> left = new HashSet<>();
        for (int i = 0; i < m; i++) {
            left.add(i);
        }
        // 记录此次循环中度为0的的值
        Set<Integer> deleted = new HashSet<>();
        for (int i = 0; i < k; i++) {
            int tempA = input[i][1];
            int tempB = input[i][0];
            // 添加入度
            storage.putIfAbsent(tempA, new HashSet<>());
            storage.get(tempA).add(tempB);
            left.remove(tempA);
        }
        int count = 1;
        while (storage.size() != 0) {
            // 遍历left中的，并删除
            for (Integer integer : left) {
                for (Integer integer1 : storage.keySet()) {
                    storage.get(integer1).remove(integer);
                    if (storage.get(integer1).size() == 0) {
                        deleted.add(integer1);
                    }
                }
            }
            count++;
            for (Integer integer : deleted) {
                storage.remove(integer);
                left.add(integer);
            }

        }
        System.out.println(count);

    }
}