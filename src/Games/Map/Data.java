package Games.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 作者：戴郭轶
 * 日期：2021.5.4
 * 本程序用于存储一些数据
 *
 * 现在不仅仅是存数据了 由于新建player类 大改此data 5.19
 *
 */
public class Data {
    private static int[][] HasClicked;//判断位置是否被遍历
    public static int zhuti = 1;
    private static int[][] tool;      //道具
    private static int order = 0;       //下一步的次序
    private static int mines = 0;     //雷的个数
    private static int click =0;     //每回合点击次数
    private static ArrayList<Player> players = new ArrayList<>();

    public static void initializeData(int row,int column){
        HasClicked = new int[row][column];
        tool = new int[row][column];
        for (int i = 0; i < tool.length; i++) {
            for (int j = 0; j < tool[0].length; j++) {
                tool[i][j] = 0;
            }
        }
        Random random = new Random();
        int r = random.nextInt(row);
        int c = random.nextInt(column);
        int check = 0;
        while (tool[r][c] == 0 && check < 3) {
            if (tool[r][c] == 0){
                tool[r][c] = 1;
                check++;
            }
            r = random.nextInt(row);
            c = random.nextInt(column);
        }
        check = 0;
        while (tool[r][c] == 0 && check < 3) {
            if (tool[r][c] == 0){
                tool[r][c] = 2;
                check++;
            }
            r = random.nextInt(row);
            c = random.nextInt(column);
        }
    }

    public static void addOrder() {
        order++;
    }

    public static void clear() {
        order =0;
        mines = 0;
        click =0;
        players = new ArrayList<>();

    }

    public static void resetorder(){
        order = 0;
    }


    //        for (int i=0;i<players.size();i++){
//            Player player = players.get(i);
//            player.score=Score[i];
//        }
//    }


    public void addPlayer(String name, String characteristic){
        players.add(new Player(name, characteristic));
    }
    public void addPlayer(String name,String characteristic, int score,int mistake){
        players.add(new Player(name, characteristic, score, mistake));
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        Data.players = players;
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

    public static boolean isAllClicked() {
        for (int[] ints : HasClicked) {
            for (int j = 0; j < HasClicked[0].length; j++) {
                if (ints[j] == 0) return false;
            }
        }
        return true;
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
        try {
            return HasClicked[row][column];
        } catch (Exception e){
            return 2;
        }
    }

    /**
     * 遍历过后修改HasClicked
     * @param row      点击的行数
     * @param column   点击的列数
     */
    public static void HasClicked(int row,int column) {
        HasClicked[row][column] = 1;
    }

    public static void setHasClicked(int[][] hasClicked) {
        HasClicked = hasClicked;
    }

    public static int[][] getHasClicked() {
        return HasClicked;
    }



    public static int getOrder() {
        return order;
    }

    public static void setOrder(int order1) {
        order = order1;
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

    public static int[][] getTool() {
        return tool;
    }

    public static int getTool(int r , int c){
        return tool[r][c];
    }

    public static void setTool(int[][] tool) {
        Data.tool = tool;
    }
}

