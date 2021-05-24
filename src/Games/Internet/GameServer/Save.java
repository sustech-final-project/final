package Games.Internet.GameServer;


import java.io.*;

/**
 * 作者：戴郭轶
 * 日期：2021.5.4
 * 本程序用于执行存档功能
 * 存档格式为：
 *   第一行为地图的行数和列数
 *   第二行为两位玩家的分数
 *   下面分别问地图与遍历情况
 */
public class Save extends Map {
    public static void Save(){
        Save("save.txt");
    }
    public static void Save(String filename){
        int a[][] = Data.getHasClicked();
        char map1[][] = getMap();
        int point1 = Data.getPoint1();
        int point2 = Data.getPoint2();
        //int order = Data.getOrder();
        try {
            String s = "ServerMap/"+filename+".txt";
            File file = new File(s);
            PrintStream writer = new PrintStream(new FileOutputStream(file));
            writer.println(map1.length + " " + map1[0].length);
            writer.println(point1 + " " + point2);
     //       writer.println(order);
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
        } catch (IOException e) {
            System.out.println("writer");
        }
    }

    /**
     * 本程序为读取存档程序，读取格式与上相同
     */
    public static void Reader() {
        Reader("save.txt");
    }

    public static void Reader(String filename) {
        try {
            String s = "buffer/" + filename;
            BufferedReader in = new BufferedReader(new FileReader(s));
            String str = in.readLine();
            String[] arr = str.split("\\s+");
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[1]);
            str = in.readLine();
            String[] arr1 = str.split("\\s+");
            int point1 = Integer.parseInt(arr1[0]);
            int point2 = Integer.parseInt(arr1[1]);
            str = in.readLine();
            //int order = Data.getOrder();
            char [][] map1 = new char[a][b];
            int [][] HasClicked1 = new int[a][b];
            int i = 0;
            while ((str = in.readLine()) != null &&i<a) {
                for (int j = 0; j < b; j++) {
                    map1[i][j] = str.charAt(j);
                }
                i++;
            }
            i=0;
            while ((str = in.readLine()) != null &&i<a) {
                for (int j = 0; j < b; j++) {
                    HasClicked1[i][j] = str.charAt(j)-'0';
                }
                i++;
            }

            setMap(map1);
            Data.setPoint1(point1);
            Data.setPoint2(point2);
            Data.setHasClicked(HasClicked1);
           // Data.setOrder(order);
        } catch (IOException e) {
            System.out.println("reader");
        }
    }





}
