package Games.Map;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * 作者：戴郭轶
 * 日期：2021.5.4
 * 本程序用于执行存档功能
 * 存档格式为：
 *   第一行为地图的行数、列数、雷数、玩家个数、当前回合数、每回合点击次数
 *   第二行开始为各位玩家的姓名、分数、错误数量
 *   下面分别问地图与遍历情况和道具
 */
public class Save<choosenFile> extends Map {
    public static void Save() {
        Save("save.txt");
    }

    public static void Save(String filename) {
        int a[][] = Data.getHasClicked();
        char map1[][] = getMap();
        int b[][] = Data.kong;
        ArrayList<Player> players = Data.getPlayers();
        int playernumber = players.size();
//        int point1 = Data.getPoint1();
//        int point2 = Data.getPoint2();
        try {
            String s = "save/" + filename;
            File file = new File(s);
            PrintStream writer = new PrintStream(new FileOutputStream(file));
            writer.println(map1.length + " " + map1[0].length + " " + Data.getMines() + " " + playernumber + " " + Data.getOrder() + " " + Data.getClick());
//            writer.println(point1 + " " + point2);
            for (int i = 0; i < playernumber; i++) {
                Player player = players.get(i);
                writer.println(player.name + " " + player.score + " " + player.mistake+" "+player.characteristic);
            }
//            for (int j=0;j<map1[0].length;j++){
//                writer.write(0);
//            }
//            writer.println();
//            writer.println(order);
            for (int i = 0; i < map1.length; i++) {
                for (int j = 0; j < map1[i].length; j++) {
                    writer.write(map1[i][j]);
                }
                writer.println();
            }
            for (int i = 0; i < map1.length; i++) {
                for (int j = 0; j < map1[i].length; j++) {
                    writer.print(a[i][j]);
                }
                writer.println();
            }
            for (int i = 0; i < map1.length; i++) {
                for (int j = 0; j < map1[i].length; j++) {
                    writer.print(b[i][j]);
                }
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("writer");
        }
    }

    /**
     * 本程序为读取存档程序，读取格式与上相同
     */
    public static boolean Reader() {
        return Reader("save.txt");
    }

    public static boolean Reader(String filename) {
        try {
            String s = "buffer/" + filename;
            BufferedReader in = new BufferedReader(new FileReader(s));
            String str = in.readLine();
            String[] arr = str.split("\\s+");
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[1]);
            int mines = Integer.parseInt(arr[2]);
            int platernumbers = Integer.parseInt(arr[3]);
            int order = Integer.parseInt(arr[4]);
            int clicks = Integer.parseInt(arr[5]);
            ArrayList<Player> players = new ArrayList<>();
            for (int i = 0; i < platernumbers; i++) {
                str = in.readLine();
                arr = str.split("\\s+");
                Player player = new Player(arr[0], "No characteristic", Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
                players.add(player);
            }
//            str = in.readLine();
//            String[] arr1 = str.split("\\s+");
//            int point1 = Integer.parseInt(arr1[0]);
//            int point2 = Integer.parseInt(arr1[1]);
            str = in.readLine();
//            int order = Data.getOrder();
            char[][] map1 = new char[a][b];
            int[][] HasClicked1 = new int[a][b];
            int[][] tool = new int[a][b];
            int i = 0;
            while ((str = in.readLine()) != null && i < a) {
                for (int j = 0; j < b; j++) {
                    map1[i][j] = str.charAt(j);
                }
                i++;
            }
            i = 0;
            while ((str = in.readLine()) != null && i < a) {
                for (int j = 0; j < b; j++) {
                    HasClicked1[i][j] = str.charAt(j) - '0';
                }
                i++;
            }
            i = 0;
            while ((str = in.readLine()) != null && i < a) {
                for (int j = 0; j < b; j++) {
                    tool[i][j] = str.charAt(j) - '0';
                }
                i++;
            }

            setMap(map1);
//            Data.setPoint1(point1);
//            Data.setPoint2(point2);
            Data.setMines(mines);
            Data.setClick(clicks);
            Data.setPlayers(players);
            Data.setHasClicked(HasClicked1);
            Data.setOrder(order);
            Data.setTool(tool);
            return true;
        } catch (Exception e) {
            //System.out.println("reader");
            return false;
        }
    }

    public static Map MapReader() {
        return MapReader("save.txt");
    }

    public static Map MapReader(String filename) {
        Map map = new Map();
        try {
            String s = "buffer/" + filename;
            BufferedReader in = new BufferedReader(new FileReader(s));
            String str = in.readLine();
            String[] arr = str.split("\\s+");
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[1]);
            int mines = Integer.parseInt(arr[2]);
            int platernumbers = Integer.parseInt(arr[3]);
            int order = Integer.parseInt(arr[4]);
            int clicks = Integer.parseInt(arr[5]);
            ArrayList<Player> players = new ArrayList<>();
            for (int i = 0; i < platernumbers; i++) {
                str = in.readLine();
                arr = str.split("\\s+");
                Player player = new Player(arr[0], arr[3], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
                players.add(player);
            }
//            str = in.readLine();
//            String[] arr1 = str.split("\\s+");
//            int point1 = Integer.parseInt(arr1[0]);
//            int point2 = Integer.parseInt(arr1[1]);
            str = in.readLine();
//            int order = Data.getOrder();
            char[][] map1 = new char[a][b];
            int[][] HasClicked1 = new int[a][b];
            int[][] tool = new int[a][b];
            int i = 0;
            while ((str = in.readLine()) != null && i < a) {
                for (int j = 0; j < b; j++) {
                    map1[i][j] = str.charAt(j);
                }
                i++;
            }
            i = 0;
            while ((str = in.readLine()) != null && i < a) {
                for (int j = 0; j < b; j++) {
                    HasClicked1[i][j] = str.charAt(j) - '0';
                }
                i++;
            }
            i = 0;
            while ((str = in.readLine()) != null && i < a) {
                for (int j = 0; j < b; j++) {
                    tool[i][j] = str.charAt(j) - '0';
                }
                i++;
            }

            setMap(map1);
//            Data.setPoint1(point1);
//            Data.setPoint2(point2);
            Data.setMines(mines);
            Data.setClick(clicks);
            Data.setPlayers(players);
            Data.setHasClicked(HasClicked1);
            Data.setOrder(order);
            Data.setTool(tool);
        } catch (IOException e) {
            System.out.println("reader");
        }
        return map;
    }

    public static Map filechosser() throws Exception {
        JFileChooser chooser = new JFileChooser(new File("save"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
// 保存所选目录chooser.showSaveDialog(parent);
        Component parent = null;
        int returnVal = chooser.showSaveDialog(parent);
// 获得选中的文件对象JFileChooser.APPROVE_OPTION
// 如果保存的目录跟获得选中的文件对象一致，成功都是返回0
        String selectPath = "";
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectPath = chooser.getSelectedFile().getPath();
            //System.out.println("你选择的目录是：" + selectPath);
            //System.exit(0);
        }
        Map map = new Map();
        BufferedReader in = new BufferedReader(new FileReader(selectPath));
        String str = in.readLine();
        String[] arr = str.split("\\s+");
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[1]);
        int mines = Integer.parseInt(arr[2]);
        int platernumbers = Integer.parseInt(arr[3]);
        int order = Integer.parseInt(arr[4]);
        int clicks = Integer.parseInt(arr[5]);
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < platernumbers; i++) {
            str = in.readLine();
            arr = str.split("\\s+");
            Player player = new Player(arr[0], arr[3], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
            players.add(player);
        }
//            str = in.readLine();
//            String[] arr1 = str.split("\\s+");
//            int point1 = Integer.parseInt(arr1[0]);
//            int point2 = Integer.parseInt(arr1[1]);
       // str = in.readLine();
//            int order = Data.getOrder();
        char[][] map1 = new char[a][b];
        int[][] HasClicked1 = new int[a][b];
        int[][] tool = new int[a][b];
        int i = 0;
        while ((str = in.readLine()) != null && i < a) {
            for (int j = 0; j < b; j++) {
                map1[i][j] = str.charAt(j);
            }
            i++;
        }
        i = 0;
        while ((str = in.readLine()) != null && i < a) {
            for (int j = 0; j < b; j++) {
                HasClicked1[i][j] = str.charAt(j) - '0';
            }
            i++;
        }
        i = 0;
        while ((str = in.readLine()) != null && i < a) {
            for (int j = 0; j < b; j++) {
                tool[i][j] = str.charAt(j) - '0';
            }
            i++;
        }

        setMap(map1);
        map.setRow(a);
        map.setColumn(b);
        map.setNum(mines);
//            Data.setPoint1(point1);
//            Data.setPoint2(point2);
        Data.setMines(mines);
        Data.setClick(clicks);
        Data.setPlayers(players);
        Data.setHasClicked(HasClicked1);
        Data.setOrder(order);
        Data.setTool(tool);

//        catch (Exception e){
//            System.out.println("数据不合法，请重新选择");
//        }
        return map;
    }

//    public static Map Filechosser() throws Exception {
//        JFileChooser chooser = new JFileChooser(new File("save"));
//        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//// 保存所选目录chooser.showSaveDialog(parent);
//        Component parent = null;
//        int returnVal = chooser.showSaveDialog(parent);
//// 获得选中的文件对象JFileChooser.APPROVE_OPTION
//// 如果保存的目录跟获得选中的文件对象一致，成功都是返回0
//        String selectPath = "";
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            selectPath = chooser.getSelectedFile().getPath();
//            //System.out.println("你选择的目录是：" + selectPath);
//            //System.exit(0);
//        }
//        Map map = new Map();
//   //     try {
//            BufferedReader in = new BufferedReader(new FileReader(selectPath));
//            String str = in.readLine();
//            String[] arr = str.split("\\s+");
//            int a = Integer.parseInt(arr[0]);
//            int b = Integer.parseInt(arr[1]);
//            int mines = Integer.parseInt(arr[2]);
//            int platernumbers = Integer.parseInt(arr[3]);
//            int order = Integer.parseInt(arr[4]);
//            int clicks = Integer.parseInt(arr[5]);
//            ArrayList<Player> players = new ArrayList<>();
//            for (int i = 0; i < platernumbers; i++) {
//                str = in.readLine();
//                arr = str.split("\\s+");
//                Player player = new Player(arr[0], "No characteristic", Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
//                players.add(player);
//            }
////            str = in.readLine();
////            String[] arr1 = str.split("\\s+");
////            int point1 = Integer.parseInt(arr1[0]);
////            int point2 = Integer.parseInt(arr1[1]);
//            //str = in.readLine();
////            int order = Data.getOrder();
//            char[][] map1 = new char[a][b];
//            int[][] HasClicked1 = new int[a][b];
//            int[][] tool = new int[a][b];
//            int i = 0;
//            while ((str = in.readLine()) != null && i < a) {
//                for (int j = 0; j < b; j++) {
//                    map1[i][j] = str.charAt(j);
//                }
//                i++;
//            }
//            i = 0;
//            while ((str = in.readLine()) != null && i < a) {
//                for (int j = 0; j < b; j++) {
//                    HasClicked1[i][j] = str.charAt(j) - '0';
//                }
//                i++;
//            }
//            i = 0;
//            while ((str = in.readLine()) != null && i < a) {
//                for (int j = 0; j < b; j++) {
//                    tool[i][j] = str.charAt(j) - '0';
//                }
//                i++;
//            }
//            Data.setMines(mines);
//            setMap(map1);
//            map.setRow(a);
//            map.setColumn(b);
//            map.setNum(mines);
////            Data.setPoint1(point1);
////            Data.setPoint2(point2);
//
//            Data.setClick(clicks);
//            Data.setPlayers(players);
//            Data.setHasClicked(HasClicked1);
//            Data.setOrder(order);
//            Data.setTool(tool);
////        }  catch (Exception e){
////            System.out.println("数据不合法，请重新选择");
////            //new error("error");
////            int a = JOptionPane.showConfirmDialog(null, "数据不合法", "数据不合法", JOptionPane.YES_NO_OPTION);
////            if (a == 1) {
////                System.exit(0); // 关闭
////            }
////            else {
////                System.exit(0);
////            }
////            return Filechosser();
////
////        }
//            return map;
//        }
//
    }

