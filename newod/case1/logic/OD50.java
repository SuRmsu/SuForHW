package newod.case1.logic;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 货币单位换算
 * 题目描述
 * 记账本上记录了若干条多国货币金额，需要转换成人民币分（fen），汇总后输出。
 * 每行记录一条金额，金额带有货币单位，格式为数字+单位，可能是单独元，或者单独分，或者元与分的组合。
 * 要求将这些货币全部换算成人民币分（fen）后进行汇总，汇总结果仅保留整数，小数部分舍弃。
 * 元和分的换算关系都是1:100，如下：
 *
 * 1CNY=100fen（1元=100分）
 * 1HKD=100cents（1港元=100港分）
 * 1JPY=100sen（1日元=100仙）
 * 1EUR=100eurocents（1欧元=100欧分）
 * 1GBP=100pence（1英镑=100便士）
 *
 * 汇率表如下：
 *
 * CNY	JPY	HKD	EUR	GBP
 * 100	1825	123	14	12
 * 即：100CNY = 1825JPY = 123HKD = 14EUR = 12GBP
 *
 * 输入描述
 * 第一行输入为N，N表示记录数。0<N<100
 *
 * 之后N行，每行表示一条货币记录，且该行只会是一种货币。
 *
 * 输出描述
 * 将每行货币转换成人民币分（fen）后汇总求和，只保留整数部分。
 * 输出格式只有整数数字，不带小数，不带单位。
 *
 * 解法：
 * String reg = "(\\d+)((CNY)|(JPY)|(HKD)|(EUR)|(GBP)|(fen)|(cents)|(sen)|(eurocents)|(pence))"
 * Pattern p = Pattern.compile(reg);
 * Matcher m = p.matcher(s);
 * m.find();
 * m.group(1);
 * m.group(2);
 */
public class OD50 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }

        System.out.println(getResult(arr));
    }

    public static int getResult(String[] arr) {
        String reg = "(\\d+)((CNY)|(JPY)|(HKD)|(EUR)|(GBP)|(fen)|(cents)|(sen)|(eurocents)|(pence))";

        // 用来存储换算表
        HashMap<String, Double> exchange = new HashMap<>();
        exchange.put("CNY", 100.0);
        exchange.put("JPY", 100.0 / 1825 * 100);
        exchange.put("HKD", 100.0 / 123 * 100);
        exchange.put("EUR", 100.0 / 14 * 100);
        exchange.put("GBP", 100.0 / 12 * 100);
        exchange.put("fen", 1.0);
        exchange.put("cents", 100.0 / 123);
        exchange.put("sen", 100.0 / 1825);
        exchange.put("eurocents", 100.0 / 14);
        exchange.put("pence", 100.0 / 12);

        double ans = 0.0;
        Pattern p = Pattern.compile(reg);

        for (String s : arr) {
            Matcher m = p.matcher(s);
            while (true) {
                if (m.find()) {
                    Double amount = Double.parseDouble(m.group(1));
                    String unit = m.group(2);
                    ans += amount * exchange.get(unit);
                } else {
                    break;
                }
            }
        }

        return (int) ans;
    }
}
