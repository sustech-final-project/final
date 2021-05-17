package Games.Map;

/**
 * 作者：戴郭轶
 * 日期：2021.5.4
 * 本程序用于存储一些数据
 *
 */
public class Data {



    private static int[][] HasClicked;//判断位置是否被遍历
    private static int[][] tool;      //道具
    private static int point1=0;      //玩家1的分数
    private static int point2=0;      //玩家2的分数
    private static int point3=0;      //玩家3的分数
    private static int point4=0;      //玩家4的分数
    private static int order=1;       //下一步的次序
    private static int mines = 0;     //雷的个数
    private static int click =0 ;     //每回合点击次数
    private static int players = 0;   //玩家个数
    private static String name1="";   //玩家1的姓名
    private static String name2="";   //玩家2的姓名
    private static String name3="";   //玩家3的姓名
    private static String name4="";   //玩家4的姓名
    private static int mine1;         //玩家1触雷个数
    private static int mine2;         //玩家2触雷个数
    private static int mine3;         //玩家3触雷个数
    private static int mine4;         //玩家4触雷个数


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

    public static int[][] getTool() {
        return tool;
    }

    public static void setTool(int[][] tool) {
        Data.tool = tool;
    }

    public static String getName3() {
        return name3;
    }

    public static void setName3(String name3) {
        Data.name3 = name3;
    }

    public static String getName4() {
        return name4;
    }

    public static void setName4(String name4) {
        Data.name4 = name4;
    }

    public static int getMine1() {
        return mine1;
    }

    public static void setMine1(int mine1) {
        Data.mine1 = mine1;
    }

    public static int getMine2() {
        return mine2;
    }

    public static void setMine2(int mine2) {
        Data.mine2 = mine2;
    }

    public static int getMine3() {
        return mine3;
    }

    public static void setMine3(int mine3) {
        Data.mine3 = mine3;
    }

    public static int getMine4() {
        return mine4;
    }

    public static void setMine4(int mine4) {
        Data.mine4 = mine4;
    }

    public static int getPoint3() {
        return point3;
    }

    public static void setPoint3(int point3) {
        Data.point3 = point3;
    }

    public static int getPoint4() {
        return point4;
    }

    public static void setPoint4(int point4) {
        Data.point4 = point4;
    }
}
