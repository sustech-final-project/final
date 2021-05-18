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
    private static int mine1=0;         //玩家1触雷个数
    private static int mine2=0;         //玩家2触雷个数
    private static int mine3=0;         //玩家3触雷个数
    private static int mine4=0;         //玩家4触雷个数
    private static int mistake1=0;      //  mistake n 对应玩家n插旗错误次数
    private static int mistake2=0;
    private static int mistake3=0;
    private static int mistake4=0;
    private static int pointOfRo1=0;//左边为上方类似数据 机器人1 2 3分别表示简单 中等 困难模式
    private static int mineOfRo1=0;//point 机器人得分 mistake 机器人错误数 mine 机器人触雷数 最后只使用Ro1数据因为不同难度机器人可用一个数据（因为不会同时出现）方便设置方法
    private static int mistakeOfRo1=0;
    private static int pointOfRo2=0;
    private static int mineOfRo2=0;
    private static int mistakeOfRo2=0;
    private static int pointOfRo3=0;
    private static int mineOfRo3=0;

    public static int getPointOfRo1() {
        return pointOfRo1;
    }

    public static void setPointOfRo1(int pointOfRo1) {
        Data.pointOfRo1 = pointOfRo1;
    }

    public static int getMineOfRo1() {
        return mineOfRo1;
    }

    public static void setMineOfRo1(int mineOfRo1) {
        Data.mineOfRo1 = mineOfRo1;
    }

    public static int getMistakeOfRo1() {
        return mistakeOfRo1;
    }

    public static void setMistakeOfRo1(int mistakeOfRo1) {
        Data.mistakeOfRo1 = mistakeOfRo1;
    }

    public static int getPointOfRo2() {
        return pointOfRo2;
    }

    public static void setPointOfRo2(int pointOfRo2) {
        Data.pointOfRo2 = pointOfRo2;
    }

    public static int getMineOfRo2() {
        return mineOfRo2;
    }

    public static void setMineOfRo2(int mineOfRo2) {
        Data.mineOfRo2 = mineOfRo2;
    }

    public static int getMistakeOfRo2() {
        return mistakeOfRo2;
    }

    public static void setMistakeOfRo2(int mistakeOfRo2) {
        Data.mistakeOfRo2 = mistakeOfRo2;
    }

    public static int getPointOfRo3() {
        return pointOfRo3;
    }

    public static void setPointOfRo3(int pointOfRo3) {
        Data.pointOfRo3 = pointOfRo3;
    }

    public static int getMineOfRo3() {
        return mineOfRo3;
    }

    public static void setMineOfRo3(int mineOfRo3) {
        Data.mineOfRo3 = mineOfRo3;
    }

    public static int getMistakeOfRo3() {
        return mistakeOfRo3;
    }

    public static void setMistakeOfRo3(int mistakeOfRo3) {
        Data.mistakeOfRo3 = mistakeOfRo3;
    }

    public static String getNameEz() {
        return nameEz;
    }

    public static void setNameEz(String nameEz) {
        Data.nameEz = nameEz;
    }

    public static String getNameMd() {
        return nameMd;
    }

    public static void setNameMd(String nameMd) {
        Data.nameMd = nameMd;
    }

    public static String getNameHa() {
        return nameHa;
    }

    public static void setNameHa(String nameHa) {
        Data.nameHa = nameHa;
    }

    private static int mistakeOfRo3=0;
    private static String nameEz ="容易被击败的玩家";
    private static String nameMd ="不太容易被击败的玩家";
    private static String nameHa ="强大的机器人玩家";

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

    public static int getMistake3() {
        return mistake3;
    }

    public static void setMistake3(int mistake3) {
        Data.mistake3 = mistake3;
    }

    public static int getMistake4() {
        return mistake4;
    }

    public static void setMistake4(int mistake4) {
        Data.mistake4 = mistake4;
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
