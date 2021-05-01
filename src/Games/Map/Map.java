package Games.Map;

import java.util.Random;

/**
 * 该类是创造并保存一个x行y列的地图，其中数字代表周围雷的个数，字符“M”代表雷
 * XI、YI两个值代表初始坐标（从‘0’开始）
 * row和column两个值表示行数和列数
 */
public class Map {
    private char[][] map;
    /**
     * 构造一个新地图，对地图进行随机的初始化操作，并保证第一次点击位置不是雷
     * @param XI 第一次点击的X坐标，从'0'开始编号
     * @param YI 第一次点击的Y坐标，从'0'开始编号
     * @param row 地图总行数
     * @param column 地图总列数
     * @param num 地雷个数
     */
    public Map(int XI, int YI, int row, int column, int num) {
        this.map = new char[row][column];
        Random r = new Random();

        int[][] tem = new int[row + 2][column + 2];
        for (int i = 0; i < tem.length; i++) {
            for (int j = 0; j < tem.length; j++) {
                tem[i][j] = 0;
            }
        }

        for (int i = 0; i < num; i++) {
            int row1 = r.nextInt(row);
            int column1 = r.nextInt(column);
            if ('M' != map[row1][column1] && (row1 != XI && column1 != YI)) {
                map[row1][column1] = 'M';
                row1++;
                column1++;
                tem[row1 + 1][column1 + 1]++;
                tem[row1 + 1][column1]++;
                tem[row1 + 1][column1 - 1]++;
                tem[row1][column1 + 1]++;
                tem[row1][column1 - 1]++;
                tem[row1 - 1][column1 + 1]++;
                tem[row1 - 1][column1]++;
                tem[row1 - 1][column1 - 1]++;
            } else {
                i--;
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] != 'M') map[i][j] = (char) (tem[i + 1][j + 1] + '0');
            }
        }
    }

}
