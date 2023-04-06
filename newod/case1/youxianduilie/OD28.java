package newod.case1.youxianduilie;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 题目描述
 * 现有一个CPU和一些任务需要处理，已提前获知每个任务的任务ID、优先级、所需执行时间和到达时间。
 * CPU同时只能运行一个任务，请编写一个任务调度程序，采用“可抢占优先权调度”调度算法进行任务调度，规则如下：
 * <p>
 * 如果一个任务到来时，CPU是空闲的，则CPU可以运行该任务直到任务执行完毕。
 * 但是如果运行中有一个更高优先级的任务到来，则CPU必须暂停当前任务去运行这个优先级更高的任务；
 * 如果一个任务到来时，CPU正在运行一个比他优先级更高的任务时，信道大的任务必须等待；
 * 当CPU空闲时，如果还有任务在等待，CPU会从这些任务中选择一个优先级最高的任务执行，相同优先级的任务选择到达时间最早的任务。
 * 输入描述
 * 输入有若干行，每一行有四个数字（均小于10^8）,分别为任务ID，任务优先级，执行时间和到达时间。
 * <p>
 * 每个任务的任务ID不同，优先级数字越大优先级越高，并且相同优先级的任务不会同时到达。
 * <p>
 * 输入的任务已按照到达时间从小到大排序，并且保证在任何时间，处于等待的任务不超过10000个。
 * <p>
 * 输出描述
 * 按照任务执行结束的顺序
 * <p>
 * 解法：
 * 1、等待队列的实现
 * 2、CPU的任务执行逻辑
 *
 * CPU执行某个任务时，如果有新任务加入，则我们应该比较正在执行的任务和新任务的优先级，
 *
 * 如果新任务优先级较高，则应该将正在执行的任务撤出，加入到等待队列中，然后执行新任务。
 * 如果新任务优先级不高于正在执行的任务，则新任务进入等待队列，继续执行当前任务。
 * CPU空转期间，应该检查等待队列是否有任务，并取出最高优先级任务执行。
 *
 * 当CPU空闲时，如果还有任务在等待，CPU会从这些任务中选择一个优先级最高的任务执行，相同优先级的任务选择到达时间最早的任务。
 * 而上面逻辑中遗漏考虑了相同优先级时，按照到达时间为第二优先级来安排任务执行的场景。已修复。
 */
public class OD28 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LinkedList<Task> list = new LinkedList<>();

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if ("".equals(s)) break;
            Integer[] arr = Arrays.stream(s.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
            Task task = new Task(arr[0], arr[1], arr[2], arr[3]);
            list.add(task);
        }

        getResult(list);
    }

    /**
     * @param tasks 任务列表*/
    public static void getResult(LinkedList<Task> tasks) {
        PriorityQueue<Task> pq =
                new PriorityQueue<>(
                        (a, b) -> a.priority != b.priority ? b.priority - a.priority : a.arrived - b.arrived);

        pq.offer(tasks.removeFirst());
        int curTime = pq.peek().arrived; // curTime记录当前时刻

        while (tasks.size() > 0) {
            Task curtTask = pq.peek(); // 当前正在运行的任务curtTask
            Task nextTask = tasks.removeFirst(); // 下一个任务nextTask

            int curtTask_endTime = curTime + curtTask.need; // 当前正在运行任务的“理想”结束时间

            if (curtTask_endTime > nextTask.arrived) { // 如果当前正在运行任务的理想结束时间  超过了  下一个任务的开始时间
                curtTask.need -= nextTask.arrived - curTime; // 先不看优先级，先将当前任务可以运行的时间减去
                curTime = nextTask.arrived;
            } else { // 如果当前正在运行任务的理想结束时间  没有超过  下一个任务的开始时间，则当前任务可以执行完
                pq.poll(); // 当前任务出队
                System.out.println(curtTask.id + " " + curtTask_endTime); // 打印执行完的任务的id和结束时间
                curTime = curtTask_endTime;

                if (nextTask.arrived > curtTask_endTime) { // 如果当前任务结束时，下一次任务还没有达到，那么存在CPU空转(即idle)
                    while (pq.size() > 0) { // 此时，我们应该从优先队列中取出最优先的任务出来执行，逻辑同上
                        Task idleTask = pq.peek();
                        int idleTask_endTime = curTime + idleTask.need;

                        if (idleTask_endTime > nextTask.arrived) {
                            idleTask.need -= nextTask.arrived - curTime;
                            break;
                        } else {
                            pq.poll();
                            System.out.println(idleTask.id + " " + idleTask_endTime);
                            curTime = idleTask_endTime;
                        }
                    }
                    curTime = nextTask.arrived;
                }
            }

            pq.offer(nextTask);
        }

        // 所有任务都加入优先队列后，我们就可以按照优先队列的安排，顺序取出任务来执行了
        while (pq.size() > 0) {
            Task pollTask = pq.poll();
            int pollTask_endTime = curTime + pollTask.need;
            System.out.println(pollTask.id + " " + pollTask_endTime);
            curTime = pollTask_endTime;
        }
    }
}

class Task {
    int id; // 任务id
    int priority; // 任务优先级
    int need; // 任务执行时长
    int arrived; // 任务到达时刻

    public Task(int id, int priority, int need, int arrived) {
        this.id = id;
        this.priority = priority;
        this.need = need;
        this.arrived = arrived;
    }
}