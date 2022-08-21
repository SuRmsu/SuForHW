import java.util.Arrays;
import java.util.Scanner;

public class TempTestClass {
    public static void main(String[] args) throws Exception {
        Scanner a = new Scanner(System.in);
        if (a.hasNextLine()) {
            String s = a.nextLine();
            String[] temp = s.split(" ");
            int length = temp.length;
            length = temp[length - 1].length();
            System.out.println(length);


        }

    }
}
