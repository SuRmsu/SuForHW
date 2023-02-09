package Important;

import java.util.Scanner;

public class HJ64 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            String instructions = sc.next();
            process(num,instructions);
        }
    }

    public static void process(int num, String instructions) {
        // sizeOfList = Math.min(4,num)
        int sizeOfList = num > 4 ? 4 : num;
        int current = 1;
        int indexOfList = 1;
        for (int i = 0; i < instructions.length(); i++) {
            if (instructions.charAt(i) == 'U') {
                //特殊情况第一个往上
                if ((current == 1 && indexOfList == 1)) {
                    current = num;
                    indexOfList = num + 1 -sizeOfList;
                } else {
                    if ( current == indexOfList){
                        indexOfList--;
                    }
                    current--;
                }
            } else {
                //特殊情况最后一个往下
                if ((current == num  && indexOfList == num + 1  - sizeOfList)) {
                    indexOfList = 1;
                    current = 1;
                } else {
                    current++;
                    if (current == indexOfList + sizeOfList) {
                        indexOfList++;
                    }

                }
            }
        }

        for (int i = indexOfList; i < indexOfList + sizeOfList; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(current);

    }


}
