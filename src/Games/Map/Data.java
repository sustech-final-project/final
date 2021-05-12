package Games.Map;

/**
 * 作者：戴郭轶
 * 日期：2021.5.4
 * 本程序用于存储一些数据
 *
 */
public class Data {

    private static int[][] HasClicked; //判断位置是否被遍历
    private static int point1=0;      //玩家1的分数
    private static int point2=0;      //玩家2的分数
    private static int order=1;       //下一步的次序
    private static int mines = 0;     //雷的个数
    private static int click =0 ;     //每回合点击次数
    private static int players = 0;   //玩家个数
    private static String name1="";   //玩家1的姓名
    private static String name2="";   //玩家2的姓名



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

    public static String getName1() {
        return name1;
    }

    public static void setName1(String name1) {
        Data.name1 = name1;
    }

    public static String getName2() {
        return name2;
    }

    public static void setName2(String name2) {
        Data.name2 = name2;
    }

    public static int getMines() {
        return mines;
    }

    public static void setMines(int mines) {
        Data.mines = mines;
    }

    public static int getClick() {
        return click;
    }

    public static void setClick(int click) {
        Data.click = click;
    }

    public static int getPlayers() {
        return players;
    }

    public static void setPlayers(int players) {
        Data.players = players;
    }
}
