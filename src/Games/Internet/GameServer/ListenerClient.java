package Games.Internet.GameServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ListenerClient extends Thread{

    BufferedReader reader;
    PrintWriter writer;
    ServerUI ui;
    Socket client;
    Map map;

    public ListenerClient(ServerUI ui, Socket client) {
        this.ui = ui;
        this.client=client;
        this.start();
    }
    public void startgame(){
        map = ServerMap.severmap();
    }


    //为每一个客户端创建线程等待接收信息，然后把信息广播出去
    //msg的形式为
    @Override
    public void run() {

        String msg = "";
        while (true) {
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream(), true);
                msg = reader.readLine();
                String [] arr = msg.split("\\s+");//msg的格式为"play 玩家编号 点击位置"
                if (arr[0].equals("play")){
                    int row = Integer.parseInt(arr[2]);
                    int column = Integer.parseInt(arr[3]);
                    String clickType = arr[4];
                    if (Integer.parseInt(arr[1])==1){
                        judge1(row, column, clickType);
                    }
                    if (Integer.parseInt(arr[1])==2){
                        judge2(row, column, clickType);
                    }
                }
            } catch (IOException e) {
                println(e.toString());
                break;
            }
            if (msg != null && msg.trim() != "") {
                println("客户端 " + msg);
            }
        }
    }

    public void judge1(int row, int column, String clickType) {
        Data.HasClicked(row,column);
        if (map.getMap(row,column)=='M'&&clickType.equals("LEFT_CLICK")) {
            Data.setMistake1(Data.mistake1+1);
            Data.setPoint1(Data.point1-1);
            sendMsg("play " + 1 + " " + map.getMap(row,column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
        }
        if (map.getMap(row,column)=='M'&&clickType.equals("RIGHT_CLICK")) {
            //Data.setMistake1(Data.mistake1+1);
            Data.setPoint1(Data.point1+1);
            sendMsg("play " + 1 + " " + map.getMap(row,column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
        }
        if (map.getMap(row,column)!='M'&&clickType.equals("LEFT_CLICK")) {
            //Data.setMistake1(Data.mistake1+1);
            Data.setPoint1(Data.point1+1);
            sendMsg("play " + 1 + " " + map.getMap(row,column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
        }
        if (map.getMap(row,column)=='M'&&clickType.equals("RIGHT_CLICK")) {
            Data.setMistake1(Data.mistake1+1);
            Data.setPoint1(Data.point1-1);
            sendMsg("play " + 1 + " " + map.getMap(row,column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
        }
    }
    public void judge2(int row, int column, String clickType) {
        Data.HasClicked(row,column);
        if (map.getMap(row,column)=='M'&&clickType.equals("LEFT_CLICK")) {
            Data.setMistake1(Data.mistake1+1);
            Data.setPoint1(Data.point1-1);
            sendMsg("play " + 2 + " " + map.getMap(row,column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
        }
        if (map.getMap(row,column)=='M'&&clickType.equals("RIGHT_CLICK")) {
            //Data.setMistake1(Data.mistake1+1);
            Data.setPoint1(Data.point1+1);
            sendMsg("play " + 2 + " " + map.getMap(row,column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
        }
        if (map.getMap(row,column)!='M'&&clickType.equals("LEFT_CLICK")) {
            //Data.setMistake1(Data.mistake1+1);
            Data.setPoint1(Data.point1+1);
            sendMsg("play " + 2 + " " + map.getMap(row,column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
        }
        if (map.getMap(row,column)=='M'&&clickType.equals("RIGHT_CLICK")) {
            Data.setMistake1(Data.mistake1+1);
            Data.setPoint1(Data.point1-1);
            sendMsg("play " + 2 + " " + map.getMap(row,column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
        }
    }

    //把信息广播到所有用户
    public synchronized void sendMsg(String msg) {
        try {
            for (int i = 0; i < ui.clients.size(); i++) {
                Socket client = ui.clients.get(i);
                writer = new PrintWriter(client.getOutputStream(), true);
                writer.println(msg);
            }

        } catch (Exception e) {
            println(e.toString());
        }
    }

    public void println(String s) {
        if (s != null) {
            this.ui.taShow.setText(this.ui.taShow.getText() + s + "\n");
            System.out.println(s + "\n");
        }
    }
}

