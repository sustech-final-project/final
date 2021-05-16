package Games.listener;

//import Games.Map.Data;
import Games.Map.Map;
import Games.Map.Save;

public class test implements GameController{
    Map map;
    int order;
    @Override
    public boolean isSaveExist() {
        return true;
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
    public boolean isMapLoad() {
        return true;
    }

    @Override
    public void setSize(int row, int column, int mine) {
        map = new Map(row, column, mine);
    }

    @Override
    public void createMap(int r, int c) {
//        Data.setHasClicked(r, c);
        map.createMap(r,c);
    }

    @Override
    public String getChar(int r, int c) {
//        Data.HasClicked(r,c);
        return "" + map.getMap(r, c);
    }

    @Override
    public boolean isPrint(int r, int c) {
//        return Data.getHasClicked(r, c) == 0;
        return true;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public String whoWin() {
        return null;
    }

    @Override
    public void addPlayer(int num, String player) {

    }

    @Override
    public void setTurns(int turn) {

    }

    @Override
    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
