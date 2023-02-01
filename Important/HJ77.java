package Important;

import java.util.*;

public class HJ77 {
    static ArrayList<String> arraylist = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int count = input.nextInt();
            int[] nums = new int[count];
            for (int i = 0; i < count; i++) {
                nums[i] = input.nextInt();
            }
            Stack<Integer> stack = new Stack<Integer>();
            arraylist.clear();
            process(nums, 0, stack, "", 0);
            Collections.sort(arraylist);
            for (String s : arraylist) {
                System.out.println(s);
            }
        }

    }

    public static void process(int[] nums, int start, Stack<Integer> stack, String storage, int end) {
        if (end >= nums.length) {
            arraylist.add(storage);
            return;
        } else {
            if (!stack.isEmpty()) {
                int temp = stack.pop();
                process(nums, start, stack, storage + (temp + 1) + " " , end + 1);
                stack.push(temp);
            }
            if (start < nums.length) {
                stack.push(nums[start]);
                process(nums, start + 1, stack, storage, end);
                stack.pop();
            }
        }


    }
}
