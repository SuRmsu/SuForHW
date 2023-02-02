package Important;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class HJ43 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int cow = sc.nextInt();
            int col = sc.nextInt();
            int[][] maze = new int[cow][col];
            for (int i = 0 ; i < cow ; i ++) {
                for (int j = 0 ; j < col; j++) {
                    maze[i][j] = sc.nextInt();
                }
            }
            class MyPoint {
                public int x;
                public int y;
                public MyPoint parent = null;

                public MyPoint(int x, int y, MyPoint parent) {
                    this.x = x;
                    this.y = y;
                    this.parent = parent;
                }

                public MyPoint(){};
                @Override
                public String toString() {
                    return "(" + x + "," + y + ")";
                }
            }

            Stack<MyPoint> stack = new Stack<>();
            MyPoint myPoint = new MyPoint(0, 0, null);
            stack.push(myPoint);
            maze[0][0] = 1;
            while (true) {
                myPoint = stack.pop();
                //System.out.println(myPoint);
                int tempCow = myPoint.x;
                int tempCol = myPoint.y;


                if (tempCow == cow - 1 && tempCol == col - 1) {
                    stack.push(myPoint);
                    break;
                } else {
                    if (tempCow + 1 < cow && maze[tempCow + 1][tempCol] == 0) {
                        maze[tempCow + 1][tempCol] = 1;
                        stack.push(new MyPoint(tempCow + 1, tempCol, myPoint));
                    }
                    if (tempCol + 1 < col && maze[tempCow][tempCol + 1] == 0) {
                        maze[tempCow][tempCol + 1] = 1;
                        stack.push(new MyPoint(tempCow, tempCol + 1, myPoint));
                    }
                    if (tempCol - 1 >= 0 && maze[tempCow][tempCol - 1] == 0) {
                        maze[tempCow][tempCol - 1] = 1;
                        stack.push(new MyPoint(tempCow, tempCol - 1, myPoint));
                    }
                    if (tempCow - 1 >= 0 && maze[tempCow - 1][tempCol] == 0) {
                        maze[tempCow - 1][tempCol] = 1;
                        stack.push(new MyPoint(tempCow - 1, tempCol, myPoint));
                    }
                }
            }
            ArrayList<MyPoint> arraylist = new ArrayList<>();
            myPoint = stack.pop();
            while (myPoint.parent != null) {
                arraylist.add(myPoint);
                myPoint = myPoint.parent;
            }
            arraylist.add(new MyPoint(0, 0, null));
            Collections.reverse(arraylist);
            MyPoint[] output = arraylist.toArray(new MyPoint[0]);
            for (MyPoint point : output) {
                System.out.println(point);
            }


        }
    }
}