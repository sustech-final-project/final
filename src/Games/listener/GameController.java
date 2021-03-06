package Games.listener;

import Games.Map.Player;

import java.util.ArrayList;

public interface GameController {

    /**
     * 检查是否存在存档
     * @return 存在 -> true
     */
    boolean isSaveExist();

    /**
     * 选择存档，载入地图
     */
    void choseSave() throws Exception;

    /**
     * 获得已载入的地图
     * @return 已载入的地图
     */
    char[][] getMap();

    /**
     *设置地图的参数
     * @param row 行
     * @param column 列
     * @param mine 雷
     */
    void setSize(int row, int column, int mine);

    /**
     * 由第一次点击位置创建地图
     * 注意： 该方法之前需要使用this.setSize方法
     * @param r 第一次点击的行数
     * @param c 第一次点击的列数
     */
    void createMap(int r, int c);

    /**
     * 返回坐标位置的字符（转化为字符串），并将其转化为已经遍历的状态
     * 注意：无论该坐标位置的字符是否已经被遍历，均需要返回
     * @param r 行
     * @param c 列
     * @return 坐标位置的字符（转化为字符串）
     */
    String getChar(int r, int c);

    /**
     *返回坐标位置是否以被遍历
     * @param r 行
     * @param c 列
     * @return 以被遍历 -> true
     */
    boolean isPrint(int r, int c);

    /**
     * 判断游戏是否结束
     * @return 结束 -> true
     */
    boolean isEnd();

    /**
     * 返回赢家姓名
     * @return 赢家姓名
     */
    ArrayList<String> whoWin();

    /**
     * 按顺序添加玩家
     *
     * @param player 玩家昵称
     */
    void addPlayer(String player, String characteristic);


    void addRobot(String player, String characteristic, int level);
    /**
     * 设置游戏中一人一轮点击次数
     * @param turn 游戏中一人一轮点击次数
     */
    void setTurns(int turn);

    /**
     * 获得现在点击的次数
     * @return 现在点击的次数
     */
    int getOrder();

    /**
     * 获得玩家列表
     * @return 玩家列表
     */
    ArrayList<Player> getPlayers();

    /**
     * 点击(r,c)处时进行的所有数值改变，包括order等
     * @param r 行
     * @param c 列
     * @param button 1 -> 左键； 3 -> 右键
     */
    void Click(int r, int c, int button);


    /**
     *
     * @return 返回现在是这一轮的第几步
     */
    int whichStep();

    int getTurn();

    /**
     *
     * @return 这是哪个玩家的行动轮
     */
    Player whoseTurn();

    /**
     * 保存当前棋盘
     */
    void save();

    /**
     * 清除所有数据，准备重开
     */
    void clear();
}
