package Games.Map;

import java.util.Arrays;
import java.util.Scanner;

public class SweeperTest_Normal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("行,列,雷");
        int row = sc.nextInt();
        int column = sc.nextInt();
        int num = sc.nextInt();
        System.out.println("第一个点");
        int X = sc.nextInt();
        int Y = sc.nextInt();
        Map map = new Map(X,Y,row,column,num);
        char[][] board = new char[row][column];
        System.out.println("其他");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;
            }
        }
        while (map.getMap(X,Y) != 'M' || board[X][Y] == 'F') {
            if (board[X][Y] != 'F')board[X][Y] = map.getMap(X, Y);
            for (char[] chars : board) {
                System.out.println(Arrays.toString(chars));
            }
            System.out.println("continue.");
            X = sc.nextInt();
            Y = sc.nextInt();
            System.out.println("F?(\"1\" means yes)");
            if (sc.nextInt() == 1) board[X][Y] = 'F';
        }
        System.out.println("false");
    }
}
