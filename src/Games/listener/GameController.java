package Games.listener;

public interface GameController {

    /**
     * 检查是否存在存档
     * @return 存在 -> true
     */
    boolean isSaveExist();

    /**
     * 选择存档，载入地图
     */
    void choseSave();

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
    String whoWin();

    /**
     * 按顺序添加玩家
     * @param num 玩家总个数
     * @param player 玩家昵称
     */
    void addPlayer(String player);

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
    String[] getPlayers();

    /**
     * 获得分数列表（顺序与上方相同）
     * @return 分数列表
     */
    int[] getScores();

    /**
     * 点击(r,c)处时进行的所有数值改变，包括order等
     * @param r 行
     * @param c 列
     * @param button 1 -> 左键； 3 -> 右键
     */
    void Click(int r, int c, int button);

    /**
     * 获得错误次数列表（顺序与上方相同）
     * @return 出现错误次数列表
     */
    int[] getMistakes();

    int getTurn();
}
