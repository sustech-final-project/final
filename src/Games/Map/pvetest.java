package Games.Map;

import Games.utils.ClickType;

import java.util.Random;

/**
 * 作者：戴郭轶
 * 日期：2021.5.21
 * 本程序用于pve的中级使用
 */
public class pvetest extends DoubleClick {
    public String testOne(int row, int column) {
        String str = "";
//        if (DoubleClick(row,column)&&Data.getHasClicked(row,column)==1) str = str + row + " " + column + " " + ClickType.DOUBLE_CLICK;
        /*else*/
        if (robotcheck(row, column) && Data.getHasClicked(row, column) == 1) {
            int counter = 0;
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column; j++) {
                    boolean check = false;
                    counter++;
                    try {
                        check = (Map.getMapchar(i, j) == 'M');
                    } catch (Exception ignored) {
                    }
                    if (check) {
                        str = str + i + " " + j + " " + 3;
                        break;
                    }
                    if (counter == 50){
                        return "";
                    }
                }
            }
        } else str = "";
        return str;
    }

    public String pvemiddle() {
        char[][] map = Map.getMap();
        Random random = new Random();
        for (int i = 0; i < map.length * map[0].length; i++) {
            int k = random.nextInt(map.length);
            int j = random.nextInt(map[0].length);
            if (!testOne(k, j).equals("")) return testOne(k, j);
        }
        String str = "";
        boolean check = true;
        while (check) {
            int a = random.nextInt(map.length);
            int b = random.nextInt(map[1].length);
            if (Data.getHasClicked(a, b) == 0) {
                str = str + a + " " + b + " " + 1;
                check = false;
            }
        }

        if (str.equals("")) {
            return ra(map);
        }

        return str;
    }

    private String ra(char[][] map) {
        Random random = new Random();

        int r = random.nextInt(map.length);
        int c = random.nextInt(map[0].length);
        int t = random.nextInt(2);
        if (t == 1) t = 3;
        if (t == 0) t = 1;
        if (Data.getHasClicked(r, c) == 1) {
            System.out.println("has clicked");
            return ra(map);
        } else {
            System.out.println("Can Return");
            return r + " " + c + " " + t;
        }
    }
}
