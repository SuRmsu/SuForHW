package String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HJ26 {
    /**
     * 暴力算法，分别储存每个字符按顺序存放
     * 还有很多等效方法，本质上都是时间和空间的互换
     * @throws Exception
     */
    public void mySolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] surm = new char[26][20];//此处能不能改一改
        //ArrayList<ArrayList<character>> cutPlan = new ArrayList<>(); 改成这样可行码？c.get(i)).get(j)应该可行
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 20; j++) {
                surm[i][j] = '\n';
            }
        }
        char[] input = br.readLine().toCharArray();
        for (int i = 0; i < input.length; i++) {
            char temp = Character.toLowerCase(input[i]);
            if (temp >= 'a' && temp <= 'z') {
                for (int j = 0; j < 20; j++) {
                    if (surm[temp - 'a'][j] == '\n') {
                        surm[temp - 'a'][j] = input[i];
                        input[i] = '\n';
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < input.length; i++) {
            char temp = Character.toLowerCase(input[i]);
            if (temp == '\n') {
                int flag = 0;
                for (int j = 0; j < 26; j++) {
                    if (flag == 1) {
                        break;
                    }
                    for (int h = 0; h < 20; h++) {
                        if (surm[j][h] == '\n') {
                            continue;
                        } else {
                            input[i] = surm[j][h];
                            surm[j][h] = '\n';
                            flag = 1;
                            break;
                        }
                    }
                }
            }
        }
        System.out.print(String.valueOf(input));


    }

    /**
     * 重写比较器，忽略大小写排序就可以完成按照输入顺序排序
     * @throws Exception
     */
    public void secondSolution() throws Exception {
        String str = "This is the input";

        // 先将英文字母收集起来
        List<Character> letters = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                letters.add(ch);
            }
        }
        // 将英文字母先排序好
        letters.sort(new Comparator<Character>() {
            public int compare(Character o1, Character o2) {
                return Character.toLowerCase(o1) - Character.toLowerCase(o2);
            }
        });
        // 若是非英文字母则直接添加
        StringBuilder result = new StringBuilder();
        for (int i = 0, j = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                result.append(letters.get(j++));
            }
            else {
                result.append(str.charAt(i));
            }
        }
        System.out.println( result.toString());
    }
}
