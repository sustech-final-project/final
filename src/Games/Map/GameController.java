package Games.Map;
import Games.components.Winner;

import java.util.ArrayList;
import java.util.Arrays;

/** 5.17 杨基同学根据清淞的需要整接口
 *注释点击每个方法左边的小按钮就好啦！！
 */
public class GameController implements Games.listener.GameController {
    Map map;
    int order;//点击的次数
    int turns;//每回合玩家课点击次数
    String[] players = new String[0];
    int index = 0;
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
        System.out.println(Arrays.deepToString(Data.getHasClicked()));
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
        return false;
    }

    @Override
    public ArrayList<String> whoWin() {
        return Winner.getWinner();
    }

    @Override
    public void addPlayer( String player) {
        Data.getPlayers().add(new Player(player));
    }

    @Override
    public void setTurns(int turn) {
        Data.setClick(turn);
    }

    @Override
    public int getOrder() {
        return Data.getOrder();
    }

    @Override
    public String[] getPlayers() {
        return players;
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
    public int[] getScores(){

       return Data.getScore();
    }

    @Override
    public void Click(int r, int c, int button) {

            for(int i=1;i<=Data.getPlayers().size();i++){
                if(button==1) {
                    if (map.getMap(r, c) == 'M' && order == i) {
                        Data.getScore()[i] -= 1;
                        Data.HasClicked(r, c);
                    }

                    if (map.getMap(r, c) != 'M' && order == i) {
                        Data.getScore()[i] += 1;
                        Data.HasClicked(r, c);
                    }
                }
                if(button==3){
                if(map.getMap(r,c)=='M'&&order==i){
                    Data.getScore()[i]+=2;
                    Data.HasClicked(r,c);
                }
                if(map.getMap(r,c)!='M'&&order==i){
                    Data.getMistake()[i]+=1;
                    Data.HasClicked(r,c);
                }

            }
        }
//        if(button==1){
//            if(map.getMap(r,c)=='M'&&order==1){
//                Data.setPoint1(Data.getPoint1()-1);
//                Data.setMine1(Data.getMine1()+1);
//                Data.HasClicked(r,c);
//            }
//            if(map.getMap(r,c)=='M'&&order==2){
//                Data.setPoint2(Data.getPoint2()-1);
//                Data.setMine2(Data.getMine2()+1);
//                Data.HasClicked(r,c);
//            }
//            if(map.getMap(r,c)=='M'&&order==3){
//                Data.setPoint3(Data.getPoint3()-1);
//                Data.setMine3(Data.getMine3()+1);
//                Data.HasClicked(r,c);
//            }
//            if(map.getMap(r,c)=='M'&&order==4){
//                Data.setPoint4(Data.getPoint4()+1);
//                Data.setMine4(Data.getMine4()+1);
//                Data.HasClicked(r,c);
//            }
//            if(map.getMap(r,c)!='M'&&order==1){
//                Data.setPoint1(Data.getPoint1()+1);
//                Data.HasClicked(r,c);
//            }
//            if(map.getMap(r,c)!='M'&&order==2){
//                Data.setPoint2(Data.getPoint2()+1);
//                Data.HasClicked(r,c);
//            }
//            if(map.getMap(r,c)!='M'&&order==3){
//                Data.setPoint3(Data.getPoint3()+1);
//                Data.HasClicked(r,c);
//            }
//            if(map.getMap(r,c)!='M'&&order==4){
//                Data.setPoint4(Data.getPoint4()+1);
//                Data.HasClicked(r,c);
//            }
//            if(button==3){
//                if(map.getMap(r,c)=='M'&&order==1){
//                    Data.setPoint1(Data.getPoint1()+2);
//                    Data.HasClicked(r,c);
//                }
//                if(map.getMap(r,c)=='M'&&order==2){
//                    Data.setPoint2(Data.getPoint2()+2);
//                    Data.HasClicked(r,c);
//                }
//                if(map.getMap(r,c)=='M'&&order==3){
//                    Data.setPoint3(Data.getPoint3()+2);
//                    Data.HasClicked(r,c);
//                }
//                if(map.getMap(r,c)=='M'&&order==4){
//                    Data.setPoint4(Data.getPoint4()+2);
//                    Data.HasClicked(r,c);
//                }
//                if(map.getMap(r,c)!='M'&&order==1){
//                    Data.setMistake1(Data.getMistake1()+1);
//                    Data.HasClicked(r,c);
//                }
//                if(map.getMap(r,c)!='M'&&order==2){
//                    Data.setMistake2(Data.getMistake2()+1);
//                    Data.HasClicked(r,c);
//                }
//                if(map.getMap(r,c)!='M'&&order==3){
//                    Data.setMistake3(Data.getMistake3()+1);
//                    Data.HasClicked(r,c);
//                }
//                if(map.getMap(r,c)!='M'&&order==4){
//                    Data.setMistake4(Data.getMistake4()+1);
//                    Data.HasClicked(r,c);
//                }
//            }
//        }

    }

    @Override
    public int[] getMistakes() {
     return Data.getMistake();
    }

    @Override
    public int getTurn() {
        return Data.getClick();
    }
}
