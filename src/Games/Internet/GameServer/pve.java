package Games.Internet.GameServer;

import Games.Map.Data;
import Games.Map.Map;
import Games.utils.ClickType;

public class pve {
    private static char[][] map;
    private static int[][] hasclicked;
    private static int row,column;
    private static ClickType clickType;
    public static String pve(){
        String string = "";
        setHasclicked(Data.getHasClicked());
        setMap(Map.getMap());
        clickType = ClickType.LEFT_CLICK;
        row = map.length;
        column = map[1].length;
        for (int i=0;i<row;i++){
            for (int j=0;j<column;j++){
                if (hasclicked[i][j]==1){
                    if (hasclicked[i-1][j-1]==0)  string = string + row + " " + column + " " + clickType;
                }
            }
        }
        return string;
    }

    public static char[][] getMap() {
        return map;
    }

    public static void setMap(char[][] map) {
        pve.map = map;
    }

    public static int[][] getHasclicked() {
        return hasclicked;
    }

    public static void setHasclicked(int[][] hasclicked) {
        pve.hasclicked = hasclicked;
    }
}
