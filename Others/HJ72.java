package Others;
public class HJ72 {

    public static void main(String[] args) {
        int money = 100;
        for (int x = 0; x <= money / 5; x++) {
            for (int y = 0; 5 * x + 3 * y <= money; y++) {
                for (int z = 0; 15 * x + 9 * y + z <= 3 * money; z += 3) {
                    if (x + y + z == 100 && 15 * x + 9 * y + z == 3 * money) {
                        System.out.println(x + " " + y + " " + z);
                    }
                }
            }
        }
    }
}
