package Games.Map;

import java.io.*;
import java.util.*;

/**
 * 该类是创造并保存一个x行y列的地图，其中数字代表周围雷的个数，字符“M”代表雷
 * XI、YI两个值代表初始坐标（从‘0’开始）
 * row和column两个值表示行数和列数
 */
public class Map {
    private static char[][] map;
    private int row, column, num;

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getNum() {
        return num;
    }

    public static void setMap(char[][] map1) {
        map = map1;
    }

    public static char[][] getMap() {
        return map;
    }
    public char[][] geTMap() {
        return map;
    }
    public static char getMapchar(int row,int column){
        return map[row][column];
    }



    public char getMap(int X, int Y) {
        return map[X][Y];
    }


    public Map(int row, int column, int num) {
        map = new char[row][column];
        this.row = row;
        this.column = column;
        this.num = num;
    }

    public Map(String fileName) {
        mapReader(fileName);
        row = map.length;
        column = map[0].length;
    }

    public Map() {
        //mapReader();
    }

    /**
     * 作者：杨清淞<br/>
     * 日期：2021.5.1<br/>
     * 构造一个新地图，对地图进行随机的初始化操作，并保证第一次点击位置不是雷<br/>
     *
     * @param XI     第一次点击的X坐标，从'0'开始编号
     * @param YI     第一次点击的Y坐标，从'0'开始编号
     */
    public char[][] createMap(int XI, int YI) {
        Random r = new Random();

        int[][] tem = new int[row + 2][column + 2];
        for (int i = 0; i < tem.length; i++) {
            for (int j = 0; j < tem[0].length; j++) {
                tem[i][j] = 0;
            }
        }

        for (int i = 0; i < num; i++) {
            int row1 = r.nextInt(row);
            int column1 = r.nextInt(column);
            if ('M' != map[row1][column1] && !((row1 <= 1 + XI && row1 >= XI - 1) && (column1 <= YI + 1 && column >= YI - 1))) {
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
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != 'M') map[i][j] = (char) (tem[i + 1][j + 1] + '0');
            }
        }
        return map;
    }

    /**
     * 作者： 戴郭轶
     * 日期：2021.5.1
     * 将构造的地图输入至:"mine.txt"文件中
     * 文件格式为：第一行为两个整数，分别代表了行数和列数
     * 下面的row行column列为雷区分布
     * 默认路径为/buffer/*.txt；
     * 调用方法为mapWriter("*")
     * 若调用mapWriter()，则默认为mine.txt
     */
    public void mapWriter() {
        mapWriter("mine.txt");
    }

    public void mapWriter(String filename) {
        try {
            String s = "buffer/" + filename;
            File file = new File(s);
            PrintStream writer = new PrintStream(new FileOutputStream(file));
            writer.println(map.length + " " + map[0].length);
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    writer.write(map[i][j]);
                }
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("writer");
        }
    }

    /**
     * 作者： 戴郭轶
     * 日期：2021.5.1
     * 读取构造的地图
     * 文件格式为：第一行为两个整数，分别代表了行数和列数
     * 下面的row行column列为雷区分布
     * 默认路径为/buffer/*.txt；
     * 调用方法为mapReader("*")
     * 若调用mapReader(),则默认为mine.txt
     */
    public void mapReader() {
        mapReader("mine.txt");
    }

    public void mapReader(String filename) {
        try {
            String s = "buffer/" + filename;
            BufferedReader in = new BufferedReader(new FileReader(s));
            String str = in.readLine();
            String[] arr = str.split("\\s+");
            row = Integer.parseInt(arr[0]);
            column = Integer.parseInt(arr[1]);
            map = new char[row][column];
            int i = 0;
            while ((str = in.readLine()) != null && i < row) {
                for (int j = 0; j < column; j++) {
                    map[i][j] = str.charAt(j);
                }
                i++;
            }
        } catch (IOException e) {
            System.out.println("reader");
        }
    }

    public void save(int order) {
        mapWriter("mine" + order + ".txt");
    }

}
