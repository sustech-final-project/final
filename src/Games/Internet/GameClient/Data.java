package Games.Internet.GameClient;

public class Data {
    public static String filename = "";

    public static Player player;

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

    public static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename1) {
        filename = filename1;
    }
}
