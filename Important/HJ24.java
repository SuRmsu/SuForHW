package Important;

import java.util.Scanner;

public class HJ24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int[] storages = new int[count];
        for (int i = 0; i < count; i++) {
            storages[i] = sc.nextInt();
        }

        int[] left = new int[count];
        int[] right = new int[count];
        left[0] = 1;
        right[count - 1] = 1;
        //计算最左侧的最长递增
        for (int i = 1; i < count; i++) {
            left[i] = 1;
            for (int i1 = 0; i1 < i; i1++) {
                if (storages[i] > storages[i1]) {
                    left[i] = Math.max(left[i], left[i1] + 1);
                }
            }
        }
        //计算最右侧的最长递减
        for (int i = count - 2; i >= 0; i--) {
            right[i] = 1;
            for (int i1 = count - 1; i1 > i; i1--) {
                if (storages[i] > storages[i1])
                    right[i] = Math.max(right[i], right[i1] + 1);
            }
        }

        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = left[i] + right[i] - 1;
        }
        int max = 1;
        for (int i = 0; i < count; i++) {
            max = Math.max(result[i], max);
        }
        System.out.println(count - max);
    }
}
