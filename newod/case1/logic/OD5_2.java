package newod.case1.logic;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 题目描述
 * Excel工作表中对选定区域的数值进行统计的功能非常实用。
 *
 * 仿照Excel的这个功能，请对给定表格中选中区域中的单元格进行求和统计，并输出统计结果。
 *
 * 为简化计算，假设当前输入中每个单元格内容仅为数字或公式两种。
 *
 * 如果为数字，则是一个非负整数，形如3、77
 *
 * 如果为公式，则固定以=开头，且仅包含下面三种情况：
 *
 * 等于某单元格的值，例如=B12
 * 两个单元格的双目运算（仅为+或-），形如=C1-C2、C3+B2
 * 单元格和数字的双目运算（仅为+或-），形如=B1+1、100-B2
 * 注意：
 *
 * 公式内容都是合法的，例如不存在，=C+1、=C1-C2+B3,=5、=3+5
 * 不存在循环引用，例如A1=B1+C1、C1=A1+B2
 * 内容中不存在空格、括号
 * 输入描述
 * 第一行两个整数rows cols，表示给定表格区域的行数和列数，1<=rows<=20，1<=cols<=26。
 * 接下来rows行，每行cols个以空格分隔的字符串，表示给定表格values的单元格内容。
 * 最后一行输入的字符串，表示给定的选中区域，形如A1:C2。
 *
 * 输出描述
 * 一个整数，表示给定选中区域各单元格中数字的累加总和，范围-2,147,483,648 ~ 2,147,483,647
 *
 * 备注
 * 表格的行号1~20，列号A~Z，例如单元格B3对应values[2][1]。
 *
 * 解法：逻辑解法 1. 转换Excel坐标 2. 判断操作（递归调用函数）
 * 需手动写一次
 * 注意正则的用法
 */
public class OD5_2 {

    static Pattern p = Pattern.compile("^([A-Z])(\\d+)$");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();

        String[][] matrix = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.next();
            }
        }

        String[] range = sc.next().split(":");

        System.out.println(getResult(matrix, rows, cols, range[0], range[1]));
    }

    /**
     * @param matrix 给定表格区域
     * @param rows 给定表格区域的行数
     * @param cols 给定表格区域的列数
     * @param start 选中区域的左上角位置
     * @param end 选中区域的右下角位置
     * @return 求和选中区域所有数的和
     */
    public static int getResult(String[][] matrix, int rows, int cols, String start, String end) {
        // 正则p于分解出Excel单元格位置坐标（形式如A1，B2，C3）的列和行，注意字母是列号，数字是行号
        Matcher m1 = p.matcher(start);
        Matcher m2 = p.matcher(end);

        if (m1.find() && m2.find()) {
            // 选中区域左上角坐标的解析
            int col_start = m1.group(1).charAt(0);
            int row_start = Integer.parseInt(m1.group(2));

            // 选中区域右下角坐标的解析
            int col_end = m2.group(1).charAt(0);
            int row_end = Integer.parseInt(m2.group(2));

            // ans保存选中区域所有数的和
            int ans = 0;
            // 从左上角坐标遍历到右下角坐标
            for (int j = col_start; j <= col_end; j++) {
                for (int i = row_start; i <= row_end; i++) {
                    char c = (char) j;
                    ans += getCell(c + "" + i, matrix);
                }
            }
            return ans;
        }

        // 异常情况，应该不会走到这步
        return 0;
    }

    /**
     * @param pos 指定Excel表格坐标，pos形式如A1，B2，C3
     * @param matrix Excel给定表格区域
     * @return 指定坐标pos的单元格内的值
     */
    public static int getCell(String pos, String[][] matrix) {
        Matcher m = p.matcher(pos);
        if (m.find()) {
            // 题目说列号取值A~Z，起始列A对应的码值65，A列等价于matrix矩阵的第0列
            int col = m.group(1).charAt(0) - 65;
            // 起始行1，等价于matrix矩阵的第0行
            int row = Integer.parseInt(m.group(2)) - 1;

            String cell = matrix[row][col];
            // 如果单元格内容以=开头，则为公式
            if (cell.startsWith("=")) {
                // 公式有三种情况
                // 等于某单元格的值，例如=B12
                // 两个单元格的双目运算（仅为+或-），形如=C1-C2、C3+B2
                String[] combine = cell.split("[\\=\\+\\-]");

                String cell1 = combine[1];

                // 对于 =A1 这种情况，cell2没有值
                String cell2 = null;
                if (combine.length > 2) {
                    cell2 = combine[2];
                }

                int cell1_val;
                if (cell1.matches("^-?\\d+$")) {
                    // 如果cell解析出来是值，则直接使用
                    cell1_val = Integer.parseInt(cell1);
                } else {
                    // 如果cell解析出来不是值，那就是Excel坐标
                    cell1_val = getCell(cell1, matrix);
                }

                // 同上
                int cell2_val;
                if (cell2 != null) {
                    if (cell2.matches("^-?\\d+$")) {
                        cell2_val = Integer.parseInt(cell2);
                    } else {
                        cell2_val = getCell(cell2, matrix);
                    }
                } else {
                    cell2_val = 0;
                }

                // 如果cell1和cell2是相加
                if (cell.contains("+")) {
                    matrix[row][col] = cell1_val + cell2_val + "";
                }
                // 如果cell1和cell2是相减
                else if (cell.contains("-")) {
                    matrix[row][col] = cell1_val - cell2_val + "";
                }
                // 如果没有运算，那就只可能是单值，直接使用
                else {
                    matrix[row][col] = cell1_val + "";
                }
            }

            // 如果单元格内容以=开头，则以上逻辑会将单元格内容更新为数值
            // 如果单元格内容不以=开头，则为可以直接使用的数值
            return Integer.parseInt(matrix[row][col]);
        }

        // 异常情况，应该不会走到这步
        return 0;
    }
}
