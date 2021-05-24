package Games.Internet.GameServer;

public class ServerMap {
    public static String filename;
    public static int a,b;//第一次点击的行列数
    public static int row,column;//行数和列数
    public static int num;//雷数
    public static int point1,point2,mistake1,mistake2;
    public static Map severmap(){
        a=1;
        b=1;
        row=16;
        column=16;
        num =40;
        Map map = new Map(a,b,row,column,num);
        Data.initializeData();
        filename = MapSaver.createRandomStr();
        Save.Save(filename);
        return map;
    }

    public static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename) {
        ServerMap.filename = filename;
    }

    public static int getA() {
        return a;
    }

    public static void setA(int a) {
        ServerMap.a = a;
    }

    public static int getB() {
        return b;
    }

    public static void setB(int b) {
        ServerMap.b = b;
    }

    public static int getRow() {
        return row;
    }

    public static void setRow(int row) {
        ServerMap.row = row;
    }

    public static int getColumn() {
        return column;
    }

    public static void setColumn(int column) {
        ServerMap.column = column;
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        ServerMap.num = num;
    }

    public static int getPoint1() {
        return point1;
    }

    public static void setPoint1(int point1) {
        ServerMap.point1 = point1;
    }

    public static int getPoint2() {
        return point2;
    }

    public static void setPoint2(int point2) {
        ServerMap.point2 = point2;
    }

    public static int getMistake1() {
        return mistake1;
    }

    public static void setMistake1(int mistake1) {
        ServerMap.mistake1 = mistake1;
    }

    public static int getMistake2() {
        return mistake2;
    }

    public static void setMistake2(int mistake2) {
        ServerMap.mistake2 = mistake2;
    }
}
