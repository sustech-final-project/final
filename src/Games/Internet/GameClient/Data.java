package Games.Internet.GameClient;

public class Data {
    public static String Playername = "1";

    public static int playerid = 1;
    public static int mistake1,mistake2,point1,point2;

    public static int getPlayerid() {
        return playerid;
    }

    public static void setPlayerid(int playerid) {
        Data.playerid = playerid;
    }

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

    public static int getPoint1() {
        return point1;
    }

    public static void setPoint1(int point1) {
        Data.point1 = point1;
    }

    public static int getPoint2() {
        return point2;
    }

    public static void setPoint2(int point2) {
        Data.point2 = point2;
    }

    public static Player player;

    public static Player rival;

    public static int row;

    public static int column;

    public static char aChar;

    public static char getaChar() {
        return aChar;
    }

    public static void setaChar(char aChar) {
        Data.aChar = aChar;
    }

    public static int getRow() {
        return row;
    }

    public static void setRow(int row) {
        Data.row = row;
    }

    public static int getColumn() {
        return column;
    }

    public static void setColumn(int column) {
        Data.column = column;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Data.player = player;
    }

    public static String getPlayername() {
        return Playername;
    }

    public static void setPlayername(String playername) {
        Playername = playername;
    }

    public static Player getRival() {
        return rival;
    }

    public static void setRival(Player rival) {
        Data.rival = rival;
    }
}
