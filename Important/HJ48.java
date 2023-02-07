package Important;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class HJ48 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int count = sc.nextInt();
            LinkedList<Integer> linkedList = new LinkedList<Integer>();
            linkedList.add(sc.nextInt());
            for (int i = 0; i < count - 1; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                linkedList.add(linkedList.indexOf(b) + 1,a);
            }
            linkedList.remove((linkedList.indexOf(sc.nextInt())));

            Iterator<Integer> it = linkedList.iterator();
            while (it.hasNext()) {
                System.out.print(it.next() + " ");
            }
        }
    }
}
