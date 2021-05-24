package Games.Map;
import Games.components.Winner;

import java.util.ArrayList;
import java.util.Arrays;

/** 5.17 杨基同学根据清淞的需要整接口
 *注释点击每个方法左边的小按钮就好啦！！
 */
public class GameController implements Games.listener.GameController {
    Map map;
    int row;
    int column;

    @Override
  /**
    *Reader 更改为boolean类型且可以直接导出
    *isSaveExist会直接导出map
 */

    public boolean isSaveExist() {
        return Save.Reader();
    }

    @Override
    public void choseSave() {
        map = Save.filechosser();
        row = map.getRow();
        column = map.getColumn();
    }

    @Override
    public char[][] getMap() {
        return map.geTMap();
    }

    @Override
    public void setSize(int row, int column, int mine) {
        map = new Map(row, column, mine);
    }

    @Override
    public void createMap(int r, int c) {
        row = r;
        column = c;
        map.createMap(r,c);
        Data.setHasClicked(map.getRow(),map.getColumn());
    }

    /**Data 中 HasClicked方法用于判断是否遍历
     * 返回值： 坐标位置 ， 过程： 将其变为遍历状
     * @param r 行
     * @param c 列
     * @return 坐标位置
     */
    @Override
    public String getChar(int r, int c) {
        Data.HasClicked(r,c);
        return ""+map.getMap(r,c);
    }

    /**地铁 老人 手机.jpg 一无所知.jpg
     * getHasClicked()==1 已被遍历；==0 未遍历；
     * @param r 行
     * @param c 列
     * @return 坐标是否已遍历 -> true
     */
    @Override
    public boolean isPrint(int r, int c) {
      if(Data.getHasClicked(r,c)==1)
          return true;
     else
          return false;
    }

    @Override
    public boolean isEnd() {
        int[][] check = Data.getHasClicked();
        for (int i = 0; i < check.length; i++) {
            for (int j = 0; j < check[0].length; j++) {
                if (check[i][j] == 0) return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<String> whoWin() {
        return Winner.getWinner();
    }

    @Override
    public void addPlayer( String player, String characteristic) {
        Data.getPlayers().add(new Player(player, characteristic));
    }

    @Override
    public void setTurns(int turn) {
        Data.setClick(turn);
    }

    @Override
    public int getOrder() {
        System.out.println("GC get order: " + Data.getOrder());
        return Data.getOrder();
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return Data.getPlayers();
    }


//    public int getEachScores(String name) {
//        if(name==Data.getName1())
//            return Data.getPoint1();
//            if(name==Data.getName2())
//                return Data.getPoint2();
//                if(name==Data.getName3())
//                    return Data.getPoint3();
//                    if(name==Data.getName4())
//                        return Data.getPoint4();
//                    else return 0;
//    }

    @Override
    public void Click(int r, int c, int button) {
        if (button == 1 && map.getMap(r,c) != 'M') {

        } else if (button == 3 && map.getMap(r,c) != 'M'){
            whoseTurn().addMistake();
        } else if (button == 1 && map.getMap(r,c) == 'M'){
            whoseTurn().loseScore(1);
        } else if (button == 3 && map.getMap(r,c) == 'M'){
            whoseTurn().addScore(1);
        }
        Data.addOrder();
        Data.HasClicked(r,c);
    }

    @Override
    public int whichStep() {
        return Data.getOrder() % Data.getClick();
    }


    @Override
    public int getTurn() {
        return Data.getClick();
    }

    @Override
    public Player whoseTurn() {
        return Data.getPlayers().get(Data.getOrder() / Data.getClick() % Data.getPlayers().size());
    }

    @Override
    public void save() {
        Save.Save();
    }

    @Override
    public void clear(){
        Data.clear();
    }
}
