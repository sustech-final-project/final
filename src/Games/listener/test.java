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
        return false;
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public String whoWin() {
        return "null";
    }

    @Override
    public void addPlayer(int num, String player) {

    }

    @Override
    public void setTurns(int turn) {

    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public String[] getPlayers() {
        return new String[] {"1", "2", "3"};
    }

    @Override
    public int[] getScores() {
        return new int[] {1, 2, 3};
    }

    @Override
    public void Click(int r, int c, int button) {
        order++;
    }

    @Override
    public int[] getMistakes() {
        return new int[] {1, 2 ,3};
    }

    @Override
    public int getTurn() {
        return 0;
    }
}
