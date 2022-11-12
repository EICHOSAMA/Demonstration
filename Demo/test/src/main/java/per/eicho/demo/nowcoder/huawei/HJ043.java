package per.eicho.demo.nowcoder.huawei;

import java.util.LinkedList;
import java.util.Scanner;

public final class HJ043 {    
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int[] nm = new int[2];
        int nmp = 0;
        int[][] grid = null;
        int size = -1;
        int gridP = 0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            if (nmp < 2) {
                nm[nmp++] = in.nextInt();
                if (nmp == 2) {
                    grid = new int[nm[0]][nm[1]];
                    size = nm[0] * nm[1];
                }
                continue;
            }
            
            // load grid.
            grid[gridP / nm[1]][gridP % nm[1]] = in.nextInt();
            if (++gridP == size) {
                processInput(nm[0], nm[1], grid);

                nmp = 0;
                grid = null;
                size = -1;
                gridP = 0;
            }
        }

        in.close();
    }

    private static void processInput(int n, int m, int[][] grid) {
        // grid它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，
        // 只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的路线。
        // 入口点为[0,0],既第一格是可以走的路。
        LinkedList<Integer> ops = new LinkedList<>();
        search(0, 0, grid, ops);

        int x = 0, y = 0;
        println(x, y);
        for (int op : ops) {
            final int[] direction = directions[op];
            println(x += direction[0], y += direction[1]);
        }
    }

    private static void println(int x, int y) {
        System.out.println("(" + x + ',' + y + ')');
    }

    private static int[][] directions = new int[][]{
        new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1}, new int[]{-1, 0}  
    };

    private static boolean search(int x, int y, int[][] grid, LinkedList<Integer> ops) {
        if (x == grid.length - 1 && y == grid[0].length - 1) return true;
        
        for (int i = 0; i < directions.length; i++) {
            final int[] direction = directions[i];
            final int nx = x + direction[0];
            if (nx < 0 || nx >= grid.length) continue;
            final int ny = y + direction[1];
            if (ny < 0 || ny >= grid[0].length) continue;

            if (grid[nx][ny] == 1) continue;
            grid[nx][ny] = 1;
            ops.addLast(i);
            if (search(nx, ny, grid, ops)) return true;
            ops.removeLast();
            grid[nx][ny] = 0;
        }
        return false;
    }
}
