package Games.Internet.GameClient;

public class Data {
    public static String Playername = "";

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
