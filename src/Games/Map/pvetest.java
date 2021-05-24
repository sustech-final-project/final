package Games.Map;

import Games.utils.ClickType;

import java.util.Random;

/**
 * 作者：戴郭轶
 * 日期：2021.5.21
 * 本程序用于pve的中级使用
 */
public class pvetest extends DoubleClick{
    public String test1(int row,int column){
        String str = "";
        if (DoubleClick(row,column)&&Data.getHasClicked(row,column)==1) str = str + row + " " + column + " " + ClickType.DOUBLE_CLICK;
        else if (robotcheck(row,column)&&Data.getHasClicked(row,column)==1){
            for (int i=row-1;i<=row+1;i++){
                for (int j=column-1;j<=column;j++) {
                    if (Map.getMapchar(i,j)=='M') str = str + i + " " + j + " " + ClickType.RIGHT_CLICK;
                    break;
                }
            }
        }
        else str = pvemiddle();
        return str;
    }

    public String pvemiddle(){
        char[][] map = Map.getMap();
        for (int i=0;i<map.length;i++){
            for (int j=0;j<map[0].length;j++){
                if (test1(i,j)!=null) return test1(i,j);
            }
        }
        String str = "";
        Random random = new Random();
        boolean check = true;
        while (check){
            int a = random.nextInt(map.length);
            int b = random.nextInt(map[1].length);
            if (Data.getHasClicked(a,b)==0) {
                str = str + a + " " + b + " " + ClickType.LEFT_CLICK;
                check = false;
            }
        }

        return str;
    }


}
