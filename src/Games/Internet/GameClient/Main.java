package Games.Internet.GameClient;

/**
 * 作者：戴郭轶
 * 日期：2021.5.2
 * 本程序用于构建游戏大厅并选择玩家进行对战
 */

public class Main {
    public static void main(String[] args) {
        //new ClientUI();
        Player player = new Player("dgy",1);
        Data.setPlayer(player);
        Data.setPlayername("dgy");
//        Client client = new Client();
        Player player1 = new Player("a",2);
        Data.setRival(player1);
        //client.sendMsg("abcd "+Data.getPlayer().name);
        Muti muti = new Muti(" ");
    }
}
