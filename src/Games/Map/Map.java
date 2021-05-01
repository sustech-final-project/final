package Games.Map;

import java.io.*;
import java.util.*;

/**
 * 该类是创造并保存一个x行y列的地图，其中数字代表周围雷的个数，字符“M”代表雷
 * XI、YI两个值代表初始坐标（从‘0’开始）
 * row和column两个值表示行数和列数
 */
public class Map {
    private char[][] map;

    public char[][] getMap() {
        return map;
    }

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

    /**
     * 将构造的地图输入至:"mine.txt"文件中
     * 文件格式为：第一行为两个整数，分别代表了行数和列数
     *          下面的row行column列为雷区分布
     * @param row 地图总行数
     * @param column 地图总列数
     */
    public void mapwritter(int row,int column){
        try{
            File file = new File("mine.txt");
            PrintStream writer = new PrintStream(new FileOutputStream(file));
            writer.println(row + " "+column);
            for (int i =0;i<row;i++){
                for (int j=0;j<column;j++){
                    writer.write(map[i][j]);
                }
                writer.println();
            }
        }catch (IOException e ){
            System.out.println("opps");
        }
    }


}
