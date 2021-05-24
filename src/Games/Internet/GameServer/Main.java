package Games.Internet.GameServer;

import Games.Internet.GameClient.Player;

/**
 * 作者：戴郭轶
 * 日期：2021.5.2
 * 本程序用于构建游戏大厅，使得玩家在在线对战时能够点击游戏大厅选择对战对象
 */
public class Main {
    public static void main(String[] args) {
        Player player = new Player("dgy",1);
        Player player1 = new Player("dgy1",2);


        startgame();
        new ServerUI();
       // new ListenerClient();
    }
    public static void startgame(){
        Data.setMap(ServerMap.severmap());
    }
}
