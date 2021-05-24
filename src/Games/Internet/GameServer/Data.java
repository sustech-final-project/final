package Games.Internet.GameServer;

import java.util.ArrayList;

/**
 * 作者：戴郭轶
 * 日期：2021.5.4
 * 本程序用于存储一些数据
 *
 */
public class Data {

    private static Map map;
    static int id = 1;
    static String[] name1 = new String[4];

    private static int[][] HasClicked; //判断位置是否被遍历
    public static int point1=0;      //玩家1的分数
    public static int point2=0;      //玩家2的分数
 //   private static int order=1;       //下一步的次序
    private static ArrayList<String> history = new ArrayList<>();

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        Data.map = map;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Data.id = id;
    }

    public static String[] getName1() {
        return name1;
    }

    public static void setName1(String[] name1) {
        Data.name1 = name1;
    }

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
        return HasClicked[row][column];
    }

    /**
     * 遍历过后修改HasClicked
     * @param row      点击的行数
     * @param column   点击的列数
     */
    public static void HasClicked(int row,int column) {
        HasClicked[row][column] = 1;
    }

    /**
     * 第a步，从1开始
     * @param a
     * @return
     */
    public static String gethistory(int a){
        return history.get(a-1);
    }

    /**
     * history格式：
     * 玩家编号，点击行，列，点击格式，分数，错误数量
     * @param str
     */
    public static void addhistory(String str){
        history.add(str);
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

    public static void initializeData(){
        HasClicked = new int[16][16];
        //tool = new int[row][column];
    }
//    public static int getOrder() {
//        return order;
//    }
//
//    public static void setOrder(int order1) {
//        order = order1;
//    }

    public static ArrayList<String> getHistory() {
        return history;
    }

    public static void setHistory(ArrayList<String> history) {
        Data.history = history;
    }


}
