package Games.Internet.GameServer;

/**
 * 作者：戴郭轶
 * 日期：2021.5.4
 * 本程序用于存储一些数据
 *
 */
public class Data {

    private static int[][] HasClicked; //判断位置是否被遍历
    public static int point1=0;      //玩家1的分数
    public static int point2=0;      //玩家2的分数
    private static int order=1;       //下一步的次序

    public static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename) {
        Data.filename = filename;
    }

    public static int mistake1,mistake2;
    public static String filename;

    public static int getMistake1() {
        return mistake1;
    }

    public static void setMistake1(int mistake1) {
        Data.mistake1 = mistake1;
    }

    public static int getMistake2() {
        return mistake2;
    }

    public static void setMistake2(int mistake2) {
        Data.mistake2 = mistake2;
    }

    /**
     * 本程序在初始化棋盘式需要执行
     * @param row      棋盘的行数
     * @param column   棋盘的列数
     */
    public static void setHasClicked(int row,int column) {
        HasClicked = new int[row][column];
    }

    /**
     * 获取当前是否被遍历
     * @param row     点击的行数
     * @param column  点击的列数
     * @return        返回0，未被遍历
     *                返回1，被遍历
     */

    public static int getHasClicked(int row,int column){
        return HasClicked[row-1][column-1];
    }

    /**
     * 遍历过后修改HasClicked
     * @param row      点击的行数
     * @param column   点击的列数
     */
    public static void HasClicked(int row,int column) {
        HasClicked[row-1][column-1] = 1;
    }

    public static void setHasClicked(int[][] hasClicked) {
        HasClicked = hasClicked;
    }

    public static int[][] getHasClicked() {
        return HasClicked;
    }

    public static int getPoint1() {
        return point1;
    }

    public static void setPoint1(int points) {
        point1 = points;
    }

    public static int getPoint2() {
        return point2;
    }

    public static void setPoint2(int point) {
        point2 = point;
    }

    public static int getOrder() {
        return order;
    }

    public static void setOrder(int order1) {
        order = order1;
    }


}
