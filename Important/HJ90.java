package Important;

import java.util.Scanner;

public class HJ90 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Boolean flag = true;
        while (sc.hasNext()) {
            String s = sc.next();
            String[] nums = s.split("\\.");
            if (nums.length == 4) {
                for (String num : nums) {
                    if(num.length() == 0 || num.length() > 3){
                        flag = false;
                        break;
                    }

                    for (char ch : num.toCharArray()) {
                        if(!Character.isDigit(ch)){
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) {
                        break;
                    }

                    if (num.charAt(0) == '0' && num.length() != 1){
                        flag = false;
                        break;
                    }
                    //不需要判断是否小于0，前面判断了不包含负号
                    if (Integer.parseInt(num) > 255) {
                        flag = false;
                        break;
                    }
                }
            } else {
                flag = false;
            }
            System.out.println(flag == true ? "YES":"NO");
        }
    }
}
