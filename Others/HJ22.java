package Others;

import java.util.Scanner;

/**
 * 脑筋急转弯，每有两个空瓶子直接和老板要一瓶，喝完正好还他
 */
public class HJ22 {
    public void mySolution() throws Exception{
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int temp = sc.nextInt();
            if ( temp != 0) {
                System.out.println(temp / 2);
            }

        }
    }
}
