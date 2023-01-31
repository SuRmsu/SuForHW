package Important;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class HJ43 {
    //迷宫 广度优先搜索

    public static void main(String[] args) {
        int cow = 5;
        int col = 5;
        int[][] maze = new int[][]{
                {0, 1, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0}};
        class MyPoint {
            public int x;
            public int y;
            public MyPoint parent = null;

            public MyPoint(int x, int y, MyPoint parent) {
                this.x = x;
                this.y = y;
                this.parent = parent;
            }

            @Override
            public String toString() {
                return "MyPoint{" +
                        "x=" + x +
                        ", y=" + y +
                        //", parent=" + parent +
                        '}';
            }
        }

        Stack<MyPoint> stack = new Stack<>();
        MyPoint myPoint = new MyPoint(0, 0, null);
        stack.push(myPoint);
        maze[0][0] = 1;
        while (true) {
            myPoint = stack.pop();
            //System.out.println(myPoint);
            int tempCol = myPoint.x;
            int tempCow = myPoint.y;


            if (tempCow == cow - 1 && tempCol == col - 1) {
                stack.push(myPoint);
                break;
            } else {
                if (tempCow + 1 < cow && maze[tempCol][tempCow + 1] == 0) {
                    maze[tempCol][tempCow + 1] = 1;
                    stack.push(new MyPoint(tempCol, tempCow + 1, myPoint));
                }
                if (tempCol + 1 < col && maze[tempCol + 1][tempCow] == 0) {
                    maze[tempCol + 1][tempCow] = 1;
                    stack.push(new MyPoint(tempCol + 1, tempCow, myPoint));
                }
                if (tempCow - 1 > 0 && maze[tempCol][tempCow - 1] == 0) {
                    maze[tempCol][tempCow - 1] = 1;
                    stack.push(new MyPoint(tempCol, tempCow - 1, myPoint));
                }
                if (tempCol - 1 > 0 && maze[tempCol - 1][tempCow] == 0) {
                    maze[tempCol - 1][tempCow] = 1;
                    stack.push(new MyPoint(tempCol - 1, tempCow, myPoint));
                }
            }
        }
        ArrayList<MyPoint> arraylist = new ArrayList<>();
        myPoint = stack.pop();
        while(myPoint.parent != null) {
            arraylist.add(myPoint);
            myPoint = myPoint.parent;
        }
        arraylist.add(new MyPoint(0,0,null));
        Collections.reverse(arraylist);

        System.out.println(arraylist);


    }


}
